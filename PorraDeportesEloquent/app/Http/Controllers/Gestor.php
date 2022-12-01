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

    public function index(Request $request)
    {

        $eventos = Evento::all();
        $apuestas = Apuesta::all();
        $equipos = Equipo::all();
        $deportes = Deporte::all();
        $roles = Rol::all();
        $usuarios = Usuario::all();
        session()->put("eventos", $eventos);
        session()->put("apuestas", $apuestas);
        session()->put("equipos", $equipos);
        session()->put("deportes", $deportes);
        session()->put("roles", $roles);
        session()->put("usuarios", $usuarios);

        $tabE = session("tabE");
        $tabA = session("tabA");
        $tabU = session("tabU");

        if ($tabE == null && $tabA == null && $tabU == null) {

            $tabE = "active show";
            session()->put("tabE", $tabE);
            $tabA = " ";
            session()->put("tabA", $tabA);
            $tabU = " ";
            session()->put("tabU", $tabU);
        }

        $res = "hola";
        return view('vistaGestor', compact("res"));
    }
    public function crearEvento(Request $request)
    {
        $nombre = $request->get("nombreCrearE");
        $fecha1 = $request->get("FechaCrearE");
        $fecha2 = $request->get("FechaFinCrearE");
        $strParticipantes = $request->get("PartCrearE");
        $strDeporte = $request->get("DeporteCrearE");


        $evento = new Evento();
        $evento->nombre = $nombre;
        $evento->fecha_inicio = $fecha1;
        $evento->fecha_fin = $fecha2;
        $split = explode(",", $strParticipantes);


        $evento->id_deporte = Deporte::find($strDeporte)->id;


        foreach ($split as $id) {
            $equipo = Equipo::find($id);
            $equipo->eventos()->save($evento);
            
        }
        $evento->save();


        $tabE = "active show";
        session()->put("tabE", $tabE);
        $tabA = " ";
        session()->put("tabA", $tabA);
        $tabU = " ";
        session()->put("tabU", $tabU);
    }
    public function modificarEvento(Request $request)
    {
        $nombre = $request->get("nombreCrearE")??"";
        $fecha1 = $request->get("FechaCrearE")??"";
        $fecha2 = $request->get("FechaFinCrearE")??"";
        $strParticipantes = $request->get("PartCrearE")??"";
        $strDeporte = $request->get("DeporteCrearE")??"";



        $tabE = "active show";
        session()->put("tabE", $tabE);
        $tabA = " ";
        session()->put("tabA", $tabA);
        $tabU = " ";
        session()->put("tabU", $tabU);
    }
    public function eliminarEvento(Request $request)
    {




        $tabE = "active show";
        session()->put("tabE", $tabE);
        $tabA = " ";
        session()->put("tabA", $tabA);
        $tabU = " ";
        session()->put("tabU", $tabU);
    }
    public function buscarEvento(Request $request)
    {



        $tabE = "active show";
        session()->put("tabE", $tabE);
        $tabA = " ";
        session()->put("tabA", $tabA);
        $tabU = " ";
        session()->put("tabU", $tabU);
    }
    public function mostrarTodosEvento(Request $request)
    {



        $tabE = "active show";
        session()->put("tabE", $tabE);
        $tabA = " ";
        session()->put("tabA", $tabA);
        $tabU = " ";
        session()->put("tabU", $tabU);
    }



    public function modificarApuesta(Request $request)
    {
    }
    public function cerrarApuestas(Request $request)
    {
    }
    public function buscarApuesta(Request $request)
    {
    }
    public function mostrarTodosApuestas(Request $request)
    {
    }



    public function modificarUsuario(Request $request)
    {
    }
    public function eliminarApuestas(Request $request)
    {
    }
    public function buscarUsuario(Request $request)
    {
    }
    public function mostrarTodosUsuarios(Request $request)
    {
    }
}
