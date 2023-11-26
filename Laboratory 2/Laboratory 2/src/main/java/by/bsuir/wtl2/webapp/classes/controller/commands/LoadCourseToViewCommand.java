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

public class LoadCourseToViewCommand implements ICommand {
    private static final Logger logger = Logger.getLogger(LoadCourseToViewCommand.class.getName());
    @Override
    public PageName completeCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        PageName resultRedirectPage = PageNames.COURSE_VIEW;
        List<Course> courses;
        int currentOffset = Integer.parseInt(String.valueOf(context.getAttribute("offset")));
        try {
            CourseService courseService = new CourseService();
            courses = courseService.getPageCoursesList(currentOffset);
            context.setAttribute("course",courses.get(Integer
                    .parseInt(request.getParameter("courseId"))));
            return resultRedirectPage;
        }catch (Exception e){
            logger.log(Level.ERROR,"Error while loading course to edit",e);
            resultRedirectPage = PageNames.ERROR_PAGE;
            return resultRedirectPage;
        }
    }
}
