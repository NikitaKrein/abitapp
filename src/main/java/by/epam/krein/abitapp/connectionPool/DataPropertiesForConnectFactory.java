package by.epam.krein.abitapp.connectionPool;

public class DataPropertiesForConnectFactory {

    private static final DataPropertiesForConnection INSTANCE = new DataPropertiesForConnection();

    private DataPropertiesForConnectFactory() {
    }

    public static DataPropertiesForConnection getInstance() {
        return INSTANCE;
    }
}
