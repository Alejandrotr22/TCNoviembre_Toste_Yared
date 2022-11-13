package es.iespuertodelacruz.yt.porradeportes.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "eventos")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_deporte", nullable = false)
    private Deporte idDeporte;

    @Column(name = "fecha_inicio", nullable = false)
    private Instant fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private Instant fechaFin;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @ManyToMany
    @JoinTable(name = "historial_eventos",
            joinColumns = @JoinColumn(name = "id_evento"),
            inverseJoinColumns = @JoinColumn(name = "id_equipo_ganador"))
    private Set<Equipo> equipos_historial = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEvento")
    private Set<Apuesta> apuestas = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "participantes",
            joinColumns = @JoinColumn(name = "id_evento"),
            inverseJoinColumns = @JoinColumn(name = "id_equipo"))
    private Set<Equipo> equipos_participantes = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Deporte getIdDeporte() {
        return idDeporte;
    }

    public void setIdDeporte(Deporte idDeporte) {
        this.idDeporte = idDeporte;
    }

    public Instant getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Instant fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Instant getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Instant fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Equipo> getEquipos_historial() {
        return equipos_historial;
    }

    public void setEquipos_historial(Set<Equipo> equipos_historial) {
        this.equipos_historial = equipos_historial;
    }

    public Set<Apuesta> getApuestas() {
        return apuestas;
    }

    public void setApuestas(Set<Apuesta> apuestas) {
        this.apuestas = apuestas;
    }

    public Set<Equipo> getEquipos_participantes() {
        return equipos_participantes;
    }

    public void setEquipos_participantes(Set<Equipo> equipos_participantes) {
        this.equipos_participantes = equipos_participantes;
    }

}