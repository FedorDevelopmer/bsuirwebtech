<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/courseView.css" />
<!DOCTYPE html>
<html>
<head>
    <title>Course Description</title>
</head>
<body>
    <h1>Course Description</h1>
    <table>
        <tr>
            <th>Name:</th>
            <td>${course.name}</td>
        </tr>
        <tr>
            <th>Price:</th>
            <td>${course.price}</td>
        </tr>
        <tr>
            <th>Author:</th>
            <td>${course.author}</td>
        </tr>
        <tr>
            <th>Description:</th>
            <td>${course.description}</td>
        </tr>
        <tr>
            <th>Main Technology:</th>
            <td>${course.mainTech}</td>
        </tr>
    </table>
</body>
</html>