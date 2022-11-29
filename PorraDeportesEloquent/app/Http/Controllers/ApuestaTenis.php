<?php

namespace App\Http\Controllers;

use App\Models\Apuesta;
use App\Models\Evento;
use Illuminate\Http\Request;

class ApuestaTenis extends Controller
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
                "cuotaEquipo1"  => "1.55",
                "cuotaEquipo2"  => "1.95"
            );
            session()->put("cuotas", $cuotas);

        }else if ($ganadoEquipo1 < $ganadoEquipo2){

            $cuotas = array(
                "cuotaEquipo1"  => "1.95",
                "cuotaEquipo2"  => "1.55"
            );
            session()->put("cuotas", $cuotas);

        }else{

            $cuotas = array(
                "cuotaEquipo1"  => "1.75",
                "cuotaEquipo2"  => "1.75"
            );
            session()->put("cuotas", $cuotas);

        }

        session()->put("participantes", $participantes);

        return view('apuestaTenis');

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

}
