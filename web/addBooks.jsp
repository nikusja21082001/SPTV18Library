<%-- 
    Document   : addBooks
    Created on : Feb 17, 2020, 8:44:48 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавление книги</title>
    </head>
    <body>
        <h1>Добавить книгу</h1>
        <p>${info}</p>
        <form method="POST" action="addBook">
            Заголовок: <input type="text" name="title"><br>
            Автор: <input type="text" name="author"><br>
            Год издания: <input type="text" name="publishedYear"><br>
            Считать: <input type="text" name="count"><br>
            Колличество: <input type="text" name="quantity"><br>
            <input type="submit" value="Создать">
        </form>
    </body>
</html>
