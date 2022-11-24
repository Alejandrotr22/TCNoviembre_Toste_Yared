package es.iespuertodelacruz.yt.porradeportes.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "equipos")
public class Equipo {
    private Integer id;

    private String nombre;

    private Set<Evento> eventosGanador = new LinkedHashSet<>();

    private Set<Evento> eventosParticipantes = new LinkedHashSet<>();

    public Equipo(){}

    public Equipo(Equipo equipo){

        this.id = equipo.getId();
        this.nombre = equipo.getNombre();
        this.eventosGanador = getEventosGanador();
        this.eventosParticipantes = getEventosParticipantes();

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Equipo setId(Integer id) {
        this.id = id;
        return this;
    }

    @Column(name = "nombre", nullable = false, length = 200)
    public String getNombre() {
        return nombre;
    }

    public Equipo setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @OneToMany(mappedBy = "idEquipoGanador", fetch = FetchType.EAGER)
    public Set<Evento> getEventosGanador() {
        return eventosGanador;
    }

    public Equipo setEventosGanador(Set<Evento> eventosGanador) {
        this.eventosGanador = eventosGanador;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "participantes",
            joinColumns = @JoinColumn(name = "id_equipo"),
            inverseJoinColumns = @JoinColumn(name = "id_evento"))
    public Set<Evento> getEventosParticipantes() {
        return eventosParticipantes;
    }

    public Equipo setEventosParticipantes(Set<Evento> eventosParticipantes) {
        this.eventosParticipantes = eventosParticipantes;
        return this;
    }

}