<?php

namespace App\Http\Controllers;

use App\Models\Usuario;
use Illuminate\Http\Request;

class pruebasDDBB extends Controller
{

    public function pruebaFind(Request $request){

        $miUsuario = Usuario::find(2);

        $miUsuarioJson = json_encode($miUsuario, JSON_UNESCAPED_UNICODE);

        return view('find', compact('miUsuarioJson'));

    }

}
