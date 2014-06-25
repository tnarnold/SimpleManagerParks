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
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiago
 */
public class ControllerOnu {

    private ControllerOlt co;
    public ControllerOnu(OLT olt) {
       this.co=new ControllerOlt(olt.getIpAccess(), olt.getUser(),olt.getPass());
    }

    public ONU getOnu(String serial) {
        List<ONU> onus=co.getOnus();
        if (onus !=null) {
            for (ONU o : onus) {
                if(o.getSerial().equals(serial)){
                    return o;
                }
            }
        }
        return null;
    }
    
    public boolean provisionOnu(ONU onu) {

        return false;
    }

  
}
