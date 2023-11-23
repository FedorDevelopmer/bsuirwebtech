package by.bsuir.wtl2.webapp.classes.controller.commands;

import by.bsuir.wtl2.webapp.classes.controller.logic.ICommand;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageName;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageNames;
import by.bsuir.wtl2.webapp.classes.entities.Course;
import by.bsuir.wtl2.webapp.classes.service.Cart;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadCartCommand implements ICommand {
    @Override
    public PageName completeCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        Cart cart = new Cart();
        HttpSession session = request.getSession();
        Object objCart = session.getAttribute("cart");
        if (objCart instanceof Cart) {
            cart = (Cart) objCart;
        }
        List<Course> chosenCourses = new ArrayList<>(cart.getAll());
        if(chosenCourses.isEmpty()) {
            session.setAttribute("chosen", null);
        } else {
            session.setAttribute("chosen", chosenCourses);
        }
        return PageNames.ORDER_CREATION;
    }
}
