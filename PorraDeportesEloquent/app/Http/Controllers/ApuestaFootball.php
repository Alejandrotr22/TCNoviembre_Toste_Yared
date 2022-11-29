<?php

namespace App\Http\Controllers;

use App\Models\Apuesta;
use App\Models\Evento;
use Illuminate\Http\Request;


class ApuestaFootball extends Controller
{

    public function index(Request $request){

        $eventoID = $request->evento;
        $eventoApostar = Evento::where('id', '=', $eventoID)
            ->first();

        session()->put("evento", $eventoApostar);

        $participantes = $eventoApostar->equipos()->get();

        $ganadoEquipo1 = Evento::where('id_equipo_ganador', '=', $participantes[0]->id)
            ->count();
        $ganadoEquipo2 = Evento::where('id_equipo_ganador', '=', $participantes[1]->id)
            ->count();

        if($ganadoEquipo1 > $ganadoEquipo2){

            $cuotas = array(
                "cuotaEquipo1"  => "1.35",
                "cuotaEquipo2"  => "2.20",
                "cuotaEmpate"  => "1.85"
            );
            session()->put("cuotas", $cuotas);

        }else if ($ganadoEquipo1 < $ganadoEquipo2){

            $cuotas = array(
                "cuotaEquipo1"  => "2.20",
                "cuotaEquipo2"  => "1.25",
                "cuotaEmpate"  => "1.85"
            );
            session()->put("cuotas", $cuotas);

        }else{

            $cuotas = array(
                "cuotaEquipo1"  => "1.85",
                "cuotaEquipo2"  => "1.85",
                "cuotaEmpate"  => "1.40"
            );
            session()->put("cuotas", $cuotas);

        }

        session()->put("participantes", $participantes);

        return view('apuestaFootball');

    }

    public function ganador(Request $request){

        $eventoApostar = session()->get('evento');
        $ganador = $request->ganador;
        $user = session()->get('user');
        $cantidad = $request->CuantiaGanador;
        $cuotaFind = 'cuota'.$ganador;
        $cuota = $request->{$cuotaFind};

        $apuesta = new Apuesta();
        $apuesta->id_usuario = $user->id;
        $apuesta->id_evento = $eventoApostar->id;
        $apuesta->prediccion = $ganador;
        $apuesta->cantidad = $cantidad;
        $apuesta->estado = 'Realizada';
        $apuesta->cuota = $cuota;
        $apuesta->save();

        $user->saldo -= $cantidad;
        $user->save();
        $user->refresh();

        return view('principal');



    }

    public function apostarResultado(Request $request){

        $eventoApostar = session()->get('evento');
        $marcador1 = $request->marcador1;
        $marcador2 = $request->marcador2;
        $user = session()->get('user');
        $cantidad = $request->CuantiaGanador;
        if($marcador1 > $marcador2){
            $cuota = session()->get('cuotas')['cuotaEquipo1'];
        }else if($marcador1 < $marcador2){
            $cuota = session()->get('cuotas')['cuotaEquipo2'];
        }else{
            $cuota = session()->get('cuotas')['cuotaEmpate'];
        }

        $apuesta = new Apuesta();
        $apuesta->id_usuario = $user->id;
        $apuesta->id_evento = $eventoApostar->id;
        $apuesta->prediccion = session()->get('participantes')[0]->nombre . "_" . $marcador1 . "_" . session()->get('participantes')[1]->nombre . "_" . $marcador2;
        $apuesta->cantidad = $cantidad;
        $apuesta->estado = 'Realizada';
        $apuesta->cuota = $cuota;
        $apuesta->save();

        $user->saldo -= $cantidad;
        $user->save();
        $user->refresh();

        return view('principal');



    }

}
