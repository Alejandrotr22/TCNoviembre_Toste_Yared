<?php

namespace App\Http\Controllers;

use App\Models\Usuario;
use Illuminate\Http\Request;

class Login extends Controller
{

    public function index(Request $request){

        $emailLogin = $request->email;
        $passLogin = $request->password;

        $usuario = Usuario::where('email', '=', $emailLogin)
            ->first();
        if($usuario != null) {
            $checkPass = password_verify($passLogin, $usuario->password);
            if($checkPass) {
                session()->put('user', $usuario);
                return view('home');
            }else{
                $respuesta = "Login incorrecto";
            }
        }else{
            $respuesta = "No existe esa cuenta";
        }
        return view('login', compact('respuesta'));

    }

    public function registro(Request $request){

        $usuarioLogin = $request->usuario;
        $emailLogin = $request->email;
        $passLogin = $request->password;

        $usuarioFindByEmail = Usuario::where('email', '=', $emailLogin)
            ->first();
        $usuarioFindByName = Usuario::where('nombre', '=', $usuarioLogin)
            ->first();

        if($usuarioFindByEmail != null || $usuarioFindByName != null){

            $respuesta = "Ya existe un usuario con ese user o email";
            return view('registrarse', compact('respuesta'));

        }else{

            $hashPassBcrypt = password_hash($passLogin, PASSWORD_BCRYPT);
            $hashPass = str_replace('$2y$', '$2a$', $hashPassBcrypt);
            $usuario = new Usuario();
            $usuario->nombre = $usuarioLogin;
            $usuario->password = $hashPass;
            $usuario->id_rol = 1;
            $usuario->email = $emailLogin;
            $usuario->saldo = 0;
            $usuario->save();
            session()->put('user', $usuario);
            echo $usuario;
            return view('home');


        }

    }

}
