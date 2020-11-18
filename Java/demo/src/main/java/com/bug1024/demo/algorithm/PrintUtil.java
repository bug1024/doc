package com.bug1024.demo.algorithm;

/**
 * 打印工具
 * @author bug1024
 * @date 2020-06-07
 */
public class PrintUtil {
    private PrintUtil() {
    }

    public static void print(int[] input) {
        if (input == null || input.length == 0) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder("[");
        for (int t : input) {
            sb.append(t).append(", ");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
