public class Dog{
    String name;
    int age;
   
    Dog(String name1, int age1){
        name = name1;
        age = age1;
        System.out.println("感谢主人领养了我");
    }
   
    void bark(){
        System.out.println("汪汪，不要过来");
    }
 
    void hungry(){
        System.out.println("主人，我饿了");
    }
   
    public static void main(String arg[]){
        Dog myDog = new Dog("花花", 3);
        // 访问成员变量
        String name = myDog.name;
        int age = myDog.age;
        System.out.println("我是一只小狗，我名字叫" + name + "，我" + age + "岁了");
        // 访问方法
        myDog.bark();
        myDog.hungry();
    }
}
