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
            boolean valid;
            HttpSession session = request.getSession();
            CourseService courseService = new CourseService();
            Course updatedCourse = new Course();
            Map<String, String[]> requestParams = request.getParameterMap();
            Map<String, Object> params = new HashMap<>();
            for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
                params.put(entry.getKey(), new String(entry.getValue()[0].getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8));
            }
            params.put("c_id",0);
            valid = applyValidation(params);
            if(valid) {
                courseService.fillCourseWithParams(updatedCourse, params);
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

    private boolean applyValidation(Map<String,Object> params){
        boolean valid;
        ValidatorHandler validator = ValidatorHandler.getInstance();
        valid = validator.getValidatorByName("name_validator").validate(String.valueOf(params.get("c_name")))
                && validator.getValidatorByName("name_validator").validate(String.valueOf(params.get("c_author")))
                && validator.getValidatorByName("text_validator").validate(String.valueOf(params.get("c_main_tech")))
                && validator.getValidatorByName("text_validator").validate(String.valueOf(params.get("c_description")))
                && validator.getValidatorByName("price_validator").validate(String.valueOf(params.get("c_price")));
    return valid;
    }
}

