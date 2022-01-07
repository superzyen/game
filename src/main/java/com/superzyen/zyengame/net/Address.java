package com.superzyen.zyengame.net;

import com.superzyen.zyengame.netty.config.NetConfig;

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
                    for (int i = 0; i < NetConfig.DEFAULT_IP_ADDRESS.length; i++) {
                        ipAddressRecords.add(new InetSocketAddress(NetConfig.DEFAULT_IP_ADDRESS[i], NetConfig.PORT));
                        ipStrList.add(NetConfig.DEFAULT_IP_ADDRESS[i]);
                    }
                }
            }
        }
        return address;
    }

    public void add(InetSocketAddress address) {
        String ip = address.getAddress().getHostAddress();
        if (!ipStrList.contains(ip)) {
            ipAddressRecords.add(address);
            ipStrList.add(ip);
        }
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
