package by.bsuir.wtl2.webapp.classes.controller.logic;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This interface represents basic interface of controller commands.
 *
 * @author Fedor
 * @since 2023-11-27
 * @version 1.0
 */
public interface ICommand {
    /**
     * This method executes the command.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @param context  The servlet context.
     * @return The name of the page to redirect to.
     * @throws ServletException If an error occurs during execution.
     * @throws IOException      If an error occurs during I/O.
     */
    PageName completeCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException;
}
