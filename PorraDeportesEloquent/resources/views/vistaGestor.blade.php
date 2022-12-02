<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
    <head>
        <meta charset="UTF-8">
        <title>Vista Gestor</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <!-- Course CSS -->
        <link rel="stylesheet" href="../css/Principal.css" />
        </head>
        <body>
            <main class="container-fluid">
                <h1>Gestiona los diferentes parametros</h1>
                <section class="justify-content-center">
                    <div class="">
                        <div class="list-group">
                            <!--session('tabE') -->
                            <a class="list-group-item list-group-item-action {{session('tabE')}}" data-toggle="list"
                                data-target="#eventos">Eventos</a>
                            <a class="list-group-item list-group-item-action {{session('tabA')}}" data-toggle="list"
                                data-target="#apuestas">Apuestas</a>
                            <a class="list-group-item list-group-item-action {{session('tabU')}}" data-toggle="list"
                                data-target="#usuarios">Usuarios</a>
                        </div>
                        <a href="logout" style="float: right; margin-right: 3%">Logout</a>
                    </div>
                    <div class="mt-3">
                        <div class="tab-content">
                            <!--  formularios de Eventos   -->
                            <div class="tab-pane fade  row {{session('tabE')}}" id="eventos">
                                <div class="form-row justify-content-center">
                                   <form class="formGanador col-md-4 mt-3" action="/crearEvento" method="POST">
                                    @csrf
                                        <h3>Crear Evento</h3>
                                        <label for="" class="form-label">Nombre</label>
                                        <input type="text" name="nombreCrearE" class="form-control"  >
                                        <label for="" class="form-label">Fecha</label>
                                        <input type="datetime-local" name="FechaCrearE" class="form-control"  >
                                        <label for="" class="form-label">Participantes(id separados por comas)</label>
                                        <input type="text" name="PartCrearE" class="form-control"  >
                                        <label for="" class="form-label">Deporte</label>
                                        <select class="form-control"  name="DeporteCrearE">
                                            <option></option>
                                            @foreach(session("deportes") as $deporte)
                                                <option value="{{$deporte->id}}">{{$deporte->nombre}}</option>
                                            @endforeach
                                        </select>
                                        <br>
                                        <input type="submit" name="CrearE" class="form-control" value="Crear Evento">
                                    </form>

                                   <form class="formGanador col-md-4 mt-3" action="/modificarEvento" method="POST">
                                    @csrf
                                        <h3>Modificar/Cerrar Evento</h3>
                                        <label for="">Evento</label>
                                        <select class="form-control"  name="IdModE">
                                            <option></option>
                                            @foreach(session("eventos") as $evento)
                                                <option value="{{$evento->id}}">{{$evento->nombre}}</option>
                                            @endforeach
                                        </select>
                                        <label for="" class="form-label">Nombre</label>
                                        <input type="text" name="NombreModE" class="form-control"  >
                                        <label for="" class="form-label">Fecha inicio</label>
                                        <input type="datetime-local" name="FechaInicioModE" class="form-control"  >
                                        <label for="" class="form-label">Fecha fin</label>
                                        <input type="datetime-local" name="FechaFinModE" class="form-control"  >
                                        <label for="" class="form-label">Resultado</label>
                                        <input type="text" name="ResModE" class="form-control"  >
                                        <label for="" class="form-label">Ganador</label>
                                        <select class="form-control" name="GanadorModE">
                                            <option></option>
                                            @foreach(session("equipos") as $equipo)
                                                <option value="{{$equipo->id}}">{{$equipo->nombre}}</option>
                                            @endforeach
                                        </select>
                                        <br>
                                        <input type="submit" name="ModE" class="form-control" value="Modificar/Cerrar Evento">
                                    </form>

                                   <form class="formGanador col-md-4 mt-3" action="/eliminarEvento" method="POST">
                                    @csrf
                                        <h3>Eliminar Evento</h3>
                                        <label for="">Evento</label>
                                        <select class="form-control" name="IdDelE">
                                            <option></option>
                                            @foreach(session("eventos") as $evento)
                                                <option value="{{$evento->id}}">{{$evento->nombre}}</option>
                                            @endforeach
                                        </select>
                                        <br>
                                        <input type="submit" name="DelE" class="form-control" value="Eliminar Evento">
                                    </form>

                                   <form class="formGanador col-md-4 mt-3" action="/buscarEvento" method="POST">
                                    @csrf
                                        <h3>Buscar Evento</h3>
                                        <label for="">Evento</label>
                                        <select class="form-control" name="IdFindE">
                                            <option></option>
                                            @foreach(session("eventos") as $evento)
                                                <option value="{{$evento->id}}">{{$evento->nombre}}</option>
                                            @endforeach
                                        </select>
                                        <br>
                                        <input type="submit" name="FindE" class="form-control" value="Buscar Evento">
                                    </form>

                                   <form class="formGanador col-md-4 mt-3" action="/mostrarTodosEventos" method="POST">
                                    @csrf
                                        <h3>Mostrar Todos</h3>
                                        <br>
                                        <input type="submit" name="FindAllE" class="form-control" value="Mostrar Todos ">
                                    </form>
                                </div>
                                <br>
                                <textarea class="resultado" name="res" id=""  rows="20">
{{session("res")}}

                                </textarea>
                            </div>

                            <!--  formularios de Apuestas   -->

                            <div class="tab-pane fade row {{session('tabA')}}" id="apuestas">
                                <div class="form-row justify-content-center">
                                   <form class="formGanador col-md-4 mt-3" action="/modificarApuesta" method="POST">
                                    @csrf
                                        <h3>Modificar/Comprobar Apuesta</h3>
                                        <label for="">Apuesta</label>
                                        <select class="form-control"  name="IdModA">
                                            @foreach(session("apuestas") as $apuesta)
                                                <option value="{{$apuesta->id}}">{{$apuesta->usuario->nombre}}-{{$apuesta->prediccion}}</option>
                                            @endforeach
                                        </select>
                                        <label for="" class="form-label">Estado</label>
                                        <input type="text" name="EstadoModA" class="form-control"  >
                                        <br>
                                        <input type="submit" name="ModA" class="form-control" value="Modificar Apuesta">
                                    </form>

                                   <form class="formGanador col-md-4 mt-3" action="/cerrarApuesta" method="POST">
                                    @csrf
                                        <h3>Cerrar Apuesta</h3>
                                        <label for="">Apuesta</label>
                                        <select class="form-control" name="IdDelA">
                                            @foreach(session("apuestas") as $apuesta)
                                                <option value="{{$apuesta->id}}">{{$apuesta->usuario->nombre}}-{{$apuesta->prediccion}}</option>
                                            @endforeach
                                        </select>
                                        <br>
                                        <input type="submit" name="DelA" class="form-control" value="Cerrar Apuesta">
                                    </form>

                                   <form class="formGanador col-md-4 mt-3" action="/buscarApuesta" method="POST">
                                    @csrf
                                        <h3>Buscar Apuesta</h3>
                                        <label for="">Apuesta</label>
                                        <select class="form-control" name="IdFindA">
                                            @foreach(session("apuestas") as $apuesta)
                                                <option value="{{$apuesta->id}}">{{$apuesta->usuario->nombre}}-{{$apuesta->prediccion}}</option>
                                            @endforeach
                                        </select>
                                        <br>
                                        <input type="submit" name="FindA" class="form-control" value="Buscar Apuesta">
                                    </form>

                                   <form class="formGanador col-md-4 mt-3" action="/mostrarTodosApuestas" method="POST">
                                    @csrf
                                        <h3>Mostrar Todos</h3>
                                        <br>
                                        <input type="submit" name="FindAllA" class="form-control" value="Mostrar Todas ">
                                    </form>
                                </div>
                                <br>
                                <textarea class="resultado" name="res" id=""  rows="20">
{{session("res")}}
                                </textarea>
                            </div>
                            <!-- fromularios de Usuario -->
                            <div class="tab-pane fade {{session('tabU')}}" id="usuarios">
                                <div class="form-row justify-content-center">

                                   <form class="formGanador col-md-4 mt-3" action="/modificarUsuario" method="POST">
                                    @csrf
                                        <h3>Modificar Usuario</h3>
                                        <label for="">Usuario</label>
                                        <select class="form-control"  name="IdModU">
                                            @foreach(session("usuarios") as $usuario)
                                                <option value="{{$usuario->id}}">{{$usuario->nombre}}</option>
                                            @endforeach
                                        </select>
                                        <label for="" class="form-label">Email</label>
                                        <input type="email" name="EmailModU" class="form-control"  >
                                        <label for="" class="form-label">Contraseña</label>
                                        <input type="password" name="PassModU" class="form-control"  >
                                        <label for="">Rol</label>
                                        <select class="form-control"  name="RolModU">
                                            <option></option>
                                            @foreach(session("roles") as $rol)
                                                <option value="{{$rol->id}}">{{$rol->nombre}}</option>
                                            @endforeach
                                        </select>
                                        <br>
                                        <input type="submit" name="ModU" class="form-control" value="Modificar Usuario">
                                    </form>

                                   <form class="formGanador col-md-4 mt-3" action="/eliminarUsuario" method="POST">
                                    @csrf
                                        <h3>Eliminar Usuario</h3>
                                        <label for="">Usuario</label>
                                        <select class="form-control" name="IdDelU">
                                            @foreach(session("usuarios") as $usuario)
                                                <option value="{{$usuario->id}}">{{$usuario->nombre}}</option>
                                            @endforeach
                                        </select>
                                        <br>
                                        <input type="submit" name="DelU" class="form-control" value="Eliminar Usuario">
                                    </form>

                                   <form class="formGanador col-md-4 mt-3" action="/buscarUsuario" method="POST">
                                    @csrf
                                        <h3>Buscar Usuario</h3>
                                        <label for="">Usuario</label>
                                        <select class="form-control" name="IdFindU">
                                            @foreach(session("usuarios") as $usuario)
                                                <option value="{{$usuario->id}}">{{$usuario->nombre}}</option>
                                            @endforeach
                                        </select>
                                        <br>
                                        <input type="submit" name="FindU" class="form-control" value="Buscar Usuario">
                                    </form>

                                   <form class="formGanador col-md-4 mt-3" action="/mostrarTodosUsuarios" method="POST">
                                    @csrf
                                        <h3>Mostrar Todos</h3>
                                        <br>
                                        <input type="submit" name="FindAllU" class="form-control" value="Mostrar Todos ">
                                    </form>
                                </div>
                                <br>
                                <textarea class="resultado" name="res" id=""  rows="20">
{{session("res")}}
                                </textarea>
                            </div>

                        </div>

                    </div>
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
