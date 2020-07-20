package by.epam.krein.abitapp.ConnectionPool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    //private static Logger logger = Logger.getRootLogger();

    private Connector() {
    }

    public static Connection getConnection() {

        DataPropertiesForConnection data = DataPropertiesForConnectFactory.getInstance();
        Connection connection = null;

        try {

            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            Class.forName("com.mysql.cj.jdbc.Driver"); //нужен ли он ??

            connection = BasicConnectionPool.create(
                    data.getUrl(),
                    data.getUser(),
                    data.getPassword()).getConnection();

        } catch (SQLException e) {
            //logger.error(e.getMessage());
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
