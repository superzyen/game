package com.superzyen.zyengame.control;

import com.superzyen.zyengame.account.CheckLocalAccount;
import com.superzyen.zyengame.config.ConfigProcessor;
import com.superzyen.zyengame.exception.SettingInitialException;
import com.superzyen.zyengame.page.*;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    private static Integer homeTag = 0;
    private static final int EXIST_ACCOUNT = 1;
    private static final int REGISTER_ACCOUNT = 2;
    private static Boolean isConfig = false;
    private static Boolean isRegisted = false;
    private static List<AbstractPage> pages = new ArrayList<>();
    private static RegisterPage registerPage;
    private static OperatedPage operatedPage;
    private static ConfigPage configPage;
    private static ConnectionPage connectionPage;

    public void mainProcess() throws SettingInitialException {
        initPage();
        while (true) {
            loopBranch();
        }
    }

    private void loopBranch() throws SettingInitialException {
        switch (homeTag) {
            case 1:
                //主操作界面
                operatedPage.page();
                break;
            case 2:
                //注册界面
                registerPage.register();
                break;
            case 3:
                //初始化配置界面
                configPage.setting();
                break;
            case 4:
                //进入区块链
                connectionPage.run();
                break;
            default:
                //欢迎界面
                HomePage.home();
                break;
        }
    }

    public static void setHomeTag(Integer tag) {
        homeTag = tag;
    }

    public static void setIsConfig(boolean isConfig) {
        isConfig = isConfig;
    }

    public static boolean getIsCongfig(){
        return isConfig;
    }

    public static void setIsRegisted(boolean isRegisted) {
        isRegisted = isRegisted;
    }

    public static boolean getIsRegisted(){
        return isRegisted;
    }

    private void initPage() throws SettingInitialException {
        registerPage = new RegisterPage();
        operatedPage = new OperatedPage();
        configPage = new ConfigPage();
        connectionPage = new ConnectionPage();

        addPages();
        preCheckFlag();

    }

    /**
     * 初始化检查
     */
    private void preCheckFlag() throws SettingInitialException {
        isRegisted = new CheckLocalAccount().check();
        isConfig = new ConfigProcessor().check();
    }

    /**
     * 添加主操作页面类
     */
    private void addPages() {
        pages.add(registerPage);
        registerPage.setHoemTag(2);
        pages.add(configPage);
        configPage.setHoemTag(3);
        pages.add(connectionPage);
        connectionPage.setHoemTag(4);
    }

    public static List<AbstractPage> getPages() {
        return pages;
    }
}
