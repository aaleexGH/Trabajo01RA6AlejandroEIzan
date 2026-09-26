package repositorios;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    public static Connection conectar() throws SQLException {
        Dotenv env = Dotenv.load();
        String url = env.get("DB_URL");
        String user = env.get("DB_USER");
        String pass = env.get("DB_PASS");
        
        return DriverManager.getConnection(url, user, pass);
    }
}