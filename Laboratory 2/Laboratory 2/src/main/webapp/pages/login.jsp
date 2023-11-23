<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
<html>

<head>
    <meta charset="UTF-8">
    <title>Admin Login</title>
</head>
<body>
     <h1>Admin Login</h1>
     <form action="/main" method="post">
        <input type="hidden" name="command" value="login">
        <label for="email">Login:</label>
        <input type="text" id="login" name="login" required><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        <button type="submit">Login</button>
     </form>
     <c:if test="${sessionScope.input_error != null}">
             <label class="error">Введенные данные для обновления некорректны!</label>
     </c:if>
   </body>
</html>