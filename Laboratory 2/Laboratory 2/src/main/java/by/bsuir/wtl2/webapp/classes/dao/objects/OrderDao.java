package by.bsuir.wtl2.webapp.classes.dao.objects;

import by.bsuir.wtl2.webapp.classes.dao.commands.DeleteCommand;
import by.bsuir.wtl2.webapp.classes.dao.commands.InsertionCommand;
import by.bsuir.wtl2.webapp.classes.dao.commands.SelectionCommand;
import by.bsuir.wtl2.webapp.classes.dao.commands.UpdateCommand;
import by.bsuir.wtl2.webapp.classes.dao.connection.ConnectionPool;
import by.bsuir.wtl2.webapp.classes.entities.Order;
import by.bsuir.wtl2.webapp.classes.exceptions.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDao {

    private static String orderTableName = "user_order";

    private static ResultSet lastResult = null;

    private static boolean lastResultEmpty = true;

    public void addOrder(List<String> attributes,
                        Map<String,Object> params) throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            InsertionCommand.completeCommand(connection, orderTableName,attributes,params);
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }
    public void updateOrder(List<String> updateAttributes, Map<String,Object> params,
                           List<String> selectAttributes, Map<String,Object> newParams)
            throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            UpdateCommand.completeCommand(connection, orderTableName,updateAttributes,params,
                    selectAttributes,newParams);
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }
    public void getOrder(String selectionAttribute, List<String> attributes, Map<String,Object> params)
            throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            lastResult = SelectionCommand.completeCommand(connection, orderTableName,
                    selectionAttribute,attributes,params);
            lastResultEmpty = !lastResult.next();
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }

    public void deleteOrder(List<String> attributes, Map<String,Object> params)throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            DeleteCommand.completeCommand(connection, orderTableName,attributes,params);
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
            result = SelectionCommand.selectTableRowsCount(connection,orderTableName);
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
        return result;
    }

    public String getOrderById() throws DaoException {
        try {
            if(!lastResultEmpty) {
                return lastResult.getString("ord_id");
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
        return null;
    }

    public void getOrderList(String selectionAttribute,int offset,int limit)
            throws DaoException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            lastResult = SelectionCommand.completeCommandForPagination(connection, orderTableName,
                    selectionAttribute,offset,limit);
            lastResultEmpty=!lastResult.next();
            pool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(),e);
        }
    }


    public Map<String,Object> getOrderSelectionResult(List<String> attributes) throws DaoException {
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
    public Object getOrderSelectionResult(String attribute) throws DaoException {
        try {
            if(!lastResultEmpty) {
                return lastResult.getObject(attribute);
            }
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
        return null;
    }

    public List<Map<String,Object>> getOrdersSelectionResult(List<String> attributes) throws DaoException {
        Map<String,Object> resultOrder = new HashMap<>();
        List<Map<String,Object>> resultOrders = new ArrayList<>();
        try {
            if(!lastResultEmpty) {
                for (String attribute : attributes) {
                    resultOrder.put(attribute, lastResult.getObject(attribute));
                }
                resultOrders.add(resultOrder);
                while(lastResult.next()) {
                    resultOrder = new HashMap<>();
                    for (String attribute : attributes) {
                        resultOrder.put(attribute, lastResult.getObject(attribute));
                    }
                    resultOrders.add(resultOrder);
                }
                lastResult.first();
            }
            return resultOrders;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public List<Object> getOrdersSelectionResult(String attribute) throws DaoException {
        List<Object> resultOrders = new ArrayList<>();
        try {
            if(!lastResultEmpty) {
                resultOrders.add(lastResult.getObject(attribute));
                while(lastResult.next()) {
                    resultOrders.add(lastResult.getObject(attribute));
                }
            }
            return resultOrders;
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    public static List<String> orderAttributes() {
        List<String> attributes = new ArrayList<>();
        attributes.add("ord_id");
        attributes.add("ord_creation_date");
        attributes.add("ord_sum_price");
        attributes.add("ord_is_accepted");
        return attributes;
    }

    public static Map<String,Object> orderParams(Order order) {
        Map<String,Object> params = new HashMap<>();
        params.put("ord_id",order.getId());
        params.put("ord_creation_date",order.getCreationDate());
        params.put("ord_sum_price",order.getSummaryPrice());
        if(order.isAccepted()) {
            params.put("ord_is_accepted", 1);
        } else {
            params.put("ord_is_accepted", 0);
        }
        return params;
    }
}
