package MODELS.DAO;
import java.sql.*;

public class BaseDados {
    private static final String URL = "jdbc:mysql://estga-dev.ua.pt:3306/PTDA25_BD_02";
    private static final String USER = "PTDA25_02";
    private static final String PASSWORD = "Jkis$985x";
    private static Connection con = null;
    private static int contador=0;

    public static Connection getConnection() throws SQLException {
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return con;
    }
}