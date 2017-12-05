/**
 *
 * @author bug1024
 * @date 2017-12-05
 */
public class Memory {

    public static void main(String[] args) throws Exception {
        testAllocation();
    }

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -Xloggc:/Users/bug1024/gc.log
     */
    private static void testAllocation() {
        int m = 1024 * 1024;
        byte[] b1 = new byte[2 * m];
        byte[] b2 = new byte[2 * m];
        byte[] b3 = new byte[2 * m];
        byte[] b4 = new byte[4 * m];
    }
}
