package by.bsuir.wtl2.webapp.classes.controller.commands;

import by.bsuir.wtl2.webapp.classes.controller.logic.ICommand;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageName;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageNames;
import by.bsuir.wtl2.webapp.classes.entities.User;
import by.bsuir.wtl2.webapp.classes.exceptions.ServiceException;
import by.bsuir.wtl2.webapp.classes.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class UserUpdateCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(UserUpdateCommand.class.getName());

    @Override
    public PageName completeCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        PageName resultRedirectPage = PageNames.MAIN_PAGE;
        try{
            HttpSession session = request.getSession();
            UserService userService = new UserService();
            User updatedUser = new User();
            Map<String, String[]> requestParams = request.getParameterMap();
            Map<String, Object> params = new HashMap<>();
            for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
                params.put(entry.getKey(), new String(entry.getValue()[0].getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8));
            }
            User oldUser = (User) session.getAttribute("user");
            params.put("u_id",oldUser.getId());
            params.put("u_reg_date",oldUser.getRegistrationDate());
            params.put("u_pass_hash",oldUser.getPassword());
            UserService.fillUserWithParams(updatedUser, params);
            userService.updateUser(oldUser, updatedUser);
            session.setAttribute("login",updatedUser.getLogin());
            session.setAttribute("user",null);
            return resultRedirectPage;
        } catch (ServiceException e) {
            logger.log(Level.ERROR,"Error while updating user info",e);
            resultRedirectPage=PageNames.ERROR_PAGE;
            return resultRedirectPage;

        }
    }
}
