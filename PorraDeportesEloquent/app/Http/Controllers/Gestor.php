<?php

namespace App\Http\Controllers;

use App\Models\Apuesta;
use App\Models\Deporte;
use App\Models\Equipo;
use App\Models\Evento;
use App\Models\Rol;
use App\Models\Usuario;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Nette\Utils\Arrays;

class Gestor extends Controller
{

    public function inicio(Request $request)
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


        return view('vistaGestor', );
    }
    public function crearEvento(Request $request)
    {

        $nombre = $request->get("nombreCrearE");
        $fecha1 = $request->get("FechaCrearE");
        $strParticipantes = $request->get("PartCrearE");
        $strDeporte = $request->get("DeporteCrearE");


        $evento = new Evento();
        $evento->nombre = $nombre;
        $evento->fecha_inicio = now();
        $evento->fecha_fin = $fecha1;
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
        session()->put("res",json_encode($evento, JSON_UNESCAPED_UNICODE));
        return redirect("/vistaGestor");
    }
    public function modificarEvento(Request $request)
    {

        $evento = Evento::find($request->get("IdModE"));
        $nombre = $request->get("nombreCrearE")??"";
        $fecha1 = $request->get("FechaCrearE")??"";
        $fecha2 = $request->get("FechaFinCrearE")??"";
        $resultado = $request->get("ResModE")??"";
        $ganador = Equipo::find($request->get("GanadorModE"))->id;

        if ($nombre != ""){
            $evento->nombre = $nombre;
        }
        if ($fecha1 != ""){
            $evento->fecha_inicio = $fecha1;
        }
        if ($fecha2 != ""){
            $evento->fecha_fin = $fecha2;
        }
        if ($resultado != ""){
            $evento->resultado = $resultado;
        }
        if ($ganador != ""){
            $evento->id_equipo_ganador = $ganador;
        }
        if ($resultado != "" && $ganador != ""){
            $evento->save();
            $this->comprobarApuestas($evento);
        }
        //$evento->save();
        $tabE = "active show";
        session()->put("tabE", $tabE);
        $tabA = " ";
        session()->put("tabA", $tabA);
        $tabU = " ";
        session()->put("tabU", $tabU);
        session()->put("res",json_encode($evento, JSON_UNESCAPED_UNICODE));
        return redirect("/vistaGestor");

    }
    public function eliminarEvento(Request $request)
    {
        try {
            $evento = Evento::find($request->get("IdDelE"));
            $evento->delete();
            session()->put("res","se ha eliminado correctamente");
            $tabE = "active show";
            session()->put("tabE", $tabE);
            $tabA = " ";
            session()->put("tabA", $tabA);
            $tabU = " ";
            session()->put("tabU", $tabU);
            return redirect("/vistaGestor");
        } catch (\Throwable $th) {
            session()->put("res","No se puede eliminar");
            $tabE = "active show";
            session()->put("tabE", $tabE);
            $tabA = " ";
            session()->put("tabA", $tabA);
            $tabU = " ";
            session()->put("tabU", $tabU);
            return redirect("/vistaGestor");
        }
    }
    public function buscarEvento(Request $request)
    {

        $evento = Evento::find($request->get("IdFindE"));
        session()->put("res",json_encode($evento, JSON_UNESCAPED_UNICODE));
        $tabE = "active show";
        session()->put("tabE", $tabE);
        $tabA = " ";
        session()->put("tabA", $tabA);
        $tabU = " ";
        session()->put("tabU", $tabU);
        return redirect("/vistaGestor");
    }
    public function mostrarTodosEvento(Request $request)
    {
        $eventos = Evento::all();
        session()->put("res",json_encode($eventos, JSON_UNESCAPED_UNICODE));
        $tabE = "active show";
        session()->put("tabE", $tabE);
        $tabA = " ";
        session()->put("tabA", $tabA);
        $tabU = " ";
        session()->put("tabU", $tabU);
        return redirect("/vistaGestor");
    }



    public function modificarApuesta(Request $request)
    {
        $apuesta = Apuesta::find($request->get("IdModA"));
        $estado = $request->get("EstadoModA")??"Realizada";
        $apuesta->estado = $estado;
        $apuesta->save();

        session()->put("res",json_encode($apuesta, JSON_UNESCAPED_UNICODE));
        $tabE = " ";
        session()->put("tabE", $tabE);
        $tabA = "active show";
        session()->put("tabA", $tabA);
        $tabU = " ";
        session()->put("tabU", $tabU);
        return redirect("/vistaGestor");

    }
    public function cerrarApuesta(Request $request)
    {
        $apuesta = Apuesta::find($request->get("IdDelA"));
        $apuesta->estado = "Rechazada";
        $apuesta->save();

        session()->put("res",json_encode($apuesta, JSON_UNESCAPED_UNICODE));
        $tabE = " ";
        session()->put("tabE", $tabE);
        $tabA = "active show";
        session()->put("tabA", $tabA);
        $tabU = " ";
        session()->put("tabU", $tabU);
        return redirect("/vistaGestor");


    }
    public function buscarApuesta(Request $request)
    {
        $apuesta = Apuesta::find($request->get("IdFindA"));

        session()->put("res",json_encode($apuesta, JSON_UNESCAPED_UNICODE));
        $tabE = " ";
        session()->put("tabE", $tabE);
        $tabA = "active show";
        session()->put("tabA", $tabA);
        $tabU = " ";
        session()->put("tabU", $tabU);
        return redirect("/vistaGestor");

    }
    public function mostrarTodosApuestas(Request $request)
    {
        $apuestas = Apuesta::all();

        session()->put("res",json_encode($apuestas, JSON_UNESCAPED_UNICODE));
        $tabE = " ";
        session()->put("tabE", $tabE);
        $tabA = "active show";
        session()->put("tabA", $tabA);
        $tabU = " ";
        session()->put("tabU", $tabU);
        return redirect("/vistaGestor");


    }



    public function modificarUsuario(Request $request)
    {
        $usuario = Usuario::find($request->get("IdModU"));

        $email = $request->get("EmailModU")??"";
        $contrasenia = $request->get("PassModU")??"";

        $rol = Rol::find($request->get("RolModU"))->id;

        if ($email != ""){
            $usuario->email = $email;
        }
        if ($contrasenia != ""){
            $usuario->password = password_hash($contrasenia, PASSWORD_BCRYPT);;
        }
        if ($rol != null){
            $usuario->id_rol = $rol;
        }
        $usuario->save();
        session()->put("res",json_encode($usuario, JSON_UNESCAPED_UNICODE));
        $tabE = " ";
        session()->put("tabE", $tabE);
        $tabA = " ";
        session()->put("tabA", $tabA);
        $tabU = " active show";
        session()->put("tabU", $tabU);
        return redirect("/vistaGestor");


    }
    public function eliminarUsuario(Request $request)
    {
        try {
            $usuario = Usuario::find($request->get("IdDelU"));
            $usuario->delete();
            session()->put("res","se ha eliminado correctamente");
            $tabE = "";
            session()->put("tabE", $tabE);
            $tabA = " ";
            session()->put("tabA", $tabA);
            $tabU = " active show";
            session()->put("tabU", $tabU);
            return redirect("/vistaGestor");
        } catch (\Throwable $th) {
            session()->put("res","No se puede eliminar");
            $tabE = "";
            session()->put("tabE", $tabE);
            $tabA = " ";
            session()->put("tabA", $tabA);
            $tabU = " active show";
            session()->put("tabU", $tabU);
            return redirect("/vistaGestor");
        }

    }
    public function buscarUsuario(Request $request)
    {
        $usuario = Usuario::find($request->get("IdFindA"));

        session()->put("res",json_encode($usuario, JSON_UNESCAPED_UNICODE));
        $tabE = " ";
        session()->put("tabE", $tabE);
        $tabA = "active show";
        session()->put("tabA", $tabA);
        $tabU = " ";
        session()->put("tabU", $tabU);
        return redirect("/vistaGestor");

    }
    public function mostrarTodosUsuarios(Request $request)
    {
        $usuarios = Apuesta::all();

        session()->put("res",json_encode($usuarios, JSON_UNESCAPED_UNICODE));
        $tabE = " ";
        session()->put("tabE", $tabE);
        $tabA = "active show";
        session()->put("tabA", $tabA);
        $tabU = " ";
        session()->put("tabU", $tabU);
        return redirect("/vistaGestor");
    }
    public function comprobarApuestas(Evento $evento){


        $all = Apuesta::where("apuestas.id_evento","=",$evento->id)->get();
        $res = DB::select("SELECT a.* FROM apuestas as a INNER JOIN eventos as e on a.id_evento=e.id WHERE a.prediccion = e.resultado AND a.estado != 'Rechazada' and a.estado = 'Realizada' and e.id = ".$evento->id);
        foreach ($res as $arrayApuesta) {
            $findAllByPrediccion2[] = Apuesta::find($arrayApuesta->id);
        }
        if ($evento->id_deporte == 1){
            $res = DB::select("SELECT a.* FROM apuestas as a INNER JOIN eventos as e INNER JOIN equipos as eq ON a.id_evento= e.id AND e.id_equipo_ganador = eq.id WHERE a.prediccion=eq.nombre and a.estado != 'Rechazada' and a.estado = 'Realizada' and a.id_evento = ".$evento->id);
            foreach ($res as $arrayApuesta) {
                $findAllByPrediccion1[] = Apuesta::find($arrayApuesta->id);
            }
        }
        foreach ($all as $apuesta) {
            if ($this->comprobarSiEsGanadora($findAllByPrediccion2,$apuesta) || $this->comprobarSiEsGanadora($findAllByPrediccion1,$apuesta) ){
                $apuesta->estado = "Aceptada";
                $usuario = Usuario::find($apuesta->id_usuario);
                $usuario->saldo = $usuario->saldo + ($apuesta->cuota * $apuesta->cantidad);
                $usuario->save();
                $apuesta->save();
            }else{
                $apuesta->estado = "Rechazada";
                $apuesta->save();
            }
        }






/*
        $findAllByPrediccion1 = Apuesta::join("eventos", "apuestas.id_evento", "=", "eventos.id")
            ->where("apuestas.prediccion", "=", "eventos.resultado")
            ->where("apuestas.estado", "=", "'Realizada'")
            ->where("eventos.id", "=", $evento->id)
            ->select("apuestas.*")
            ->get();
*/
/*
        $findAllByPrediccion2 = Apuesta::join("eventos", "apuestas.id_evento", "=", "eventos.id")->join("equipos","equipos.id","=","eventos.id_equipo_ganador")
            ->where("apuestas.prediccion", "=", "equipos.nombre")
            ->where("apuestas.estado", "=", "'Realizada'")
            ->where("eventos.id", "=", $evento->id)
            ->select("apuestas.*")
            ->get();
*/

    }

    public function comprobarSiEsGanadora(Array $lista,Apuesta $apuesta): bool
    {
        $res = false;
        foreach ($lista as $a) {
            if ($a->id == $apuesta->id){
                $res = true;
            }else{
                $res = false;
            }
        }
        return $res;
    }
}
