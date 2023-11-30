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
import java.util.List;

/**
 * The CartAddCommand class implements the ICommand interface and is
 * responsible for adding a course to the user's cart.
 *
 * @author Fedor
 * @version 1.0
 * @since 2023-11-27
 */
public class CartAddCommand implements ICommand {

    /**
     * Adds a course to the user's cart.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @param context  The ServletContext object.
     * @return The name of the page to redirect to.
     * @throws ServletException If an error occurs during execution.
     * @throws IOException If an error occurs during I/O.
     */
    @Override
    public PageName completeCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) {
            Cart cart = new Cart();
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            List<Course> courses = (List<Course>) context.getAttribute("courses");
            cart.add(courses.get(courseId));
            session.setAttribute("cart", cart);
        } else {
            Cart cart = (Cart) session.getAttribute("cart");
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            List<Course> courses = (List<Course>) context.getAttribute("courses");
            cart.add(courses.get(courseId));
            session.setAttribute("cart", cart);
        }
        return PageNames.MAIN_PAGE;
    }
}