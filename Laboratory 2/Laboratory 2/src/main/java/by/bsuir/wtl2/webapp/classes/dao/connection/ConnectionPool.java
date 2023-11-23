package by.bsuir.wtl2.webapp.classes.dao.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private static final ConnectionPool INSTANCE = new ConnectionPool();
    private BlockingQueue<Connection> availableConnections;
    private BlockingQueue<Connection> usedConnections;

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    private ConnectionPool() {

    }

    public void initialize() throws SQLException {
        int poolSize = 50;
        availableConnections = new ArrayBlockingQueue<>(poolSize);
        usedConnections = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            Connection connection = ConnectionFactory.createConnection();
            availableConnections.add(connection);
        }
    }

    public void releaseConnection(Connection proxyConnection) throws SQLException {
        if (proxyConnection != null) {
            usedConnections.remove(proxyConnection);
            try {
                availableConnections.put(proxyConnection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new SQLException(e.getMessage(), e);
            }
        }
    }

    public Connection getConnection() throws SQLException {
        Connection connection;
        try {
            connection = availableConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SQLException(e.getMessage(), e);
        }
        return connection;
    }

    public void destroy() throws SQLException {
        try {
            for (Connection connection : availableConnections) {
                connection.close();
            }
            for (Connection connection : usedConnections) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }

    }
}
