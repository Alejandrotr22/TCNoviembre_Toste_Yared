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
    return view('welcome');
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
