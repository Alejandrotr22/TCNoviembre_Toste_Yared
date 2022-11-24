<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name='author' content='yared'/>
        <link rel='stylesheet' type='text/css' href='css/index.css'>
        <title>Apuestas TostePerez</title>

    </head>
    <body>
        <h1>Apuestas TostePerez</h1>
        <img class="logo" src="resources/logo.png">
        <section>
            <section class="cartaLogin">
                <h3 class="titleLogin">Iniciar Sesión</h3>
                <form class="formulario" action="ServletLoginRegister" method="POST">
                    <%--@declare id="introduce un email válido"--%><label class="label">Email</label>
                    <input class="inputText" type="email" name="email" placeholder="correo" aria-errormessage="Introduce un email válido">
                    <label class="label">Contraseña</label>  
                    <input class="inputText" type="password" name="password" placeholder="contraseña">
                    <input class="inputSubmit" type="submit" id="login" name="login" value="Iniciar Sesión">
                </form>
                <div class="other">
                    <a href="registrarse.jsp"><button class="btn">Registrarse</button></a>
                </div>
            </section>
        </section>
        <p style="color: #ff9100; font-size: 1.8rem; " >${respuesta}</p>
    </body>
</html>
