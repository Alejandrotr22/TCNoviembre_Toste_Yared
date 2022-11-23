<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>Registrarse</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name='author' content='yared'/>
        <link rel='stylesheet' type='text/css' href='css/index.css'>
    </head>
    <body>
    <h1>Apuestas TostePerez</h1>
    <img class="logo" src="resources/logo.png">
    <section>
        <section class="cartaLogin">
            <h3 class="titleLogin">Registro</h3>
            <form class="formulario" action="ServletLoginRegister" method="POST">
                <label class="label">Usuario</label>
                <input class="inputText" type="text" name="usuario" placeholder="nombre de usuario">
                <label class="label">Email</label>
                <input class="inputText" type="email" name="email" placeholder="correo">
                <label class="label">Contraseña</label>
                <input class="inputText" type="password" name="password" placeholder="contraseña">
                <input class="inputSubmit" type="submit" name="registro" value="Registrarse">
            </form>
            <div class="other">
                <a href="login.jsp"><button class="btn">Iniciar Sesión</button></a>
            </div>
        </section>
    </section>
    <p style="color: #ff9100; font-size: 1.8rem; " >${respuesta}</p>
    </body>
</html>
