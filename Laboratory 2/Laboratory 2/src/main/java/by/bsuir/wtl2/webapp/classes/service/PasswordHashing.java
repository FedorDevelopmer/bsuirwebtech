package by.bsuir.wtl2.webapp.classes.service;

import by.bsuir.wtl2.webapp.classes.exceptions.DaoException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {
    public static String generatePasswordHash(String password) throws DaoException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(String.valueOf(password).getBytes());
            int hashNumber = 0;
            for (byte hashByte : hash) {
                hashNumber += hashByte;
                hashNumber <<= 1;
            }
            return String.valueOf(hashNumber);
        } catch (NoSuchAlgorithmException e) {
            throw new DaoException("Error in password hashing");
        }
    }
}
