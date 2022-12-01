<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="es.iespuertodelacruz.yt.porradeportes.entities.Usuario" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Apuesta Formula 1</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<!-- Course CSS -->
<link rel="stylesheet" href="css/Principal.css" />
</head>
<body>

    <main class="container-fluid">

        <section class="justify-content-center">
            <div class="">
                <div class="list-group d-block">
                        <h2>${evento.getNombre()}</h2>
                </div>
            </div>
            <div class="mt-3">
                <div class="tab-content">
                    <div class="tab-pane fade show active row" id="ganador">
                        <form class="formGanador" action="ServletApuestaFormula" method="POST">
                            <div class="form-row justify-content-center">
                                <div class="col-md-3 form-group">
                                    <label for="">Ganador</label>
                                    <select class="form-control" id="ganador" name="ganador">
                                        <c:forEach items="${participantes}" var="participante">
                                            <option><c:out value="${participante}"></c:out></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-row justify-content-center">
                                <div class="col-md-5 text-center">
                                    <label>Cuantia</label>
                                    <input type="number" step=".01" class="" name="CuantiaGanador" min="1" value="1" max="${user.getSaldo()}" oninvalid="this.setCustomValidity('No puedes apostar una cantidad mayor a tu saldo')">
                                    <span>€</span>
                                    <span>Tu saldo actual: ${user.getSaldo()}</span>
                                </div>
                                <div class="col-md-5 text-center">
                                    <input type="submit" name="ApuestaGanador" class="btn btn-primary" value="Crear Apuesta">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </section>

        <form class="usuario mt-2" action="ServletUsuario" method="GET" style="float: right">
            <input type="submit" name="" class="btn btn-primary" value="Ver Usuario">
        </form>






    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

</body>
</html>