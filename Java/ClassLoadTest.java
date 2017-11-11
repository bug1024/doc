import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author bug1024
 * @date 2017-11-11
 */
public class ClassLoadTest {

    public static void main(String[] args) {
        new Demo().get();
    }

    private static class Demo {
        private void get() {
            Map<String, String> map = new HashMap<String, String>(){{
                put("a", "b");
                put("b", "b");
            }};
            String path = getClass().getClassLoader().getResource("").toString();
            System.out.println(path + map);
        }
    }

}
