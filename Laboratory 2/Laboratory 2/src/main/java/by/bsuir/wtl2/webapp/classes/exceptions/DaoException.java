package by.bsuir.wtl2.webapp.classes.exceptions;

import java.sql.SQLException;

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
