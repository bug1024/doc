
import java.util.*;

public class Arr {

    public static void main (String[] args) {
        String arr[] = {"hello", "world", "good bye"};
        // 泛型不支持int
        // int arr2[] = {1, 2, 3};
        Integer arr2[] = {1, 2, 3};
        printArr(arr);
        printArr(arr2);
        fun();
    }

    private static void fun() {
        Hashtable<String, Integer> h = new Hashtable<>();
        h.put("one", new Integer(1111));
        h.put("two", new Integer(2222));
        System.out.println(h.get("one"));
        System.out.println(h.get("two"));
    }

    private static <T> void printArr(T[] arr) {
        for (T x: arr) {
            System.out.print(x + ",");
        }
    }

}
