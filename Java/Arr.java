
public class Arr {

    public static void main (String[] args) {
        String arr[] = {"hello", "world", "good bye"};
        printArr(arr);
    }

    private static void printArr(String[] arr) {
        for (String x: arr ) {
            System.out.print(x + ",");
        }
    }

}
