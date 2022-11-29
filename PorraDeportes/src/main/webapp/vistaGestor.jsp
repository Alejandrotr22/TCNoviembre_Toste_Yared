<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
<link rel="stylesheet" href="css/Principal.css" />
</head>
<body>
    <main class="container-fluid">
        <h1>Gestiona los diferentes parametros</h1>
        <section class="justify-content-center">
            <div class="">
                <div class="list-group">
                    <a class="list-group-item list-group-item-action ${tabE}" data-toggle="list"
                        data-target="#eventos">Eventos</a>
                    <a class="list-group-item list-group-item-action ${tabA}" data-toggle="list"
                        data-target="#apuestas">Apuestas</a>
                    <a class="list-group-item list-group-item-action ${tabU}" data-toggle="list"
                        data-target="#usuarios">Usuarios</a>
                    <a class="list-group-item list-group-item-action ${tabD}" data-toggle="list"
                        data-target="#deportes">Deportes</a>
                </div>
            </div>
            <div class="mt-3">
                <div class="tab-content">
                    <!--  formularios de Eventos   -->
                    <div class="tab-pane fade ${tabE} row" id="eventos">
                        <div class="form-row justify-content-center">
                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
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
                                    <c:forEach items="${deportes}" var="deporte">
        								<option>${deporte.getNombre()}-${deporte.getId()}</option>
									</c:forEach>
                                </select>
                                <br>
                                <input type="submit" name="CrearE" class="form-control" value="Crear Evento">
                            </form>
                            
                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
                                <h3>Modificar/Cerrar Evento</h3>
                                <label for="">Evento</label>
                                <select class="form-control"  name="IdModE">
                                    <option></option>
                                    <c:forEach items="${eventos}" var="evento">
        								<option>${evento.getNombre()}-${evento.getId()}</option>
									</c:forEach>
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
                                    <c:forEach items="${equipos}" var="equipo">
        								<option>${equipo.getNombre()}-${equipo.getId()}</option>
									</c:forEach>
                                </select>
                                <label for="" class="form-label">Deporte</label>
                                <select class="form-control" name="DeporteModE">
                                    <option></option>
                                    <c:forEach items="${deportes}" var="deporte">
        								<option>${deporte.getNombre()}-${deporte.getId()}</option>
									</c:forEach>
                                </select>
                                <br>
                                <input type="submit" name="ModE" class="form-control" value="Modificar/Cerrar Evento">
                            </form>

                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
                                <h3>Eliminar Evento</h3>
                                <label for="">Evento</label>
                                <select class="form-control" name="IdDelE">
                                    <option></option>
                                    <c:forEach items="${eventos}" var="evento">
        								<option>${evento.getNombre()}-${evento.getId()}</option>
									</c:forEach>
                                </select>
                                <br>
                                <input type="submit" name="DelE" class="form-control" value="Eliminar Evento">
                            </form>

                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
                                <h3>Buscar Evento</h3>
                                <label for="">Evento</label>
                                <select class="form-control" name="IdFindE">
                                    <option></option>
                                    <c:forEach items="${eventos}" var="evento">
        								<option>${evento.getNombre()}-${evento.getId()}</option>
									</c:forEach>
                                </select>
                                <br>
                                <input type="submit" name="FindE" class="form-control" value="Buscar Evento">
                            </form>

                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
                                <h3>Mostrar Todos</h3>
                                <br>
                                <input type="submit" name="FindAllE" class="form-control" value="Mostrar Todos ">
                            </form>
                        </div>
                        <br>
                        <textarea class="resultado" name="res" id=""  rows="20">
${res}

                        </textarea>
                    </div>

                    <!--  formularios de Apuestas   -->

                    <div class="tab-pane fade row ${tabA}" id="apuestas">
                        <div class="form-row justify-content-center">
                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
                                <h3>Modificar/Comprobar Apuesta</h3>
                                <label for="">Apuesta</label>
                                <select class="form-control"  name="IdModA">
                                    <c:forEach items="${apuestas}" var="apuesta">
        								<option>${apuesta.getUsuario().getNombre()}-${apuesta.getPrediccion()}-${apuesta.getId()}</option>
									</c:forEach>
                                </select>
                                <label for="" class="form-label">Estado</label>
                                <input type="text" name="EstadoModA" class="form-control"  >
                                <br>
                                <input type="submit" name="ModA" class="form-control" value="Crear Apuesta">
                            </form>

                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
                                <h3>Cerrar Apuesta</h3>
                                <label for="">Apuesta</label>
                                <select class="form-control" name="IdDelA">
                                    <c:forEach items="${apuestas}" var="apuesta">
        								<option>${apuesta.getUsuario().getNombre()}-${apuesta.getPrediccion()}-${apuesta.getId()}</option>
									</c:forEach>
                                </select>
                                <br>
                                <input type="submit" name="DelA" class="form-control" value="Eliminar Apuesta">
                            </form>

                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
                                <h3>Buscar Apuesta</h3>
                                <label for="">Apuesta</label>
                                <select class="form-control" name="IdFindA">
                                    <c:forEach items="${apuestas}" var="apuesta">
        								<option>${apuesta.getUsuario().getNombre()}-${apuesta.getPrediccion()}-${apuesta.getId()}</option>
									</c:forEach>
                                </select>
                                <br>
                                <input type="submit" name="FindA" class="form-control" value="Buscar Apuesta">
                            </form>

                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
                                <h3>Mostrar Todos</h3>
                                <br>
                                <input type="submit" name="FindAllA" class="form-control" value="Mostrar Todos ">
                            </form>
                        </div>
                        <br>
                        <textarea class="resultado" name="res" id=""  rows="20">
${res}
                        </textarea>
                    </div>
                    <!-- fromularios de Usuario -->
                    <div class="tab-pane fade ${tabU}" id="usuarios">
                        <div class="form-row justify-content-center">
                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
                                <h3>Crear Usuario</h3>
                                <label for="" class="form-label">Nombre</label>
                                <input type="text" name="NombreCrearU" class="form-control"  >
                                <label for="" class="form-label">Email</label>
                                <input type="email" name="EmailCrearU" class="form-control"  >
                                <label for="" class="form-label">Contraseña</label>
                                <input type="password" name="PassCrearU" class="form-control"  >
                                <label for="" class="form-label">Saldo</label>
                                <input type="number" name="SaldoCrearU" class="form-control"  >
                                <label for="">Rol</label>
                                <select class="form-control"  name="RolCrearU">
                                    <option></option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                </select>
                                <br>
                                <input type="submit" name="CrearU" class="form-control" value="Crear Usuario">
                            </form>
                            
                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
                                <h3>Modificar Usuario</h3>
                                <label for="">Usuario</label>
                                <select class="form-control"  name="IdModU">
                                    <option></option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                </select>
                                <label for="" class="form-label">Nombre</label>
                                <input type="text" name="NombreModU" class="form-control"  >
                                <label for="" class="form-label">Email</label>
                                <input type="email" name="EmailModU" class="form-control"  >
                                <label for="" class="form-label">Contraseña</label>
                                <input type="password" name="PassModU" class="form-control"  >
                                <label for="" class="form-label">Saldo</label>
                                <input type="number" name="SaldoModU" class="form-control"  >
                                <label for="">Rol</label>
                                <select class="form-control"  name="RolModU">
                                    <option></option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                </select>
                                <br>
                                <input type="submit" name="ModU" class="form-control" value="Modificar Usuario">
                            </form>

                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
                                <h3>Eliminar Usuario</h3>
                                <label for="">Usuario</label>
                                <select class="form-control" name="IdDelU">
                                    <option></option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                </select>
                                <br>
                                <input type="submit" name="DelU" class="form-control" value="Eliminar Usuario">
                            </form>

                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
                                <h3>Buscar Usuario</h3>
                                <label for="">Usuario</label>
                                <select class="form-control" name="IdFindU">
                                    <option></option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                </select>
                                <br>
                                <input type="submit" name="FindU" class="form-control" value="Buscar Usuario">
                            </form>

                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
                                <h3>Mostrar Todos</h3>
                                <br>
                                <input type="submit" name="FindAllU" class="form-control" value="Mostrar Todos ">
                            </form>
                        </div>
                        <br>
                        <textarea class="resultado" name="res" id=""  rows="20">
${res}
                        </textarea>
                    </div>
                    <!-- fromularios de Deportes -->
                    <div class="tab-pane fade ${tabD}" id="deportes">
                        <div class="form-row justify-content-center">
                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
                                <h3>Crear Deporte</h3>
                                <label for="" class="form-label">Nombre</label>
                                <input type="text" name="NombreCrearD" class="form-control"  >
                                <br>
                                <input type="submit" name="CrearD" class="form-control" value="Crear Deporte">
                            </form>
                            
                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
                                <h3>Modificar Deporte</h3>
                                <label for="">Deporte</label>
                                <select class="form-control"  name="IdModD">
                                    <option></option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                </select>
                                <label for="" class="form-label">Nombre</label>
                                <input type="text" name="NombreModD" class="form-control"  >
                                <br>
                                <input type="submit" name="ModD" class="form-control" value="Modificar Deporte">
                            </form>

                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
                                <h3>Eliminar Deporte</h3>
                                <label for="">Deporte</label>
                                <select class="form-control" name="IdDelD">
                                    <option></option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                </select>
                                <br>
                                <input type="submit" name="DelD" class="form-control" value="Eliminar Deporte">
                            </form>

                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
                                <h3>Buscar Deporte</h3>
                                <label for="">Deporte</label>
                                <select class="form-control" name="IdFindD">
                                    <option></option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                </select>
                                <br>
                                <input type="submit" name="FindD" class="form-control" value="Buscar Deporte">
                            </form>

                           <form class="formGanador col-md-4 mt-3" action="/ServletGestor" method="POST">
                                <h3>Mostrar Todos</h3>
                                <br>
                                <input type="submit" name="FindAllD" class="form-control" value="Mostrar Todos ">
                            </form>
                        </div>
                        <br>
                        <textarea class="resultado" name="res" id=""  rows="20">
${res}
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