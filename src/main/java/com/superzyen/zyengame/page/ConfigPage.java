package com.superzyen.zyengame.page;

import com.superzyen.zyengame.common.FileUtil;
import com.superzyen.zyengame.common.ScannerUtil;
import com.superzyen.zyengame.config.LocalConfig;
import com.superzyen.zyengame.control.MainController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigPage extends AbstractPage {

    public void setting() {
        System.out.println("请输入公网ip");
        String ip = ScannerUtil.nextLine();
        System.out.println("请输入端口");
        Integer port = ScannerUtil.nextInt();
        LocalConfig localConfig = LocalConfig.builder()
                .ip(ip)
                .port(port)
                .build();
        try {
            FileUtil.serializeObject(localConfig, LocalConfig.DEFUALT_DIR_NAME, LocalConfig.DEFUALT_FILE_NAME);
        } catch (Exception e) {
            log.error("初始化配置失败，请重新配置。");
        }
        MainController.setHomeTag(1);
        MainController.setIsConfig(true);
    }

    @Override
    public String introduce() {
        return "初始化配置";
    }
}
