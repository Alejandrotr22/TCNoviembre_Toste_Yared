package es.iespuertodelacruz.yt.Repositories;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class EventoRepositoryTest {

    EntityManagerFactory emf;

    @BeforeAll
    void setUp() {
        emf = Persistence.createEntityManagerFactory("PorraDeportes");
    }

    @AfterAll
    void tearDown() {
        emf.close();
    }

    @Test
    void save() {
    }

    @Test
    void findByID() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }
}