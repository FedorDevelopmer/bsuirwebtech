package by.bsuir.wtl2.webapp.classes.validation.validators;

import by.bsuir.wtl2.webapp.classes.validation.IValidator;

public class NameValidator implements IValidator {
    @Override
    public boolean validate(String value) {
        return value.matches("[A-Za-z\\\\s_0-9]+");
    }
}
