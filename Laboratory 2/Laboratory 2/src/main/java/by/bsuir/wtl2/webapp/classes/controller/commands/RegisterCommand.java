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
import by.bsuir.wtl2.webapp.classes.validation.ValidatorHandler;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterCommand implements ICommand {

    private final static Logger logger = Logger.getLogger(RegisterCommand.class.getName());

    @Override
    public PageName completeCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        PageName resultRedirectPage = PageNames.MAIN_PAGE;;
        boolean valid;
        try {
            Map<String,String[]> requestParams = request.getParameterMap();
            Map<String,Object> params = new HashMap<>();
            Admin admin = null;
            Client client = null;
            for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
                params.put(entry.getKey(), new String(entry.getValue()[0].getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8));
            }
            if(request.getParameter("role").equals("client")){
                client = new Client();
                client.setName(String.valueOf(params.get("name")));
                client.setSurname(String.valueOf(params.get("surname")));
                client.setPhoneNumber(String.valueOf(params.get("phone")));
                client.setEmail(String.valueOf(params.get("email")));
                client.setLogin(String.valueOf(params.get("login")));
                client.setPassword(String.valueOf(params.get("password")));
                client.setRegistrationDate(new Date(System.currentTimeMillis()));
                client.setBanned(false);

            } else {
                admin = new Admin();
                admin.setName(String.valueOf(params.get("name")));
                admin.setSurname(String.valueOf(params.get("surname")));
                admin.setPhoneNumber(String.valueOf(params.get("phone")));
                admin.setEmail(String.valueOf(params.get("email")));
                admin.setLogin(String.valueOf(params.get("login")));
                admin.setPassword(String.valueOf(params.get("password")));
                admin.setRegistrationDate(new Date(System.currentTimeMillis()));
                admin.setActive(true);
            }
            valid = applyValidation(params);
            if(valid) {
                UserService userService = new UserService();
                boolean loginAlreadyExist = false;
                boolean phoneAlreadyExist = false;
                boolean emailAlreadyExist = false;
                if (client != null) {
                    loginAlreadyExist = userService.checkUserLogin(client);
                    phoneAlreadyExist = userService.checkUserPhoneNumber(client);
                    emailAlreadyExist = userService.checkUserEmail(client);
                } else if (admin != null) {
                    loginAlreadyExist = userService.checkUserLogin(admin);
                    phoneAlreadyExist = userService.checkUserPhoneNumber(admin);
                    emailAlreadyExist = userService.checkUserEmail(admin);
                } else {
                    request.getSession().setAttribute("input_error", "Unknown registration error");
                    resultRedirectPage = PageNames.ERROR_PAGE;
                    return resultRedirectPage;
                }
                if (!loginAlreadyExist && !phoneAlreadyExist && !emailAlreadyExist) {
                    if (client != null) {
                        ClientService clientService = new ClientService();
                        clientService.registerClient(client);
                        request.getSession().setAttribute("log", true);
                        request.getSession().setAttribute("login", client.getLogin());
                        request.getSession().setAttribute("password_hash",
                                client.getPassword());
                        request.getSession().setAttribute("role", "client");
                    } else {
                        AdminService adminService = new AdminService();
                        adminService.registerAdmin(admin);
                        request.getSession().setAttribute("log", true);
                        request.getSession().setAttribute("login", admin.getLogin());
                        request.getSession().setAttribute("password_hash",
                                admin.getPassword());
                        request.getSession().setAttribute("role", "admin");
                    }
                } else {
                    if (loginAlreadyExist) {
                        request.getSession().setAttribute("login_error",true);
                        return resultRedirectPage;
                    } else if (emailAlreadyExist) {
                        request.getSession().setAttribute("email_error",true);
                        return resultRedirectPage;
                    } else {
                        request.getSession().setAttribute("phone_error",true);
                        return resultRedirectPage;
                    }
                }
            } else {
                request.getSession().setAttribute("input_error",true);
            }
            return resultRedirectPage;
        } catch (Exception e){
            logger.log(Level.ERROR,"Error while register client",e);
            resultRedirectPage = PageNames.ERROR_PAGE;
            return resultRedirectPage;
        }
    }

    private boolean applyValidation(Map<String,Object> params){
        boolean valid;
        ValidatorHandler validator = ValidatorHandler.getInstance();
        valid = validator.getValidatorByName("name_validator").validate(String.valueOf(params.get("name")))
                && validator.getValidatorByName("name_validator").validate(String.valueOf(params.get("surname")))
                && validator.getValidatorByName("phone_validator").validate(String.valueOf(params.get("phone")))
                && validator.getValidatorByName("email_validator").validate(String.valueOf(params.get("email")))
                && validator.getValidatorByName("name_validator").validate(String.valueOf(params.get("login")))
                && validator.getValidatorByName("text_validator").validate(String.valueOf(params.get("password")));
        return valid;
    }


}
