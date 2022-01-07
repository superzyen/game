package com.superzyen.zyengame.page;

import com.superzyen.zyengame.account.RegisterAccount;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class RegisterPage {

    public void register() {
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
    }
}
