package es.iespuertodelacruz.yt.porradeportes.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "eventos")
public class Evento {
    private Integer id;

    private Deporte idDeporte;

    private Instant fechaInicio;

    private Instant fechaFin;

    private String nombre;

    private Equipo idEquipoGanador;

    private Set<Apuesta> apuestas = new LinkedHashSet<>();

    private Set<Equipo> participantes = new LinkedHashSet<>();

    /**
     * Constructor de copia de la clase Evento
     * @param e objeto evento a copiar
     */
    public Evento(Evento e){
        this.id = e.getId();
        this.idDeporte = e.getIdDeporte();
        this.fechaInicio = e.getFechaInicio();
        this.fechaFin = e.getFechaFin();
        this.nombre = e.getNombre();
        this.idEquipoGanador = e.getIdEquipoGanador();
        this.apuestas = e.getApuestas();
        this.participantes = e.getParticipantes();
    }

    /**
     * Constructor por defecto
     */
    public Evento(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Evento setId(Integer id) {
        this.id = id;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_deporte", nullable = false)
    public Deporte getIdDeporte() {
        return idDeporte;
    }

    public Evento setIdDeporte(Deporte idDeporte) {
        this.idDeporte = idDeporte;
        return this;
    }

    @Column(name = "fecha_inicio", nullable = false)
    public Instant getFechaInicio() {
        return fechaInicio;
    }

    public Evento setFechaInicio(Instant fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    @Column(name = "fecha_fin", nullable = false)
    public Instant getFechaFin() {
        return fechaFin;
    }

    public Evento setFechaFin(Instant fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    @Column(name = "nombre", nullable = false, length = 100)
    public String getNombre() {
        return nombre;
    }

    public Evento setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo_ganador")
    public Equipo getIdEquipoGanador() {
        return idEquipoGanador;
    }

    public Evento setIdEquipoGanador(Equipo idEquipoGanador) {
        this.idEquipoGanador = idEquipoGanador;
        return this;
    }

    @OneToMany(mappedBy = "idEvento")
    public Set<Apuesta> getApuestas() {
        return apuestas;
    }

    public Evento setApuestas(Set<Apuesta> apuestas) {
        this.apuestas = apuestas;
        return this;
    }

    @ManyToMany
    @JoinTable(name = "participantes",
            joinColumns = @JoinColumn(name = "id_evento"),
            inverseJoinColumns = @JoinColumn(name = "id_equipo"))
    public Set<Equipo> getParticipantes() {
        return participantes;
    }

    public Evento setParticipantes(Set<Equipo> equipos) {
        this.participantes = equipos;
        return this;
    }

}