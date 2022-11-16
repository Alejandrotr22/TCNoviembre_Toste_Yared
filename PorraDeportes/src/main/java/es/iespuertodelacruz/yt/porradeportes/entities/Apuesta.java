package es.iespuertodelacruz.yt.porradeportes.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "apuestas")
public class Apuesta {
    private Integer id;

    private Evento idEvento;

    private Usuario idUsuario;

    private TipoApuesta idTipo;

    private String resultado;

    private BigDecimal cuota;

    private BigDecimal cantidad;

    private String estado;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_evento", nullable = false)
    public Evento getIdEvento() {
        return idEvento;
    }

    public Apuesta setIdEvento(Evento idEvento) {
        this.idEvento = idEvento;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public Apuesta setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo", nullable = false)
    public TipoApuesta getIdTipo() {
        return idTipo;
    }

    public Apuesta setIdTipo(TipoApuesta idTipo) {
        this.idTipo = idTipo;
        return this;
    }

    @Column(name = "resultado", nullable = false, length = 50)
    public String getResultado() {
        return resultado;
    }

    public Apuesta setResultado(String resultado) {
        this.resultado = resultado;
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