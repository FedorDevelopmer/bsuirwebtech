package by.bsuir.wtl2.webapp.classes.validation.validators;

import by.bsuir.wtl2.webapp.classes.validation.IValidator;

public class PhoneNumberValidator implements IValidator {
    @Override
    public boolean validate(String value) {
        boolean valid;
        valid = value.matches("^\\+[0-9]{10,}$");
        return valid;
    }
}
