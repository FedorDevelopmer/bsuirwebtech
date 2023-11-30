package by.bsuir.wtl2.webapp.classes.validation.validators;

import by.bsuir.wtl2.webapp.classes.validation.IValidator;

/**
 * The TextValidator class implements the IValidator interface and provides a method for text validation.
 *
 * @version 1.0
 * @author Fedor
 * @since 2023-11-29
 */
public class TextValidator implements IValidator {
    @Override
    public boolean validate(String value) {
        boolean valid;
        valid = value.matches("[A-Za-z\\s_0-9,+\\-:;?!.()\"&*#%№]+");
        return valid;
    }
}
