import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayMsg implements Delayed {

    private final String msg;

    private final long delay;

    private final long expire;

    private final long now;

    public DelayMsg(String msg, long delay) {
        this.msg = msg;
        this.delay = delay;
        this.expire = System.currentTimeMillis();
        this.now = System.currentTimeMillis() + delay;
    }

    /**
     * 获取延迟时间 过期时间 - 当前时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis() , TimeUnit.MILLISECONDS);
    }

    /**
     * 比较排序 当前对象的延迟时间 - 比较对象的延迟时间
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Msg{");
        sb.append("delay=").append(delay);
        sb.append(", expire=").append(expire);
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", now=").append(now);
        sb.append('}');
        return sb.toString();
    }

    public String getMsg() {
        return msg;
    }
}
