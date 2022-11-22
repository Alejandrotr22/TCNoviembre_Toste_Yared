<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id
 * @property string $nombre
 * @property Evento[] $eventos
 * @property Evento[] $eventos
 */
class Equipo extends Model
{
    /**
     * @var array
     */
    protected $fillable = ['nombre'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsToMany
     */
    public function eventos()
    {
        return $this->belongsToMany('App\Models\Evento', 'participantes', 'id_equipo', 'id_evento');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function eventos()
    {
        return $this->hasMany('App\Models\Evento', 'id_equipo_ganador');
    }
}
