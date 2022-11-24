<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * @property integer $id
 * @property integer $id_evento
 * @property integer $id_usuario
 * @property string $prediccion
 * @property float $cuota
 * @property float $cantidad
 * @property string $estado
 * @property Usuario $usuario
 * @property Evento $evento
 */
class Apuesta extends Model
{
    /**
     * @var array
     */
    protected $fillable = ['id_evento', 'id_usuario', 'prediccion', 'cuota', 'cantidad', 'estado'];

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
