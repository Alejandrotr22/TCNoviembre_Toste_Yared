package es.iespuertodelacruz.yt.porradeportes.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "apuestas")
@NamedQuery(name="Apuesta.findAll", query = "select a from Apuesta a")
public class Apuesta {
    private Integer id;

    private Evento evento;

    private Usuario usuario;

    private String prediccion;

    private BigDecimal cuota;

    private BigDecimal cantidad;

    private String estado;

    public Apuesta(){}

    public Apuesta(Apuesta apuesta){

        this.id = apuesta.getId();
        this.evento = apuesta.getEvento();
        this.usuario = apuesta.getUsuario();
        this.prediccion = apuesta.getPrediccion();
        this.cuota = apuesta.getCuota();
        this.cantidad = apuesta.getCantidad();
        this.estado = apuesta.getEstado();

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Apuesta setId(Integer id) {
        this.id = id;
        return this;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_evento", nullable = false)
    public Evento getEvento() {
        return evento;
    }

    public Apuesta setEvento(Evento idEvento) {
        this.evento = idEvento;
        return this;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    public Usuario getUsuario() {
        return usuario;
    }

    public Apuesta setUsuario(Usuario idUsuario) {
        this.usuario = idUsuario;
        return this;
    }

    @Column(name = "prediccion", nullable = false, length = 200)
    public String getPrediccion() {
        return prediccion;
    }


    public Apuesta setPrediccion(String prediccion) {
        this.prediccion = prediccion;
        return this;
    }

    @Column(name = "cuota", nullable = false, precision = 8, scale = 2)
    public BigDecimal getCuota() {
        return cuota;
    }

    public Apuesta setCuota(BigDecimal cuota) {
        this.cuota = cuota;
        return this;
    }

    @Column(name = "cantidad", nullable = false, precision = 8, scale = 2)
    public BigDecimal getCantidad() {
        return cantidad;
    }

    public Apuesta setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    @Column(name = "estado", nullable = false, length = 50)
    public String getEstado() {
        return estado;
    }

    public Apuesta setEstado(String estado) {
        this.estado = estado;
        return this;
    }

}