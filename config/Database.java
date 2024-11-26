package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final String dbName;
    private final String userName;
    private final String password;
    private final String host;
    private final String port;
    private Connection connection;


    public Database(String dbName, String userName, String password, String host, String port) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.port = port;
    }


    public void setup() {
        String mysqlConnUrlTemplate = "jdbc:mysql://%s:%s/%s";
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection
            connection = DriverManager.getConnection(
                    String.format(mysqlConnUrlTemplate, host, port, dbName),
                    userName,
                    password
            );
            System.out.println("Database connected!");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database!");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public Connection getConnection() {
        return connection;
    }
}
