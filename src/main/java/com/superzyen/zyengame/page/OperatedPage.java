package com.superzyen.zyengame.page;

import com.superzyen.zyengame.common.ScannerUtil;
import com.superzyen.zyengame.control.MainController;

import java.util.List;

public class OperatedPage {

    public void page() {
        System.out.println("=============================================");
        System.out.println("请输入以下步骤编号");
        List<AbstractPage> pages = MainController.getPages();
        for (int i = 0; i < pages.size(); i++) {
            AbstractPage page = pages.get(i);
            System.out.println(i + " " + page.introduce());
        }
        System.out.println("=============================================");
        Integer step = ScannerUtil.nextInt();
        int tag = pages.get(step).getHoemTag();
        MainController.setHomeTag(tag);
    }
}
