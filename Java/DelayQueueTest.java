import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

    private static final int DELAY = 1000;

    public static void main(String[] args) throws Exception {
        DelayQueue<DelayMsg> delayQueue = new DelayQueue<>();
        producer(delayQueue);
        producer(delayQueue);
        producer(delayQueue);
        producer(delayQueue);
        producer(delayQueue);
        consumer(delayQueue);
        printQueueSize(delayQueue);
    }

    private static void producer(DelayQueue<DelayMsg> delayQueue) {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                DelayMsg delayMsg = new DelayMsg("hello", DELAY);
                delayQueue.offer(delayMsg);
            }
        }, "delayedMsgProducer").start();
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
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ": " + delayMsg);
            }
        }, "delayMsgConsumer").start();
    }

}
