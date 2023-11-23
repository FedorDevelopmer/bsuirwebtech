<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/userEdit.css"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User Data</title>
</head>
<body>
    <h1>User edit</h1>
    <form action="/main" method="post">
        <input type="hidden" name="command" value="update_user"/>
        <label for="username">Name:</label>
        <input type="text" name="u_name" value = "${user.name}"  required><br><br>

        <label for="surname">Surname:</label>
        <input type="text" name="u_surname" value="${user.surname}" required><br><br>

        <label for="phone">Phone number:</label>
        <input type="text" name="u_phone_num" value="${user.phoneNumber}" required><br><br>

        <label for="email">Email:</label>
        <input type="text" name="u_email" value="${user.email}" required><br><br>

        <label for="email">Login:</label>
        <input type="text" name="u_login" value="${user.login}" required><br><br>

        <button type="submit">Apply changes</button>
    </form>
    <c:if test="${sessionScope.input_error != null}">
            <label class="error">Введенные данные для обновления некорректны!</label>
    </c:if>
</body>
</html>