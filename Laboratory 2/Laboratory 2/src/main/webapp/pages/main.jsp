<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="messages"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
<html>
<header class="header">
    <div>
        <c:choose>
            <c:when test="${sessionScope.login != null}">
                <h3><fmt:message key="GREETING"/>${sessionScope.login}</h3>
                <form action="/main" method="post">
                    <input type="hidden" name="command" value="logout"/>
                    <button class="button" type="submit">Log out</button>
                </form>
                <form action="/main" method="get">
                    <input type = "hidden" name="command" value="load_user_to_edit"></input>
                    <button class="button" type = "submit">Edit Profile</button>
                </form>
                <form action="/main" method="get">
                    <input type="hidden" name="command" value="form_my_orders">
                    <button class="button" type="submit">My orders</button>
                </form>
                <form action="/main" method="post">
                  <input type="hidden" name="command" value="finalize_order">
                  <button type="submit">Оформить заказ</button><br/>
                </form>
                 <c:if test="${sessionScope.role eq 'admin'}">
                     <form action="/main" method="get">
                         <input type = "hidden" name="command" value="load_orders"></input>
                         <button class="button" type = "submit">Orders</button>
                     </form>
                     <form action="/main" method="post">
                         <input type="hidden" name="command" value="redirect">
                         <input type="hidden" name="redirect" value="true">
                         <input type="hidden" name="page" value="/pages/course/courseCreation.jsp">
                         <button type="submit">Создать курс</button>
                     </form>
                 </c:if>
            </c:when>
            <c:otherwise>
               <form action="/main" method="post">
                    <input type="hidden" name="command" value="redirect">
                    <input type="hidden" name="redirect" value="true">
                    <input type="hidden" name="page" value="/pages/login.jsp">
                    <button class="button" type="submit">Login</button>
                </form>
                <form action="/main" method="post">
                    <input type="hidden" name="command" value="redirect">
                    <input type="hidden" name="redirect" value="true">
                    <input type="hidden" name="page" value="/pages/register.jsp">
                    <button class="button" type = "submit">Register</button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</header>








<c:forEach var="course" items="${applicationScope.courses}" >
    <div>
            <h3>${course.name}</h3>
            <h4>Основная технология курса: ${course.mainTech}</h4>
            <h4>Цена: ${course.price}</h4>
            <c:if test="${sessionScope.role eq 'admin'}">
                <form action="/main" method="get">
                    <input type="hidden"  name="command" value="load_course_to_edit"></input>
                    <input type="hidden"  name="courseId" value="${courses.indexOf(course)}"/>
                    <input type="submit"  value="Изменить курс"></input>
                </form>
            </c:if>
            <form action="/main" method="post">
                <input type="hidden"  name="courseId" value="${courses.indexOf(course)}"/>
                <input type="hidden"  name="command" value="cart_add"></input>
                <c:if test="${sessionScope.cart.contains(course) && sessionScope.role != null}">
                    <input type="submit"  value="Добавить курс в корзину" disabled></input>
                </c:if>
                <c:if test="${!(sessionScope.cart.contains(course) || sessionScope.role == null)}">
                    <input type="submit"  value="Добавить курс в корзину"></input>
                </c:if>
            </form>
            <form action="/main" method="get">
            <input type = "hidden" name="command" value="view_course"></input>
            <input type="hidden"  name="courseId" value="${courses.indexOf(course)}"/>
            <button type = "submit">Показать курс</button>
            </form>
            <c:if test="${sessionScope.role eq 'admin'}">
                <form action="/main" method="post">
                    <input type="hidden"  name="command" value="course_delete"></input>
                 <input type="submit"  value="Удалить курс"></input>
            </form>
        </c:if>
    </div>
</c:forEach>
<form action="/main" method="get">
<input type="hidden" name="command" value="next_page"></input>
<c:if test="${applicationScope.courses.size() < 10}">
<input type="submit" value="Следующая страница" disabled></input>
</c:if>
<c:if test="${applicationScope.courses.size() == 10}">
<input type="submit" value="Следующая страница"></input>
</c:if>
</form>
<form action="/main" method="get">

<input type="hidden" name="command" value="previous_page"></input>
<c:if test="${applicationScope.offset < 10}">
<input type="submit" value="Предыдущая страница" disabled></input>
</c:if>
<c:if test="${applicationScope.offset >= 10}">
<input type="submit" value="Предыдущая страница"></input>
</c:if>
</form>
</html>