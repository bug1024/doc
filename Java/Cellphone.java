
public class Cellphone extends Telephone {

    String brand;

    public Cellphone(String brandName) {
        brand = brandName;
    }

    public void call() {
        System.out.println("call");
    }

    public void msg() {
        System.out.println("msg");
    }

    public void msg(String content) {
        System.out.println("msg:" + content);
    }

    public static void main(String[] args) {
        Cellphone phone = new Cellphone("iPhone");
        System.out.println(phone.brand);
        phone.call();
        phone.msg();
        phone.msg("Are you OK?");

        int a = 12;
        if (true) {
            a = 123;
        }

        System.out.println(a);
    }

}

