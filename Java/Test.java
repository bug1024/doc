
import java.sql.*;

public class Test {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("class not found");
            System.exit(1);
        }
    }

}
