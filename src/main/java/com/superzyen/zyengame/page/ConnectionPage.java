package com.superzyen.zyengame.page;

import com.superzyen.zyengame.common.ScannerUtil;
import com.superzyen.zyengame.common.SpringUtils;
import com.superzyen.zyengame.config.ConfigProcessor;
import com.superzyen.zyengame.control.MainController;
import com.superzyen.zyengame.exception.SettingInitialException;
import com.superzyen.zyengame.net.Address;
import com.superzyen.zyengame.netty.client.NettyClient;
import com.superzyen.zyengame.netty.server.NettyServer;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.List;

@Slf4j
public class ConnectionPage extends AbstractPage {

    public void run() throws SettingInitialException {
        if (MainController.getIsCongfig() && MainController.getIsRegisted()) {
            //启动服务
            start();
            //连接其他客户端服务
            connect();

        } else {
            System.out.println("请注册账号或者初始化配置");
            MainController.setHomeTag(1);
        }
    }

    /**
     * 启动区块服务
     */
    private void start() {
        //启动服务端
        NettyServer nettyServer = SpringUtils.getBean(NettyServer.class);
        nettyServer.start();
    }

    /**
     * 接入区块链
     */
    private void connect() throws SettingInitialException {
        Address address = Address.getInstance();
        List<InetSocketAddress> ipRecords = address.getList();

        //首次连接发送地址
        sendAddress(ipRecords);

        while (true) {
            if (ipRecords.size() > 0) {
                int index = 0;
                for (InetSocketAddress inetSocketAddress : ipRecords) {
                    System.out.println("Num " + index + " : " + inetSocketAddress.getAddress().getHostAddress());
                    index++;
                }
                System.out.println("请输入你的选择号码:");
                int select = ScannerUtil.nextInt();
                if (select < 0) {
                    continue;
                }
                InetSocketAddress inetSocketAddress = ipRecords.get(select);
                //启动netty客户端
                NettyClient nettyClient = SpringUtils.getBean(NettyClient.class);
                while (true) {
                    System.out.println("请输入你要发送的信息:");
                    nettyClient.syncStart(inetSocketAddress.getAddress().getHostAddress(),
                            inetSocketAddress.getPort());
                }
            }
        }

    }


    /**
     * 首次发送地址
     */
    private static void sendAddress(List<InetSocketAddress> ipRecords) throws SettingInitialException {
        if (ipRecords.size() > 0) {
            int index = 0;
            for (InetSocketAddress inetSocketAddress : ipRecords) {
                System.out.println("Num " + index + " : " + inetSocketAddress.getAddress().getHostAddress());
                index++;
            }
            System.out.println("请输入你的选择号码:");
            int select = ScannerUtil.nextInt();
            if (select < 0) {
                return;
            }
            InetSocketAddress inetSocketAddress = ipRecords.get(select);
            //启动netty客户端
            NettyClient nettyClient = SpringUtils.getBean(NettyClient.class);
            String msg = new ConfigProcessor().getAddress();
            nettyClient.syncStart(inetSocketAddress.getAddress().getHostAddress(),
                    inetSocketAddress.getPort(), msg);
        }

    }

    @Override
    public String introduce() {
        return "接入区块链";
    }
}
