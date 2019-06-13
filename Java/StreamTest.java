import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author bug1024
 * @date 2019-06-12
 */
public class StreamTest {

    private static List<User> users = new ArrayList<>();
    static {
        User user = null;
        for (int i = 0; i < 10000; i++) {
            user = new User();
            user.setAge(i);
            user.setName("name" + i);
            users.add(user);
        }
    }

    public static void main(String[] args) {
        List<User> userList = users.stream()
                .filter(e ->  e.getAge() > 9990)
                .map(e -> {e.setAge(e.getAge() + 100);return e;})
                .sorted((e1, e2) -> e1.getAge() > e2.getAge() ? -1: 1)
                .limit(3)
                .collect(Collectors.toList());

        Map<String, User> userMap = users.stream()
                .filter(e -> e.getAge() > 9990)
                .collect(Collectors.toMap(User::getName, Function.identity()));

        Map<String, Integer> userMap2 = users.stream()
                .filter(e -> e.getAge() > 9990)
                .collect(Collectors.toMap(User::getName, User::getAge));

        Map<String, List<User>> userMap3 = users.stream()
                .filter(e -> e.getAge() > 9990)
                .collect(Collectors.groupingBy(User::getName));

        Map<String, Long> userMap4 = users.stream()
                .filter(e -> e.getAge() > 9990)
                .collect(Collectors.groupingBy(User::getName, Collectors.counting()));

        System.out.println(userList);
        System.out.println(userMap);
        System.out.println(userMap2);
        System.out.println(userMap3);
        System.out.println(userMap4);
    }


    private static class User implements Serializable {

        private static final long serialVersionUID = 6779887185833816588L;

        private String name;

        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
