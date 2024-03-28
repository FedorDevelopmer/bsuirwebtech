import by.bsuir.wtl2.webapp.classes.validation.IValidator;
import by.bsuir.wtl2.webapp.classes.validation.ValidatorHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NumberValidatorTest {
    private static final IValidator numberValidator = ValidatorHandler
            .getInstance().getValidatorByName("price_validator");

    @Test
    public void testValidNumbers() {
        String[] validNumbers = {
                "123",
                "456789",
                "987654321",
                "1.23",
                "999999"
        };

        for (String number : validNumbers) {
            Assertions.assertTrue(numberValidator.validate(number), "Valid number failed: " + number);
        }
    }

    @Test
    public void testInvalidNumbers() {
        String[] invalidNumbers = {
                "abc",
                "123abc",
                "1..23",
                "-456",
                "12 34"
        };

        for (String number : invalidNumbers) {
            Assertions.assertFalse(numberValidator.validate(number), "Invalid number passed: " + number);
        }
    }
}
