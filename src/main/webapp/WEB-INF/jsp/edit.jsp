<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Редактирование еды</title>
</head>
<body>
<h2>Редактирование</h2>
<form method="post" action="meals">
    <input type="hidden" name="id" value="${meal.id}">
    <dl>
        <dt>Дата и время</dt>
        <dd>
            <input type="datetime-local" name="date-time" value="${meal.dateTime}" required>
        </dd>
    </dl>
    <dl>
        <dt>Описание</dt>
        <dd>
            <input type="text" name="description" value="${meal.description}" required>
        </dd>
    </dl>
    <dl>
        <dt>Калории</dt>
        <dd>
            <c:if test="${meal.calories != 0}">
                <input type="text" name="calories" value="${meal.calories}" required>
            </c:if>
            <c:if test="${meal.calories == 0}">
                <input type="text" name="calories" value="1000" required>
            </c:if>
        </dd>
    </dl>
    <hr>
    <button type="submit">Сохранить</button>
    <button type="button" onclick="window.history.back()">Отменить</button>
</form>

</body>
</html>
