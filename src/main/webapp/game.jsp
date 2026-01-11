<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${questService.quest.title}</title>
</head>
<body>
<h1>${questService.quest.title}</h1>

<div>
    <p>Игрок: ${sessionScope.playerName}</p>
    <p>Сыграно игр: ${sessionScope.gamesPlayed != null ? sessionScope.gamesPlayed : 0}</p>
</div>

<hr>

<c:choose>
    <c:when test="${not empty step.options}">
        <h2>${step.text}</h2>
        <form action="game" method="post">
            <input type="hidden" name="currentStep" value="${step.id}">

            <c:forEach items="${step.options}" var="entry">
                <p>
                    <input type="radio" name="answer" value="${entry.key}"
                           id="option${entry.key}"
                           required>
                    <label for="option${entry.key}">${entry.value.text}</label>
                </p>
            </c:forEach>
            <button type="submit">Ответить</button>
        </form>
    </c:when>
    <c:otherwise>
        <h3>${step.text}</h3>
        <p>
            <a href="game?step=start">Играть снова</a> |
            <a href="start">Начать сначала</a>
        </p>
    </c:otherwise>
</c:choose>

<hr>

<div>
    <p>ID сессии: ${pageContext.session.id}</p>
    <p>Текущий шаг: ${step.id}</p>
</div>
</body>
</html>