package by.bsuir.wtl2.webapp.classes.controller.commands;

import by.bsuir.wtl2.webapp.classes.controller.logic.ICommand;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageName;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageNames;
import by.bsuir.wtl2.webapp.classes.entities.Course;
import by.bsuir.wtl2.webapp.classes.exceptions.ServiceException;
import by.bsuir.wtl2.webapp.classes.service.CourseService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PreviousCoursesPageCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(PreviousCoursesPageCommand.class.getName());

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
            List<Course> courses = courseService.getAllCourses(currentOffset);
            context.setAttribute("courses",courses);
            return resultRedirectPage;
        }catch (Exception e){
            logger.log(Level.ERROR,"Error while using course pagination(previous pages)",e);
            resultRedirectPage = PageNames.ERROR_PAGE;
            return resultRedirectPage;
        }
    }
}
