package com.bug1024.demo.algorithm;

/**
 * 快速排序
 * 1．先从数列中取出一个数作为基准数。
 * 2．分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边。
 * 3．再对左右区间重复第二步，直到各区间只有一个数。
 *
 * @author bug1024
 * @date 2020-06-07
 */
public class QuickSort {
    public static void main(String[] args) {
        QuickSort bubbleSort = new QuickSort();
        int[] input = {9, 0, 61, 3, 5, 5, 3, 2, 1};
        bubbleSort.quickSort(input, 0, input.length - 1);
        PrintUtil.print(input);
    }

    void sort(int[] input, int l, int r) {
        if (l < r) {
            // Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
            int i = l, j = r, x = input[l];
            while (i < j) {
                // 从右向左找第一个小于x的数
                while (i < j && input[j] >= x) {
                    j--;
                }
                if (i < j) {
                    input[i++] = input[j];
                }
                // 从左向右找第一个大于等于x的数
                while (i < j && input[i] < x) {
                    i++;
                }
                if (i < j) {
                    input[j--] = input[i];
                }
            }
            input[i] = x;
            sort(input, l, i - 1);
            sort(input, i + 1, r);
        }
    }

    private int division(int[] list, int left, int right) {
        // 以最左边的数(left)为基准
        int base = list[left];
        while (left < right) {
            // 从序列右端开始，向左遍历，直到找到小于base的数
            while (left < right && list[right] >= base) {
                right--;
            }
            // 找到了比base小的元素，将这个元素放到最左边的位置
            list[left] = list[right];

            // 从序列左端开始，向右遍历，直到找到大于base的数
            while (left < right && list[left] <= base) {
                left++;
            }
            // 找到了比base大的元素，将这个元素放到最右边的位置
            list[right] = list[left];
        }

        // 最后将base放到left位置。此时，left位置的左侧数值应该都比left小；
        // 而left位置的右侧数值应该都比left大。
        list[left] = base;
        return left;
    }

    public void quickSort(int[] list, int left, int right){
        // 左下标一定小于右下标，否则就越界了
        if (left < right) {
            // 对数组进行分割，取出下次分割的基准标号
            int base = division(list, left, right);
            // 对“基准标号“左侧的一组数值进行递归的切割，以至于将这些数值完整的排序
            quickSort(list, left, base - 1);
            // 对“基准标号“右侧的一组数值进行递归的切割，以至于将这些数值完整的排序
            quickSort(list, base + 1, right);
        }
    }
}
