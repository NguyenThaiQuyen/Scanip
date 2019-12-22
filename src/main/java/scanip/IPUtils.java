/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanip;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class IPUtils extends Thread {

    public static InetAddress getWLANipAddress(String protocolVersion) throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {
            if (netint.isUp() && !netint.isLoopback() && !netint.isVirtual()) {
                Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();

                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    if (protocolVersion.equals("IPv4")) {
                        if (inetAddress instanceof Inet4Address) {
                            return inetAddress;
                        }
                    } 
                }
            }
        }
        return null;
    }

    public static int ipToInt(String ipAddress) {
        try {
            byte[] bytes = InetAddress.getByName(ipAddress).getAddress();
            int octet1 = (bytes[0] & 0xFF) << 24;
            int octet2 = (bytes[1] & 0xFF) << 16;
            int octet3 = (bytes[2] & 0xFF) << 8;
            int octet4 = bytes[3] & 0xFF;
            int address = octet1 | octet2 | octet3 | octet4;

            return address;
        } catch (Exception e) {
            return 0;
        }
    }

    public static String intToIp(int ipAddress) {
        int octet1 = (ipAddress & 0xFF000000) >>> 24;
        int octet2 = (ipAddress & 0xFF0000) >>> 16;
        int octet3 = (ipAddress & 0xFF00) >>> 8;
        int octet4 = ipAddress & 0xFF;

        return new StringBuffer().append(octet1).append('.').append(octet2)
                .append('.').append(octet3).append('.')
                .append(octet4).toString();
    }

}
