import by.bsuir.wtl2.webapp.classes.validation.IValidator;
import by.bsuir.wtl2.webapp.classes.validation.ValidatorHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class TextValidationTest {

    private static final IValidator textValidator = ValidatorHandler
            .getInstance().getValidatorByName("text_validator");

    @Test
    public void testValidStrings() {
        String[] validStrings = {
                "Hello World!",
                "12345",
                "Testing, 1, 2, 3",
                "Special Characters: #, \", №",
                "This is a sentence. It has punctuation marks, like commas and periods."
        };

        for (String str : validStrings) {
            Assertions.assertTrue(textValidator.validate(str), "Valid string failed: " + str);
        }
    }

    @Test
    public void testInvalidStrings() {
        String[] invalidStrings = {
                "Hello World123//",
                "Test\\!",
                "This is an invalid string \\/",
                "Testing; invalid strin}g",
                "Invalid characters: ~, `, \\"
        };

        for (String str : invalidStrings) {
            Assertions.assertFalse(textValidator.validate(str), "Invalid string passed: " + str);
        }
    }
}
