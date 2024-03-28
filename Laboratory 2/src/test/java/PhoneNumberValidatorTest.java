import by.bsuir.wtl2.webapp.classes.validation.IValidator;
import by.bsuir.wtl2.webapp.classes.validation.ValidatorHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class PhoneNumberValidatorTest {
    private static final IValidator phoneValidator = ValidatorHandler
            .getInstance().getValidatorByName("phone_validator");

    @Test
    public void testValidPhoneNumbers() {
        String[] validPhoneNumbers = {
                "+1234567890",
                "+9876543210987",
                "+01234567891234"
        };

        for (String phoneNumber : validPhoneNumbers) {
            Assertions.assertTrue(phoneValidator.validate(phoneNumber), "Valid phone number failed: " + phoneNumber);
        }
    }

    @Test
    public void testInvalidPhoneNumbers() {
        String[] invalidPhoneNumbers = {
                "1234567890",
                "+98765",
                "+0123456789a",
                "+123 456 7890"
        };

        for (String phoneNumber : invalidPhoneNumbers) {
            Assertions.assertFalse(phoneValidator.validate(phoneNumber), "Invalid phone number passed: " + phoneNumber);
        }
    }
}
