<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Usuario</title>
    <title>Apuestas </title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Course CSS -->
    <link rel="stylesheet" href="css/Principal.css" />
</head>
<body>
    <h1>${usuario.getNombre()}<h1>

    <form class="formGanador col-md-3 mt-3" action="ServletUsuario" method="POST">
        <h3>Modificar Saldo</h3>
        <label for="" class="">Nuevo Saldo</label>
        <span style="float: right; font-size: 1.5rem">Saldo actual: ${usuario.getSaldo()}</span>
        <input type="number" name="saldo" class="form-control" min="0" step="0.01" >
        <br>
        <input type="submit" name="actualizar" class="form-control" value="Actualizar Saldo">
    </form>
        <form class="usuario mt-2" action="ServletPrincipal" method="GET" style="float: right">
            <input type="submit" name="" class="btn btn-primary" value="Volver">
        </form>
    <br>
    <h3>Tus apuestas: </h3>

<textarea class="resultado" name="res" id=""  rows="20">
    <c:forEach items="${apuestas}" var="apuesta">
----${apuesta.getId()}----
${apuesta.getPrediccion()}
${apuesta.getCuota()}
${apuesta.getCantidad()}
${apuesta.getEstado()}
---------
    </c:forEach>
 </textarea>



















    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>