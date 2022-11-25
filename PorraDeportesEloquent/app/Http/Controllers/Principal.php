<?php

namespace App\Http\Controllers;

use App\Models\Evento;
use App\Models\Usuario;
use Illuminate\Http\Request;

class Principal extends Controller
{

    public function index(){

        $eventosFootball = Evento::where('fecha_fin', '>', now())
            ->where('id_deporte', 1)
            ->get();

        session()->put("eventosFootball", $eventosFootball);

        $eventosTenis = Evento::where('fecha_fin', '>', now())
            ->where('id_deporte', 2)
            ->get();

        session()->put("eventosTenis", $eventosTenis);

        $eventosFormula1 = Evento::where('fecha_fin', '>', now())
            ->where('id_deporte', 3)
            ->get();

        session()->put("eventosFormula1", $eventosFormula1);

        return view('principal');

    }

}
