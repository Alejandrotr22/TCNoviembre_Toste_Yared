<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id
 * @property integer $id_evento
 * @property integer $id_usuario
 * @property integer $id_tipo
 * @property string $resultado
 * @property float $cuota
 * @property float $cantidad
 * @property string $estado
 * @property TiposApuestum $tiposApuestum
 * @property Usuario $usuario
 * @property Evento $evento
 */
class Apuesta extends Model
{
    /**
     * @var array
     */
    protected $fillable = ['id_evento', 'id_usuario', 'id_tipo', 'resultado', 'cuota', 'cantidad', 'estado'];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function tiposApuestum()
    {
        return $this->belongsTo('App\Models\TiposApuestum', 'id_tipo');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function usuario()
    {
        return $this->belongsTo('App\Models\Usuario', 'id_usuario');
    }

    /**
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function evento()
    {
        return $this->belongsTo('App\Models\Evento', 'id_evento');
    }
}
