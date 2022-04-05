package com.superzyen.zyengame;

import com.superzyen.zyengame.common.ScannerUtil;
import com.superzyen.zyengame.common.SpringUtils;
import com.superzyen.zyengame.control.MainController;
import com.superzyen.zyengame.net.Address;
import com.superzyen.zyengame.netty.client.NettyClient;
import com.superzyen.zyengame.netty.server.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.List;

@SpringBootApplication
public class ZyengameApplication {

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(ZyengameApplication.class, args);
		MainController mainController = new MainController();
		mainController.mainProcess();
//        //启动服务端
//        NettyServer nettyServer = SpringUtils.getBean(NettyServer.class);
//        nettyServer.start();
//
//        Address address = Address.getInstance();
//        List<InetSocketAddress> ipRecords = address.getList();
//
//        //首次连接发送地址
//        sendAddress(ipRecords);
//
//        while (true) {
//            if (ipRecords.size() > 0) {
//                int index = 0;
//                for (InetSocketAddress inetSocketAddress : ipRecords) {
//                    System.out.println("Num " + index + " : " + inetSocketAddress.getAddress().getHostAddress());
//                    index++;
//                }
//                System.out.println("Pls input your num:");
//                int select = ScannerUtil.nextInt();
//                if (select < 0) {
//                    continue;
//                }
//                InetSocketAddress inetSocketAddress = ipRecords.get(select);
//                //启动netty客户端
//                NettyClient nettyClient = SpringUtils.getBean(NettyClient.class);
//                while (true) {
//                    System.out.println("Write your message");
//                    Boolean flag = nettyClient.syncStart(inetSocketAddress.getAddress().getHostAddress(),
//                            inetSocketAddress.getPort());
//                    if (!flag) {
//                        break;
//                    }
//                }
//
//            }
//        }
    }

    /**
     *  首次发送地址
     */
    private static void sendAddress(List<InetSocketAddress> ipRecords) {
        if (ipRecords.size() > 0) {
            int index = 0;
            for (InetSocketAddress inetSocketAddress : ipRecords) {
                System.out.println("Num " + index + " : " + inetSocketAddress.getAddress().getHostAddress());
                index++;
            }
            System.out.println("Pls input your num:");
            int select = ScannerUtil.nextInt();
            if (select < 0) {
                return;
            }
            InetSocketAddress inetSocketAddress = ipRecords.get(select);
            //启动netty客户端
            NettyClient nettyClient = SpringUtils.getBean(NettyClient.class);
            while (true) {
                System.out.println("Write your message");
                Boolean flag = nettyClient.syncStart(inetSocketAddress.getAddress().getHostAddress(),
                        inetSocketAddress.getPort());
                if (!flag) {
                    break;
                }
            }

        }
    }

}
