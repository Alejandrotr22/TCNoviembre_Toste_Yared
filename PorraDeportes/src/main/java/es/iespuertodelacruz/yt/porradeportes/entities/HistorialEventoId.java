package es.iespuertodelacruz.yt.porradeportes.entities;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HistorialEventoId implements Serializable {
    private static final long serialVersionUID = 6981141708401178414L;
    @Column(name = "id_evento", nullable = false)
    private Integer idEvento;

    @Column(name = "id_equipo_ganador", nullable = false)
    private Integer idEquipoGanador;

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Integer getIdEquipoGanador() {
        return idEquipoGanador;
    }

    public void setIdEquipoGanador(Integer idEquipoGanador) {
        this.idEquipoGanador = idEquipoGanador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HistorialEventoId entity = (HistorialEventoId) o;
        return Objects.equals(this.idEquipoGanador, entity.idEquipoGanador) &&
                Objects.equals(this.idEvento, entity.idEvento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEquipoGanador, idEvento);
    }

}