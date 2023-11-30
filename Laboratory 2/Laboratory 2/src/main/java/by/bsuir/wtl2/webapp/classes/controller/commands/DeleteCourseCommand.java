package by.bsuir.wtl2.webapp.classes.controller.commands;

import by.bsuir.wtl2.webapp.classes.controller.logic.ICommand;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageName;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageNames;
import by.bsuir.wtl2.webapp.classes.entities.Course;
import by.bsuir.wtl2.webapp.classes.entities.Order;
import by.bsuir.wtl2.webapp.classes.service.CourseService;
import by.bsuir.wtl2.webapp.classes.service.OrderService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * The DeleteCourseCommand class implements the ICommand interface and is responsible for deleting a course.
 * It handles the logic of deleting a course from the system.
 * It logs any errors that occur during the process.
 *
 * @author Fedor
 * @version 1.0
 * @since 2023-11-27
 */
public class DeleteCourseCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(DeleteCourseCommand.class.getName());

    /**
     * Completes the command of deleting a course.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @param context  The ServletContext object.
     * @return The name of the page to redirect to.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    @Override
    public PageName completeCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context)
            throws ServletException, IOException {
        PageName resultRedirectPage = PageNames.MAIN_PAGE;
        try {
            CourseService courseService = new CourseService();
            int coursesOffset = Integer.parseInt(String.valueOf(context.getAttribute("offset")));
            List<Course> currentOrderPage = courseService.getPageCoursesList(coursesOffset);
            courseService.deleteCourse(currentOrderPage.get(Integer.parseInt(request.getParameter("courseId"))));
            List<Course> courses = courseService.getPageCoursesList(0);
            int coursesCount = courseService.getTotalCourseCount();
            context.setAttribute("courses_count", coursesCount);
            context.setAttribute("courses", courses);
            context.setAttribute("offset", 0);
            return resultRedirectPage;
        } catch (Exception e) {
            resultRedirectPage = PageNames.ERROR_PAGE;
            logger.log(Level.ERROR, "Error while deleting order", e);
            return resultRedirectPage;
        }
    }
}
