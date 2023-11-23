package by.bsuir.wtl2.webapp.classes.dao.objects;

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

public class CourseDao {

    private static String courseTableName = "course";

    private static ResultSet lastResult = null;

    private static boolean lastResultEmpty = true;

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
    public void deleteCourse(List<String> attributes, Map<String,String> params) {

    }
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
    public List<Map<String,Object>> getCoursesSelectionResult(List<String> attributes) throws DaoException {
        Map<String,Object> resultUser = new HashMap<>();
        List<Map<String,Object>> resultUsers = new ArrayList<>();
        try {
            if(!lastResultEmpty) {
                for (String attribute : attributes) {
                    resultUser.put(attribute, lastResult.getObject(attribute));
                }
                resultUsers.add(resultUser);
                while(lastResult.next()) {
                    resultUser = new HashMap<>();
                    for (String attribute : attributes) {
                        resultUser.put(attribute, lastResult.getObject(attribute));
                    }
                    resultUsers.add(resultUser);
                }
                lastResult.first();
            }
            return resultUsers;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }
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
