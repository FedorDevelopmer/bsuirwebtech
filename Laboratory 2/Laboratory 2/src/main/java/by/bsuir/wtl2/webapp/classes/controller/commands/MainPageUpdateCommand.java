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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class MainPageUpdateCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(MainPageUpdateCommand.class.getName());

    @Override
    public PageName completeCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        PageName resultRedirectPage = PageNames.MAIN_PAGE;
        HttpSession session = request.getSession();
        if(session.isNew()) {
            session.setMaxInactiveInterval(300 * 60);
            session.setAttribute("log", false);
        }
        context.setAttribute("offset",0);
        session.setAttribute("courses",null);
        session.setAttribute("orders",null);
        session.setAttribute("input_error",null);
        try {
            CourseService courseService = new CourseService();
            List<Course> courses = courseService.getAllCourses(0);
            context.setAttribute("courses",courses);
            return resultRedirectPage;
        }catch (Exception e){
            logger.log(Level.ERROR,"Error while loading courses for pagination",e);
            resultRedirectPage = PageNames.ERROR_PAGE;
            return resultRedirectPage;
        }
    }
}
