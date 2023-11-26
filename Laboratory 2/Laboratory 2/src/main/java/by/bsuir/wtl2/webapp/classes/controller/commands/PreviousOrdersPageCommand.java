package by.bsuir.wtl2.webapp.classes.controller.commands;

import by.bsuir.wtl2.webapp.classes.controller.logic.ICommand;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageName;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageNames;
import by.bsuir.wtl2.webapp.classes.entities.Order;
import by.bsuir.wtl2.webapp.classes.service.OrderService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class PreviousOrdersPageCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(PreviousOrdersPageCommand.class.getName());

    @Override
    public PageName completeCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        PageName resultRedirectPage = PageNames.ORDER_ACCEPT;
        int currentOffset = Integer.parseInt(String.valueOf(context.getAttribute("orders_offset")));
        if(currentOffset >= 10){
            currentOffset-=10;
        }
        context.setAttribute("orders_offset",currentOffset);
        try {
            OrderService orderService = new OrderService();
            List<Order> orders = orderService.getPageOrdersList(currentOffset);
            context.setAttribute("orders",orders);
            return resultRedirectPage;
        }catch (Exception e){
            logger.log(Level.ERROR,"Error while using orders pagination(previous pages)",e);
            resultRedirectPage = PageNames.ERROR_PAGE;
            return resultRedirectPage;
        }
    }
}
