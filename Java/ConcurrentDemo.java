import java.util.concurrent.CountDownLatch;

/**
 * @author wangyu
 * @date 2019-03-03
 */
public class ConcurrentDemo {

    private static CountDownLatch countDownLatch = new CountDownLatch(4);

    public static void main(String[] args) throws Exception {
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1");
            countDownLatch.countDown();
            try {
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("thread1 finish");
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("thread2");
            countDownLatch.countDown();
            try {
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("thread2 finish");
        });
        thread1.setName("thread1");
        thread2.setName("thread2");

        thread1.start();
        thread2.start();
        countDownLatch.countDown();
        countDownLatch.countDown();
        countDownLatch.await();

        System.out.println("finish");
    }
}
