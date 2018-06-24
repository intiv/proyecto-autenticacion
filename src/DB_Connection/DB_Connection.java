package DB_Connection;
import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {

    public static void main(String[] args) {
        DB_Connection obj_DB_Connection = new DB_Connection();
        Connection connection = null;
        connection = obj_DB_Connection.get_connection();
        System.out.println(connection);
    }

    public Connection get_connection() {
        Connection connection = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/so2proyecto2", "so2admin", "so2password");
        } catch (Exception e) {
            System.err.println(e);
        }
        return connection;
    }
}
