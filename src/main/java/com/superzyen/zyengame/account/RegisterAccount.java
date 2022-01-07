package com.superzyen.zyengame.account;

import com.superzyen.zyengame.common.FileUtil;
import com.superzyen.zyengame.control.MainController;

import java.io.IOException;
import java.util.UUID;

public class RegisterAccount {

    public void register(String userName) throws IOException {
        String localId = UUID.randomUUID().toString();
        Account account = new Account();
        account.setLocalId(localId);
        account.setUserName(userName);
        FileUtil.serializeObject(account);
        MainController.setHomeTag(1);
    }
}
