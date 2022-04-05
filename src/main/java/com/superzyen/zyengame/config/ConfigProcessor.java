package com.superzyen.zyengame.config;

import com.superzyen.zyengame.common.FileUtil;
import com.superzyen.zyengame.exception.SettingInitialException;
import com.superzyen.zyengame.net.Address;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigProcessor {

    /**
     *  反序列化配置对象
     */
    public LocalConfig deserializationSetting() throws SettingInitialException {
        String path = FileUtil.getPath() + "/" + LocalConfig.DEFUALT_DIR_NAME + "/" + LocalConfig.DEFUALT_FILE_NAME;
        try {
            return FileUtil.deserializeObject(path, LocalConfig.class);
        } catch (Exception e) {
            log.info("初始化配置失败");
            throw new SettingInitialException();
        }
    }

    /**
     * 获取ip地址
     */
    public String getAddress() throws SettingInitialException {
        LocalConfig localConfig = deserializationSetting();
        return String.format("%s^%s:%s", Address.TAG_PRE_FIX, localConfig.getIp(), localConfig.getPort());
    }

    /**
     * 检查是否存在配置
     */
    public boolean check() throws SettingInitialException {
        LocalConfig localConfig = deserializationSetting();
        if (localConfig != null) {
            return true;
        } else {
            return false;
        }
    }
}
