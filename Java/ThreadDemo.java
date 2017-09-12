public class ThreadDemo implements Runnable {

    @Override
    public void run() {
        // syncronized
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }

}

public class TestDemo {

    public static void main(String[] args) throws Exception {
        Thread t = new TreadDemo();
        new Thread(t, "A").start();
        new Thread(t, "B").start();
    }
}
