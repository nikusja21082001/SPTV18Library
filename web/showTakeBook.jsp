<%-- 
    Document   : showTakeBook
    Created on : Feb 17, 2020, 10:10:29 AM
    Author     : user
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Выдача книги</title>
    </head>
    <body>
        <h1>Выдача книги</h1>
        <p id="info">${info}</p>
        <form action="createHistory" method="POST">
            Список читателей: 
            <select name="readerId">
                <c:forEach var="reader" items="${listReaders}">
                    <option value="${reader.id}">${reader.firstname} ${reader.lastname}</option>
                </c:forEach>
            </select>
            <br>
            Список книг: 
            <select name="bookId">
                <c:forEach var="book" items="${listBooks}">
                    <option value="${book.id}">${book.title}. ${book.author}. ${book.publishedYear}</option>
                </c:forEach>
            </select>
            <br>
            <button type="submit">Выдать книгу</button>
        </form>
    </body>
</html>
