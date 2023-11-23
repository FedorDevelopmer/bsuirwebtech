package by.bsuir.wtl2.webapp.classes.dao.objects;

import by.bsuir.wtl2.webapp.classes.dao.commands.InsertionCommand;
import by.bsuir.wtl2.webapp.classes.dao.commands.SelectionCommand;
import by.bsuir.wtl2.webapp.classes.dao.connection.ConnectionPool;
import by.bsuir.wtl2.webapp.classes.exceptions.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkTablesDao {

    private static ResultSet lastResult = null;

    private static boolean lastResultEmpty = true;

    public void createLink(String linkTableName,List<String> attributes,
                         Map<String,Object> params) throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            InsertionCommand.completeCommand(connection, linkTableName,attributes,params);
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }
    public void getLinks(String linkTableName, List<String> attributes, Map<String,Object> params)
            throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            lastResult = SelectionCommand.completeCommand(connection, linkTableName,
                    "*",attributes,params);
            lastResultEmpty = !lastResult.next();
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }

    public Map<String,Object> getLinkSelectionResult(List<String> attributes) throws DaoException {
        Map<String,Object> resultLinks = new HashMap<>();
        try {
            if(!lastResultEmpty) {
                for (String attribute : attributes) {
                    resultLinks.put(attribute, lastResult.getObject(attribute));
                }
            }
            return resultLinks;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }
    public Object getLinkSelectionResult(String attribute) throws DaoException {
        try {
            if(!lastResultEmpty) {
                return lastResult.getObject(attribute);
            }
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
        return null;
    }

    public List<Map<String,Object>> getLinksSelectionResult(List<String> attributes) throws DaoException {
        Map<String,Object> resultLink = new HashMap<>();
        List<Map<String,Object>> resultLinks = new ArrayList<>();
        try {
            if(!lastResultEmpty) {
                for (String attribute : attributes) {
                    resultLink.put(attribute, lastResult.getObject(attribute));
                }
                resultLinks.add(resultLink);
                while(lastResult.next()) {
                    resultLink = new HashMap<>();
                    for (String attribute : attributes) {
                        resultLink.put(attribute, lastResult.getObject(attribute));
                    }
                    resultLinks.add(resultLink);
                }
                lastResult.first();
            }
            return resultLinks;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public List<Object> getLinksSelectionResult(String attribute) throws DaoException {
        List<Object> resultLinks = new ArrayList<>();
        try {
            if(!lastResultEmpty) {
                resultLinks.add(lastResult.getObject(attribute));
                while(lastResult.next()) {
                    resultLinks.add(lastResult.getObject(attribute));
                }
            }
            return resultLinks;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public void deleteLink(List<String> attributes, Map<String,String> params) {

    }

}
