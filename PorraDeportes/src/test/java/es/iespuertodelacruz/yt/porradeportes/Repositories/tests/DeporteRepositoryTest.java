package es.iespuertodelacruz.yt.porradeportes.Repositories.tests;

import es.iespuertodelacruz.yt.porradeportes.Repositories.DeporteRepository;
import es.iespuertodelacruz.yt.porradeportes.entities.Deporte;
import es.iespuertodelacruz.yt.porradeportes.entities.Evento;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class DeporteRepositoryTest {

    EntityManagerFactory emf;

    int id = 4;
    String nombre = "Carrera1";
    Set<Evento> eventos = new LinkedHashSet<>();

    Deporte deporte = new Deporte();
    DeporteRepository deporteRepository;


    @BeforeEach
    void setUp() {
        EMFhelper EMF = EMFhelper.getSingleton();
        emf = EMF.getEMF();
        deporteRepository = new DeporteRepository(emf);
    }

    @AfterEach
    void tearDown() {

    }

    
    @Test
    void save() {
<<<<<<< HEAD
        d.setNombre(nombre);
        d.setEventos(eventos);
        deporteRepository.save(d);
        assertNotNull(d.getId(),"No se ha asignado un id al deporte");

=======
        deporte.setNombre(nombre);
        deporte.setEventos(eventos);
        deporteRepository.save(deporte);
        assertNotNull(deporte.getId(),"No se ha asignado un id al deporte");
>>>>>>> feature_LoginRegister_12
    }

    @Test
    void findByID() {

        deporte.setNombre(nombre);
        deporte.setEventos(eventos);
        deporte = deporteRepository.save(deporte);
        Deporte d2 = deporteRepository.findByID(deporte.getId());

        assertEquals(d2.getId(), deporte.getId(),"los id no son iguales");
        assertEquals(d2.getNombre(), deporte.getNombre(),"los nombres no son iguales");

    }

    @Test
    void update() {
        deporte = new Deporte();
        deporte.setNombre("carrera3");
        deporte.setEventos(eventos);
        deporte = deporteRepository.save(deporte);
        Deporte deporte1 = deporteRepository.findByID(deporte.getId());
        deporte1.setNombre("carrera2");
        Boolean update = deporteRepository.update(deporte1);
        assertTrue(update,"No se ha realizado el update correctamente");
        assertNotEquals(deporte.getNombre(),nombre,"Los nombres no se han cambiado");
    }

    @Test
    void delete() {
        Deporte deporte2 = new Deporte();
        deporte2.setNombre(nombre);
        deporte2.setEventos(eventos);
        deporteRepository.save(deporte2);
        assertTrue(deporteRepository.delete(deporte2.getId()),"no se elimina correctamente");
    }

    @Test
    void findAll() {
        List<Deporte> all = deporteRepository.findAll();
        System.out.println(all);
        assertNotNull(all,"No se ha rellenado el list");

    }
}