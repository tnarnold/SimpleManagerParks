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
import java.util.AbstractList;
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
        o.setUser(user);
        o.setPass(pass);
        o.setIpAccess(ipAccess);
        o.setOnus(getOnus());
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

    /**
     * Get an ONU list by serial number of equipment
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
     * Return the ONU in an especified list of ONUs
     *
     * @param serial serial number of ONU
     * @param os List of ONUs collected before
     * @return
     */
    public List<ONU> getOnusBySerial(String serial, List<ONU> os) {
        List<ONU> onus = new ArrayList<ONU>();
        for (ONU o : os) {
            if (o.getSerial().contains(serial)) {
                onus.add(o);
            }
        }
        return onus;
    }

    public List<ONU> getOnus() {
        e.send("show gpon onu\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        e.expect("#");
        ArrayList<ONU> onus = new ArrayList<ONU>();
        String resp = cmdr.onuGetClean(e.before);
        String[] ifPon = resp.split("Interface ");
        for (int i = 0; i < ifPon.length; i++) {
            if (ifPon[i].length() > 10) {
                String[] onu = ifPon[i].replaceAll("\t( )( )( )( )", "#").split("\n( )( )( )( )");

                for (int j = 0; j < onu.length; j++) {
                    if (!onu[j].trim().startsWith("gpon")) {
                        ONU o = new ONU();
                        o.setIfGpon(onu[0].replaceAll(":", "").trim());
                        String[] pOnu = onu[j].split("\t");
                        for (int p = 0; p < pOnu.length; p++) {
                            if (pOnu[p].trim().matches(".*prks.*")) {
                                o.setIndex(Integer.valueOf(pOnu[p].substring(0, pOnu[p].indexOf("-"))));
                                o.setSerial(pOnu[p].substring(pOnu[p].indexOf("prks"), pOnu[p].indexOf("prks") + 12));
                                if (!pOnu[p].startsWith("prks", pOnu[p].indexOf("-") + 1)) {
                                    o.setAlias(pOnu[p].substring(pOnu[p].indexOf("-") + 1, pOnu[p].indexOf("(")).trim());
                                }
                            }

                            if (pOnu[p].contains("IP address")) {
                                o.setMgmtIp(pOnu[p].replaceAll("IP address ", "").trim());
                            }
                            if (pOnu[p].contains("Flow profile:")) {
                                o.setFlowProfile(pOnu[p].replaceAll("Flow profile: ", "").trim());
                            }

                            if (pOnu[p].contains("Ports VLAN translation")) {
                                ArrayList<String> vtps = new ArrayList<String>();
                                String rhead = pOnu[p].replaceAll("Ports VLAN translation profile:\n#", "");
                                String[] vtp = rhead.split("\n#");
                                for (int vt = 0; vt < vtp.length; vt++) {
                                    vtps.add(vtp[vt].trim());
                                }
                                o.setVlanTranslate(vtps);
                            }
                        }
                        if (o != null && o.getIndex() > 0) {
                            onus.add(o);
                        }
                    }

                }
            }
        }
        return onus;
    }

    public boolean provisionOnu(ONU onu) {

        return false;
    }

    public List<String> getFlowProfiles() {
        ArrayList<String> fps = new ArrayList<String>();
        e.send("show gpon profile flow\n");
        e.expect("#");
        String[] lfp = e.before.split("[\\n\\r]+");
        if (lfp.length > 1) {
            for (int i = 0; i < lfp.length; i++) {
                if (lfp[i].contains("Index")) {
                    fps.add(lfp[i - 1].trim());
                }
            }
        } else {
            fps = null;
        }
        return fps;
    }

    public List<String> getVlanTranslationProfiles() {
        ArrayList<String> vps = new ArrayList<String>();
        e.send("show gpon profile vlan-translation\n");
        e.expect("#");
        String[] lfp = e.before.split("[\\n\\r]+");
        if (lfp.length > 1) {
            for (int i = 0; i < lfp.length; i++) {
                if (lfp[i].contains("Index")) {
                    vps.add(lfp[i - 1].replace(":", "").trim());
                }
            }
        } else {
            vps = null;
        }
        return vps;
    }

    public void connect() {
        if (!user.isEmpty() && !pass.isEmpty()) {
            try {
                s = this.jsch.getSession(this.user, this.ipAccess);
                s.setConfig("StrictHostKeyChecking", "no");
                s.setPassword(pass);
                s.connect(1000);
                c = s.openChannel("shell");
                is = c.getInputStream();
                is.mark(40000);
                e = new Expect(is, c.getOutputStream());
                c.connect();
                e.send("\n");
                e.expect("Username:");
                e.send(user + "\n");
                e.expect("Password:");
                e.send(pass + "\n");
                e.expect("#");

            } catch (JSchException ex) {
                Logger.getLogger(ControllerOlt.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ControllerOlt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
