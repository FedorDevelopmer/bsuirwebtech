package by.bsuir.wtl2.webapp.classes.dao.objects;

import by.bsuir.wtl2.webapp.classes.dao.commands.DeleteCommand;
import by.bsuir.wtl2.webapp.classes.dao.commands.InsertionCommand;
import by.bsuir.wtl2.webapp.classes.dao.commands.SelectionCommand;
import by.bsuir.wtl2.webapp.classes.dao.commands.UpdateCommand;
import by.bsuir.wtl2.webapp.classes.dao.connection.ConnectionPool;
import by.bsuir.wtl2.webapp.classes.entities.Admin;
import by.bsuir.wtl2.webapp.classes.exceptions.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDao {

    private static String adminTableName = "Admins";

    private static ResultSet lastResult = null;

    private static boolean lastResultEmpty = true;

    public void addAdmin(List<String> attributes,
                         Map<String,Object> params) throws DaoException{
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            InsertionCommand.completeCommand(connection,adminTableName,attributes,params);
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }
    public void updateAdmin(List<String> updateAttributes, Map<String,Object> params,
                            List<String> selectAttributes, Map<String,Object> newParams)
                            throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            UpdateCommand.completeCommand(connection,adminTableName,updateAttributes,params,
                    selectAttributes,newParams);
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }
    public void getAdmin(String selectionAttribute, List<String> attributes, Map<String,Object> params)
                         throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            lastResult = SelectionCommand.completeCommand(connection,adminTableName,selectionAttribute,attributes,params);
            lastResultEmpty=!lastResult.next();
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }
    public void deleteAdmin(List<String> attributes, Map<String,Object> params) throws DaoException{
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            DeleteCommand.completeCommand(connection,adminTableName,attributes,params);
            lastResultEmpty=!lastResult.next();
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
            result = SelectionCommand.selectTableRowsCount(connection,adminTableName);
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
        return result;
    }
    public Map<String,Object> getAdminSelectionResult(List<String> attributes) throws DaoException {
        Map<String,Object> resultAdmin = new HashMap<>();
        try {
            if(!lastResultEmpty) {
                for (String attribute : attributes) {
                    resultAdmin.put(attribute, lastResult.getObject(attribute));
                }
            }
            return resultAdmin;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }
    public Object getAdminSelectionResult(String attribute) throws DaoException {
        try {
            if(!lastResultEmpty) {
                return lastResult.getObject(attribute);
            }
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
        return null;
    }

    public List<Map<String,Object>> getAdminsSelectionResult(List<String> attributes) throws DaoException {
        Map<String,Object> resultAdminAttributes = new HashMap<>();
        List<Map<String,Object>> resultAdminsAttributes = new ArrayList<>();
        try {
            if(!lastResultEmpty) {
                for (String attribute : attributes) {
                    resultAdminAttributes.put(attribute, lastResult.getObject(attribute));
                }
                resultAdminsAttributes.add(resultAdminAttributes);
                while(lastResult.next()) {
                    resultAdminAttributes = new HashMap<>();
                    for (String attribute : attributes) {
                        resultAdminAttributes.put(attribute, lastResult.getObject(attribute));
                    }
                    resultAdminsAttributes.add(resultAdminAttributes);
                }
                lastResult.first();
            }
            return resultAdminsAttributes;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public List<Object> getAdminsSelectionResult(String attribute) throws DaoException {
        List<Object> resultAdminsAttribute = new ArrayList<>();
        try {
            if(!lastResultEmpty) {
                resultAdminsAttribute.add(lastResult.getObject(attribute));
                while(lastResult.next()) {
                    resultAdminsAttribute.add(lastResult.getObject(attribute));
                }
            }
            return resultAdminsAttribute;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public static List<String> adminAttributes() {
        List<String> attributes = new ArrayList<>();
        attributes.add("adm_id");
        attributes.add("adm_is_active");
        return attributes;
    }

    public static Map<String,Object> adminParams(Admin admin) {
        Map<String,Object> params = new HashMap<>();
        params.put("adm_id",admin.getId());
        if(admin.isActive()) {
            params.put("adm_is_active", 1);
        } else {
            params.put("adm_is_active", 0);
        }
        return params;
    }

}
