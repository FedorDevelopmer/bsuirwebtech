package by.bsuir.wtl2.webapp.classes.service;

import by.bsuir.wtl2.webapp.classes.dao.objects.AdminDao;
import by.bsuir.wtl2.webapp.classes.dao.objects.UserDao;
import by.bsuir.wtl2.webapp.classes.entities.Admin;
import by.bsuir.wtl2.webapp.classes.entities.Client;
import by.bsuir.wtl2.webapp.classes.entities.User;
import by.bsuir.wtl2.webapp.classes.exceptions.DaoException;
import by.bsuir.wtl2.webapp.classes.exceptions.ServiceException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Date;


public class UserService {
    public boolean registerUser(User user) throws ServiceException {
        try {
            String hashNumber = PasswordHashing.generatePasswordHash(user.getPassword());
            user.setPassword(hashNumber);
            UserDao userDao = new UserDao();
            List<String> attributes =  UserDao.userAttributes();
            Map<String, Object> params = UserDao.userParams(user);
            attributes.remove("u_id");
            params.remove("u_id");
            userDao.addUser(attributes, params);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
        return true;
    }

    public boolean updateUser(User originalUser, User updatedUser) throws ServiceException {
        try {
            UserDao userDao = new UserDao();
            List<String> attributes = UserDao.userAttributes();
            Map<String, Object> oldParams = UserDao.userParams(originalUser);
            Map<String, Object> newParams = UserDao.userParams(updatedUser);
            userDao.updateUser(attributes, oldParams, attributes, newParams);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
        return true;
    }

    public boolean checkUserLogin(User user) throws ServiceException{
        try {
            UserDao userDao = new UserDao();
            List<String> attributes = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();
            attributes.add("u_login");
            params.put("u_login", user.getLogin());
            userDao.getUser("*", attributes, params);
            Object existingUserId = userDao.getUserSelectionResult("u_id");
            return existingUserId != null;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public boolean checkUserEmail(User user) throws ServiceException{
        try {
            UserDao userDao = new UserDao();
            List<String> attributes = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();
            attributes.add("u_email");
            params.put("u_email", user.getEmail());
            userDao.getUser("*", attributes, params);
            Object existingUserId = userDao.getUserSelectionResult("u_id");
            return existingUserId != null;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }
    public boolean checkUserPhoneNumber(User user) throws ServiceException{
        try {
            UserDao userDao = new UserDao();
            List<String> attributes = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();
            attributes.add("u_phone_num");
            params.put("u_phone_num", user.getPhoneNumber());
            userDao.getUser("*", attributes, params);
            Object existingUserId = userDao.getUserSelectionResult("u_id");
            return existingUserId != null;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public static void fillUserWithParams(User user, Map<String,Object> params) {
        user.setId(Integer.parseInt(String.valueOf(params.get("u_id"))));
        user.setName(String.valueOf(params.get("u_name")));
        user.setSurname(String.valueOf(params.get("u_surname")));
        user.setPhoneNumber(String.valueOf(params.get("u_phone_num")));
        user.setEmail(String.valueOf(params.get("u_email")));
        user.setLogin(String.valueOf(params.get("u_login")));
        user.setPassword(String.valueOf(params.get("u_pass_hash")));
        String dateString = String.valueOf(params.get("u_reg_date"));
        Date date = Date.valueOf(dateString);
        user.setRegistrationDate(date);
    }
}
