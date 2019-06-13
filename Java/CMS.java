import java.util.ArrayList;
import java.util.List;

/**
 * CMS
 * @author bug024
 * @date 2019-04-30
 */
public class CMS {
    private String name = "hello";
    private Integer age = 28;
    private Double weight = 65.0d;

    public static void main(String[] args) {
        List<CMS> objects = new ArrayList<>();
        boolean flag = true;
        int count = 0;
        while (flag) {
            objects.add(new CMS());
            count++;
            if (count % 10000 == 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
