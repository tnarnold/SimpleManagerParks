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
public class Controller {

    private JSch jsch = new JSch();
    private Session s = null;
    private Channel c = null;
    private Expect e = null;
    private OLT olt = null;
    private InputStream is = null;
    private StringUtil cmdr=new StringUtil();

    public Controller(String ip, String user, String pass) {
        if (olt == null) {
            OLT o=new OLT();
            try {
                s = jsch.getSession(user, ip);
                s.setConfig("StrictHostKeyChecking", "no");
                s.setPassword(pass);
                s.connect();
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
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } 
            e.send("show inventory\n");
            e.expect("# ");
            o.setSerial(cmdr.oltGetSerial(e.before));
            
            olt=o;
        }

    }

    public OLT getOlt() {
        return olt;
    }

    public List<ONU> getOnuBySerial(OLT olt, String serial) {
        return new ArrayList<ONU>();
    }

    public List<ONU> getOnuByIp(OLT olt, String ip) {
        return new ArrayList<ONU>();
    }
}
