<%--
    Document   : lidtReaders
    Created on : Feb 12, 2020, 2:48:50 PM
    Author     : user
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список книг</title>
    </head>
    <body>
        <h1>Список книг:</h1>
        <c:forEach var="book" items="${listBooks}" varStatus="status">
        <li>${status.index+1}. ${book.title}. ${book.author}. ${book.publishedYear}. ${book.count}</li>
        </c:forEach>
    </body>
</html>
