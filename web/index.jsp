<%-- 
    Document   : newjsp
    Created on : Feb 7, 2020, 8:46:49 AM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Сетевая библиотека группы SPTV18</title>
    </head>
    <body>
        <h1>Сетевая библиотека</h1>
        <p id="info">${info}</p>
        <p>
            <a href="showLogin">Вход</a><br>
            <a href="showNewReader">Зарегистрировать читателя</a><br>
            <a href="listBooks">Cписок книг</a><br>
            <a href="listReaders">Список читателей</a><br>
            <a href="showTakeBook">Выдать книгу</a><br>
            <a href="showReturnBook">Вернуть книгу</a><br>
            <a href="showNewBook">Добавить новую книгу</a><br>
        </p>
        
    </body>
</html>
