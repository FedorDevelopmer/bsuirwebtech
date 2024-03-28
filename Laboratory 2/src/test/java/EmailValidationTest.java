import by.bsuir.wtl2.webapp.classes.validation.IValidator;
import by.bsuir.wtl2.webapp.classes.validation.ValidatorHandler;
import org.junit.Test;
import static org.junit.Assert.*;
public class EmailValidationTest {

    private static final IValidator emailValidator = ValidatorHandler
            .getInstance().getValidatorByName("email_validator");

    @Test
    public void testValidEmails() {

        String[] validEmails = {
                "example123@example.com",
                "test.email@example.co",
                "firstname.lastname@example.com",
                "user+123@example.co.in",
                "user-xyz@example-domain.com"
        };

        for (String email : validEmails) {
            assertTrue(emailValidator.validate(email));
        }
    }

    @Test
    public void testInvalidEmails() {
        String[] invalidEmails = {
                "example@.com",
                "user@example..com",
                "@example.com",
                "user@example_com",
                "user@-example.com"
        };

        for (String email : invalidEmails) {
            assertFalse(emailValidator.validate(email));
        }
    }

    @Test
    public void testEmptyEmail() {
        String emptyEmail = "";
        assertFalse(emailValidator.validate(emptyEmail));
    }
}
