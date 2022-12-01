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
            <h1>Eventos</h1>
            <div class="mt-3">
                <div class="tab-content">
                    <!-- formulario de Ganador -->
                    <!-- formulario -->
                    <form class="formFutbol mt-2" action="ControllerApuestaFootball" method="POST">
                        @csrf
                        <div class="form-row justify-content-center">
                            <div class="col-md-3 form-group">
                                <label for="">Eventos Futbol</label>
                                <select class="form-control" id="evento" name="evento">
                                    @foreach(session("eventosFootball") as $evento)
                                        <option value="{{$evento->id}}">{{$evento->nombre}}</option>
                                    @endforeach
                                </select>
                            </div>
                            <div class="col-md-5 text-center">
                                <!-- Nombre del submit -->
                                <input type="submit" name="elegirEvento" class="btn btn-primary" value="Apostar en este evento">
                            </div>
                        </div>
                    </form>
                </div>
                <form class="formTenis mt-2" action="ControllerApuestaTenis" method="POST">
                    @csrf
                    <div class="form-row justify-content-center">
                        <div class="col-md-3 form-group">
                            <label for="">Eventos Tenis</label>
                            <select class="form-control" id="evento" name="evento">
                                @foreach(session("eventosTenis") as $evento)
                                    <option value="{{$evento->id}}">{{$evento->nombre}}</option>
                                @endforeach
                            </select>
                        </div>
                        <div class="col-md-5 text-center">
                            <!-- Nombre del submit -->
                            <input type="submit" name="elegirEvento" class="btn btn-primary" value="Apostar en este evento">
                        </div>
                    </div>
                </form>
            </div>
            <form class="formFormula mt-2" action="ControllerApuestaFormula" method="POST">
                @csrf
                <div class="form-row justify-content-center">
                    <div class="col-md-3 form-group">
                        <label for="">Eventos Formula 1</label>
                        <select class="form-control" id="evento" name="evento">
                            @foreach(session("eventosFormula1") as $evento)
                                <option value="{{$evento->id}}">{{$evento->nombre}}</option>
                            @endforeach
                        </select>
                    </div>
                    <div class="col-md-5 text-center">
                        <!-- Nombre del submit -->
                        <input type="submit" name="elegirEvento" class="btn btn-primary" value="Apostar en este evento">
                    </div>
                </div>
            </form>

            <form class="usuario mt-2" action="vistaUsuario" method="GET">
                <input type="submit" name="" class="btn btn-primary" value="Ver Usuario">
            </form>
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
    </body>
</html>
