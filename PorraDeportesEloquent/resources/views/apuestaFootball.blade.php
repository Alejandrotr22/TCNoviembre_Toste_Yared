<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Laravel</title>

        <!-- Fonts -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel='stylesheet' type='text/css' href='css/Principal.css'>

    </head>
    <body class="antialiased">


    <main class="container-fluid">

        <section class="justify-content-center">
            <div class="">
                <div class="list-group d-block">
                    <h1>Tipos de apuestas</h1>
                    <a class="list-group-item list-group-item-action active" data-toggle="list"
                       data-target="#ganadorTab">Ganador</a>
                    <a class="list-group-item list-group-item-action" data-toggle="list"
                       data-target="#marcadorTab">Marcador</a>
                </div>
            </div>
            <div class="mt-3">
                <div class="tab-content">
                    <!-- formulario de Ganador -->
                    <div class="tab-pane fade show active row" id="ganadorTab">
                        <!-- formulario -->
                        <form class="formGanador" action="ControllerApuestaFootballGanador" method="POST">
                            @csrf
                            <div class="form-row justify-content-center">
                                <div class="col-md-3 form-group">
                                    <label for="">Ganador</label>
                                    <select class="form-control" id="ganador" name="ganador">
                                        <option>{{session('participantes')[0]->nombre}}</option>
                                        <option>{{session('participantes')[1]->nombre}}</option>
                                        <option>{{session('participantes')[2]->nombre}}</option>
                                    </select>
                                </div>
                                <div class="col-md-5 text-center">
                                    <label>Cuotas</label>
                                    <br>
                                    <!-- Nombre equipo 1 -->
                                    <label for="">{{session('participantes')[0]->nombre}}</label>
                                    <input class="cuota" type="text" name="cuota{{session('participantes')[0]->nombre}}" id="cuota{{session('participantes')[0]->nombre}}" value="{{session('cuotas')['cuotaEquipo1']}}"  readonly>
                                    <input class="cuota" type="text" name="cuota{{session('participantes')[2]->nombre}}" id="cuota{{session('participantes')[2]->nombre}}" value="{{session('cuotas')['cuotaEmpate']}}"  readonly>
                                    <input class="cuota" type="text" name="cuota{{session('participantes')[1]->nombre}}" id="cuota{{session('participantes')[1]->nombre}}" value="{{session('cuotas')['cuotaEquipo2']}}"  readonly>
                                    <!-- Nombre equipo 2 -->
                                    <label for="">{{session('participantes')[1]->nombre}}</label>
                                    <br>
                                    <label for="">{{session('participantes')[2]->nombre}}</label>
                                </div>
                            </div>
                            <div class="form-row justify-content-center">
                                <div class="col-md-5 text-center">
                                    <label>Cuantia</label>
                                    <input type="number" step=".01" class="" name="CuantiaGanador" id="CuantiaGanador" min="1" value="1" max="{{session('user')->saldo}}" oninvalid="this.setCustomValidity('No puedes apostar una cantidad mayor a tu saldo o inferior a 0')" onchange="this.setCustomValidity('')">
                                    <span>€</span>
                                    <span>Tu saldo actual: {{session('user')->saldo}}</span>
                                </div>
                                <div class="col-md-5 text-center">
                                    <!-- Nombre del submit -->
                                    <input type="submit" name="ApuestaGanador" class="btn btn-primary" value="Crear Apuesta">
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- fromulario de Marcador -->
                    <div class="tab-pane fade show row" id="marcadorTab">
                        <!-- formulario -->
                        <form class="formGanador" action="ControllerApuestaFootballResultado" method="POST">
                            @csrf
                            <div class="form-row justify-content-center">
                                <div class="col-md-6 form-group text-center">
                                    <label>Marcador</label>
                                    <br>
                                    <!-- Nombre equipo 1 -->
                                    <label for="">{{session('participantes')[0]->nombre}}</label>
                                    <input class="marcador" type="text" name="marcador1" id="marcador1"  >
                                    -
                                    <input class="marcador" type="text" name="marcador2" id="marcador2"  >
                                    <!-- Nombre equipo 2 -->
                                    <label for="">{{session('participantes')[1]->nombre}}</label>
                                </div>
                                <div class="col-md-5 text-center">
                                    <label>Cuotas</label>
                                    <br>
                                    <label for="">{{session('participantes')[0]->nombre}}</label>
                                    <input class="cuota" type="text" name="cuota{{session('participantes')[0]->nombre}}" id="cuota{{session('participantes')[0]->nombre}}" value="{{session('cuotas')['cuotaEquipo1']}}" readonly >
                                    <input class="cuota" type="text" name="cuota{{session('participantes')[1]->nombre}}" id="cuota{{session('participantes')[1]->nombre}}" value="{{session('cuotas')['cuotaEquipo2']}}"  readonly>
                                    <label for="">{{session('participantes')[1]->nombre}}</label>
                                </div>
                            </div>
                            <div class="form-row justify-content-center">
                                <div class="col-md-5 text-center">
                                    <label>Cuantia</label>
                                    <input type="number" step=".01" class="" name="CuantiaGanador" min="1" value="1" max="{{session('user')->saldo}}" oninvalid="this.setCustomValidity('No puedes apostar una cantidad mayor a tu saldo')">
                                    <span>€</span>
                                    <span>Tu saldo actual: {{session('user')->saldo}}</span>
                                </div>
                                <div class="col-md-5 text-center">
                                    <!-- Nombre del submit -->
                                    <input type="submit" name="apostarResultado" class="btn btn-primary" value="Crear Apuesta">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <form class="usuario mt-2" action="principalIndex" method="GET" style="float: right; margin: 0">
            <input type="submit" name="" class="btn btn-primary" value="Volver">
        </form>
        <form class="usuario mt-2" action="vistaUsuario" method="GET">
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
    </main>

    </body>
</html>
