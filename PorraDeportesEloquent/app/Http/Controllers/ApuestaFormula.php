<?php

namespace App\Http\Controllers;

use App\Models\Apuesta;
use App\Models\Evento;
use Illuminate\Http\Request;

class ApuestaFormula extends Controller
{

    public function index(Request $request){

        $eventoID = $request->evento;
        $eventoApostar = Evento::where('id', '=', $eventoID)
            ->first();

        session()->put("evento", $eventoApostar);

        $participantes = $eventoApostar->equipos()->get();



        $participantesCuotas = array();

        foreach ($participantes as $participante){

            $cuota = random_int(145, 185)/100.0;
            $participantesCuotas[] = $participante->nombre . " " . $cuota;

        }



        session()->put("participantes", $participantesCuotas);

        return view('apuestaFormula');

    }

    public function ganador(Request $request){

        $eventoApostar = session()->get('evento');
        $ganadorCuota = explode(" ", $request->ganador);
        $ganador = $ganadorCuota[0];
        $cuota = $ganadorCuota[1];
        $user = session()->get('user');
        $cantidad = $request->CuantiaGanador;

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
