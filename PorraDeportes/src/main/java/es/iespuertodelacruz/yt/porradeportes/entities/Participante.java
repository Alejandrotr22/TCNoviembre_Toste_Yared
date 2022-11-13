package es.iespuertodelacruz.yt.porradeportes.entities;

import javax.persistence.*;

@Entity
@Table(name = "participantes")
public class Participante {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private ParticipanteId id;

    @MapsId("idEquipo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_equipo", nullable = false)
    private Equipo idEquipo;

    @MapsId("idEvento")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento idEvento;

    public ParticipanteId getId() {
        return id;
    }

    public void setId(ParticipanteId id) {
        this.id = id;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Evento getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Evento idEvento) {
        this.idEvento = idEvento;
    }

}