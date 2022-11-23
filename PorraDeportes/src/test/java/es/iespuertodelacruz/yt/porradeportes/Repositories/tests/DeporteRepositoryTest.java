package es.iespuertodelacruz.yt.porradeportes.Repositories.tests;

import es.iespuertodelacruz.yt.porradeportes.Repositories.DeporteRepository;
import es.iespuertodelacruz.yt.porradeportes.entities.Deporte;
import es.iespuertodelacruz.yt.porradeportes.entities.Evento;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DeporteRepositoryTest {

    EntityManagerFactory emf;

    int id = 4;
    String nombre = "Carrera1";
    Set<Evento> eventos = new LinkedHashSet<>();

    Deporte d = new Deporte();



    @BeforeEach
    void setUp() {
        EMFhelper EMF = EMFhelper.getSingleton();
        emf = EMF.getEMF();
    }

    @AfterEach
    void tearDown() {

    }

    
    @Test
    void save() {
        DeporteRepository deporteRepository = new DeporteRepository(emf);
        d.setNombre(nombre);
        d.setEventos(eventos);
        d.setId(id);
        deporteRepository.save(d);
        assertNotNull(d.getId(),"No se ha asignado un id al deporte");
    }

    @Test
    void findByID() {
        DeporteRepository deporteRepository = new DeporteRepository(emf);
        Deporte d2 = deporteRepository.findByID(d.getId());

        assertEquals(d2.getId(),d.getId(),"los id no son iguales");
        assertEquals(d2.getNombre(),d.getNombre(),"los nombres no son iguales");

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
        DeporteRepository deporteRepository = new DeporteRepository(emf);
        deporteRepository.delete();
    }

    @Test
    void findAll() {
    }
}