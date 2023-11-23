package by.bsuir.wtl2.webapp.classes.controller.commands;

import by.bsuir.wtl2.webapp.classes.controller.logic.ICommand;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageName;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageNames;
import by.bsuir.wtl2.webapp.classes.entities.Course;
import by.bsuir.wtl2.webapp.classes.exceptions.ServiceException;
import by.bsuir.wtl2.webapp.classes.service.CourseService;
import by.bsuir.wtl2.webapp.classes.validation.ValidatorHandler;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CourseEditCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(CourseEditCommand.class.getName());

    @Override
    public PageName completeCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        PageName resultRedirectPage = PageNames.MAIN_PAGE;
        try {
            boolean valid = true;
            ValidatorHandler validator = ValidatorHandler.getInstance();
            HttpSession session = request.getSession();
            CourseService courseService = new CourseService();
            Course updatedCourse = new Course();
            Map<String, String[]> requestParams = request.getParameterMap();
            Map<String, Object> params = new HashMap<>();
            for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
                params.put(entry.getKey(), new String(entry.getValue()[0].getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8));
            }
            params.put("c_id",0);
            courseService.fillCourseWithParams(updatedCourse, params);
            valid = validator.getValidatorByName("name_validator").validate(updatedCourse.getName())
                && validator.getValidatorByName("name_validator").validate(updatedCourse.getAuthor())
                && validator.getValidatorByName("text_validator").validate(updatedCourse.getMainTech())
                && validator.getValidatorByName("text_validator").validate(updatedCourse.getDescription());
            if(valid) {
                courseService.updateCourse((Course) context.getAttribute("course"), updatedCourse);
                context.setAttribute("course", updatedCourse);
                session.setAttribute("course", null);
            } else {
                session.setAttribute("input_error",true);
                resultRedirectPage=PageNames.COURSE_EDIT;
            }
            return resultRedirectPage;
        } catch(Exception e) {
            logger.log(Level.ERROR,"Error while editing course",e);
            resultRedirectPage = PageNames.ERROR_PAGE;
            return resultRedirectPage;

        }
    }
}
