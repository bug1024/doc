package com.bug1024.demo.algorithm;

/**
 * 冒泡排序
 * @author bug1024
 * @date 2020-06-07
 */
public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] input = {9, 0, 61, 3, 5, 5, 3, 2, 1};
        bubbleSort.sort(input);
        PrintUtil.print(input);
    }

    /**
     * 从小到到排序
     * @param input
     * @return
     */
    public void sort(int[] input) {
        if (input == null || input.length == 0) {
            return;
        }

        int len = input.length;
        for (int i = 0; i < len; i++) {
            // 用于标识一次循环中是否有发生交互操作
            int flag = 0;
            for (int j = 0; j < len - 1 - i; j++) {
                if (input[j] > input[j + 1]) {
                    int temp = input[j];
                    input[j] = input[j + 1];
                    input[j + 1] = temp;
                    flag = 1;
                }
            }
            // 如果没有发生交互说明已经是排好序的可直接返回
            if (flag == 0) {
                return;
            }
        }
    }
}
