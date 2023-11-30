package by.bsuir.wtl2.webapp.classes.controller.commands;

import by.bsuir.wtl2.webapp.classes.controller.logic.ICommand;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageName;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageNames;
import by.bsuir.wtl2.webapp.classes.entities.Course;
import by.bsuir.wtl2.webapp.classes.service.CourseService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * This class represents a command for navigating to the previous page of courses.
 * It updates the offset value in the servlet context and retrieves the previous page of courses from the database.
 *
 * @author Fedor
 * @since 2023-11-27
 * @version 1.0
 */
public class PreviousCoursesPageCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(PreviousCoursesPageCommand.class.getName());

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
    @Override
    public PageName completeCommand(HttpServletRequest request, HttpServletResponse response,
                                    ServletContext context)
                                    throws ServletException, IOException {
        PageName resultRedirectPage = PageNames.MAIN_PAGE;
        int currentOffset = Integer.parseInt(String.valueOf(context.getAttribute("offset")));
        if(currentOffset>=10) {
            currentOffset -= 10;
            context.setAttribute("offset", currentOffset);
        }
        try {
            CourseService courseService = new CourseService();
            List<Course> courses = courseService.getPageCoursesList(currentOffset);
            context.setAttribute("courses",courses);
            return resultRedirectPage;
        }catch (Exception e){
            logger.log(Level.ERROR,"Error while using course pagination(previous pages)",e);
            resultRedirectPage = PageNames.ERROR_PAGE;
            return resultRedirectPage;
        }
    }
}
