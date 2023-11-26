package by.bsuir.wtl2.webapp.classes.dao.objects;

import by.bsuir.wtl2.webapp.classes.dao.commands.DeleteCommand;
import by.bsuir.wtl2.webapp.classes.dao.commands.InsertionCommand;
import by.bsuir.wtl2.webapp.classes.dao.commands.SelectionCommand;
import by.bsuir.wtl2.webapp.classes.dao.commands.UpdateCommand;
import by.bsuir.wtl2.webapp.classes.dao.connection.ConnectionPool;
import by.bsuir.wtl2.webapp.classes.entities.Client;
import by.bsuir.wtl2.webapp.classes.exceptions.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDao {

    private static String clientTableName = "clients";

    private static ResultSet lastResult = null;

    private static boolean lastResultEmpty = true;

    public void addClient(List<String> attributes,
                         Map<String,Object> params) throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            InsertionCommand.completeCommand(connection,clientTableName,attributes,params);
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }
    public void updateClient(List<String> updateAttributes, Map<String,Object> params,
                            List<String> selectAttributes, Map<String,Object> newParams)
            throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            UpdateCommand.completeCommand(connection,clientTableName,updateAttributes,params,
                    selectAttributes,newParams);
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }
    public void getClient(String selectionAttribute, List<String> attributes, Map<String,Object> params)
            throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            lastResult = SelectionCommand.completeCommand(connection,clientTableName,selectionAttribute,attributes,params);
            lastResultEmpty=!lastResult.next();
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }
    public void deleteClient(List<String> attributes, Map<String,Object> params)throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            DeleteCommand.completeCommand(connection,clientTableName,attributes,params);
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
            result = SelectionCommand.selectTableRowsCount(connection,clientTableName);
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
        return result;
    }
    public Map<String,Object> getClientSelectionResult(List<String> attributes) throws DaoException {
        Map<String,Object> resultClient = new HashMap<>();
        try {
            if(!lastResultEmpty) {
                for (String attribute : attributes) {
                    resultClient.put(attribute, lastResult.getObject(attribute));
                }
            }
            return resultClient;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }
    public Object getClientSelectionResult(String attribute) throws DaoException {
        try {
            if(!lastResultEmpty) {
                return lastResult.getObject(attribute);
            }

        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
        return null;
    }

    public List<Map<String,Object>> getClientsSelectionResult(List<String> attributes) throws DaoException {
        Map<String,Object> resultClientAttributes = new HashMap<>();
        List<Map<String,Object>> resultClientsAttributes = new ArrayList<>();
        try {
            if(!lastResultEmpty) {
                for (String attribute : attributes) {
                    resultClientAttributes.put(attribute, lastResult.getObject(attribute));
                }
                resultClientsAttributes.add(resultClientAttributes);
                while(lastResult.next()) {
                    resultClientAttributes = new HashMap<>();
                    for (String attribute : attributes) {
                        resultClientAttributes.put(attribute, lastResult.getObject(attribute));
                    }
                    resultClientsAttributes.add(resultClientAttributes);
                }
                lastResult.first();
            }
            return resultClientsAttributes;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public List<Object> getClientsSelectionResult(String attribute) throws DaoException {
        List<Object> resultClientsAttribute = new ArrayList<>();
        try {
            if(!lastResultEmpty) {
                resultClientsAttribute.add(lastResult.getObject(attribute));
                while(lastResult.next()) {
                    resultClientsAttribute.add(lastResult.getObject(attribute));
                }
            }
            return resultClientsAttribute;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public static List<String> clientAttributes() {
        List<String> attributes = new ArrayList<>();
        attributes.add("cl_id");
        attributes.add("cl_is_banned");
        return attributes;
    }

    public static Map<String,Object> clientParams(Client client) {
        Map<String,Object> params = new HashMap<>();
        params.put("cl_id", client.getId());
        if(client.isBanned()) {
            params.put("cl_is_banned", 1);
        } else {
            params.put("cl_is_banned", 0);
        }
        return params;
    }
}
