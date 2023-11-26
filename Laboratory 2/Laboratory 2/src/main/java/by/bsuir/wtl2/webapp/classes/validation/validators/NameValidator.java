package by.bsuir.wtl2.webapp.classes.validation.validators;

import by.bsuir.wtl2.webapp.classes.validation.IValidator;

public class NameValidator implements IValidator {
    @Override
    public boolean validate(String value) {
        boolean valid;
        valid = value.matches("[A-Za-z\\\\s_0-9]+");
        return valid;
    }
}
