import java.util.concurrent.TimeUnit;

public class UniqueQueueTest {

    public static void main(String[] args) {
        UniqueLinkedBlockingQueue<Integer> uniqueLinkedBlockingQueue = new UniqueLinkedBlockingQueue<>();
        for (int i = 0; i < 10; i++) {
            producer(uniqueLinkedBlockingQueue, i);
        }
        consumer(uniqueLinkedBlockingQueue);
    }

    private static void producer(UniqueLinkedBlockingQueue<Integer> uniqueLinkedBlockingQueue, Integer element) {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                uniqueLinkedBlockingQueue.offer(element);
            }
        }, "producer").start();
    }

    private static void consumer(UniqueLinkedBlockingQueue<Integer> uniqueLinkedBlockingQueue) {
        new Thread(() -> {
            while (true) {
                try {
                    Integer el = uniqueLinkedBlockingQueue.take();
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.println("queueSize:" + uniqueLinkedBlockingQueue.size() + " element:" + el);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer").start();
    }
}
