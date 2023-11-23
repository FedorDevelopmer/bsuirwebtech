<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<!DOCTYPE html>
<html>
<head>
    <title>Создание курса по программированию</title>
</head>
<body>
    <h1>Создание курса по программированию</h1>

    <form action="/main" method="post" accept-charset="UTF-8" >
        <input type="hidden" name="command" value="add_course"/>
        <label for="title">Название курса:</label><br>
        <input type="text" name="c_name"  required><br>

        <label for="author">Автор:</label><br>
        <input type="text" name="c_author"  required><br>

        <label for="technology">Ключевая технология:</label><br>
        <input type="text" name="c_main_tech"  required><br>

        <label for="price">Цена:</label><br>
        <input type="number" name="c_price" min="0" required><br>

        <label for="description">Описание:</label><br>
        <textarea name="c_description" rows="4" cols="50"></textarea><br>

        <input type="submit" value="Создать курс">
    </form>
    <c:if test="${sessionScope.input_error != null}">
        <label class="error">Введенные данные для обновления некорректны!</label>
    </c:if>
</body>
</html>