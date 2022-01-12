package com.superzyen.zyengame.netty.server;

import com.superzyen.zyengame.net.Address;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 客户端连接会触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("服务器 active......");
        //把新连接添加进ip记录表
        Address.getInstance().add((InetSocketAddress) ctx.channel().remoteAddress());
        System.out.println(((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress());
    }

    /**
     * 客户端发消息会触发
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("服务器收到消息: {}", msg.toString());
        System.out.println("收到的消息：" + msg);
    }

    /**
     * 发生异常触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
