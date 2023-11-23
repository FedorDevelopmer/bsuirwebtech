<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myOrders.css"/>
<!DOCTYPE html>
<html>
<head>
    <title>Мои заказы</title>
</head>
<body>
    <h1>Мои заказы:</h1>
        <c:if test="${sessionScope.orders != null && !sessionScope.orders.isEmpty()}">
            <c:forEach var="order" items="${sessionScope.orders}">
            <div>
                <c:forEach var="course" items="${sessionScope.courses.get(orders.indexOf(order))}">
                <div>
                   <h3>${course.name}</h3>
                   <h4>Основная технология курса: ${course.mainTech}</h4>
                   <h4>Цена: ${course.price}</h4>
                </div>
                </c:forEach>
                <h3>Суммарная цена: ${order.summaryPrice}</h3>
                <h3>Дата оформления: ${order.creationDate}</h3>
                <c:if test="${order.isAccepted()}">
                    <h3 class="accepted">Ваш заказ принят!</h3>
                </c:if>
                <c:if test="${!order.isAccepted()}">
                    <h3 class="processing">Ваш заказ в обработке</h3>
                </c:if>
            </div>
            </c:forEach>
        </c:if>
        <c:if test="${sessionScope.orders == null || sessionScope.orders.isEmpty()}">
        <h3>Вы пока не совершали заказов...</h3>
        </c:if>
    <%-- Форма для ввода деталей заказа --%>
    <form action="/main" method="get">
        <input type="hidden" name="command" value="main" />
        <button class="button" type="submit">Назад</button>
    </form>
</body>
</html>