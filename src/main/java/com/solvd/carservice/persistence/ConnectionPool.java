package com.solvd.carservice.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectionPool {
    //lazy-initialization - initialize object in getInstance
    //thread-safe - using volatile
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(ConnectionPool.class);
    private volatile static ConnectionPool instance;
    private final List<Connection> connections = new CopyOnWriteArrayList<>();
    private final int poolNumbers;
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/car_service_project";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "dafik1987";

    public ConnectionPool(int poolNumbers) {
        this.poolNumbers = poolNumbers;
        for (int i = 0; i < this.poolNumbers; i++) {
            Connection connection = null;
            try {
                Class.forName(DB_DRIVER);
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                if (connection != null) {
                    LOGGER.info("Successfully connected");
                } else {
                    LOGGER.info("Failed to connect");
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            connections.add(connection);
        }
    }
    //lazy-initialization with thread-safe and high performance - using double checked block with synchronized
    public static ConnectionPool getInstance() {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionPool.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionPool(1);
                }
            }
        }
        return localInstance;
    }
    public Connection getConnection() {
        while (connections.size() == 0) {
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return connections.remove(connections.size() - 1);
    }
    public void releaseConnection(Connection connection) {
        if (connections.size() < this.poolNumbers) {
            connections.add(connection);
        }
        synchronized (this) {
            notify();
        }
    }
}
