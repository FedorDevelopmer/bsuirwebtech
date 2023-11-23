package by.bsuir.wtl2.webapp.classes.controller.commands;

import by.bsuir.wtl2.webapp.classes.controller.logic.ICommand;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageName;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageNames;
import by.bsuir.wtl2.webapp.classes.entities.Order;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChangeOrderStatusCommand implements ICommand {
    @Override
    public PageName completeCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        PageName resultRedirectPage = PageNames.ORDER_ACCEPT;
        HttpSession session = request.getSession();
        if (session.getAttribute("changed_orders") == null) {
            List<Order> changedOrders = new ArrayList<>();
            List<Order> orders = (List<Order>) context.getAttribute("orders");
            Order changedOrder = orders.get(Integer.parseInt(request.getParameter("orderId")));
            changedOrders.add(changedOrder);
            session.setAttribute("changed_orders",changedOrders);
        } else {
            List<Order> changedOrders  = (List<Order>) session.getAttribute("changed_orders");
            List<Order> orders = (List<Order>) context.getAttribute("orders");
            Order changedOrder = orders.get(Integer.parseInt(request.getParameter("orderId")));
            if(changedOrders.contains(changedOrder)){
                changedOrders.remove(changedOrder);
            } else {
                changedOrders.add(changedOrder);
            }
            session.setAttribute("changed_orders",changedOrders);
        }
        return resultRedirectPage;
    }
}
