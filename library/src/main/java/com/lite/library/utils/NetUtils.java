package com.lite.library.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by lxx on 2016/7/1 9:48
 * 网络各种函数的工具类
 */
public class NetUtils {


    /**
     * 获取本机的Ip地址
     * 有待考验
     *
     * @return 192.168.4.102
     */
    public static String getNetWorkIP() {
        StringBuilder sb = new StringBuilder();
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = en.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress
                            .isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
                        sb.append(inetAddress.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            return "";
        }
        return sb.toString();
    }

}
