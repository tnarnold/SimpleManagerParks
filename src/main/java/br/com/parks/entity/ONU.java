/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parks.entity;

/**
 *
 * @author tiago
 */
public class ONU {

    private String serial;
    private String mgmtIp;
    private String mgmtMacAddr;
    private String lanIp;
    private String lanMacAddr;
    private String user;
    private String pass;
    private String iface;
    private String flowProfile;

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

}
