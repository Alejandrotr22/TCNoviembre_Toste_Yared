<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id
 * @property integer $id_rol
 * @property string $nombre
 * @property string $email
 * @property string $password
 * @property float $saldo
 * @property Apuesta[] $apuestas
 * @property Rol $rol
 */
class Usuario extends Model
{
    /**
     * @var array
     */
    protected $fillable = ['id_rol', 'nombre', 'email', 'password', 'saldo'];

    public $timestamps = false;
    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function apuestas()
    {
        return $this->hasMany('App\Models\Apuesta', 'id_usuario');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function role()
    {
        return $this->belongsTo('App\Models\Rol', 'id_rol');
    }
}
