<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>${questService.quest.title}</title>
</head>
<body>

<h2>Пролог</h2>
<p>${questService.quest.prolog}</p>

<p>Как ваше имя?</p>

<form action="start" method="post">
    <input type="text" id="playerName" name = "playerName"
           placeholder="Введите ваше имя" required
           value="${sessionScope.playerName != null ? sessionScope.playerName : ''}"><br><br>
    <button type="submit">Начать приключение</button>
</form>
</body>
</html>
