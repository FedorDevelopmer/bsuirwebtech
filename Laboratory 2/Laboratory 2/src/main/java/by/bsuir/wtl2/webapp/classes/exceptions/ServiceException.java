package by.bsuir.wtl2.webapp.classes.exceptions;

/**
 * The ServiceException class represents an exception that occurs in the service layer.
 * It extends the DaoException class.
 *
 * @version 1.0
 * @author Fedor
 * @since 2023-11-29
 */
public class ServiceException extends DaoException {
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message,Throwable packedException) {
        super(message,packedException);
    }
}
