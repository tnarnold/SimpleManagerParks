/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.parks.util;

import br.com.parks.entity.OLT;
import br.com.parks.entity.ONU;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author tiago
 */
public class ControllerTest extends TestCase {
    Controller c=new Controller("192.168.201.130", "admin", "parks");
  

    public void testGetOlt() {
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
