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

public class DeleteOrderCommand implements ICommand {
    private static final Logger logger = Logger.getLogger(DeleteOrderCommand.class.getName());

    @Override
    public PageName completeCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        PageName resultRedirectPage = PageNames.MAIN_PAGE;
        try {
            OrderService orderService = new OrderService();
            int ordersOffset = Integer.parseInt(String
                    .valueOf(context.getAttribute("orders_offset")));
            List<Order> currentOrderPage = orderService.getAllOrders(ordersOffset);
            orderService.deleteOrder(currentOrderPage.get(Integer
                    .parseInt(request.getParameter("orderId"))));
            request.getSession().setAttribute("changed_orders",null);
            context.setAttribute("orders",null);
            context.setAttribute("orders_offset",null);
            return resultRedirectPage;
        }catch (Exception e){
            resultRedirectPage=PageNames.ERROR_PAGE;
            logger.log(Level.ERROR,"Error while deleting order",e);
            return resultRedirectPage;
        }
    }
}