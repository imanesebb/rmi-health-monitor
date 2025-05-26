package medbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/medbd";
            String user = "root";  
            String password = "";  

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion réussie !");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
