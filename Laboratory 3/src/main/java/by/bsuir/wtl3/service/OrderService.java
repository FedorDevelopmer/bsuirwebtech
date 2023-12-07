package by.bsuir.wtl3.service;


import by.bsuir.wtl3.dao.CourseDao;
import by.bsuir.wtl3.dao.OrderDao;
import by.bsuir.wtl3.dao.UserDao;
import by.bsuir.wtl3.entities.Course;
import by.bsuir.wtl3.entities.Order;
import by.bsuir.wtl3.entities.User;
import by.bsuir.wtl3.exceptions.DaoException;
import by.bsuir.wtl3.exceptions.ServiceException;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private UserDao userDao;

    public Order createOrder(List<Course> courses,String userLogin) throws ServiceException {
        try {
            User user = userDao.getUserByLogin(userLogin);
            if(user != null) {
                double summaryPrice = 0;
                for (Course course : courses) {
                    summaryPrice += course.getPrice();
                }
                Order order = new Order(0, new Date(System.currentTimeMillis()), summaryPrice, false,user);
                long orderId = orderDao.add(order);
                return order;
            } else {
                return null;
            }

        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public Order findOrderById(int id) throws ServiceException {
        try {
            return orderDao.getById(id);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<Order> getOrdersPageElements(int firstResult, int maxResult) throws ServiceException {
        try{
            List<Order> courses = orderDao.getPageElements(firstResult,maxResult);
            return courses;
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public long getOrdersCount() throws ServiceException {
        try{
            long coursesCount = orderDao.getTableRowsCount();
            return coursesCount;
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void updateOrder(Order order) throws ServiceException{
        try {
            orderDao.update(order);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void deleteOrder(Order order) throws ServiceException{
        try {
            orderDao.delete(order.getId());
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Transactional
    public List<Order> getOrdersByCourse(Course course) throws ServiceException {
        try{
            List<Order> orders = orderDao.getOrdersByCourse(course);
            return orders;
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Transactional
    public List<Order> getOrdersByUser(User user) throws ServiceException {
        try{
            List<Order> orders = orderDao.getOrdersByUser(user);
            return orders;
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }


}
