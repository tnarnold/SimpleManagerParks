/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.parks.util;

import br.com.parks.entity.OLT;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;


/**
 *
 * @author tiago
 */
public class ControllerTest  {
    Controller c=new Controller("192.168.201.130", "admin", "parks");
  
    
    @Test
    public void testGetFlowProfiles(){
        System.out.println("Testing if get all flow profiles:");
        ArrayList<String> pfs=new ArrayList<String>();
        pfs.add("br2.2.2pr2");
        assertEquals(pfs.get(0), c.getFlowProfiles().get(0));
    }
    
    @Ignore
    @Test
    public void testGetOlt() {
        System.out.println("Test if OLT object is the same object that we want:");
        OLT olt = new OLT();
        OLT oltResult = c.getOlt();
        olt.setMgmtIP("192.168.201.130");
        olt.setHostname("oltsiplab");
        olt.setSerial("B53B28");
        assertEquals(olt.getSerial(), oltResult.getSerial());
        assertEquals(olt.getMgmtIP(), oltResult.getMgmtIP());
        assertEquals(olt.getHostname(), oltResult.getHostname());
    }
    
    


    
}
