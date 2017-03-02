
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

    public void msg(String content) throws Exception {
        if (content.length() == 0) {
            throw new Exception("content can not be empty");
        }
        System.out.println("msg:" + content);
    }

    public static void main(String[] args) {
        Cellphone phone = new Cellphone("iPhone");
        System.out.println(phone.brand);
        phone.call();
        phone.msg();
        try {
            phone.msg("Are you OK?");
            phone.msg("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        int a = 12;
        if (true) {
            a = 123;
        }

        System.out.println(a);
    }

}

