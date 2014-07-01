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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiago
 */
public class ControllerOnu {

    private ControllerOlt co;
    private OLT selectedOlt;
    private List<ONU> selectedOnus;
    private JSch jsch = null;
    private Session s = null;
    private Channel c = null;
    private Expect e = null;
    private InputStream is = null;
    private StringUtil cmdr = new StringUtil();
    private String user;
    private String pass;
    private String ipAccess;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getIpAccess() {
        return ipAccess;
    }

    public void setIpAccess(String ipAccess) {
        this.ipAccess = ipAccess;
    }

    
    
    public boolean connect() {
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
        }
        return true;
    }

    public ControllerOnu(OLT olt) {
        this.selectedOlt = olt;
        selectedOnus = olt.getOnus();
        this.co = new ControllerOlt(olt.getIpAccess(), olt.getUser(), olt.getPass());
        co.connect();
    }

    public ControllerOnu() {
        this.co = new ControllerOlt(selectedOlt.getIpAccess(), selectedOlt.getUser(), selectedOlt.getPass());
        co.connect();
    }

    public ONU configOnu() {
        return new ONU();
    }

    public ONU getOnu(String serial) {
        List<ONU> onus = co.getOnus();
        if (onus != null) {
            for (ONU o : onus) {
                if (o.getSerial().equals(serial)) {
                    return o;
                }
            }
        }
        return null;
    }

}
