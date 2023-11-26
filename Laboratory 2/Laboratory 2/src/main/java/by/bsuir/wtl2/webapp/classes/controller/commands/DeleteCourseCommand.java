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

public class DeleteCourseCommand implements ICommand {
        private static final Logger logger = Logger.getLogger(DeleteCourseCommand.class.getName());

        @Override
        public PageName completeCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
            PageName resultRedirectPage = PageNames.MAIN_PAGE;
            try {
                CourseService courseService = new CourseService();
                int coursesOffset = Integer.parseInt(String
                        .valueOf(context.getAttribute("offset")));
                List<Course> currentOrderPage = courseService.getPageCoursesList(coursesOffset);
                courseService.deleteCourse(currentOrderPage.get(Integer
                        .parseInt(request.getParameter("courseId"))));
                List<Course> courses = courseService.getPageCoursesList(0);
                int coursesCount = courseService.getTotalCourseCount();
                context.setAttribute("courses_count",coursesCount);
                context.setAttribute("courses",courses);
                context.setAttribute("offset",0);
                return resultRedirectPage;
            }catch (Exception e){
                resultRedirectPage=PageNames.ERROR_PAGE;
                logger.log(Level.ERROR,"Error while deleting order",e);
                return resultRedirectPage;
            }
        }
}
