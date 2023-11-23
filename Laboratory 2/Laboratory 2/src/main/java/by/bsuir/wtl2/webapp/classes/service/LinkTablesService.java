package by.bsuir.wtl2.webapp.classes.service;

import by.bsuir.wtl2.webapp.classes.dao.objects.*;
import by.bsuir.wtl2.webapp.classes.entities.Client;
import by.bsuir.wtl2.webapp.classes.entities.Course;
import by.bsuir.wtl2.webapp.classes.entities.Order;
import by.bsuir.wtl2.webapp.classes.entities.User;
import by.bsuir.wtl2.webapp.classes.exceptions.DaoException;
import by.bsuir.wtl2.webapp.classes.exceptions.ServiceException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkTablesService {
    public boolean createLinkUserOrder(User user, Order order) throws ServiceException {
        try {
            UserDao userDao = new UserDao();
            OrderDao orderDao = new OrderDao();
            LinkTablesDao tablesDao = new LinkTablesDao();
            List<String> attributes =  UserDao.userAttributes();
            Map<String, Object> params = UserDao.userParams(user);
            userDao.getUser("*",attributes,params);
            attributes.remove("u_id");
            params.remove("u_id");
            Object userId = userDao.getUserSelectionResult("u_id");
            attributes =  OrderDao.orderAttributes();
            params = OrderDao.orderParams(order);
            attributes.remove("ord_id");
            params.remove("ord_id");
            orderDao.getOrder("*",attributes,params);
            List<Object> orderIds = orderDao.getOrdersSelectionResult("ord_id");
            int orderId = Integer.parseInt(String.valueOf(orderIds.get(orderIds.size() - 1)));
            tablesDao.createLink("users_orders",linkAttributes("u_id","ord_id"),
                    linkParam("u_id","ord_id",userId, orderId));
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }
    public boolean createLinkOrderCourse(Course course, Order order) throws ServiceException {
        try {
            CourseDao courseDao = new CourseDao();
            OrderDao orderDao = new OrderDao();
            LinkTablesDao tablesDao = new LinkTablesDao();
            List<String> attributes =  CourseDao.courseAttributes();
            Map<String, Object> params = CourseDao.courseParams(course);
            attributes.remove("c_id");
            params.remove("c_id");
            courseDao.getCourse("*",attributes,params);
            Map<String,Object> courseId = courseDao.getCourseSelectionResult("c_id");
            attributes =  OrderDao.orderAttributes();
            params = OrderDao.orderParams(order);
            attributes.remove("ord_id");
            params.remove("ord_id");
            orderDao.getOrder("*",attributes,params);
            List<Object> orderIds = orderDao.getOrdersSelectionResult("ord_id");
            int orderId = Integer.parseInt(String.valueOf(orderIds.get(orderIds.size() - 1)));
            tablesDao.createLink("orders_courses",linkAttributes("c_id","ord_id"),
                    linkParam("c_id","ord_id",courseId.get("c_id"), orderId));
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<Order> getOrdersByUser(User user) throws ServiceException {
        try {
            List<Order> result = new ArrayList<>();
            OrderService orderService = new OrderService();
            UserDao userDao = new UserDao();
            OrderDao orderDao = new OrderDao();
            LinkTablesDao linkTablesDao = new LinkTablesDao();
            userDao.getUser("*", UserDao.userAttributes(),
                    UserDao.userParams(user));
            Object clientId = userDao.getUserSelectionResult("u_id");
            List<String> attributes = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();
            attributes.add("u_id");
            params.put("u_id", clientId);
            linkTablesDao.getLinks("users_orders", attributes, params);
            List<Object> userOrdersLink = linkTablesDao.getLinksSelectionResult("ord_id");
            for (Object orderIdObj : userOrdersLink) {
                int orderId = Integer.parseInt(String.valueOf(orderIdObj));
                List<String> orderAttributes = new ArrayList<>();
                Map<String, Object> orderParams = new HashMap<>();
                orderAttributes.add("ord_id");
                orderParams.put("ord_id", orderId);
                orderDao.getOrder("*", orderAttributes, orderParams);
                Map<String, Object> fullOrderParams = orderDao
                        .getOrderSelectionResult(OrderDao.orderAttributes());
                Order order = new Order();
                orderService.fillOrderWithParams(order, fullOrderParams);
                result.add(order);
            }
            return result;
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<Course> getCoursesByOrder(Order order) throws  ServiceException {
        try {
            List<Course> result = new ArrayList<>();
            CourseService courseService = new CourseService();
            CourseDao courseDao = new CourseDao();
            OrderDao orderDao = new OrderDao();
            LinkTablesDao linkTablesDao = new LinkTablesDao();
            orderDao.getOrder("*", OrderDao.orderAttributes(),
                    OrderDao.orderParams(order));
            Object orderId = orderDao.getOrderSelectionResult("ord_id");
            List<String> attributes = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();
            attributes.add("ord_id");
            params.put("ord_id", orderId);
            linkTablesDao.getLinks("orders_courses", attributes, params);
            List<Object> orderCoursesLink = linkTablesDao.getLinksSelectionResult("c_id");
            for (Object courseIdObj : orderCoursesLink) {
                int courseId = Integer.parseInt(String.valueOf(courseIdObj));
                List<String> courseAttributes = new ArrayList<>();
                Map<String, Object> courseParams = new HashMap<>();
                courseAttributes.add("c_id");
                courseParams.put("c_id", courseId);
                courseDao.getCourse("*", courseAttributes, courseParams);
                Map<String, Object> fullCourseParams = courseDao
                        .getCourseSelectionResult(CourseDao.courseAttributes());
                Course course = new Course();
                courseService.fillCourseWithParams(course, fullCourseParams);
                result.add(course);
            }
            return result;
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public static List<String> linkAttributes(String firstIdName,String secondIdName) {
        List<String> attributes = new ArrayList<>();
        attributes.add(firstIdName);
        attributes.add(secondIdName);
        return attributes;
    }

    public static Map<String,Object> linkParam(String firstIdName,String secondIdName,
                                               Object firstParam,Object secondParam) {
        Map<String,Object> params = new HashMap<>();
        params.put(firstIdName,firstParam);
        params.put(secondIdName,secondParam);
        return params;
    }
}
