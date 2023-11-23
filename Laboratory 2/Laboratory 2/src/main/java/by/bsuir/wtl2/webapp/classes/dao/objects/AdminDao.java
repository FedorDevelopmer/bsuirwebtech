package by.bsuir.wtl2.webapp.classes.dao.objects;

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
    public void deleteAdmin(List<String> attributes, Map<String,String> params) {

    }
    public String getAdminById() throws DaoException {
        try {
            if(!lastResultEmpty) {
                return lastResult.getString("adm_id");
            } else {
                return "";
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
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
