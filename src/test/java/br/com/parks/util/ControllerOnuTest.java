/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parks.util;

import br.com.parks.entity.ONU;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author tiago
 */
public class ControllerOnuTest {

    ControllerOnu conu;
    
    @Before
    public void setUp() {
        
        ControllerOlt colt=new ControllerOlt("192.168.201.130", "admin", "parks");
        colt.connect();
        conu = new ControllerOnu(colt.getOlt());
    }

    @After
    public void tearDown() {
    }
    @Ignore
    @Test
    public void testGetOnu() {
        System.out.println("Test if the ONU come by serial:");
        ONU onu1 = new ONU();
        ONU onu2 = new ONU();
        ArrayList<String> vtp = null;
        onu1.setIndex(1);
        onu1.setIfGpon("gpon2/3");
        onu1.setSerial("prks00b25ccb");
        onu1.setAlias("rt1");
        onu1.setMgmtIp("172.31.255.101/24");
        onu1.setFlowProfile("router_profile");
        vtp = new ArrayList<String>();
        vtp.add("5: IPHOST (_100)");
        vtp.add("6: VEIP (_101)");
        onu1.setVlanTranslate(vtp);
        
        onu2.setIndex(2);
        onu2.setIfGpon("gpon2/3");
        onu2.setSerial("prks00b6150f");
        onu2.setAlias(null);
        onu2.setMgmtIp("172.31.255.102/24");
        onu2.setFlowProfile("router_profile");
        vtp = new ArrayList<String>();
        vtp.add("5: IPHOST (_100)");
        vtp.add("6: VEIP (_101)");
        onu2.setVlanTranslate(vtp);
        
        ONU onua=conu.getOnu("prks00b25ccb");
        ONU onuan=conu.getOnu("prks00b25");
        assertEquals(onu1, onua);
        assertNotEquals(onu2,onua);
        assertEquals(null,onuan);
    }

}
