/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parks.util;

/**
 *
 * @author tiago
 */
public class StringUtil {

    public String oltGetSerial(String in) {
        String[] result1 = in.split("[\\n\\r]+");
        String[] result2 = result1[3].split(":");
        return result2[2].trim();
    }

    public String oltGetMgmtIP(String in) {
        String[] lines = in.split("[\\n\\r]+");
        String ip = "";
        for (String line : lines) {
            if (line.contains("inet ")) {
                String[] ipf = line.split("broadcast");
                ip = ipf[0].replace("inet ", "").trim();
            }
        }
        return ip;
    }

    public String oltGetHostname(String in) {
        return in.trim();
    }

    public String onuGetClean(String in) {
        String clean = in.replace("\r", "").replaceAll("--More--", "")
                .replace("[7m[m", "").replace("[K", "")
                .replace(" show gpon onu\n", "");
        clean=clean.substring(0,clean.lastIndexOf("\n"));
        return clean.trim();
    }
}
