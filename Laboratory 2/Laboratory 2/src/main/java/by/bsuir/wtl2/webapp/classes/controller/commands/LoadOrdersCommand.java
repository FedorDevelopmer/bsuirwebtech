package by.bsuir.wtl2.webapp.classes.controller.commands;

import by.bsuir.wtl2.webapp.classes.controller.logic.ICommand;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageName;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageNames;
import by.bsuir.wtl2.webapp.classes.entities.Order;
import by.bsuir.wtl2.webapp.classes.exceptions.ServiceException;
import by.bsuir.wtl2.webapp.classes.service.OrderService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LoadOrdersCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(LoadOrdersCommand.class.getName());

    @Override
    public PageName completeCommand(HttpServletRequest request, HttpServletResponse response,ServletContext context) throws ServletException, IOException {
        PageName resultRedirectPage = PageNames.ORDER_ACCEPT;
        try{
            OrderService orderService = new OrderService();
            List<Order> orders = orderService.getAllOrders(0);
            context.setAttribute("orders",orders);
            context.setAttribute("orders_offset",0);
            return resultRedirectPage;
        } catch (Exception e) {
            logger.log(Level.ERROR,"Error while loading orders for accept page",e);
            resultRedirectPage = PageNames.ERROR_PAGE;
            return resultRedirectPage;
        }
    }
}
