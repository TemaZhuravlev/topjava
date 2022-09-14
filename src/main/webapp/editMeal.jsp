<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>${param.action == 'edit' ? 'Редактирование еды' : 'Добавление еды'}</title>
</head>
<body>
<h2>${param.action == 'edit' ? 'Редактирование еды' : 'Добавление еды'}</h2>
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
            <input type="number" name="calories" value="${meal.calories}" required>
        </dd>
    </dl>
    <hr>
    <button type="submit">Сохранить</button>
    <button type="button" onclick="window.history.back()">Отменить</button>
</form>
</body>
</html>
