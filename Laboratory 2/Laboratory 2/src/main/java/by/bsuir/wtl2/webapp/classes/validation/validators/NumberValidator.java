package by.bsuir.wtl2.webapp.classes.validation.validators;

import by.bsuir.wtl2.webapp.classes.validation.IValidator;

/**
 * The NumberValidator class implements the IValidator interface and provides a method for number validation.
 *
 * @version 1.0
 * @author Fedor
 * @since 2023-11-29
 */
public class NumberValidator implements IValidator {
    @Override
    public boolean validate(String value) {
        boolean valid;
        valid = value.matches("^\\d+(\\.\\d+)?$");
        return valid;
    }
}
