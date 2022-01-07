package com.superzyen.zyengame.account;

import com.superzyen.zyengame.common.FileUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckLocalAccount {

    public Boolean check() {
        String path = FileUtil.getPath() + "/" + Account.DEFUALT_DIR_NAME + "/" + Account.DEFUALT_FILE_NAME;
        try {
            Account account = FileUtil.deserializeObject(path, Account.class);
            if (null != account) {
                return true;
            }
        } catch (Exception e) {
            log.info("本地无账户");
        }
        return false;
    }
}
