package by.bsuir.wtl2.webapp.classes.exceptions;

import java.sql.SQLException;

/**
 * The DaoException class represents an exception that occurs in the DAO (Data Access Object) layer.
 * It extends the SQLException class.
 *
 * @version 1.0
 * @author Fedor
 * @since 2023-11-29
 */
public class DaoException extends SQLException {

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message,Throwable packedException) {
        super(message,packedException);
    }

}
