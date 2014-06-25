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
        ArrayList<String> vtp=null;
        ONU onu1=new ONU();
        ONU onu2=new ONU();
        ONU onu3=new ONU();
        onu1.setIndex(1);
        onu1.setIfGpon("gpon2/3");
        onu1.setSerial("prks00b25ccb");
        onu1.setAlias("rt1");
        onu1.setMgmtIp("172.31.255.101/24");
        onu1.setFlowProfile("router_profile");
        vtp=new ArrayList<String>();
        vtp.add("5: IPHOST (_100)");
        vtp.add("6: VEIP (_101)");
        onu1.setVlanTranslate(vtp);
        
        onu2.setIndex(2);
        onu2.setIfGpon("gpon2/3");
        onu2.setSerial("prks00b6150f");
        onu2.setAlias(null);
        onu2.setMgmtIp("172.31.255.102/24");
        onu2.setFlowProfile("router_profile");
        vtp=new ArrayList<String>();
        vtp.add("5: IPHOST (_100)");
        vtp.add("6: VEIP (_101)");
        onu2.setVlanTranslate(vtp);
        
        onu3.setIndex(3);
        onu3.setIfGpon("gpon2/3");
        onu3.setSerial("prks00b63c65");
        onu3.setAlias("br1");
        onu3.setMgmtIp("172.31.255.201/24");
        onu3.setFlowProfile("bridge_profile");
        vtp=new ArrayList<String>();
        vtp.add("1: _101");
        vtp.add("2: _101");
        vtp.add("5: IPHOST (_100)");
        onu3.setVlanTranslate(vtp);
        
        
        onusER.add(onu1);
        onusER.add(onu2);
        onusER.add(onu3);
        List<ONU>  onusA= co.getOnus();        
        assertEquals(onusER.get(0).getIndex(), onusA.get(0).getIndex());
        assertEquals(onusER.get(0).getAlias(), onusA.get(0).getAlias());
        assertEquals(onusER.get(0).getSerial(), onusA.get(0).getSerial());
        assertEquals(onusER.get(0).getMgmtIp(), onusA.get(0).getMgmtIp());
        assertEquals(onusER.get(0).getFlowProfile(), onusA.get(0).getFlowProfile());
        assertEquals(onusER.get(0).getVlanTranslate().get(0), onusA.get(0).getVlanTranslate().get(0));
        assertEquals(onusER.get(0).getVlanTranslate().get(1), onusA.get(0).getVlanTranslate().get(1));
        
        assertEquals(onusER.get(1).getIndex(), onusA.get(1).getIndex());
        assertEquals(onusER.get(1).getAlias(), onusA.get(1).getAlias());
        assertEquals(onusER.get(1).getSerial(), onusA.get(1).getSerial());
        assertEquals(onusER.get(1).getMgmtIp(), onusA.get(1).getMgmtIp());
        assertEquals(onusER.get(1).getFlowProfile(), onusA.get(1).getFlowProfile());
        assertEquals(onusER.get(1).getVlanTranslate().get(0), onusA.get(1).getVlanTranslate().get(0));
        assertEquals(onusER.get(1).getVlanTranslate().get(1), onusA.get(1).getVlanTranslate().get(1));
        
        assertEquals(onusER.get(2).getIndex(), onusA.get(2).getIndex());
        assertEquals(onusER.get(2).getAlias(), onusA.get(2).getAlias());
        assertEquals(onusER.get(2).getSerial(), onusA.get(2).getSerial());
        assertEquals(onusER.get(2).getMgmtIp(), onusA.get(2).getMgmtIp());
        assertEquals(onusER.get(2).getFlowProfile(), onusA.get(2).getFlowProfile());
        assertEquals(onusER.get(2).getVlanTranslate().get(0), onusA.get(2).getVlanTranslate().get(0));
        assertEquals(onusER.get(2).getVlanTranslate().get(1), onusA.get(2).getVlanTranslate().get(1));
        assertEquals(onusER.get(2).getVlanTranslate().get(2), onusA.get(2).getVlanTranslate().get(2));
    }

   
    
}
