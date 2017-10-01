
public class GC {

    public static GC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("I'm still alive");
    }

    @Override
    protected void finalize()  throws Throwable {
        super.finalize();
        System.out.println("finalize method executed");
        GC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new GC();
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(1000);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("No, I'm dead");
        }

        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(1000);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("No, I'm dead");
        }
    }
}
