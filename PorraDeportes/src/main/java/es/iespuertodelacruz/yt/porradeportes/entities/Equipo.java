package es.iespuertodelacruz.yt.porradeportes.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "equipos")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @ManyToMany
    @JoinTable(name = "historial_eventos",
            joinColumns = @JoinColumn(name = "id_equipo_ganador"),
            inverseJoinColumns = @JoinColumn(name = "id_evento"))
    private Set<Evento> eventos_historial = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "participantes",
            joinColumns = @JoinColumn(name = "id_equipo"),
            inverseJoinColumns = @JoinColumn(name = "id_evento"))
    private Set<Evento> eventos_participantes = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Evento> getEventos_historial() {
        return eventos_historial;
    }

    public void setEventos_historial(Set<Evento> eventos_historial) {
        this.eventos_historial = eventos_historial;
    }

    public Set<Evento> getEventos_participantes() {
        return eventos_participantes;
    }

    public void setEventos_participantes(Set<Evento> eventos_participantes) {
        this.eventos_participantes = eventos_participantes;
    }

}