package by.bsuir.wtl3.service;

import by.bsuir.wtl3.dao.AdminDao;
import by.bsuir.wtl3.dao.ClientDao;
import by.bsuir.wtl3.entities.Client;
import by.bsuir.wtl3.entities.User;
import by.bsuir.wtl3.exceptions.DaoException;
import by.bsuir.wtl3.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class ClientService {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private UserService userService;


    public void registerClient(User user) throws ServiceException {
        try {
            String hashedPassword = PasswordHashing.generatePasswordHash(user.getPassword());
            user.setPassword(hashedPassword);
            userService.createUser(user);
            User createdUser = userService.findUserByLogin(user.getLogin());
            Client client = new Client(createdUser, false);
            clientDao.add(client);
        } catch (NoSuchAlgorithmException | DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public Client loginClient(User user) throws ServiceException{
        try {
            User logInUser = userService.findUserByLoginAndPassword(user.getLogin(),user.getPassword());
            if(logInUser == null){
                return null;
            } else {
                Client logInClient = clientDao.getById(logInUser.getId());
                return logInClient;
            }
        } catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
