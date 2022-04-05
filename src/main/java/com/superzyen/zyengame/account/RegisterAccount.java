package com.superzyen.zyengame.account;

import com.superzyen.zyengame.common.FileUtil;
import com.superzyen.zyengame.control.MainController;

import java.util.UUID;

public class RegisterAccount {

    public void register(String userName) throws Exception {
        String localId = UUID.randomUUID().toString();
        Account account = new Account();
        account.setLocalId(localId);
        account.setUserName(userName);
        FileUtil.serializeObject(account, Account.DEFUALT_DIR_NAME, Account.DEFUALT_FILE_NAME);
        MainController.setHomeTag(1);
    }
}
