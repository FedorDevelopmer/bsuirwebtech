package by.bsuir.wtl2.webapp.classes.service;

import by.bsuir.wtl2.webapp.classes.dao.objects.CourseDao;
import by.bsuir.wtl2.webapp.classes.dao.objects.OrderDao;
import by.bsuir.wtl2.webapp.classes.entities.Course;
import by.bsuir.wtl2.webapp.classes.entities.Order;
import by.bsuir.wtl2.webapp.classes.exceptions.DaoException;
import by.bsuir.wtl2.webapp.classes.exceptions.ServiceException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    public boolean createOrder(Order order) throws ServiceException {
        try {
            OrderDao orderDao = new OrderDao();
            List<String> attributes =  OrderDao.orderAttributes();
            Map<String, Object> params = OrderDao.orderParams(order);
            attributes.remove("ord_id");
            params.remove("ord_id");
            orderDao.addOrder(attributes, params);

            return true;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }
    public boolean deleteOrder(Order order) throws ServiceException{
        try {
            OrderDao orderDao = new OrderDao();
            Map<String, Object> orderParams = OrderDao.orderParams(order);
            orderDao.deleteOrder(OrderDao.orderAttributes(), orderParams);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
        return true;
    }
    public boolean updateOrder(Order originalOrder,Order updatedOrder) throws ServiceException{
        try {
            OrderDao userDao = new OrderDao();
            List<String> attributes = OrderDao.orderAttributes();
            Map<String, Object> oldParams = OrderDao.orderParams(originalOrder);
            Map<String, Object> newParams = OrderDao.orderParams(updatedOrder);
            userDao.updateOrder(attributes, oldParams, attributes, newParams);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
        return true;
    }

    public int getOrderId(Order order) throws ServiceException{
        try {
            OrderDao orderDao = new OrderDao();
            List<String> attributes = OrderDao.orderAttributes();
            Map<String, Object> params = OrderDao.orderParams(order);
            orderDao.getOrder("*",attributes,params);
            Object orderIdObj = orderDao.getOrderSelectionResult("ord_id");
            return Integer.parseInt(String.valueOf(orderIdObj));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }
    public List<Order> getAllOrders(int offset) throws ServiceException{
        try {
            OrderDao orderDao = new OrderDao();
            List<Order> orders = new ArrayList<>();
            orderDao.getOrderList("*",offset,10);
            List<Map<String, Object>> ordersParams = orderDao.getOrdersSelectionResult(OrderDao
                    .orderAttributes());
            for(Map<String,Object> orderParams : ordersParams) {
                Order currentOrder = new Order();
                fillOrderWithParams(currentOrder,orderParams);
                orders.add(currentOrder);
            }
            return orders;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void fillOrderWithParams(Order order,Map<String,Object> params) {
        order.setId(Integer.parseInt(String.valueOf(params.get("ord_id"))));
        String dateString = String.valueOf(params.get("ord_creation_date"));
        order.setCreationDate(Date.valueOf(dateString));
        order.setSummaryPrice(Double.parseDouble(String.valueOf(params.get("ord_sum_price"))));
        order.setAccepted(Boolean.parseBoolean(String.valueOf(params.get("ord_is_accepted"))));
    }
}
