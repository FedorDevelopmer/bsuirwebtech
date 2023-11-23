<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
    <h1>Registration</h1>
    <form action="/main" method="post">
        <input type="hidden" name="command" value="register">
        <label for="username">Name:</label>
        <input type="text" name="name" required><br><br>

        <label for="surname">Surname:</label>
        <input type="text" name="surname" required><br><br>

         <label for="phone">Phone number:</label>
         <input type="text" name="phone" required><br><br>

         <label for="email">Email:</label>
         <input type="text" name="email" required><br><br>

         <label for="email">Login:</label>
         <input type="text" name="login" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        <input type="radio" name="role" value="user" checked>Обычный пользователь</input><br><br>
        <input type="radio" name="role" value="admin">Администратор</input><br><br>

        <input type="submit" value="Register">
    </form>
    <c:if test="${sessionScope.input_error != null}">
        <label class="error">Введенные данные для обновления некорректны!</label>
    </c:if>
</body>
</html>