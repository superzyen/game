package com.superzyen.zyengame.page;

import java.util.Scanner;

public class OperatedPage {

    public void page() {
        System.out.println("=============================================");
        System.out.println("请输入以下步骤编号");
        System.out.println("=============================================");
        Scanner scanner = new Scanner(System.in);
        Integer step = scanner.nextInt();
        System.out.println(step);
    }
}
