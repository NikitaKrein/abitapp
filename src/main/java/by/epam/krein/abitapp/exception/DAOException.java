package by.epam.krein.abitapp.exception;

public class DAOException extends RuntimeException{
    public DAOException(String message, Throwable exception){
        super(message, exception);
    }
}
