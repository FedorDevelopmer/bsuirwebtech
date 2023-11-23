<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/orderCreation.css" />
<!DOCTYPE html>
<html>
<head>
    <title>Оформление заказа</title>
</head>
<body>
    <h1>Оформление заказа</h1>
    <h2>Товары в корзине:</h2>
    <c:if test="${sessionScope.chosen != null}">
    <table>
        <tr>
            <th>Наименование</th>
            <th>Основная технология</th>
            <th>Цена</th>
            <c:forEach var="course" items="${sessionScope.chosen}">
                <tr>
                    <td>${course.name}</td>
                    <td>${course.mainTech}</td>
                    <td>${course.price}</td>
                </tr>
            </c:forEach>
        </tr>
        <%-- Получение товаров из сессии --%>
    </table>
    </c:if>
    <c:if test="${sessionScope.chosen == null}">
    <h3>Ваша корзина пуста</h3>
    </c:if>
    <form action="/main" method="post">
        <input type="hidden" name="command" value="create_order" />
        <input type="submit" value="Оформить заказ">
    </form>
</body>
</html>