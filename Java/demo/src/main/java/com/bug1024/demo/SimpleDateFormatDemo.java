package com.bug1024.demo;

import java.text.SimpleDateFormat;

/**
 * SimpleDateFormat
 *
 * @author bug1024
 * @date 2020-04-05
 */
public class SimpleDateFormatDemo {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    /**
     * 不安全原因主要在于底层的Calendar的clear和get不安全
     * Calendar是用来承载字符串转化成日期对象的容器，Calendar对象有个clear后set值的过程，高并发下，set过程会出现把上次set值给覆盖的情况。
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i <= 15; i++) {
            new Thread(() -> {
                try {
                    System.out.println(sdf.parse("2020-3-27 10:09:01"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
