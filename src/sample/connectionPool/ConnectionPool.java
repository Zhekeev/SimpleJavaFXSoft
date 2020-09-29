package sample.connectionPool;

import org.w3c.dom.ls.LSOutput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
    private final String url = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;database=Tour Operator;";
    private final String userName = "ERGAZI\\ergaz";
    private final String password = "";

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(url);
        if(connection!=null)
            System.out.println("Connection Successful!");
        return connection;
    }
}
