package by.bsuir.wtl2.webapp.classes.controller.commands;

import by.bsuir.wtl2.webapp.classes.controller.logic.ICommand;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageName;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageNames;
import by.bsuir.wtl2.webapp.classes.entities.Admin;
import by.bsuir.wtl2.webapp.classes.entities.Client;
import by.bsuir.wtl2.webapp.classes.exceptions.ServiceException;
import by.bsuir.wtl2.webapp.classes.service.AdminService;
import by.bsuir.wtl2.webapp.classes.service.ClientService;
import by.bsuir.wtl2.webapp.classes.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;

public class RegisterCommand implements ICommand {

    private final static Logger logger = Logger.getLogger(RegisterCommand.class.getName());

    @Override
    public PageName completeCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        PageName resultRedirectPage = PageNames.MAIN_PAGE;;
        try {
            Map<String,String[]> params = request.getParameterMap();
            Admin admin = null;
            Client client = null;
            if(request.getParameter("role").equals("client")){
                client = new Client();
                client.setName(params.get("name")[0]);
                client.setSurname(params.get("surname")[0]);
                client.setPhoneNumber(params.get("phone")[0]);
                client.setEmail(params.get("email")[0]);
                client.setLogin(params.get("login")[0]);
                client.setPassword(params.get("password")[0]);
                client.setRegistrationDate(new Date(System.currentTimeMillis()));
                client.setBanned(false);

            } else {
                admin = new Admin();
                admin.setName(params.get("name")[0]);
                admin.setSurname(params.get("surname")[0]);
                admin.setPhoneNumber(params.get("phone")[0]);
                admin.setEmail(params.get("email")[0]);
                admin.setLogin(params.get("login")[0]);
                admin.setPassword(params.get("password")[0]);
                admin.setRegistrationDate(new Date(System.currentTimeMillis()));
                admin.setActive(true);
            }

            UserService userService = new UserService();
            boolean loginAlreadyExist = false;
            boolean phoneAlreadyExist = false;
            boolean emailAlreadyExist = false;
            if(client != null) {
                loginAlreadyExist = userService.checkUserLogin(client);
                phoneAlreadyExist = userService.checkUserPhoneNumber(client);
                emailAlreadyExist = userService.checkUserEmail(client);
            } else if(admin != null){
                loginAlreadyExist = userService.checkUserLogin(admin);
                phoneAlreadyExist = userService.checkUserPhoneNumber(admin);
                emailAlreadyExist = userService.checkUserEmail(admin);
            } else {
                request.getSession().setAttribute("input_error","Unknown registration error");
                resultRedirectPage = PageNames.ERROR_PAGE;
                return resultRedirectPage;
            }
            if(!loginAlreadyExist && !phoneAlreadyExist && !emailAlreadyExist) {
                if(client != null){
                    ClientService clientService = new ClientService();
                    clientService.registerClient(client);
                    request.getSession().setAttribute("log", true);
                    request.getSession().setAttribute("login", client.getLogin());
                    request.getSession().setAttribute("password_hash",
                            client.getPassword());
                    request.getSession().setAttribute("role","client");
                } else {
                    AdminService adminService = new AdminService();
                    adminService.registerAdmin(admin);
                    request.getSession().setAttribute("log", true);
                    request.getSession().setAttribute("login", admin.getLogin());
                    request.getSession().setAttribute("password_hash",
                            admin.getPassword());
                    request.getSession().setAttribute("role","admin");
                }
            } else {
                if(loginAlreadyExist){
                    resultRedirectPage = PageNames.ERROR_PAGE;
                    return resultRedirectPage;
                } else if(emailAlreadyExist){
                    resultRedirectPage = PageNames.ERROR_PAGE;
                    return resultRedirectPage;
                } else {
                    resultRedirectPage = PageNames.ERROR_PAGE;
                    return resultRedirectPage;
                }
            }
            return resultRedirectPage;
        } catch (Exception e){
            logger.log(Level.ERROR,"Error while register client",e);
            resultRedirectPage = PageNames.ERROR_PAGE;
            return resultRedirectPage;
        }
    }


}
