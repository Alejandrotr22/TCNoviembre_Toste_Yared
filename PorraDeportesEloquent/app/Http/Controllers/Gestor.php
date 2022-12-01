<?php

namespace App\Http\Controllers;

use App\Models\Apuesta;
use App\Models\Deporte;
use App\Models\Equipo;
use App\Models\Evento;
use App\Models\Rol;
use App\Models\Usuario;
use Illuminate\Http\Request;

class Gestor extends Controller
{

    public function index(Request $request){

        $eventos = Evento::all();
        $apuestas = Apuesta::all();
        $equipos = Equipo::all();
        $deportes = Deporte::all();
        $roles = Rol::all();
        $usuarios = Usuario::all();
        session()->put("eventos",$eventos);
        session()->put("apuestas",$apuestas);
        session()->put("equipos",$equipos);
        session()->put("deportes",$deportes);
        session()->put("roles",$roles);
        session()->put("usuarios",$usuarios);


        $res = "hola";
        return view('vistaGestor',compact("res"));

    }
    public function crearEvento(Request $request){

    }
    public function modificarEvento(Request $request){

    }
    public function eliminarEvento(Request $request){

    }
    public function buscarEvento(Request $request){

    }
    public function mostrarTodosEvento(Request $request){

    }



    public function modificarApuesta(Request $request){

    }
    public function cerrarApuestas(Request $request){

    }
    public function buscarApuesta(Request $request){

    }
    public function mostrarTodosApuestas(Request $request){

    }



    public function modificarUsuario(Request $request){

    }
    public function eliminarApuestas(Request $request){

    }
    public function buscarUsuario(Request $request){

    }
    public function mostrarTodosUsuarios(Request $request){

    }





}
