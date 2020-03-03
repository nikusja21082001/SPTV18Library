<%-- 
    Document   : showLogin
    Created on : Feb 7, 2020, 8:59:03 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Вход в систему</title>
    </head>
    <body>
        <h1>Введите логин и пароль</h1>
        <p>${info}</p>
        <form method="POST" action="login">
        Логин: <input type="text" name="login"><br>
        Пароль: <input type="password" name="password"><br>
        <input type="submit" value="Войти">
    </form>
    </body>
</html>
