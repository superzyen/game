package com.superzyen.zyengame;

import com.superzyen.zyengame.common.ScannerUtil;
import com.superzyen.zyengame.common.SpringUtils;
import com.superzyen.zyengame.config.ConfigProcessor;
import com.superzyen.zyengame.control.MainController;
import com.superzyen.zyengame.exception.SettingInitialException;
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

    public static void main(String[] args) throws UnknownHostException, SettingInitialException {
        SpringApplication.run(ZyengameApplication.class, args);
		MainController mainController = new MainController();
		mainController.mainProcess();

    }




}
