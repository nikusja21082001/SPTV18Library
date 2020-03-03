<%-- 
    Document   : showReturnBook
    Created on : Feb 20, 2020, 8:32:02 AM
    Author     : user
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Возврат книги</title>
    </head>
    <body>
        <h1>Выберите возвращаемую книгу:</h1>
        <p id="info">${info}</p>
        <form action="returnBook" method="POST">
        <select name="historyId" size="5">
            <c:forEach var="history" items="${listHistories}">
                <option value="${history.id}">
                    Книгу \"${history.book.title}\" читает ${history.reader.firstname} ${history.reader.lastname} 
                </option>
            </c:forEach>
        </select>
            <br>
            <button type="submit">Вернуть выбранную книгу</button>
        </form>
    </body>
</html>
