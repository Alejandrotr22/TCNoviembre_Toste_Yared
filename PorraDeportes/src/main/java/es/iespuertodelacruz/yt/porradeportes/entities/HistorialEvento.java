package es.iespuertodelacruz.yt.porradeportes.entities;

import javax.persistence.*;

@Entity
@Table(name = "historial_eventos")
public class HistorialEvento {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private HistorialEventoId id;

    @MapsId("idEvento")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento idEvento;

    @MapsId("idEquipoGanador")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_equipo_ganador", nullable = false)
    private Equipo idEquipoGanador;

    public HistorialEventoId getId() {
        return id;
    }

    public void setId(HistorialEventoId id) {
        this.id = id;
    }

    public Evento getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Evento idEvento) {
        this.idEvento = idEvento;
    }

    public Equipo getIdEquipoGanador() {
        return idEquipoGanador;
    }

    public void setIdEquipoGanador(Equipo idEquipoGanador) {
        this.idEquipoGanador = idEquipoGanador;
    }

}