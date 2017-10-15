import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class UniqueLinkedBlockingQueue<E> extends LinkedBlockingQueue<E> {

    private static final long serialVersionUID = 6523405086129214113L;
    private final ReentrantLock offerLock = new ReentrantLock();

    public boolean offer(E e) {
        offerLock.lock();
        try {
            if (!contains(e)) {
                return super.offer(e);
            }
        } finally {
            offerLock.unlock();
        }

        return true;
    }
}  