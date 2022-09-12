<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Список еды</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Список еды</h2>
<table>
    <a href="meals?action=add">Добавить</a>
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach var="mealTo" items="${mealsTo}">
        <jsp:useBean id="mealTo" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr date-meal-excess="${mealTo.excess}">
            <td>${mealTo.getDate()} ${mealTo.getTime()}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td><a href="meals?action=delete&id=${mealTo.id}">Удалить</a></td>
            <td><a href="meals?action=edit&id=${mealTo.id}">Редактировать</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
