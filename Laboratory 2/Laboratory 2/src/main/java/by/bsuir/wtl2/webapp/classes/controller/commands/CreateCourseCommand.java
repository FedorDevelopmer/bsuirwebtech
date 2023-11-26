package by.bsuir.wtl2.webapp.classes.controller.commands;

import by.bsuir.wtl2.webapp.classes.controller.logic.ICommand;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageName;
import by.bsuir.wtl2.webapp.classes.controller.logic.PageNames;
import by.bsuir.wtl2.webapp.classes.entities.Course;
import by.bsuir.wtl2.webapp.classes.exceptions.ServiceException;
import by.bsuir.wtl2.webapp.classes.service.CourseService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import by.bsuir.wtl2.webapp.classes.validation.ValidatorHandler;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;

public class CreateCourseCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(CreateCourseCommand.class.getName());

    @Override
    public PageName completeCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException {
        PageName resultRedirectPage = PageNames.MAIN_PAGE;
        try {
            boolean valid;
            HttpSession session = request.getSession();
            Course course = new Course();
            Map<String,String[]> requestParams = request.getParameterMap();
            Map<String,Object> params = new HashMap<>();
            for(Map.Entry<String,String[]> param: requestParams.entrySet()){
                params.put(param.getKey(),new String(param.getValue()[0]
                        .getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8));
            }
            CourseService courseService = new CourseService();
            params.put("c_id",0);
            valid = applyValidation(params);
            if(valid){
                courseService.fillCourseWithParams(course,params);
                courseService.createCourse(course);
                session.setAttribute("chosen",null);
                session.setAttribute("cart",null);
            } else {
                session.setAttribute("input_error",true);
                resultRedirectPage=PageNames.COURSE_CREATION;
            }
            return resultRedirectPage;
        } catch (Exception e){
            logger.log(Level.ERROR,"Error while creating course",e);
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
