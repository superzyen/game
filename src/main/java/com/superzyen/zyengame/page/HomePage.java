package com.superzyen.zyengame.page;

import com.superzyen.zyengame.account.CheckLocalAccount;
import com.superzyen.zyengame.control.MainController;

public class HomePage {

    public static void home() {
        System.out.println("欢迎来到 zyen game !");
        System.out.println("检测是否存在账户...");
        checkAccount();
    }

    private static void checkAccount() {
        Boolean isExist = new CheckLocalAccount().check();
        if (isExist) {
            MainController.setHomeTag(1);
        } else {
            MainController.setHomeTag(2);
        }
    }
}
