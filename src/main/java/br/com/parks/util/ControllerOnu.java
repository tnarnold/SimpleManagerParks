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
        String[] gpon = resp.split("Interface ");

        for (int i = 0; i < gpon.length; i++) {
            if (gpon[i].contains("    ")) {
                String[] onu = gpon[i].split("\\s\\s\\s\\s");
                for (int j = 0; j < onu.length; j++) {
                    ONU o = new ONU();
                    if (!onu[j].contains("gpon")) {
                        o.setIndex(Integer.valueOf(onu[j].substring(0, onu[j].indexOf("-")).trim()));
                        o.setIfGpon(onu[j].replace(":", ""));
                        String[] onup = onu[j].split("\n");
                        for (int k = 0; k < onup.length; k++) {
                            if (onup[k].contains("\t")) {
                                if (onup[k].contains("IP address")) {
                                    o.setMgmtIp(onup[k].replace("IP address", "").trim());
                                }
                                if (onup[k].contains("Flow profile: ")) {
                                    o.setFlowProfile(onup[k].replace("Flow profile: ", "").trim());
                                }
                            }
                        }
                    }
                }
            }
        }
        return new ArrayList<ONU>();
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
