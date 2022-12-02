<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="es.iespuertodelacruz.yt.porradeportes.entities.Usuario" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Apuesta Football</title>
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
                <h1>Eventos</h1>

        <div class="mt-3">
            <div class="tab-content">
                <!-- formulario de Ganador -->
                    <!-- formulario -->
                    <form class="formFutbol" action="ServletApuestaFootball" method="POST">

                        <div class="form-row justify-content-center">
                            <div class="col-md-3 form-group">
                                <label for="">Eventos Futbol</label>
                                <select class="form-control" id="evento" name="evento">
                                    <c:forEach items="${eventosFutbol}" var="evento">
                                        <option value="${evento.getId()}">${evento.getNombre()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-5 text-center">
                                <!-- Nombre del submit -->
                                <input type="submit" name="elegirEvento" class="btn btn-primary" value="Apostar en este evento">
                            </div>
                        </div>
                    </form>
                </div>
            <form class="formTenis mt-2" action="ServletApuestaTenis" method="POST">
                <div class="form-row justify-content-center">
                    <div class="col-md-3 form-group">
                        <label for="">Eventos Tenis</label>
                        <select class="form-control" id="evento" name="evento">
                            <c:forEach items="${eventosTenis}" var="evento">
                                <option value="${evento.getId()}"><c:out value="${evento.getNombre()}"></c:out></option>
                            </c:forEach>
                            <!--
                                    <option>${equipo1}</option>
                                    <option>${equipo2}</option>
                                    <option>${empate}</option>-->
                        </select>
                    </div>
                    <div class="col-md-5 text-center">
                        <!-- Nombre del submit -->
                        <input type="submit" name="elegirEvento" class="btn btn-primary" value="Apostar en este evento">
                    </div>
                </div>
            </form>
        </div>
        <form class="formFormula mt-2" action="ServletApuestaFormula" method="POST">
            <div class="form-row justify-content-center">
                <div class="col-md-3 form-group">
                    <label for="">Eventos Formula 1</label>
                    <select class="form-control" id="evento" name="evento">
                        <c:forEach items="${eventosFormula}" var="evento">
                            <option value="${evento.getId()}"><c:out value="${evento.getNombre()}"></c:out></option>
                        </c:forEach>
                        <!--
                                    <option>${equipo1}</option>
                                    <option>${equipo2}</option>
                                    <option>${empate}</option>-->
                    </select>
                </div>
                <div class="col-md-5 text-center">
                    <!-- Nombre del submit -->
                    <input type="submit" name="elegirEvento" class="btn btn-primary" value="Apostar en este evento">
                </div>
            </div>
        </form>

             <form class="usuario mt-2" action="ServletUsuario" method="GET">
                <input type="submit" name="" class="btn btn-primary" value="Ver Usuario">
            </form>
        <a href="login.jsp" style="float: right; margin-right: 3%">Logout</a>

    </section>

</main>






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