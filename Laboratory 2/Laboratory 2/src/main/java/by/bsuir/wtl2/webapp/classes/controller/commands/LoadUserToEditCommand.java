package by.bsuir.wtl2.webapp.classes.controller.commands;

import by.bsuir.wtl2.webapp.classes.controller.logic.ICommand;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageName;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageNames;
import by.bsuir.wtl2.webapp.classes.entities.Admin;
import by.bsuir.wtl2.webapp.classes.entities.Client;
import by.bsuir.wtl2.webapp.classes.exceptions.ServiceException;
import by.bsuir.wtl2.webapp.classes.service.AdminService;
import by.bsuir.wtl2.webapp.classes.service.ClientService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoadUserToEditCommand implements ICommand{

    private static final Logger logger = Logger.getLogger(LoadOrdersCommand.class.getName());

    @Override
    public PageName completeCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        PageName resultRedirectPage = PageNames.USER_EDIT;
        try {
            AdminService adminService = new AdminService();
            ClientService clientService = new ClientService();
            Admin admin;
            Client client;
            HttpSession session = request.getSession();
            if (session.getAttribute("role").equals("admin")) {
                admin = adminService.loginAdmin(String.valueOf(session.getAttribute("login")),
                        String.valueOf(session.getAttribute("password_hash")), true);
                session.setAttribute("user",admin);
            } else {
                client = clientService.loginClient(String.valueOf(session.getAttribute("login")),
                        String.valueOf(session.getAttribute("password_hash")), true);
                session.setAttribute("user",client);
            }
            request.getSession().setAttribute("input_error",null);
            return resultRedirectPage;
        }catch (Exception e){
            logger.log(Level.ERROR,"Error while getting editable user info",e);
            resultRedirectPage = PageNames.ERROR_PAGE;
            return resultRedirectPage;
        }
    }
}
