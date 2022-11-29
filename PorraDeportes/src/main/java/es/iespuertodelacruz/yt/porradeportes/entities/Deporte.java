package es.iespuertodelacruz.yt.porradeportes.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NamedQuery(name="Deporte.findAll", query = "SELECT d FROM Deporte d")
@Table(name = "deportes")
public class Deporte {
    private Integer id;

    private String nombre;

    private Set<Evento> eventos = new LinkedHashSet<>();

    /**
     * Constructor de copia de la case de Deporte
     * @param d Clase a copiar
     */
    public Deporte(Deporte d){
        this.id = d.getId();
        this.nombre = d.getNombre();
        this.eventos = d.getEventos();
    }

    /**
     * Constructor por defecto
     */
    public Deporte(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Deporte setId(Integer id) {
        this.id = id;
        return this;
    }

    @Column(name = "nombre", nullable = false, length = 50)
    public String getNombre() {
        return nombre;
    }

    public Deporte setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @OneToMany(mappedBy = "idDeporte", fetch = FetchType.EAGER)
    public Set<Evento> getEventos() {
        return eventos;
    }

    public Deporte setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
        return this;
    }

    @Override
    public String toString(){

        return "----------\nDeporte: " + id + "\nNombre: " + nombre+"\n----------";


    }

}