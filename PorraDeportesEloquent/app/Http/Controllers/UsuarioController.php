<?php

namespace App\Http\Controllers;

use App\Models\Apuesta;
use App\Models\Evento;
use Illuminate\Http\Request;


class UsuarioController extends Controller
{

    public function index(Request $request){

        $usuario = session("user");

        $id = $usuario->id;
        $apuestas = Apuesta::where('id_usuario',"=",$id)->get();

        session()->put("apuestas", $apuestas);

        return view('vistaUsuario');

    }

    public function nuevoSaldo(Request $request){


        $user = session()->get('user');
        $cantidad = $request->saldo;
        if ($cantidad >= 0){
            $user->saldo = $cantidad;
            $user->save();
            $user->refresh();
        }

        return redirect('/vistaUsuario');


    }



}
