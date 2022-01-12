package com.superzyen.zyengame;

import com.superzyen.zyengame.common.ScannerUtil;
import com.superzyen.zyengame.common.SpringUtils;
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
//		MainController mainController = new MainController();
//		mainController.mainProcess();
        //启动服务端
        NettyServer nettyServer = SpringUtils.getBean(NettyServer.class);
        nettyServer.start();

        Address address = Address.getInstance();
        List<InetSocketAddress> ipRecords = address.getList();
        while (true) {
            if (ipRecords.size() > 0) {
                int index = 0;
                for (InetSocketAddress inetSocketAddress : ipRecords) {
                    System.out.println("序号" + index + " : " + inetSocketAddress.getAddress().getHostAddress());
                    index++;
                }
                System.out.println("请输入你要连接的ip的序号");
                int select = ScannerUtil.nextInt();
                if (select < 0) {
                    continue;
                }
                InetSocketAddress inetSocketAddress = ipRecords.get(select);
                //启动netty客户端
                NettyClient nettyClient = SpringUtils.getBean(NettyClient.class);
                while (true) {
                    System.out.println("请输入你的消息");
                    Boolean flag = nettyClient.syncStart(inetSocketAddress.getAddress().getHostAddress());
                    if (!flag) {
                        break;
                    }
                }

            }
        }
    }

}
