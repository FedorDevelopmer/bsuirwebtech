package by.bsuir.wtl2.webapp.classes.dao.objects;

import by.bsuir.wtl2.webapp.classes.dao.commands.DeleteCommand;
import by.bsuir.wtl2.webapp.classes.dao.commands.InsertionCommand;
import by.bsuir.wtl2.webapp.classes.dao.commands.SelectionCommand;
import by.bsuir.wtl2.webapp.classes.dao.commands.UpdateCommand;
import by.bsuir.wtl2.webapp.classes.dao.connection.ConnectionPool;
import by.bsuir.wtl2.webapp.classes.entities.Course;
import by.bsuir.wtl2.webapp.classes.exceptions.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The CourseDao class provides methods for managing courses in the database.
 *
 * @version 1.0
 * @author Fedor
 * @since 2023-11-29
 */
public class CourseDao {

    private static final String courseTableName = "course";

    private static ResultSet lastResult = null;

    private static boolean lastResultEmpty = true;

    /**
     * Adds a course to the database.
     *
     * @param attributes the attributes of the course
     * @param params the parameters of the course
     * @throws DaoException if an error occurs while adding the course
     */
    public void addCourse(List<String> attributes,
                          Map<String,Object> params) throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            InsertionCommand.completeCommand(connection, courseTableName,attributes,params);
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }

    /**
     * Updates a course in the database.
     *
     * @param updateAttributes the attributes to update
     * @param params the parameters of the course
     * @param selectAttributes the attributes to select
     * @param newParams the new parameters of the course
     * @throws DaoException if an error occurs while updating the course
     */
    public void updateCourse(List<String> updateAttributes, Map<String,Object> params,
                             List<String> selectAttributes, Map<String,Object> newParams)
            throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            UpdateCommand.completeCommand(connection, courseTableName,updateAttributes,params,
                    selectAttributes,newParams);
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }

    /**
     * Gets a course from the database.
     *
     * @param selectionAttribute the attribute to select
     * @param attributes the attributes to return
     * @param params the parameters of the course
     * @throws DaoException if an error occurs while getting the course
     */
    public void getCourse(String selectionAttribute, List<String> attributes, Map<String,Object> params)
            throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            lastResult = SelectionCommand.completeCommand(connection, courseTableName,selectionAttribute,attributes,params);
            lastResultEmpty=!lastResult.next();
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }

    /**
     * Deletes a course from the database.
     *
     * @param attributes the attributes of the course
     * @param params the parameters of the course
     * @throws DaoException if an error occurs while getting the course
     */
    public void deleteCourse(List<String> attributes, Map<String,Object> params) throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            DeleteCommand.completeCommand(connection,courseTableName,attributes,params);
            lastResultEmpty=!lastResult.next();
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }

    /**
     * Gets the number of rows in the course table.
     *
     * @return the number of rows
     * @throws DaoException if an error occurs while getting the number of rows
     */
    public int getTableRowsCount() throws DaoException{
        int result = -1;
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            result = SelectionCommand.selectTableRowsCount(connection, courseTableName);
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
        return result;
    }

    /**
     * Gets a list of courses from the database using pagination.
     *
     * @param selectionAttribute the attribute to select
     * @param offset the offset for pagination
     * @param limit the limit for pagination
     * @throws DaoException if an error occurs while getting the course list
     */
    public void getCourseList(String selectionAttribute,int offset,int limit)
            throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            lastResult = SelectionCommand.completeCommandForPagination(connection, courseTableName,selectionAttribute,offset,limit);
            lastResultEmpty=!lastResult.next();
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }

    /**
     * Retrieves the selection result for a single course.
     *
     * @param attributes the attributes to retrieve
     * @return the selection result as a map of attribute-value pairs
     * @throws DaoException if an error occurs while retrieving the selection result
     */
    public Map<String,Object> getCourseSelectionResult(List<String> attributes) throws DaoException {
        Map<String,Object> resultOrder = new HashMap<>();
        try {
            if(!lastResultEmpty) {
                for (String attribute : attributes) {
                    resultOrder.put(attribute, lastResult.getObject(attribute));
                }
            }
            return resultOrder;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    /**
     * Retrieves the selection result for a specific attribute of a single course.
     *
     * @param attribute the attribute to retrieve
     * @return the value of the attribute
     * @throws DaoException if an error occurs while retrieving the selection result
     */
    public Map<String,Object> getCourseSelectionResult(String attribute) throws DaoException {
        Map<String,Object> resultOrder = new HashMap<>();
        try {
            if(!lastResultEmpty) {
                resultOrder.put(attribute, lastResult.getObject(attribute));
            }
            return resultOrder;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    /**
     * Retrieves the selection results for multiple courses.
     *
     * @param attributes the attributes to retrieve
     * @return the selection results as a list of maps, where each map represents a course with attribute-value pairs
     * @throws DaoException if an error occurs while retrieving the selection results
     */

    public List<Map<String,Object>> getCoursesSelectionResult(List<String> attributes) throws DaoException {
        Map<String,Object> resultCourseAttributes = new HashMap<>();
        List<Map<String,Object>> resultCoursesAttributes = new ArrayList<>();
        try {
            if(!lastResultEmpty) {
                for (String attribute : attributes) {
                    resultCourseAttributes.put(attribute, lastResult.getObject(attribute));
                }
                resultCoursesAttributes.add(resultCourseAttributes);
                while(lastResult.next()) {
                    resultCourseAttributes = new HashMap<>();
                    for (String attribute : attributes) {
                        resultCourseAttributes.put(attribute, lastResult.getObject(attribute));
                    }
                    resultCoursesAttributes.add(resultCourseAttributes);
                }
                lastResult.first();
            }
            return resultCoursesAttributes;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    /**
     * Retrieves the selected attribute values for multiple courses.
     *
     * @param attribute the attribute to retrieve
     * @return the selection results as a list of maps, where each map represents a course with attribute-value pairs
     * @throws DaoException if an error occurs while retrieving the selection results
     */
    public List<Object> getCoursesSelectionResult(String attribute) throws DaoException {
        List<Object> resultCoursesAttribute = new ArrayList<>();
        try {
            if(!lastResultEmpty) {
                resultCoursesAttribute.add(lastResult.getObject(attribute));
                while(lastResult.next()) {
                    resultCoursesAttribute.add(lastResult.getObject(attribute));
                }
            }
            return resultCoursesAttribute;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    /**
     * Retrieves the attributes of a course.
     *
     * @return the course attributes as a list
     */
    public static List<String> courseAttributes() {
        List<String> attributes = new ArrayList<>();
        attributes.add("c_id");
        attributes.add("c_name");
        attributes.add("c_price");
        attributes.add("c_author");
        attributes.add("c_description");
        attributes.add("c_main_tech");
        return attributes;
    }

    /**
     * Retrieves the parameters of a course.
     *
     * @param course the course object
     * @return the course parameters as a map of parameter-value pairs
     */
    public static Map<String,Object> courseParams(Course course) {
        Map<String,Object> params = new HashMap<>();
        params.put("c_id",course.getId());
        params.put("c_name",course.getName());
        params.put("c_price",course.getPrice());
        params.put("c_author",course.getAuthor());
        params.put("c_description",course.getDescription());
        params.put("c_main_tech",course.getMainTech());
        return params;
    }
}
