<?php

namespace App\Http\Controllers;

use App\Models\Apuesta;
use App\Models\Evento;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use PhpParser\Node\Expr\Cast\Double;

class ApuestaFootball extends Controller
{

    public function index(Request $request){

        $eventoID = $request->evento;
        $eventoApostar = Evento::where('id', '=', $eventoID)
            ->first();

        session()->put("evento", $eventoApostar);
        /*
        $participantes = DB::select('select * from participantes where id_evento = :id',
        ['id' => $eventoID]);
        */
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

}
