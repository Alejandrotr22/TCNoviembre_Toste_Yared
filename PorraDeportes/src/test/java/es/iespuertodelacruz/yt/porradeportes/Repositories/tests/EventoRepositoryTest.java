package es.iespuertodelacruz.yt.porradeportes.Repositories.tests;

import es.iespuertodelacruz.yt.porradeportes.Repositories.DeporteRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.EventoRepository;
import es.iespuertodelacruz.yt.porradeportes.entities.Apuesta;
import es.iespuertodelacruz.yt.porradeportes.entities.Deporte;
import es.iespuertodelacruz.yt.porradeportes.entities.Equipo;
import es.iespuertodelacruz.yt.porradeportes.entities.Evento;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
class EventoRepositoryTest {

    EntityManagerFactory emf;


    DeporteRepository deporteRepository;
    EventoRepository eventoRepository;
    Set<Apuesta> apuestas = new LinkedHashSet<>();
    Set<Equipo> participantes = new LinkedHashSet<>();
    String nombre = "partido 1";

    @BeforeEach
    void setUp() {
        EMFhelper EMF = EMFhelper.getSingleton();
        emf = EMF.getEMF();
        deporteRepository = new DeporteRepository(emf);
        eventoRepository = new EventoRepository(emf);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        Evento e = crearEvento();
        assertNotNull(e.getId(),"No se le ha asignado un id");
    }

    @Test
    void findByID() {
        Evento e = crearEvento();
        Evento e2 = eventoRepository.findByID(e.getId());
        assertEquals(e.getId(),e2.getId(), "los id no son iguales");
    }

    @Test
    void update() {
        Evento e = crearEvento();
        e.setNombre("evento 4");
        assertTrue(eventoRepository.update(e),"no se ha actualizado correctamente");

    }

    @Test
    void delete() {
        Evento e = crearEvento();
        assertTrue(eventoRepository.delete(e.getId()),"no se ha borrado correctamente");
    }

    @Test
    void findAll() {
        List<Evento> all = eventoRepository.findAll();
        System.out.println(all);
        assertNotNull(all,"No se ha rellenado el list");
    }
    private Evento crearEvento(){
        Evento evento = new Evento();
        evento.setNombre(nombre);
        evento.setIdDeporte(deporteRepository.findByID(1));
        evento.setApuestas(apuestas);
        long time = new Date().getTime();
        Timestamp fecha = new Timestamp(time);
        evento.setFechaFin(fecha.toInstant());
        evento.setFechaInicio(fecha.toInstant());
        evento.setParticipantes(participantes);
        evento.setResultado(null);
        evento.setIdEquipoGanador(null);
        evento = eventoRepository.save(evento);
        return evento;
    }
}