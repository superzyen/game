package com.superzyen.zyengame.page;

import com.superzyen.zyengame.account.CheckLocalAccount;
import com.superzyen.zyengame.account.RegisterAccount;
import com.superzyen.zyengame.control.MainController;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class RegisterPage extends AbstractPage {

    public void register() {
        if (existAccount()) {
            System.out.println("已经存在账户，无需重复注册");
            MainController.setHomeTag(1);
            MainController.setIsRegisted(true);
            return;
        }

        System.out.println("注册账户...");
        System.out.println("请输入你的用户名");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.next();
        RegisterAccount registor = new RegisterAccount();
        try {
            registor.register(userName);
        } catch (Exception e) {
            log.error("注册失败");
        }
        System.out.println("注册成功！");
        MainController.setHomeTag(1);
        MainController.setIsRegisted(true);
    }

    @Override
    public String introduce() {
        return "注册账户";
    }

    /**
     *  检测是否存在账户
     */
    private boolean existAccount() {
        CheckLocalAccount checkLocalAccount = new CheckLocalAccount();
        return checkLocalAccount.check();
    }
}
