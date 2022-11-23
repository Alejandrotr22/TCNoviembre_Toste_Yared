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
    <link rel="stylesheet" href="Principal.css" />
</head>
<body>
<h1><h1>

<br/>
<form>
    <div class="form-group">
      <label for="exampleInputEmail1"></label>
      <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
      <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    </div>
    <div class="form-group">
      <label for="exampleInputPassword1">Password</label>
      <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
    </div>
    <div class="form-group form-check">
      <input type="checkbox" class="form-check-input" id="exampleCheck1">
      <label class="form-check-label" for="exampleCheck1">Check me out</label>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>

    <section class="row justify-content-center">
        <div class="col-md-5">
            <div class="list-group">
                <a class="list-group-item list-group-item-action active" data-toggle="list" data-target="#contactar">Contactar</a>
                <a class="list-group-item list-group-item-action " data-toggle="list" data-target="#sugerencia">Sugerencia</a>
                <a class="list-group-item list-group-item-action" data-toggle="list" data-target="#empresas">Empresas</a>
            </div>
        </div>
        <div class="col-md-6">
            <div class="tab-content">
                <div class="tab-pane fade show active" id="contactar">
                    <form>
                        <div class="form-group">
                            <label>Email:</label>
                            <input type="email" class="form-control" placeholder="Introduce tu correo">
                        </div>
                        <div class="form-group">
                            <label>Tu problema:</label>
                            <textarea class="form-control" rows="6"></textarea>
                        </div>
                        <div class="text-right">
                            <input type="submit" class="btn btn-primary" value="Enviar">
                        </div>

                    </form>
                </div>
                <div class="tab-pane fade" id="sugerencia">
                    <form>
                        <div class="form-group">
                            <label>Email:</label>
                            <input type="email" class="form-control" placeholder="Introduce tu correo">
                        </div>
                        <div class="form-group">
                            <label>Tu Sugerencia:</label>
                            <textarea class="form-control" rows="6"></textarea>
                        </div>
                        <div class="text-right">
                            <input type="submit" class="btn btn-primary" value="Enviar">
                        </div>
                    </form>
                </div>
                <div class="tab-pane fade" id="empresas">
                    <form>
                        <div class="form-group">
                            <label>Nombre de la empresa:</label>
                            <input type="text" class="form-control" placeholder="Introduce el nombre de la empresa">
                        </div>
                        <div class="form-group">
                            <label>Email:</label>
                            <input type="email" class="form-control" placeholder="Correo electrónico de contacto">
                        </div>
                        <div class="form-group">
                            <label>¿En qué podemos ayudarte?:</label>
                            <textarea class="form-control" rows="6"></textarea>
                        </div>
                        <div class="text-right">
                            <input type="submit" class="btn btn-primary" value="Enviar">
                        </div>
                    </form>

                </div>
            </div>

        </div>
    </section>

  </form>














  <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>