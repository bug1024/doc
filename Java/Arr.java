
public class Arr {

    public static void main (String[] args) {
        String arr[] = {"hello", "world", "good bye"};
        // 泛型不支持int
        // int arr2[] = {1, 2, 3};
        Integer arr2[] = {1, 2, 3};
        printArr(arr);
        printArr(arr2);
    }

    private static <T> void printArr(T[] arr) {
        for (T x: arr) {
            System.out.print(x + ",");
        }
    }

}
