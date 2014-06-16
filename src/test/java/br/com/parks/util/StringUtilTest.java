/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parks.util;

import br.com.parks.entity.OLT;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author tiago
 */
public class StringUtilTest extends TestCase {

    private JSch jsch = new JSch();
    private Session s = null;
    private Channel c = null;
    private Expect e = null;
    private OLT olt = null;
    private InputStream is = null;
    private StringUtil cmdr = new StringUtil();

    @Override
    protected void setUp() throws Exception {
        try {
            s = jsch.getSession("admin", "192.168.201.130");
            s.setConfig("StrictHostKeyChecking", "no");
            s.setPassword("parks");
            s.connect();
            c = s.openChannel("shell");
            is = c.getInputStream();
            is.mark(40000);
            e = new Expect(is, c.getOutputStream());
            c.connect();
            e.send("\n");
            e.expect("Username:");
            e.send("admin" + "\n");
            e.expect("Password:");
            e.send("parks" + "\n");
            e.expect("#");

        } catch (JSchException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testOltGetSerial() {
        System.out.println("Test transform data of collected olt serial command:");
        String serial = "B53B28";
        e.send("show inventory\n");
        e.expect("# ");
        assertEquals(serial, cmdr.oltGetSerial(e.before));

    }

    public void testOltGetMgmtIP() {
        System.out.println("Test collecting management interface ip:");
        String mgmtIp="192.168.201.130/23";
        e.send("show interface mgmt\n");
        e.expect("#");
        assertEquals(mgmtIp, cmdr.oltGetMgmtIP(e.before));
    }

}
