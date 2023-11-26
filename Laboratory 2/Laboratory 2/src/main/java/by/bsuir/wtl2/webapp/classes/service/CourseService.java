package by.bsuir.wtl2.webapp.classes.service;

import by.bsuir.wtl2.webapp.classes.dao.objects.CourseDao;
import by.bsuir.wtl2.webapp.classes.dao.objects.OrderDao;
import by.bsuir.wtl2.webapp.classes.entities.Course;
import by.bsuir.wtl2.webapp.classes.exceptions.DaoException;
import by.bsuir.wtl2.webapp.classes.exceptions.ServiceException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseService {

    public boolean createCourse(Course course) throws ServiceException {
        try {
            CourseDao courseDao = new CourseDao();
            List<String> attributes =  CourseDao.courseAttributes();
            Map<String, Object> params = CourseDao.courseParams(course);
            attributes.remove("c_id");
            params.remove("c_id");
            courseDao.addCourse(attributes, params);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }
    public boolean deleteCourse(Course course){
        return false;
    }
    public boolean updateCourse(Course originalCourse,Course updatedCourse) throws ServiceException{
        try {
            CourseDao courseDao = new CourseDao();
            List<String> attributes = CourseDao.courseAttributes();
            Map<String, Object> oldParams = CourseDao.courseParams(originalCourse);
            Map<String, Object> newParams = CourseDao.courseParams(updatedCourse);
            courseDao.updateCourse(attributes, oldParams, attributes, newParams);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
        return true;
    }

    public Course getCourseById(int id) throws ServiceException {
        try {
            CourseDao courseDao = new CourseDao();
            List<String> attributes = new ArrayList<>();
            attributes.add("c_id");
            Map<String, Object> params = new HashMap<>();
            params.put("c_id", id);
            courseDao.getCourse("*", attributes, params);
            Map<String, Object> courseParams = courseDao.getCourseSelectionResult(CourseDao
                    .courseAttributes());
            if (courseParams.isEmpty()) {
                return null;
            } else {
                Course course = new Course();
                fillCourseWithParams(course, courseParams);
                return course;
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public int getCourseId(Course course) throws ServiceException{
        try {
            OrderDao orderDao = new OrderDao();
            List<String> attributes = CourseDao.courseAttributes();
            Map<String, Object> params = CourseDao.courseParams(course);
            orderDao.getOrder("*",attributes,params);
            Object orderIdObj = orderDao.getOrderSelectionResult("c_id");
            return Integer.parseInt(String.valueOf(orderIdObj));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<Course> getPageCoursesList(int offset) throws ServiceException{
        try {
            CourseDao courseDao = new CourseDao();
            List<Course> courses = new ArrayList<>();
            courseDao.getCourseList("*",offset,10);
            List<Map<String, Object>> coursesParams = courseDao.getCoursesSelectionResult(CourseDao
                    .courseAttributes());
            for(Map<String,Object> courseParams : coursesParams) {
                Course currentCourse = new Course();
                fillCourseWithParams(currentCourse,courseParams);
                courses.add(currentCourse);
            }
            return courses;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public int getTotalCourseCount() throws ServiceException{
        try {
            CourseDao courseDao = new CourseDao();
            int count = courseDao.getTableRowsCount();
            return count;
        } catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void fillCourseWithParams(Course course,Map<String,Object> params) {
        course.setId(Integer.parseInt(String.valueOf(params.get("c_id"))));
        course.setName(String.valueOf(params.get("c_name")));
        course.setPrice(Double.parseDouble(String.valueOf(params.get("c_price"))));
        course.setAuthor(String.valueOf(params.get("c_author")));
        course.setDescription(String.valueOf(params.get("c_description")));
        course.setMainTech(String.valueOf(params.get("c_main_tech")));
    }
}
