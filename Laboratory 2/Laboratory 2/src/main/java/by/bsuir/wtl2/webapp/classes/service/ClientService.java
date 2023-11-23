package by.bsuir.wtl2.webapp.classes.service;

import by.bsuir.wtl2.webapp.classes.dao.objects.AdminDao;
import by.bsuir.wtl2.webapp.classes.dao.objects.ClientDao;
import by.bsuir.wtl2.webapp.classes.dao.objects.UserDao;
import by.bsuir.wtl2.webapp.classes.entities.Admin;
import by.bsuir.wtl2.webapp.classes.entities.Client;
import by.bsuir.wtl2.webapp.classes.entities.Order;
import by.bsuir.wtl2.webapp.classes.exceptions.DaoException;
import by.bsuir.wtl2.webapp.classes.exceptions.ServiceException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientService {
    public boolean registerClient(Client client) throws ServiceException{
        try {
            UserService userService = new UserService();
            ClientDao clientDao = new ClientDao();
            UserDao userDao = new UserDao();
            boolean userCreated = userService.registerUser(client);
            if (userCreated) {
                List<String> attributes = ClientDao.clientAttributes();
                Map<String, Object> params = ClientDao.clientParams(client);
                List<String> userAttributes = UserDao.userAttributes();
                Map<String, Object> userParams = UserDao.userParams(client);
                userAttributes.remove("u_id");
                userParams.remove("u_id");
                userDao.getUser("u_id", userAttributes,
                       userParams);
                String userId = userDao.getUserById();
                params.put("cl_id", userId);
                clientDao.addClient(attributes, params);
            } else {
                throw new ServiceException("Error occurred during registration");
            }
        } catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
        return true;
    }
    public Client loginClient(String login,String password,boolean passwordIsHashed) throws ServiceException {
        try {
            ClientDao clientDao = new ClientDao();
            UserDao userDao = new UserDao();
            List<String> attributes = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();
            attributes.add("u_login");
            params.put("u_login", login);
            attributes.add("u_pass_hash");
            String passwordHash = PasswordHashing.generatePasswordHash(password);
            if(passwordIsHashed) {
                params.put("u_pass_hash", password);
            } else {
                params.put("u_pass_hash", passwordHash);
            }
            userDao.getUser("*", attributes, params);
            Object userParams = userDao.getUserSelectionResult("u_id");
            attributes.clear();
            params.clear();
            attributes.add("cl_id");
            params.put("cl_id", userParams);
            clientDao.getClient("*", attributes, params);
            Object clientParams = clientDao.getClientSelectionResult("cl_id");
            if(clientParams == null) {
                return null;
            } else {
                Client client = new Client();
                Map<String,Object> allUserParams=userDao
                        .getUserSelectionResult(UserDao.userAttributes());
                allUserParams.putAll(clientDao
                        .getClientSelectionResult(ClientDao.clientAttributes()));
                fillClientWithParams(client,allUserParams);
                return client;
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }
    public boolean updateClient(Client originalClient,Client updatedClient) throws ServiceException {
        try {
            UserService userService = new UserService();
            AdminDao adminDao = new AdminDao();
            boolean userUpdated = userService.updateUser(originalClient,updatedClient);
            if(userUpdated) {
                List<String> attributes = ClientDao.clientAttributes();
                Map<String, Object> oldParams = ClientDao.clientParams(originalClient);
                Map<String, Object> newParams = ClientDao.clientParams(updatedClient);
                adminDao.updateAdmin(attributes, oldParams,attributes,newParams);
            } else {
                throw new ServiceException("Error occurred during admin update");
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
        return true;
    }

    public Client getClientById(int id) throws ServiceException{
        Client client = new Client();
        try {
            ClientDao clientDao = new ClientDao();
            UserDao userDao = new UserDao();
            List<String> attributes = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();
            attributes.add("u_id");
            params.put("u_id", id);
            userDao.getUser("*", attributes, params);
            attributes.clear();
            params.clear();
            attributes.add("cl_id");
            params.put("cl_id", id);
            clientDao.getClient("*", attributes, params);
            attributes.add("cl_is_banned");
            params.put("cl_is_banned", 0);
            Map<String,Object> clientAttributes = clientDao.getClientSelectionResult(attributes);
            clientAttributes.putAll(userDao.getUserSelectionResult(UserDao.userAttributes()));
            if(clientAttributes.isEmpty()){
                throw new ServiceException("Role error: provided user isn't client");
            } else {
                return client;
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }



    public void fillClientWithParams(Client client,Map<String,Object> params) {
        UserService.fillUserWithParams(client,params);
        client.setId(Integer.parseInt(String.valueOf(params.get("cl_id"))));
        client.setBanned(String.valueOf(params.get("cl_is_banned")).equals("true"));
    }
}
