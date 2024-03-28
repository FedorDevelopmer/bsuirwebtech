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

/**
 * The CourseService class provides various operations related to courses.
 * It includes methods for creating a course, deleting a course, updating a course, retrieving a course by ID,
 * retrieving the course ID associated with an order, retrieving a list of courses for a page, and retrieving the total count of courses.
 *
 * @version 1.0
 * @author Fedor
 * @since 2023-11-29
 */
public class CourseService {

    /**
     * Creates a new course by adding course-specific attributes to the database.
     *
     * @param course the course to create
     * @return true if the course is successfully created, false otherwise
     * @throws ServiceException if an error occurs during the creation process
     */
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

    /**
     * Deletes a course from the database.
     *
     * @param course the course to delete
     * @return true if the course is successfully deleted, false otherwise
     * @throws ServiceException if an error occurs during the delete process
     */
    public boolean deleteCourse(Course course) throws ServiceException{
        try {
            CourseDao courseDao = new CourseDao();
            Map<String, Object> orderParams = CourseDao.courseParams(course);
            courseDao.deleteCourse(OrderDao.orderAttributes(), orderParams);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
        return true;
    }

    /**
     * Updates a course by updating the course-specific attributes in the database.
     *
     * @param originalCourse the original course information
     * @param updatedCourse the updated course information
     * @return true if the course is successfully updated, false otherwise
     * @throws ServiceException if an error occurs during the update process
     */
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

    /**
     * Retrieves a course by its ID from the database.
     *
     * @param id the ID of the course
     * @return the course with the specified ID if found, null otherwise
     * @throws ServiceException if an error occurs during the retrieval process
     */
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

    /**
     * Retrieves the course ID associated with an order from the database.
     *
     * @param course the course to retrieve the ID for
     * @return the course ID associated with the order if found, null otherwise
     * @throws ServiceException if an error occurs during the retrieval process
     */
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

    /**
     * Retrieves a list of courses for a specific page from the database.
     *
     * @param offset the offset of the page
     * @return a list of courses for the page
     * @throws ServiceException if an error occurs during the retrieval process
     */
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

    /**
     * Retrieves the total count of courses from the database.
     *
     * @return the total count of courses
     * @throws ServiceException if an error occurs during the retrieval process
     */
    public int getTotalCourseCount() throws ServiceException{
        try {
            CourseDao courseDao = new CourseDao();
            int count = courseDao.getTableRowsCount();
            return count;
        } catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    /**
     * Fills a course object with the provided parameters.
     *
     * @param course the course object to fill
     * @param params the parameters to fill the course object with
     */
    public void fillCourseWithParams(Course course,Map<String,Object> params) {
        course.setId(Integer.parseInt(String.valueOf(params.get("c_id"))));
        course.setName(String.valueOf(params.get("c_name")));
        course.setPrice(Double.parseDouble(String.valueOf(params.get("c_price"))));
        course.setAuthor(String.valueOf(params.get("c_author")));
        course.setDescription(String.valueOf(params.get("c_description")));
        course.setMainTech(String.valueOf(params.get("c_main_tech")));
    }
}
