package es.iespuertodelacruz.yt.porradeportes.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@NamedQuery(name="Usuario.findAll", query = "SELECT u FROM Usuario u")
@NamedQuery(name="Usuario.findByEmail", query = "select u from Usuario u where u.email = :email")
@NamedQuery(name="Usuario.findByUser", query = "select u from Usuario u where u.nombre = :usuario")
public class Usuario {
    private Integer id;

    private String nombre;

    private String email;

    private String password;

    private Rol rol;

    private BigDecimal saldo;

    private Set<Apuesta> apuestas = new LinkedHashSet<>();

    public Usuario(){

    }
    public Usuario(Usuario usuarioCopia){

        this.id = usuarioCopia.getId();
        this.nombre = usuarioCopia.getNombre();
        this.email = usuarioCopia.getEmail();
        this.password = usuarioCopia.getPassword();
        this.rol = usuarioCopia.getRol();
        this.saldo = usuarioCopia.getSaldo();
        this.apuestas = usuarioCopia.getApuestas();

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Usuario setId(Integer id) {
        this.id = id;
        return this;
    }

    @Column(name = "nombre", nullable = false, length = 50)
    public String getNombre() {
        return nombre;
    }

    public Usuario setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Column(name = "email", nullable = false, length = 200)
    public String getEmail() {
        return email;
    }

    public Usuario setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public Usuario setPassword(String password) {
        this.password = password;
        return this;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_rol", nullable = false)
    public Rol getRol() {
        return rol;
    }

    public Usuario setRol(Rol idRol) {
        this.rol = idRol;
        return this;
    }

    @Column(name = "saldo", nullable = false, precision = 8, scale = 2)
    public BigDecimal getSaldo() {
        return saldo;
    }

    public Usuario setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
        return this;
    }

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    public Set<Apuesta> getApuestas() {
        return apuestas;
    }

    public Usuario setApuestas(Set<Apuesta> apuestas) {
        this.apuestas = apuestas;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Usuario{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", saldo=").append(saldo);
        sb.append(", apuestas=").append(apuestas);
        sb.append('}');
        return sb.toString();
    }
}