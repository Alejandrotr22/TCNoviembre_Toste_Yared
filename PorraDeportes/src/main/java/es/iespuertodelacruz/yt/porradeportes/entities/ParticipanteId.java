package es.iespuertodelacruz.yt.porradeportes.entities;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ParticipanteId implements Serializable {
    private static final long serialVersionUID = -2262476312596146765L;
    @Column(name = "id_equipo", nullable = false)
    private Integer idEquipo;

    @Column(name = "id_evento", nullable = false)
    private Integer idEvento;

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ParticipanteId entity = (ParticipanteId) o;
        return Objects.equals(this.idEquipo, entity.idEquipo) &&
                Objects.equals(this.idEvento, entity.idEvento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEquipo, idEvento);
    }

}