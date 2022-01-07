package com.superzyen.zyengame.control;

import com.superzyen.zyengame.page.HomePage;
import com.superzyen.zyengame.page.OperatedPage;
import com.superzyen.zyengame.page.RegisterPage;

public class MainController {
    private static Integer homeTag = 0;
    private static final int EXIST_ACCOUNT = 1;
    private static final int REGISTER_ACCOUNT = 2;
    private static RegisterPage registerPage;
    private static OperatedPage operatedPage;

    public void mainProcess() {
        while (true) {
            loopBranch();
        }
    }

    private void loopBranch() {
        initPage();
        switch (homeTag) {
            case 1:
                //主操作界面
                operatedPage.page();
                break;
            case 2:
                //注册界面
                registerPage.register();
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

    private void initPage() {
        registerPage = new RegisterPage();
        operatedPage = new OperatedPage();
    }
}
