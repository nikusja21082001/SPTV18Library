<%-- 
    Document   : showNewBook
    Created on : Feb 17, 2020, 8:27:07 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить новую книгу</title>
    </head>
    <body>
        <h1>Добавить новую книгу</h1>
        <p id="info">${info}</p>
        <form action="addBook" method="POST">
            Название книги: <input type="text" name="title" value="${title}"><br>
            Автор книги: <input type="text" name="author"  value="${author}"><br>
            Год издания книги: <input type="text" name="publishedYear" value="${publishedYear}"><br>
            Количество экземпляров книги: <input type="text" name="quantity" value="${quantity}"><br> 
            <button type="submit">Добавить</button>
        </form>
    </body>
</html>
