package com.superzyen.zyengame.net;

import com.superzyen.zyengame.exception.SplitErrorException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Slf4j
@Component
public class AddressManager {


    /**
     *  判断是否传送地址信息
     */
    @Async("taskExecutor")
    public void isAddress(String msg) {
        if (msg.startsWith(Address.TAG_PRE_FIX)) {
            String[] msgs = StringUtils.splitPreserveAllTokens(msg, "^");
            try {
                addNewAddress(msgs[1]);
            } catch (SplitErrorException e) {
                log.error("保存地址表失败", e);
            }
        }
    }

    /**
     *  地址表中添加新地址（公网ip+端口）
     */
    private void addNewAddress(String msg) throws SplitErrorException {
        //把新连接添加进ip记录表
        String[] msgs = null;
        try {
            msgs = StringUtils.splitPreserveAllTokens(msg, ":");
        } catch (Exception e) {
            throw new SplitErrorException();
        }
        boolean flag = Address.getInstance().add(new InetSocketAddress(msgs[0], Integer.valueOf(msgs[1])));
        //如果是新进ip则打印日志
        if (flag) {
            log.info("Connected client ip:" + msg);
        }
    }

}
