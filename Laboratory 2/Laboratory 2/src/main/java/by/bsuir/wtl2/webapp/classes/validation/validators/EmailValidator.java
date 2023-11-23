package by.bsuir.wtl2.webapp.classes.validation.validators;

import by.bsuir.wtl2.webapp.classes.validation.IValidator;

public class EmailValidator implements IValidator {
    @Override
    public boolean validate(String value) {
        return value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}
