package com.superzyen.zyengame.net;

import com.superzyen.zyengame.netty.config.NetConfig;
import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class Address {
    private static Address address;
    private static List<InetSocketAddress> ipAddressRecords = new ArrayList<>();
    private static List<String> ipStrList = new ArrayList<>();

    private Address() {

    }

    public static Address getInstance() {
        if (null == address) {
            synchronized (Address.class) {
                if (null == address) {
                    address = new Address();
                    //初始化地址表
                    initAddressUnDefault();
                }
            }
        }
        return address;
    }

    /**
     * 初始化连接地址表
     * 默认端口8090 的方案
     */
    private static void initAddress() {
        //初始化地址表
        for (int i = 0; i < NetConfig.DEFAULT_IP_ADDRESS.length; i++) {
            ipAddressRecords.add(new InetSocketAddress(NetConfig.DEFAULT_IP_ADDRESS[i], NetConfig.PORT));
            ipStrList.add(NetConfig.DEFAULT_IP_ADDRESS[i]);
        }
    }

    /**
     * 初始化连接地址表
     * NAT穿透 非默认端口
     */
    private static void initAddressUnDefault() {
        //初始化地址表
        for (int i = 0; i < NetConfig.DEFAULT_SOCKET_ADDRESS.length; i++) {
            String address = NetConfig.DEFAULT_SOCKET_ADDRESS[i];
            String[] adds = StringUtils.split(address, ":");
            ipAddressRecords.add(new InetSocketAddress(adds[0], Integer.valueOf(adds[1])));
            ipStrList.add(adds[0]);
        }
    }

    public boolean add(InetSocketAddress address) {
        String ip = address.getAddress().getHostAddress();
        if (!ipStrList.contains(ip)) {
            ipAddressRecords.add(address);
            ipStrList.add(ip);
            return true;
        }
        return false;
    }

    public InetSocketAddress get(int index) {
        return ipAddressRecords.get(index);
    }

    public List<InetSocketAddress> getList() {
        return ipAddressRecords;
    }

    public Boolean contains(Object o) {
        return ipAddressRecords.contains(o);
    }

    public Boolean remove(Object o) {
        return ipAddressRecords.remove(o);
    }
}
