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
public class ONU {
    
    private int index;
    private String serial;
    private String alias;
    private String ifGpon;
    private String mgmtIp;
    private String mgmtMacAddr;
    private String lanIp;
    private String lanMacAddr;
    private String user;
    private String pass;
    private String iface;
    private String flowProfile;
    private List<String> vlanTranslate;
    private OLT olt;

    public ONU() {
    }

    public ONU(int index, String serial, String ifGpon) {
        this.index = index;
        this.serial = serial;
        this.ifGpon = ifGpon;
    }

    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getMgmtIp() {
        return mgmtIp;
    }

    public void setMgmtIp(String mgmtIp) {
        this.mgmtIp = mgmtIp;
    }

    public String getMgmtMacAddr() {
        return mgmtMacAddr;
    }

    public void setMgmtMacAddr(String mgmtMacAddr) {
        this.mgmtMacAddr = mgmtMacAddr;
    }

    public String getLanIp() {
        return lanIp;
    }

    public void setLanIp(String lanIp) {
        this.lanIp = lanIp;
    }

    public String getLanMacAddr() {
        return lanMacAddr;
    }

    public void setLanMacAddr(String lanMacAddr) {
        this.lanMacAddr = lanMacAddr;
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

    public String getIface() {
        return iface;
    }

    public void setIface(String iface) {
        this.iface = iface;
    }

    public String getFlowProfile() {
        return flowProfile;
    }

    public void setFlowProfile(String flowProfile) {
        this.flowProfile = flowProfile;
    }

    public String getIfGpon() {
        return ifGpon;
    }

    public void setIfGpon(String ifGpon) {
        this.ifGpon = ifGpon;
    }

    public List<String> getVlanTranslate() {
        return vlanTranslate;
    }

    public void setVlanTranslate(List<String> vlanTranslate) {
        this.vlanTranslate = vlanTranslate;
    }

    public OLT getOlt() {
        return olt;
    }

    public void setOlt(OLT olt) {
        this.olt = olt;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.serial != null ? this.serial.hashCode() : 0);
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
        final ONU other = (ONU) obj;
        if ((this.serial == null) ? (other.serial != null) : !this.serial.equals(other.serial)) {
            return false;
        }
        return true;
    }
    
    

}
