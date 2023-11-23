<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/orderAccept.css" />
<!DOCTYPE html>
<html>
<head>
    <title>Принятие заказов</title>
</head>
<body>
    <c:if test="${applicationScope.orders != null && !applicationScope.orders.isEmpty()}">
        <c:forEach var="order" items="${applicationScope.orders}">
            <h3>${order.creationDate}</h3>
            <h4>Общая цена: ${order.summaryPrice}</h4>
            <form action="/main" method="post">
            <input type="hidden" name="command" value="change_order_status">
            <c:if test="${sessionScope.changed_orders == null}">
            <c:if test="${order.isAccepted()}">
                <button type="submit" name="orderId" value="${orders.indexOf(order)}">Отмена принятия</button>
            </c:if>
            <c:if test="${!order.isAccepted()}">
                <button type="submit" name="orderId" value="${orders.indexOf(order)}" >Принять заказ</button>
            </c:if>
            </c:if>
             <c:if test="${sessionScope.changed_orders != null}">
                <c:if test="${order.isAccepted() && sessionScope.changed_orders.contains(order)}">
                     <button type="submit" name="orderId" value="${orders.indexOf(order)}">Принять заказ</button>
                </c:if>
                <c:if test="${order.isAccepted() && !sessionScope.changed_orders.contains(order)}">
                     <button type="submit" name="orderId" value="${orders.indexOf(order)}">Отмена принятия</button>
                </c:if>
                <c:if test="${!order.isAccepted() && sessionScope.changed_orders.contains(order)}">
                     <button type="submit" name="orderId" value="${orders.indexOf(order)}" >Отмена принятия</button>
                </c:if>
                <c:if test="${!order.isAccepted() && !sessionScope.changed_orders.contains(order)}">
                     <button type="submit" name="orderId" value="${orders.indexOf(order)}" >Принять заказ</button>
                </c:if>
            </c:if>
            </form>
            <form action="/main" method="post">
                <input type="hidden" name="command" value="order_delete">
                <button type="submit" name="orderId" value="${orders.indexOf(order)}">Удалить заказ</button>
            </form>
            <br><br>
            <c:if test="${sessionScope.changed_orders.contains(order)}">
               <h4>Заказ ожидает внесения изменений.</h4>
               <br><br>
            </c:if>

        </c:forEach>
    </c:if>
    <%-- Форма для ввода деталей заказа --%>
    <form action="/main" method="get">
        <input type="hidden" name="command" value="redirect" />
        <input type="hidden" name="redirect" value="true" />
        <input type="hidden" name="page" value="/pages/main.jsp" />
        <button type="submit">Назад</button>
    </form>
    <form action="/main" method="get">
       <input type="hidden" name="command" value="orders_accept" />
       <button type="submit">Сохранить изменения</button>
    </form>
    <form action="/main" method="get">
            <input type="hidden" name="command" value="prev_orders_page" />
            <c:if test="${applicationContext.orders_offset > 0}">
                <button type="submit">Предыдущая страница</button>
            </c:if>
            <c:if test="${applicationContext.orders_offset <= 0}">
               <button type="submit" disabled>Предыдущая страница</button>
            </c:if>

    </form>
    <form action="/main" method="get">
        <input type="hidden" name="command" value="next_orders_page" />
        <c:if test="${applicationContext.orders.size() < 10}">
        <button type="submit" value= disabled>Следующая страница</button>
        </c:if>
        <c:if test="${applicationContext.orders.size() > 10}">
        <button type="submit" disabled>Следующая страница</button>
        </c:if>
    </form>
</body>
</html>