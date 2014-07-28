/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parks.util;

import br.com.parks.entity.OLT;
import br.com.parks.entity.ONU;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author tiago
 */
public class ControllerOltTest {

    ControllerOlt c;

    @Before
    public void setUp() {
        c = new ControllerOlt("192.168.201.130", "admin", "parks");
        c.connect();
    }

    @After
    public void tearDown() {
        c.disconnect();
    }

    @Test
    public void testGetFlowProfiles() {
        System.out.println("Testing if get all flow profiles:");
        ArrayList<String> pfs = new ArrayList<String>();
        pfs.add("pks_bridge_1p,1,IPHOST,100,-,DISABLED,0,pks_mgmt_1Mb,");
        pfs.add("pks_bridge_1p,2,PBMP,101,-,DISABLED,0,pks_data_10Mb,1");
        pfs.add("pks_bridge_2p,1,IPHOST,100,-,DISABLED,0,pks_mgmt_1Mb,");
        pfs.add("pks_bridge_2p,2,PBMP,101,-,DISABLED,0,pks_data_10Mb,12");
        pfs.add("pks_routers,1,IPHOST,100,-,DISABLED,0,pks_mgmt_1Mb,");
        pfs.add("pks_routers,2,VEIP,101,-,DISABLED,0,pks_data_10Mb,");
        assertEquals(pfs.get(0), c.getFlowProfiles().get(0));
        assertEquals(pfs.get(1), c.getFlowProfiles().get(1));
        assertEquals(pfs.get(2), c.getFlowProfiles().get(2));
    }

    @Test
    public void testGetVlanTranlationProfiles() {
        System.out.println("Testing if get all Vlan Translation Profiles:");
        ArrayList<String> vpts = new ArrayList<String>();
        vpts.add("_100,2,UNTAGGED,-,100,-,-,4,F->X-F");
        vpts.add("_101,2,UNTAGGED,-,101,-,-,4,F->X-F");
        assertEquals(vpts.get(0), c.getVlanTranslationProfiles().get(0));
        assertEquals(vpts.get(1), c.getVlanTranslationProfiles().get(1));
    }

    /**
     * This test would be completed with all attributes of the OLT
     */
    @Test
    public void testGetOlt() {
        System.out.println("Test if OLT object is the same object that we want:");
        OLT olt = new OLT();
        OLT oltResult = c.getOlt();
        olt.setMgmtIP("192.168.201.130/23");
        olt.setHostname("oltsiplab");
        olt.setSerial("B53B28");
        ArrayList<String> fps = new ArrayList<String>();
        ArrayList<String> vtps = new ArrayList<String>();
        ArrayList<String> bwps = new ArrayList<String>();
        bwps.add("pks_data_10Mb,INTERNET,0,0,10240");
        bwps.add("pks_mgmt_1Mb,MANAGEMENT,0,1024,1152");
        fps.add("router_profile");
        vtps.add("_100");
        vtps.add("_101");
        olt.setFlowProfiles(fps);
        olt.setVlanTranslate(vtps);
        olt.setBwProfile(bwps);
        assertEquals(olt.getSerial(), oltResult.getSerial());
        assertEquals(olt.getMgmtIP(), oltResult.getMgmtIP());
        assertEquals(olt.getHostname(), oltResult.getHostname());
        assertEquals(olt.getBwProfile().get(0), oltResult.getBwProfile().get(0));
    }

    @Test
    public void testGetOnus() {
        System.out.println("getOnus");
        ArrayList<ONU> onusER = new ArrayList<ONU>();
        ArrayList<String> vtp = null;
        ONU onu1 = new ONU();
        ONU onu2 = new ONU();
        ONU onu3 = new ONU();
        ONU onu4 = new ONU();

        onu1.setIndex(1);
        onu1.setIfGpon("gpon2/3");
        onu1.setSerial("prks00b6150f");
        onu1.setAlias("rt1");
        onu1.setMgmtIp("192.168.2.101/24");
        onu1.setFlowProfile("pks_routers");
        vtp = new ArrayList<String>();
        vtp.add("5: IPHOST (_100)");
        vtp.add("6: VEIP (_101)");
        onu1.setVlanTranslate(vtp);

        onu2.setIndex(2);
        onu2.setIfGpon("gpon2/3");
        onu2.setSerial("prks00b25ccb");
        onu2.setAlias("rt2");
        onu2.setMgmtIp("192.168.2.102/24");
        onu2.setFlowProfile("pks_routers");
        vtp = new ArrayList<String>();
        vtp.add("5: IPHOST (_100)");
        vtp.add("6: VEIP (_101)");
        onu2.setVlanTranslate(vtp);

        onu3.setIndex(3);
        onu3.setIfGpon("gpon2/3");
        onu3.setSerial("prks00b63c65");
        onu3.setAlias("br1");
        onu3.setMgmtIp("192.168.2.201/24");
        onu3.setFlowProfile("pks_bridge_2p");
        vtp = new ArrayList<String>();
        vtp.add("1: _101");
        vtp.add("2: _101");
        vtp.add("5: IPHOST (_100)");
        onu3.setVlanTranslate(vtp);

        onu4.setIndex(4);
        onu4.setIfGpon("gpon2/3");
        onu4.setSerial("prks00b6ebdc");
        onu4.setAlias("br2");
        onu4.setMgmtIp("192.168.2.202/24");
        onu4.setFlowProfile("pks_bridge_1p");
        vtp = new ArrayList<String>();
        vtp.add("1: _101");
        vtp.add("5: IPHOST (_100)");
        onu4.setVlanTranslate(vtp);

        onusER.add(onu1);
        onusER.add(onu2);
        onusER.add(onu3);
        onusER.add(onu4);
        List<ONU> onusA = c.getOnus();
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

        assertEquals(onusER.get(3).getIndex(), onusA.get(3).getIndex());
        assertEquals(onusER.get(3).getAlias(), onusA.get(3).getAlias());
        assertEquals(onusER.get(3).getSerial(), onusA.get(3).getSerial());
        assertEquals(onusER.get(3).getMgmtIp(), onusA.get(3).getMgmtIp());
        assertEquals(onusER.get(3).getFlowProfile(), onusA.get(3).getFlowProfile());
        assertEquals(onusER.get(3).getVlanTranslate().get(0), onusA.get(3).getVlanTranslate().get(0));
        assertEquals(onusER.get(3).getVlanTranslate().get(1), onusA.get(3).getVlanTranslate().get(1));

    }

    @Test
    public void testGetBandWidthProfiles() {
        ArrayList<String> bwps=new ArrayList<String>();
        bwps.add("pks_data_10Mb,INTERNET,0,0,10240");
        bwps.add("pks_mgmt_1Mb,MANAGEMENT,0,1024,1152");
        assertEquals(bwps.get(0), c.getBandWidthProfiles().get(0));
        assertEquals(bwps.get(1), c.getBandWidthProfiles().get(1));
    }

}
