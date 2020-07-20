package by.epam.krein.abitapp.ConnectionPool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataPropertiesForConnection {

    private Properties properties;

//    private Logger logger = LogManager.getRootLogger();

    public DataPropertiesForConnection() {
        properties = new Properties();
        readProperties();
    }

    public String getDriver(){
        return properties.getProperty("driver");
    }

    public String getUrl() {
        return properties.getProperty("url");
    }

    public String getUser() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    private void readProperties() {

        try (InputStream input = getClass().getResourceAsStream("/jdbc.properties")) {

            properties.load(input);

        } catch (IOException e) {
            //logger.error(e.getMessage());
        }
    }
}
