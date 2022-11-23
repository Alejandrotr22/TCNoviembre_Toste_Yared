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
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DeporteRepositoryTest {

    EntityManagerFactory emf;

    int id = 4;
    String nombre = "Carrera1";
    Set<Evento> eventos = new LinkedHashSet<>();

    Deporte d = new Deporte();
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
        d.setNombre(nombre);
        d.setEventos(eventos);
        deporteRepository.save(d);
        assertNotNull(d.getId(),"No se ha asignado un id al deporte");
    }

    @Test
    void findByID() {

        d.setNombre(nombre);
        d.setEventos(eventos);
        d = deporteRepository.save(d);
        Deporte d2 = deporteRepository.findByID(d.getId());

        assertEquals(d2.getId(),d.getId(),"los id no son iguales");
        assertEquals(d2.getNombre(),d.getNombre(),"los nombres no son iguales");

    }

    @Test
    void update() {
        d = new Deporte();
        d.setNombre("carrera3");
        d.setEventos(eventos);
        d = deporteRepository.save(d);
        Deporte d1 = deporteRepository.findByID(d.getId());
        d1.setNombre("carrera2");
        Boolean update = deporteRepository.update(d1);
        assertTrue(update,"No se ha realizado el update correctamente");
        assertNotEquals(d.getNombre(),nombre,"Los nombres no se han cambiado");
    }

    @Test
    void delete() {
        Deporte d2 = new Deporte();
        d2.setNombre(nombre);
        d2.setEventos(eventos);
        deporteRepository.save(d2);
        assertTrue(deporteRepository.delete(d2.getId()),"no se elimina correctamente");
    }

    @Test
    void findAll() {
        List<Deporte> all = deporteRepository.findAll();
        System.out.println(all);
        assertNotNull(all,"No se ha rellenado el list");
        System.out.println(all);
    }
}