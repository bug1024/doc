import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ThreadDemo {


    public static void createBusyThread() {
        new Thread(() -> {
           while (true);
        }, "createBusyThread").start();
    }

    public static void createLockThread(final Object lock) {
        new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "createLockThread").start();
    }

    public static class SyncAddRunnable implements Runnable {
        int a, b;

        public SyncAddRunnable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            // Integer.valueOf 基于减少对象创建和减少内存考虑，会对[-128, 127]数字进行缓存
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        createLockThread(new Object());
        br.readLine();
        for (int i = 0; i < 100; i++) {
            new Thread(new SyncAddRunnable(1, 2)).start();
            new Thread(new SyncAddRunnable(2, 1)).start();
        }
    }

}
