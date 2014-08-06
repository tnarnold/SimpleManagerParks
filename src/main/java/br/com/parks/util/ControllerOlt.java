/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parks.util;

import br.com.parks.entity.OLT;
import br.com.parks.entity.ONU;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiago
 */
public class ControllerOlt {

    private JSch jsch = null;
    private Session s = null;
    private Channel c = null;
    private Expect e = null;
    private OLT olt = null;
    private InputStream is = null;
    private StringUtil cmdr = new StringUtil();
    private String user;
    private String pass;
    private String ipAccess;

    public ControllerOlt(String ipAccess, String user, String pass) {
        this.user = user;
        this.pass = pass;
        this.ipAccess = ipAccess;
        jsch = new JSch();
    }

    public ControllerOlt() {
        jsch = new JSch();
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setIpAccess(String ipAccess) {
        this.ipAccess = ipAccess;
    }

    public OLT getOlt() {
        OLT o = new OLT();
        e.send("show inventory\n");
        e.expect("# ");
        o.setSerial(cmdr.oltGetSerial(e.before));
        e.send("show interface mgmt\n");
        e.expect("#");
        o.setMgmtIP(cmdr.oltGetMgmtIP(e.before));
        e.send("\n");
        e.expect("#");
        o.setHostname(cmdr.oltGetHostname(e.before));
        o.setFlowProfiles(getFlowProfiles());
        o.setVlanTranslate(getVlanTranslationProfiles());
        o.setBwProfile(getBandWidthProfiles());
        o.setUser(user);
        o.setPass(pass);
        o.setIpAccess(ipAccess);
        olt = o;
        return olt;
    }

    public ONU getOnu(String serial) {
        return new ONU();
    }

    public ONU getOnu(String serial, List<ONU> os) {
        ONU onu = null;
        for (ONU o : os) {
            if (o.getSerial().equals(serial)) {
                onu = o;
            }
        }
        return onu;
    }

    public List<ONU> getOnusByIpAddress(String ipAddr) {
        List<ONU> onus = new ArrayList<ONU>();
        for (ONU o : getOnus()) {
            if (o.getMgmtIp().contains(ipAddr)) {
                onus.add(o);
            }
        }
        return onus;
    }

    /**
     * Get ONUs list by management IP Address into List of ONUs
     *
     * @param ipAddr IP address of ONU or ONUs
     * @param oList List of ONUs
     * @return
     */
    public List<ONU> getOnusByIpAddress(String ipAddr, List<ONU> oList) {
        List<ONU> onus = new ArrayList<ONU>();
        for (ONU o : oList) {
            if (o.getMgmtIp().contains(ipAddr)) {
                onus.add(o);
            }
        }
        return onus;
    }

    /**
     * Get an ONU list by serial number of equipment
     *
     * @param serial
     * @return List of ONUs
     */
    public List<ONU> getOnusBySerial(String serial) {
        List<ONU> onus = new ArrayList<ONU>();
        for (ONU o : getOnus()) {
            if (o.getSerial().contains(serial)) {
                onus.add(o);
            }
        }
        return onus;
    }

    /**
     * Return the ONUs in an especified list of ONUs
     *
     * @param serial serial number of ONU
     * @param oList List of ONUs collected before
     * @return
     */
    public List<ONU> getOnusBySerial(String serial, List<ONU> oList) {
        List<ONU> onus = new ArrayList<ONU>();
        for (ONU o : oList) {
            if (o.getSerial().contains(serial)) {
                onus.add(o);
            }
        }
        return onus;
    }

    /**
     * Return a list of ONUs connected in the OLT
     *
     * @return List of ONUs
     */
    public List<ONU> getOnus() {
        if (olt == null) {
            olt = getOlt();
        }
        e.send("show gpon onu\n\n\n\n                                                 ");
        e.expect("#");
        ArrayList<ONU> onus = new ArrayList<ONU>();
        String resp = cmdr.onuGetClean(e.before);
        String[] ifPon = resp.split("Interface ");
        for (int i = 0; i < ifPon.length; i++) {
            if (ifPon[i].length() > 10) {
                String[] onu = ifPon[i].replaceAll("\t( )( )( )( )", "#").split("\n( )( )( )( )");
                for (int j = 0; j < onu.length; j++) {
                    if (!onu[j].trim().startsWith("gpon")) {
                        ONU onuTemp = new ONU();
                        onuTemp.setIfGpon(onu[0].replaceAll(":", "").trim());
                        String[] pOnu = onu[j].split("\t");
                        for (int p = 0; p < pOnu.length; p++) {
                            if (pOnu[p].trim().matches(".*prks.*")) {
                                onuTemp.setIndex(Integer.valueOf(pOnu[p].substring(0, pOnu[p].indexOf("-"))));
                                onuTemp.setSerial(pOnu[p].substring(pOnu[p].indexOf("prks"), pOnu[p].indexOf("prks") + 12));
                                if (!pOnu[p].startsWith("prks", pOnu[p].indexOf("-") + 1)) {
                                    onuTemp.setAlias(pOnu[p].substring(pOnu[p].indexOf("-") + 1, pOnu[p].indexOf("(")).trim());
                                }
                            }

                            if (pOnu[p].contains("IP address")) {
                                onuTemp.setMgmtIp(pOnu[p].replaceAll("IP address ", "").trim());
                            }
                            if (pOnu[p].contains("Flow profile:")) {
                                onuTemp.setFlowProfile(pOnu[p].replaceAll("Flow profile: ", "").trim());
                            }

                            if (pOnu[p].contains("Ports VLAN translation")) {
                                ArrayList<String> vtps = new ArrayList<String>();
                                String rhead = pOnu[p].replaceAll("Ports VLAN translation profile:\n#", "");
                                String[] vtp = rhead.split("\n#");
                                for (int vt = 0; vt < vtp.length; vt++) {
                                    vtps.add(vtp[vt].trim());
                                    if (vtp[vt].startsWith("1:") || vtp[vt].startsWith("2:")) {
                                        onuTemp.setBridge(true);
                                    }
                                }
                                onuTemp.setVlanTranslate(vtps);

                            }
                        }
                        onuTemp.setOlt(olt);
                        if (onuTemp != null && onuTemp.getIndex() > 0) {
                            onus.add(onuTemp);
                        }
                    }

                }
            }
        }

        return onus;
    }

    /**
     * Return the list of profiles bandwidth in the OLT
     *
     * @return List of bandwidth profiles in the OLT
     */
    public List<String> getBandWidthProfiles() {
        ArrayList<String> bwps = new ArrayList<String>();
        e.send("show gpon profile bandwidth\n");
        e.expect("#");
        String[] bwp = e.before.split("[\\n\\r]+");
        if (bwp.length > 1) {
            for (int i = 2; i < bwp.length - 1; i++) {
                bwps.add(bwp[i].replaceAll("[|]", ",").replaceAll("\\s+", ""));
            }
        }
        return bwps;
    }

    /**
     * Return the flow profiles applied in the OLT
     *
     * @return List of flow profiles
     */
    public List<String> getFlowProfiles() {
        ArrayList<String> fps = new ArrayList<String>();
        e.send("show gpon profile flow\n");
        e.expect("#");
        String[] lfp = e.before.split("[\\n\\r]+");
        int count = 0;
        String fpLine = "";
        String fpName = "";
        if (lfp.length > 1) {
            for (int i = 0; i < lfp.length; i++) {
                if (!lfp[i].contains("|")) {
                    count = 0;
                    fpName = lfp[i].trim();
                }
                if (lfp[i].contains("|") && !lfp[i].contains("Index")) {
                    count++;
                    String[] fpls = lfp[i].split("\\|");
                    fpLine = fpName.concat("," + fpls[0].trim()
                            + "," + fpls[1].trim()
                            + "," + fpls[2].trim()
                            + "," + fpls[3].trim()
                            + "," + fpls[4].trim()
                            + "," + fpls[5].trim()
                            + "," + fpls[6].trim()
                            + "," + fpls[7].trim().replaceAll("\\s+", ""));
                }
                if (fpLine.matches(".+,.*,.*,.*,.*,.*,.*,.*,.*")) {
                    fps.add(fpLine);
                    fpLine = "";
                }
            }
        } else {
            fps = null;
        }
        return fps;
    }

    /**
     * Return the profiles for translations of VLANs in the OLT
     *
     * @return
     */
    public List<String> getVlanTranslationProfiles() {
        ArrayList<String> vps = new ArrayList<String>();
        e.send("show gpon profile vlan-translation\n");
        e.expect("#");
        String[] lvtp = e.before.split("[\\n\\r]+");
        int count = 0;
        String vtpLine = "";
        String vtpName = "";
        if (lvtp.length > 1) {
            for (int i = 0; i < lvtp.length; i++) {
                if (!lvtp[i].contains("|")) {
                    count = 0;
                    vtpName = lvtp[i].trim().replaceAll(":", "");
                }
                if (lvtp[i].contains("|") && !lvtp[i].contains("Index")) {
                    count++;
                    String[] vtpls = lvtp[i].split("\\|");
                    vtpLine = vtpName.concat("," + vtpls[0].trim()
                            + "," + vtpls[1].trim()
                            + "," + vtpls[2].trim()
                            + "," + vtpls[3].trim()
                            + "," + vtpls[4].trim()
                            + "," + vtpls[5].trim()
                            + "," + vtpls[6].trim()
                            + "," + vtpls[7].trim().replaceAll("\\s+", ""));
                }
                if (vtpLine.matches(".+,.*,.*,.*,.*,.*,.*,.*,.*")) {
                    vps.add(vtpLine);
                    vtpLine = "";
                }
            }
        } else {
            vps = null;
        }
        return vps;
    }

    /**
     * Initialize the connection in the OLT
     *
     * @return true if is connected
     */
    public boolean connect() {
        boolean cState = false;
        if (!user.isEmpty() && !pass.isEmpty()) {
            try {
                s = this.jsch.getSession(this.user, this.ipAccess);
                s.setConfig("StrictHostKeyChecking", "no");
                s.setPassword(pass);
                s.connect(1000);
                if (s.isConnected()) {
                    c = s.openChannel("shell");
                    is = c.getInputStream();
                    is.mark(40000);
                    e = new Expect(is, c.getOutputStream());
                    e.setDefault_timeout(10);

                    c.connect();
                    e.send("\n");
                    e.expect("Username:");
                    e.send(user + "\n");
                    e.expect("Password:");
                    e.send(pass + "\n");
                    e.expectOrThrow("#");

                } else {
                    System.out.println("Ipossible connect to host");
                }

            } catch (JSchException ex) {
                Logger.getLogger(ControllerOlt.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (IOException ex) {
                Logger.getLogger(ControllerOlt.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (Expect.TimeoutException ex) {
                Logger.getLogger(ControllerOlt.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (Expect.EOFException ex) {
                Logger.getLogger(ControllerOlt.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean createFlowProfile() {
        return false;
    }

    public boolean createVlanTranslateProfile() {
        return false;
    }

    public boolean provisioningOnu(ONU onu) {
        String iphost = "";
        String veip = "";
        String pbmp1 = "";
        String pbmp2 = "";
        if (!onu.isBridge()) {
            
            String command = "interface " + onu.getIfGpon() + "\n"
                    + "onu " + onu.getSerial() + " alias " + onu.getAlias() + "\n"
                    + "onu " + onu.getSerial() + " ip address " + onu.getMgmtIp() + "\n"
                    + "onu " + onu.getSerial() + " flow-profile " + onu.getFlowProfile() + "\n"
                    + "onu " + onu.getSerial() + " vlan-translation-profile " + onu.getVlanTranslate() + " iphost\n"
                    + "onu " + onu.getSerial() + " vlan-translation-profile " + "" + " veip\n"
                    + "!\n"
                    + "end\n"
                    + "copy running-config startup-config\n";

            e.send("conf t\n");
            e.expect("(config)#");
            e.send(command);
            e.expect("(config)#");
        } else {
            for (String vtp : onu.getVlanTranslate()) {
                if (vtp.matches(".*VEIP.*")) {
                    veip = vtp.substring(vtp.indexOf("(") + 1, vtp.indexOf(")"));
                } else if (vtp.matches(".*IPHOST.*")) {
                    iphost = vtp.substring(vtp.indexOf("(") + 1, vtp.indexOf(")"));
                } else if (!vtp.matches(".*VEIP.*") && vtp.matches(".*IPHOST.*")) {
                    pbmp1 = vtp.startsWith("1:") ? vtp.substring(vtp.indexOf("1: ")) : "";
                    pbmp2 = vtp.startsWith("2:") ? vtp.substring(vtp.indexOf("2: ")) : "";
                }
            }
            String command = "interface " + onu.getIfGpon() + "\n"
                    + "onu " + onu.getSerial() + " alias " + onu.getAlias() + "\n"
                    + "onu " + onu.getSerial() + " ip address " + onu.getMgmtIp() + " gw " + onu.getDefGw() + "\n"
                    + "onu " + onu.getSerial() + " flow-profile " + onu.getFlowProfile() + "\n"
                    + "onu " + onu.getSerial() + " vlan-translation-profile " + onu.getVlanTranslate() + " iphost\n"
                    + "onu " + onu.getSerial() + " vlan-translation-profile " + "" + " veip\n"
                    + "!\n"
                    + "end\n"
                    + "copy running-config startup-config\n";

            e.send("conf t\n");
            e.expect("(config)#");
            e.send(command);
            e.expect("(config)#");
        }
        return false;
    }

    public void removeOnu(ONU onu) {
        e.send("conf t\n");
        e.expect("(config)#");
        e.send("interface "+onu.getIfGpon());
        e.expect("(config-if)#");
        e.send("no onu "+onu.getSerial());
        e.expect("(config-if)#");
        e.send("onu add serial-number "+onu.getSerial());
        e.expect("(config-if)#");
        e.send("ex\nex\n");
    }

    public void disconnect() {
        if (e != null && s != null) {
            if (s.isConnected() && c.isConnected()) {
                e.close();
                s.disconnect();
                c.disconnect(); // Better for the garbage collector?
            }
        }

    }

}
