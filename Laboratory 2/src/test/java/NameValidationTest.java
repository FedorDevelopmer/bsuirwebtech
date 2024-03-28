import by.bsuir.wtl2.webapp.classes.validation.IValidator;
import by.bsuir.wtl2.webapp.classes.validation.ValidatorHandler;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class NameValidationTest {

    private static final IValidator nameValidator = ValidatorHandler
            .getInstance().getValidatorByName("name_validator");

    @Test
    public void testValidNames() {
        String[] validNames = {
                "John Doe",
                "Alice",
                "Robert Smith",
                "Mary Ann Johnson"
        };
        for (String name : validNames) {
            Assertions.assertTrue(nameValidator.validate(name), "Valid name failed: " + name);
        }


    }
    @Test
    public void testInvalidNames(){
        String[] invalidNames = {
                "John%%%",
                "Jane@Doe",
                "Sam!",
                "$123456"
        };
        for (String name : invalidNames) {
            Assertions.assertFalse(nameValidator.validate(name), "Invalid name passed: " + name);
        }
    }
}
