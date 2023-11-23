package by.bsuir.wtl2.webapp.classes.controller.commands;

import by.bsuir.wtl2.webapp.classes.controller.logic.ICommand;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageName;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageNames;
import by.bsuir.wtl2.webapp.classes.entities.Course;
import by.bsuir.wtl2.webapp.classes.entities.Order;
import by.bsuir.wtl2.webapp.classes.entities.User;
import by.bsuir.wtl2.webapp.classes.exceptions.ServiceException;
import by.bsuir.wtl2.webapp.classes.service.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

public class CreateOrderCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(CreateOrderCommand.class.getName());

    @Override
    public PageName completeCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        PageName resultRedirectPage = PageNames.MAIN_PAGE;
        try{
            HttpSession session = request.getSession();
            ClientService clientService = new ClientService();
            AdminService adminService = new AdminService();
            OrderService orderService = new OrderService();
            Cart userCart = (Cart)session.getAttribute("cart");
            User user;
            if(session.getAttribute("role").equals("admin")){
                String login = String.valueOf(session.getAttribute("login"));
                String password= String.valueOf(session.getAttribute("password_hash"));
                user = adminService.loginAdmin(login,password,true);
            } else {
                String login = String.valueOf(session.getAttribute("login"));
                String password= String.valueOf(session.getAttribute("password_hash"));
                user = clientService.loginClient(login,password,true);
            }
            LinkTablesService linkTablesService = new LinkTablesService();
            Order clientOrder = new Order();
            clientOrder.setAccepted(false);
            clientOrder.setCreationDate(new Date(System.currentTimeMillis()));
            double summaryPrice = 0;
            for(Course course : userCart.getAll()){
                summaryPrice += course.getPrice();
            }
            clientOrder.setSummaryPrice(summaryPrice);
            orderService.createOrder(clientOrder);
            for(Course course : userCart.getAll()){
                linkTablesService.createLinkOrderCourse(course,clientOrder);
            }
            linkTablesService.createLinkUserOrder(user,clientOrder);
            session.setAttribute("cart",null);
            return resultRedirectPage;
        } catch (Exception e){
            resultRedirectPage = PageNames.ERROR_PAGE;
            logger.log(Level.ERROR,"Error in creating user order",e);
            return resultRedirectPage;
        }
    }
}
