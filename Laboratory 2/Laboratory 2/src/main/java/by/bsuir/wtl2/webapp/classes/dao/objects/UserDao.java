package by.bsuir.wtl2.webapp.classes.dao.objects;

import by.bsuir.wtl2.webapp.classes.dao.commands.DeleteCommand;
import by.bsuir.wtl2.webapp.classes.dao.commands.InsertionCommand;
import by.bsuir.wtl2.webapp.classes.dao.commands.SelectionCommand;
import by.bsuir.wtl2.webapp.classes.dao.commands.UpdateCommand;
import by.bsuir.wtl2.webapp.classes.dao.connection.ConnectionPool;
import by.bsuir.wtl2.webapp.classes.entities.Course;
import by.bsuir.wtl2.webapp.classes.entities.User;
import by.bsuir.wtl2.webapp.classes.exceptions.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {

    private static String userTableName = "User";

    private static ResultSet lastResult = null;

    private static boolean lastResultEmpty = true;

    public void addUser(List<String> attributes,
                         Map<String,Object> params) throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            InsertionCommand.completeCommand(connection, userTableName,attributes,params);
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }
    public void updateUser(List<String> updateAttributes, Map<String,Object> params,
                            List<String> selectAttributes, Map<String,Object> newParams)
            throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            UpdateCommand.completeCommand(connection, userTableName,updateAttributes,params,
                    selectAttributes,newParams);
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }
    public void getUser(String selectionAttribute, List<String> attributes, Map<String,Object> params)
            throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            lastResult = SelectionCommand.completeCommand(connection, userTableName,selectionAttribute,attributes,params);
            lastResultEmpty = !lastResult.next();
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }

    public void deleteUser(List<String> attributes, Map<String,Object> params)throws DaoException {
        try{
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            DeleteCommand.completeCommand(connection, userTableName,attributes,params);
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }

    public int getTableRowsCount() throws DaoException{
        int result = -1;
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            result = SelectionCommand.selectTableRowsCount(connection,userTableName);
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
        return result;
    }

    public String getUserById() throws DaoException {
        try {
            if(!lastResultEmpty) {
                return lastResult.getString("u_id");
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
        return null;
    }
    public Map<String,Object> getUserSelectionResult(List<String> attributes) throws DaoException {
        Map<String,Object> resultUser = new HashMap<>();
        try {
            if(!lastResultEmpty) {
                for (String attribute : attributes) {
                    resultUser.put(attribute, lastResult.getObject(attribute));
                }
            }
            return resultUser;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }
    public Object getUserSelectionResult(String attribute) throws DaoException {
        try {
            if(!lastResultEmpty) {
               return lastResult.getObject(attribute);
            }

        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
        return null;
    }

    public List<Map<String,Object>> getUsersSelectionResult(List<String> attributes) throws DaoException {
        Map<String,Object> resultUserAttributes = new HashMap<>();
        List<Map<String,Object>> resultUsersAttributes = new ArrayList<>();
        try {
            if(!lastResultEmpty) {
                for (String attribute : attributes) {
                    resultUserAttributes.put(attribute, lastResult.getObject(attribute));
                }
                resultUsersAttributes.add(resultUserAttributes);
                while(lastResult.next()) {
                    resultUserAttributes = new HashMap<>();
                    for (String attribute : attributes) {
                        resultUserAttributes.put(attribute, lastResult.getObject(attribute));
                    }
                    resultUsersAttributes.add(resultUserAttributes);
                }
                lastResult.first();
            }
            return resultUsersAttributes;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public List<Object> getUsersSelectionResult(String attribute) throws DaoException {
        List<Object> resultUsers = new ArrayList<>();
        try {
            if(!lastResultEmpty) {
                resultUsers.add(lastResult.getObject(attribute));
                while(lastResult.next()) {
                    resultUsers.add(lastResult.getObject(attribute));
                }
            }
            return resultUsers;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public static List<String> userAttributes() {
        List<String> attributes = new ArrayList<>();
        attributes.add("u_id");
        attributes.add("u_name");
        attributes.add("u_surname");
        attributes.add("u_phone_num");
        attributes.add("u_email");
        attributes.add("u_login");
        attributes.add("u_pass_hash");
        attributes.add("u_reg_date");
        return attributes;
    }

    public static Map<String,Object> userParams(User user) {
        Map<String,Object> params = new HashMap<>();
        params.put("u_id", user.getId());
        params.put("u_name", user.getName());
        params.put("u_surname", user.getSurname());
        params.put("u_phone_num", user.getPhoneNumber());
        params.put("u_email", user.getEmail());
        params.put("u_login", user.getLogin());
        params.put("u_pass_hash", user.getPassword());
        params.put("u_reg_date", user.getRegistrationDate());
        return params;
    }
}
