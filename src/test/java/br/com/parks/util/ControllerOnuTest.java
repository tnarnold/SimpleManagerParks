/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.parks.util;

import br.com.parks.entity.ONU;
import java.util.ArrayList;
import java.util.List;
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
     ControllerOnu co = null;
    public ControllerOnuTest() {
    }
    
    @Before
    public void setUp() {
        co=new ControllerOnu(new Controller("192.168.201.130", "admin", "parks").getOlt());
    }
    
    @After
    public void tearDown() {
    }
    
        
    @Test
    public void testGetOnus() {
        System.out.println("getOnus");
        ArrayList<ONU> onusER=new ArrayList<ONU>();
        ONU onu1=new ONU();
        ONU onu2=new ONU();
        onu1.setIndex(1);
        onu1.setIfGpon("gpon2/3");
        onu1.setSerial("prks00b6150f");
        onu2.setIndex(2);
        onu2.setIfGpon("gpon2/3");
        onu2.setSerial("prks00b4ce4e");
        onu2.setMgmtIp("192.168.2.10/24");
        onu2.setFlowProfile("br2.2.2pr2");
        onusER.add(onu1);
        onusER.add(onu2);
        List<ONU>  onusA= co.getOnus();        
        assertEquals(onusER.get(0).getIndex(), onusA.get(0).getIndex());
        
    }

   
    
}
