package by.epam.krein.abitapp.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private ConnectionPool() {

    }

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance(){
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            Context context;
            Class.forName("com.mysql.jdbc.Driver");
            context = new InitialContext();
            DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/abitapppool");
            connection = dataSource.getConnection();
        } catch (NamingException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
