import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

    private static final int DELAY = 1000;

    private static ConcurrentHashMap<String, Boolean> msgMap;

    public static void main(String[] args) throws Exception {
        DelayQueue<DelayMsg> delayQueue = new DelayQueue<>();
        producer(delayQueue, "1");
        consumer(delayQueue);
        printQueueSize(delayQueue);
    }

    private static void producer(DelayQueue<DelayMsg> delayQueue, String msg) {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                DelayMsg delayMsg = new DelayMsg(msg, DELAY);
                delayQueue.offer(delayMsg);
            }
        }, "delayMsgProducer").start();
    }

    private static void printQueueSize(DelayQueue<DelayMsg> delayQueue) {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("queueSize:" + delayQueue.size());
            }
        }, "printDelayQueueSize").start();
    }

    private static void consumer(DelayQueue<DelayMsg> delayQueue) {
        new Thread(() -> {
            while (true) {
                DelayMsg delayMsg = null;
                try {
                    delayMsg = delayQueue.take();
                    // 模拟消费延迟
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ": " + delayMsg);
                System.out.println("delayQueueSize:" + delayQueue.size());
            }
        }, "delayMsgConsumer").start();
    }

}
