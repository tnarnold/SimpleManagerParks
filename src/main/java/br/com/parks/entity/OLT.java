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
    private List<String> bwProfile;
    private List<ONU> onus;

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

    public void setIpAccess(String ipAccess) {
        this.ipAccess = ipAccess;
    }

    public List<ONU> getOnus() {
        return onus;
    }

    public void setOnus(List<ONU> onus) {
        this.onus = onus;
    }

    public List<String> getBwProfile() {
        return bwProfile;
    }

    public void setBwProfile(List<String> bwProfile) {
        this.bwProfile = bwProfile;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.serial != null ? this.serial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OLT other = (OLT) obj;
        if ((this.serial == null) ? (other.serial != null) : !this.serial.equals(other.serial)) {
            return false;
        }
        return true;
    }

}
