import by.bsuir.wtl2.webapp.classes.exceptions.ServiceException;
import by.bsuir.wtl2.webapp.classes.service.PasswordHashing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import by.bsuir.wtl2.webapp.classes.validation.IValidator;
import by.bsuir.wtl2.webapp.classes.validation.ValidatorHandler;

import static org.junit.Assert.assertTrue;

public class PasswordHashingTest {
    @Test
    public void testGeneratePasswordHash() {
        // Test cases
        String[] passwords = {
                "password123",
                "admin",
                "123456",
                "password",
                "test",
                "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08"
        };

        String[] expectedHashes = {
                "ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f",
                "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918",
                "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92",
                "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8",
                "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08",
                "7b3d979ca8330a94fa7e9e1b466d8b99e0bcdea1ec90596c0dcc8d7ef6b4300c"
        };

        for (int i = 0; i < passwords.length; i++) {
            String password = passwords[i];
            String expectedHash = expectedHashes[i];

            try {
                String hash = PasswordHashing.generatePasswordHash(password);
                Assertions.assertEquals(expectedHash, hash);
            } catch (ServiceException e) {
                Assertions.fail("Error in password hashing: " + e.getMessage());
            }
        }
    }
}
