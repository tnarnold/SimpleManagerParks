/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.parks.entity;

import java.util.List;

/**
 *
 * @author tiago
 */
public class OLT {
    private String mgmtIP;
    private String mgmtMac;
    private String mgmtBc;
    private String mgmtIPinband;
    private String serial;
    private String hostname;
    private String user;
    private String pass;
    private String ipAccess;
    private List<String> flowProfiles;
    private List<String> vlanTranslate;

    public String getMgmtIP() {
        return mgmtIP;
    }

    public void setMgmtIP(String ipM) {
        this.mgmtIP = ipM;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public List<String> getFlowProfiles() {
        return flowProfiles;
    }

    public void setFlowProfiles(List<String> flowProfiles) {
        this.flowProfiles = flowProfiles;
    }

    public List<String> getVlanTranslate() {
        return vlanTranslate;
    }

    public void setVlanTranslate(List<String> vlanTranslate) {
        this.vlanTranslate = vlanTranslate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getIpAccess() {
        return ipAccess;
    }
    
    
    
    
}
