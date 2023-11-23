package by.bsuir.wtl2.webapp.classes.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final String DB_URL = "localhost/onlineshop";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";
    private static final String DB_DRIVER = "jdbc:mysql://";

    static Connection createConnection() throws SQLException {
        Connection proxyConnection = null;
        try {
            proxyConnection = DriverManager.getConnection(DB_DRIVER + DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }
        return proxyConnection;
    }
}
