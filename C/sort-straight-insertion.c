#include <stdio.h>

#define LENGTH(array) ((sizeof(array)) / (sizeof(array[0])))
#define SWAP(a,b) (a^=b,b^=a,a^=b)

/**
 * 直接插入排序
 * O(N^2)
 *
 * 把n个待排序的元素看成为一个有序表和一个无序表。开始时有序表中只包含1个元素，无序表中包含有n-1个元素，排序过程中每次从无序表中取出第一个元素，
 * 将它插入到有序表中的适当位置，使之成为新的有序表，重复n-1次可完成排序过程。
 */
int main(int argc, char *argv[]) {
    int numbers[] = {1, 3, 2, 7, 99, 10, 23, 20, 100, 4};
    int i, j, k, len = LENGTH(numbers);

    for (i = 1; i < len; i++) {
        // numbers[i]在前面的a[0...i-1]有序区间中找一个合适的位置
        for (j = i - 1; j >= 0; j--)
            if (numbers[j] < numbers[i]) {
                break;
            }

            // 如找到了一个合适的位置
            if (j != i - 1) {
                // 将比a[i]大的数据向后移
                int temp = numbers[i];
                for (k = i - 1; k > j; k--) {
                    numbers[k + 1] = numbers[k];
                }
                // 将a[i]放到正确位置上
                numbers[k + 1] = temp;
            }
    }

    printf("insertion sorted: \n");
    for (i = 0; i < len; i++) {
        printf("%d, ", numbers[i]);
    }

    return 0;
}
