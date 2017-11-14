import java.lang.reflect.Method;

/**
 *
 * @author bug1024
 * @date 2017-11-14
 */
public class MyAnnotationTest {

    public static void printInfo(Class<?> clazz) {
        ///Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
                System.out.println(myAnnotation.author());
                System.out.println(myAnnotation.version());
                System.out.println(myAnnotation.desc());
            }
        }
    }

    public static class MyApi {
        @MyAnnotation(version = "2.0", desc = "just test", author = "bug")
        public void test() {

        }
    }

    public static void main(String[] args) {
        MyAnnotationTest.printInfo(MyApi.class);
    }
}


