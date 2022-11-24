<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id
 * @property integer $id_deporte
 * @property integer $id_equipo_ganador
 * @property string $fecha_inicio
 * @property string $fecha_fin
 * @property string $nombre
 * @property Equipo[] $equipos
 * @property Apuesta[] $apuestas
 * @property Deporte $deporte
 * @property Equipo $equipo
 * @property string $resultado
 */
class Evento extends Model
{
    /**
     * @var array
     */
    protected $fillable = ['id_deporte', 'id_equipo_ganador', 'fecha_inicio', 'fecha_fin', 'nombre', 'resultado'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsToMany
     */
    public function equipos()
    {
        return $this->belongsToMany('App\Models\Equipo', 'participantes', 'id_evento', 'id_equipo');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function apuestas()
    {
        return $this->hasMany('App\Models\Apuesta', 'id_evento');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function deporte()
    {
        return $this->belongsTo('App\Models\Deporte', 'id_deporte');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function equipo()
    {
        return $this->belongsTo('App\Models\Equipo', 'id_equipo_ganador');
    }
}
