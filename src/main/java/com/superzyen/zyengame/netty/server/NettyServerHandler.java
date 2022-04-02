package com.superzyen.zyengame.netty.server;

import com.superzyen.zyengame.exception.SplitErrorException;
import com.superzyen.zyengame.net.Address;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;

@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 客户端连接会触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("Server active......");
    }

    /**
     * 客户端发消息会触发
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("Server receiving message: {}", msg.toString());
    }

    /**
     * 发生异常触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private void addNewAddress(String msg) throws SplitErrorException {
        //把新连接添加进ip记录表
        String[] msgs = null;
        try {
            msgs = StringUtils.split(msg, ":");
        } catch (Exception e) {
            throw new SplitErrorException();
        }
        boolean flag = Address.getInstance().add(new InetSocketAddress(msgs[0], Integer.valueOf(msgs[1])));
        //如果是新进ip则打印日志
        if (flag) {
            log.info("Connected client ip:");
        }
    }
}
