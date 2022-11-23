<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Apuesta Tenis</title>
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
                    <h1>Tipos de apuestas</h1>
                    <a class="list-group-item list-group-item-action active" data-toggle="list"
                        data-target="#ganador">Ganador</a>
                </div>
            </div>
            <div class="mt-3">
                <div class="tab-content">
                    <!--  Formulario Ganador  -->
                    <div class="tab-pane fade show active row" id="ganador">
                        <!-- Nombre del formulario -->
                        <form class="formGanador" action="/NOMBRE-SERVLET" method="POST">
                            <div class="form-row justify-content-center">
                                <div class="col-md-3 form-group">
                                    <label for="">Ganador</label>
                                    <select class="form-control" id="ganador" name="ganador">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>X</option>
                                    </select>
                                </div>
                                <div class="col-md-5 text-center">
                                    <label>Cuotas</label>
                                    <br>
                                    <label for="">Equipo1</label>
                                    <input class="cuota" type="text" name="cuota1" id="cuota1" value="1" readonly >
                                    <input class="cuota" type="text" name="cuota2" id="cuota2" value="1" readonly >
                                    <label for="">Equipo2</label>
                                </div>
                            </div>
                            <div class="form-row justify-content-center">
                                <div class="col-md-5 text-center">
                                    <label>Cuantia</label>
                                    <input type="number" class="" name="CuantiaGanador" min="1" value="1">
                                    <span>€</span>
                                </div>
                                <div class="col-md-5 text-center">
                                    <input type="submit" name="CrearApuesta" class="btn btn-primary" value="Crear Apuesta">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </section>








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