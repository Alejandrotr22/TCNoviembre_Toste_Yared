package es.iespuertodelacruz.yt.porradeportes.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Rol {
    private Integer id;

    private String nombre;

    private Set<Usuario> usuarios = new LinkedHashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Rol setId(Integer id) {
        this.id = id;
        return this;
    }

    @Column(name = "nombre", nullable = false, length = 25)
    public String getNombre() {
        return nombre;
    }

    public Rol setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @OneToMany(mappedBy = "idRol", fetch = FetchType.EAGER)
    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public Rol setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Rol{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", usuarios=").append(usuarios);
        sb.append('}');
        return sb.toString();
    }
}