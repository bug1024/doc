
import java.net.*;

public class Net {

    public static void main(String[] args) {
        try {
            InetAddress addr = InetAddress.getByName("bug1024.com");
            String domainName = addr.getHostName();
            String ipName = addr.getHostAddress();
            System.out.println(domainName);
            System.out.println(ipName);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
