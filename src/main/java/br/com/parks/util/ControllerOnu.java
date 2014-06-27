/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parks.util;

import br.com.parks.entity.OLT;
import br.com.parks.entity.ONU;
import java.util.List;

/**
 *
 * @author tiago
 */
public class ControllerOnu {

    private ControllerOlt co;
    private OLT olt;
    private List<ONU> cOnus;

    public ControllerOnu(OLT olt) {
        this.olt = olt;
        cOnus=olt.getOnus();
    }

    public ControllerOnu() {
        this.co = new ControllerOlt(olt.getIpAccess(), olt.getUser(), olt.getPass());
        co.connect();
    }

    public ONU getOnu(String serial) {
        List<ONU> onus = co.getOnus();
        if (onus != null) {
            for (ONU o : onus) {
                if (o.getSerial().equals(serial)) {
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
