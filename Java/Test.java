
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Test {

    public static void main(String[] args) {
        try {
            Date d = new Date();
            System.out.println(d);

            SimpleDateFormat da = new SimpleDateFormat("yyyy年 MM月 dd日 E 北京时间");
            System.out.println(da.format(d));

            Thread t = Thread.currentThread();
            t.setName("this is a thread");
            System.out.println(t.getName());
            for (int i = 0; i < 3; i++) {
                System.out.println(i);
                Thread.sleep(1000);
            }
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("class not found");
            System.exit(1);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException!!!");
        } finally {
            System.out.println("exception!!!");
        }
    }

}
