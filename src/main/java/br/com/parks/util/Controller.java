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

/**
 *
 * @author tiago
 */
public class Controller {
 
    
    private OLT getOltFromIp(String ip,String user,String pass){
        return new OLT();
    }
    
    private List<ONU> getOnuBySerial(OLT olt,String serial){
        return new ArrayList<ONU>();
    }
    
    private List<ONU> getOnuByIp(OLT olt,String ip){
        return new ArrayList<ONU>();
    }
    
    
    private String sendCommand(String command){
        return "";
    }
}
