<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('login');
});

Route::get('/find', 'App\Http\Controllers\pruebasDDBB@pruebaFind');

Route::get('/login', function (){
    return view('login');
});

Route::post('Login', 'App\Http\Controllers\Login@index');

Route::any('/home', function (){
    return view('home');
});

Route::post('Registro', 'App\Http\Controllers\Login@registro');

Route::get('registrarsePage', function () {
    return view('registrarse');
});

Route::get('loginPage', function (){
    return view('login');
});

Route::any('/registrarse', function () {
    return view('registrarse');
});

Route::any('principalIndex', 'App\Http\Controllers\Principal@index');

// Apuestas futbol

Route::post('ControllerApuestaFootball', 'App\Http\Controllers\ApuestaFootball@index');

Route::post('ControllerApuestaFootballGanador', 'App\Http\Controllers\ApuestaFootball@ganador');

Route::post('ControllerApuestaFootballResultado', 'App\Http\Controllers\ApuestaFootball@apostarResultado');

// Apuestas tenis

Route::post('ControllerApuestaTenis', 'App\Http\Controllers\ApuestaTenis@index');

Route::post('ControllerApuestaTenisGanador', 'App\Http\Controllers\ApuestaTenis@ganador');


// Vista Gestor

Route::get("/vistaGestor",function(){

    return view("vistaGestor");
});
