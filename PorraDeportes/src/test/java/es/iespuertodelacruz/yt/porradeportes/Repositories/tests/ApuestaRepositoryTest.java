package es.iespuertodelacruz.yt.porradeportes.Repositories.tests;

import es.iespuertodelacruz.yt.porradeportes.Repositories.ApuestaRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.EventoRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.RolRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.UsuarioRepository;
import es.iespuertodelacruz.yt.porradeportes.entities.Apuesta;
import es.iespuertodelacruz.yt.porradeportes.entities.Evento;
import es.iespuertodelacruz.yt.porradeportes.entities.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Table;
import java.math.BigDecimal;

public class ApuestaRepositoryTest {
    @BeforeAll
    static void setUpBeforeClass() throws Exception {

        EMFhelper emfHelper = EMFhelper.getSingleton();
        EntityManagerFactory emf = emfHelper.getEMF();
        apuestaRepository = new ApuestaRepository(emf);
        usuarioRepository = new UsuarioRepository(emf);
        eventoRepository = new EventoRepository(emf);


    }

    static ApuestaRepository apuestaRepository;
    static UsuarioRepository usuarioRepository;
    static EventoRepository eventoRepository;

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @Test
    void testFindById(){


        Apuesta apuesta = null;
        Assertions.assertNotNull(apuesta = apuestaRepository.findByID(1));
        System.out.println(apuesta.toString());

    }

    @Test
    void testSave(){

        Evento evento = eventoRepository.findByID(1);
        Usuario usuario = usuarioRepository.findByID(1);
        Apuesta apuesta = new Apuesta();
        apuesta.setUsuario(usuario);
        apuesta.setCantidad(BigDecimal.valueOf(25));
        apuesta.setCuota(BigDecimal.valueOf(2.5));
        apuesta.setEvento(evento);
        apuesta.setEstado("en curso");
        apuesta.setPrediccion("Madrid_2_Barca_5");
        Assertions.assertNotNull(apuestaRepository.save(apuesta), "Se ha devuelto un nulo al intentar guardar");

    }

    @Test
    void testUpdate(){

        Apuesta apuesta = apuestaRepository.findByID(1);
        apuesta.setPrediccion("Madrid_5_Barca_2");
        apuestaRepository.update(apuesta);

        Assertions.assertEquals("Madrid_5_Barca_2",
                apuestaRepository.findByID(1).getPrediccion());

    }

    @Test
    void testFindAll(){

        Assertions.assertNotNull(apuestaRepository.findAll(),
                "El findAll ha devuelto un nulo");

    }

}
