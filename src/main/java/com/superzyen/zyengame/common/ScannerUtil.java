package com.superzyen.zyengame.common;

import java.util.Scanner;

public class ScannerUtil {
    private static Scanner scanner;

    public static String next() {
        try {
            scanner = new Scanner(System.in);
            return scanner.next();
        } finally {
            scanner = null;
        }
    }

    public static String nextLine() {
        try {
            scanner = new Scanner(System.in);
            return scanner.nextLine();
        } finally {
            scanner = null;
        }
    }

    public static int nextInt() {
        try {
            scanner = new Scanner(System.in);
            return scanner.nextInt();
        } finally {
            scanner = null;
        }
    }
}
