package es.iespuertodelacruz.yt.porradeportes.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tipos_apuesta")
@NamedQuery(name="TipoApuesta.findAll", query = "Select t FROM TipoApuesta t")
public class TipoApuesta {
    private Integer id;

    private String descripcion;

    private Set<Apuesta> apuestas = new LinkedHashSet<>();

    public TipoApuesta(){}

    public TipoApuesta(TipoApuesta tipoApuesta){

        this.id = tipoApuesta.getId();
        this.descripcion = tipoApuesta.getDescripcion();
        this.apuestas = tipoApuesta.getApuestas();

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public TipoApuesta setId(Integer id) {
        this.id = id;
        return this;
    }

    @Column(name = "descripcion", nullable = false, length = 200)
    public String getDescripcion() {
        return descripcion;
    }

    public TipoApuesta setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    @OneToMany(mappedBy = "tipoApuesta", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    public Set<Apuesta> getApuestas() {
        return apuestas;
    }

    public TipoApuesta setApuestas(Set<Apuesta> apuestas) {
        this.apuestas = apuestas;
        return this;
    }

}