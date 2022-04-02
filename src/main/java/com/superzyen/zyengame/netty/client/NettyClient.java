package com.superzyen.zyengame.netty.client;

import com.superzyen.zyengame.common.ScannerUtil;
import com.superzyen.zyengame.netty.config.NetConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NettyClient {

    @Async("taskExecutor")
    public void start(String host) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .group(group)
                //该参数的作用就是禁止使用Nagle算法，使用于小数据即时传输
                .option(ChannelOption.TCP_NODELAY, true)
                .channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());

        try {
            ChannelFuture future = bootstrap.connect(host, NetConfig.PORT).sync();
            log.info("Client success....");
            //发送消息
            future.channel().writeAndFlush(ScannerUtil.nextLine());
            // 等待连接被关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public boolean syncStart(String host, int port) {
        boolean conectFlag = false;
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .group(group)
                //该参数的作用就是禁止使用Nagle算法，使用于小数据即时传输
                .option(ChannelOption.TCP_NODELAY, true)
                .channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());

        try {
            ChannelFuture future = bootstrap.connect(host, port).sync();
            log.info("Client success....");
            conectFlag = true;
            //发送消息
            future.channel().writeAndFlush(ScannerUtil.nextLine());
            // 等待连接被关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            log.error("Client connect unsuccessfully", e);
        } finally {
            group.shutdownGracefully();
        }
        return conectFlag;
    }
}
