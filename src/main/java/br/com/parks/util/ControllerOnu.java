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
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiago
 */
public class ControllerOnu {

    private JSch jsch = new JSch();
    private Session s = null;
    private Channel c = null;
    private Expect e = null;
    private OLT olt = null;
    private InputStream is = null;
    private StringUtil cmdr = new StringUtil();

    public ControllerOnu(OLT olt) {
        this.olt = olt;
        try {
            s = jsch.getSession(olt.getUser(), olt.getIpAccess());
            s.setConfig("StrictHostKeyChecking", "no");
            s.setPassword(olt.getPass());
            s.connect();
            c = s.openChannel("shell");
            is = c.getInputStream();
            is.mark(40000);
            e = new Expect(is, c.getOutputStream());
            c.connect();
            e.send("\n");
            e.expect("Username:");
            e.send(olt.getUser() + "\n");
            e.expect("Password:");
            e.send(olt.getPass() + "\n");
            e.expect("#");
        } catch (JSchException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ONU getOnu(String serial) {
        return new ONU();
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

    public List<ONU> getOnuBySerial(OLT olt, String serial) {
        return new ArrayList<ONU>();
    }

    public List<ONU> getOnuByIp(OLT olt, String ip) {
        return new ArrayList<ONU>();
    }

    public List<ONU> getOnusWithStatus() {
        return new ArrayList();
    }

    public boolean provisionOnu(ONU onu) {

        return false;
    }

    public void disconnect() {
        e.close();
        s.disconnect();
    }

}
