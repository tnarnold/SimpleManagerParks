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
import static junit.framework.TestCase.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author tiago
 */
public class StringUtilTest {

    private JSch jsch = new JSch();
    private Session s = null;
    private Channel c = null;
    private Expect e = null;
    private OLT olt = null;
    private InputStream is = null;
    private StringUtil cmdr = new StringUtil();

    @Before
    public void setUp() throws Exception {
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
            Logger.getLogger(ControllerOlt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControllerOlt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testOltGetSerial() {
        System.out.println("Test transform data of collected olt serial command:");
        String serial = "B53B28";
        e.send("show inventory\n");
        e.expect("# ");
        assertEquals(serial, cmdr.oltGetSerial(e.before));

    }

    @Test
    public void testOltGetMgmtIP() {
        System.out.println("Test collecting management interface ip:");
        String mgmtIp = "192.168.201.130/23";
        e.send("show interface mgmt\n");
        e.expect("#");
        assertEquals(mgmtIp, cmdr.oltGetMgmtIP(e.before));
    }

    @Test
    public void testOltGetHostname() {
        System.out.println("Test collected hostname of OLT:");
        String hn = "oltsiplab";
        e.send("\n");
        e.expect("#");
        String result = cmdr.oltGetHostname(e.before);
        assertEquals(hn, result);
    }

    @Test
    public void testOnuGetClean() {
        System.out.println("Test if result is clean");
        String rc = "Interface gpon1/1:\n"
                + "Interface gpon1/2:\n"
                + "Interface gpon1/3:\n"
                + "Interface gpon1/4:\n"
                + "Interface gpon2/1:\n"
                + "Interface gpon2/2:\n"
                + "Interface gpon2/3:\n"
                + "    1-rt1 (prks00b25ccb):\n"
                + "	IP address 172.31.255.101/24\n"
                + "	Flow profile: router_profile\n"
                + "	Ports VLAN translation profile:\n"
                + "	    5: IPHOST (_100)\n"
                + "	    6: VEIP (_101)\n"
                + "    2-prks00b6150f:\n"
                + "	IP address 172.31.255.102/24\n"
                + "	Flow profile: router_profile\n"
                + "	Ports VLAN translation profile:\n"
                + "	    5: IPHOST (_100)\n"
                + "	    6: VEIP (_101)\n"
                + "    3-br1 (prks00b63c65):\n"
                + "	IP address 172.31.255.201/24\n"
                + "	Flow profile: bridge_profile\n"
                + "	Ports VLAN translation profile:\n"
                + "	    1: _101\n"
                + "	    2: _101\n"
                + "	    5: IPHOST (_100)\n"
                + "Interface gpon2/4:";
        e.send("show gpon onu\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        e.expect("#");
        String onus = cmdr.onuGetClean(e.before);
        assertEquals(rc, onus);
    }

}
