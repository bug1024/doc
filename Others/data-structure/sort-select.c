#include <stdio.h>

#define LENGTH(array) ((sizeof(array)) / (sizeof(array[0])))
#define SWAP(a,b) (a^=b,b^=a,a^=b)

/**
 * 选择排序
 * O(N^2)
 *
 * 在一个长度为N的无序数组中，在第一趟遍历N个数据，找出其中最小的数值与第一个元素交换，第二趟遍历剩下的N-1个数据，
 * 找出其中最小的数值与第二个元素交换......第N-1趟遍历剩下的2个数据，找出其中最小的数值与第N-1个元素交换，至此选择排序完成。
 */
int main(int argc, char *argv[]) {
    int numbers[] = {1, 3, 2, 7, 99, 10, 23, 20, 100, 4};
    int i, j, min, temp, len = LENGTH(numbers);

    for (i = 0; i < len; i++) {
        min = i;
        for (j = i + 1; j < len; j++) {
            if (numbers[j] < numbers[min]) {
                min = j;
            }
            if (min != i) {
                SWAP(numbers[min], numbers[i]);
            }
        }
    }

    printf("select sorted: \n");
    for (i = 0; i < len; i++) {
        printf("%d, ", numbers[i]);
    }

    return 0;
}
