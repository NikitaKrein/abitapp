package by.epam.krein.abitapp.connectionPool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static ConnectionPool connectionPool;

    static {
        DataPropertiesForConnection data = DataPropertiesForConnectFactory.getInstance();
        try {

            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            Class.forName("com.mysql.cj.jdbc.Driver");
            connectionPool = BasicConnectionPool.create(
                    data.getUrl(),
                    data.getUser(),
                    data.getPassword());
        } catch (SQLException e) {
            //logger.error(e.getMessage());
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connectionPool.getConnection();
    }

    public static void releaseConnection(Connection connection) {
        connectionPool.releaseConnection(connection);
    }
}