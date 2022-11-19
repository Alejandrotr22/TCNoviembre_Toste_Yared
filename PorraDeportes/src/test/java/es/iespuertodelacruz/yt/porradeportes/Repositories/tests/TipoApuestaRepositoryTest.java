package es.iespuertodelacruz.yt.porradeportes.Repositories.tests;

import es.iespuertodelacruz.yt.porradeportes.Repositories.TipoApuestaRepository;
import es.iespuertodelacruz.yt.porradeportes.entities.TipoApuesta;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;

public class TipoApuestaRepositoryTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {

        EMFhelper emfHelper = EMFhelper.getSingleton();
        EntityManagerFactory emf = emfHelper.getEMF();
        tipoApuestaRepository = new TipoApuestaRepository(emf);
    }

    static TipoApuestaRepository tipoApuestaRepository;

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @Test
    void testFindById(){

        TipoApuesta tipoApuesta = null;

        Assertions.assertNotNull(tipoApuesta = tipoApuestaRepository.findByID(1));
        System.out.println(tipoApuesta.toString());

    }

    @Test
    void testSave() {

        TipoApuesta tipoApuesta = new TipoApuesta();
        tipoApuesta.setDescripcion("TipoApuestaTestSave");
        Assertions.assertNotNull(tipoApuestaRepository.save(tipoApuesta));


    }

    @Test
    void testUpdate(){

        TipoApuesta tipoApuesta = tipoApuestaRepository.findByID(1);
        tipoApuesta.setDescripcion("TipoApuestaUpdate");

        tipoApuestaRepository.update(tipoApuesta);

        Assertions.assertEquals("TipoApuestaUpdate", tipoApuestaRepository.findByID(1).getDescripcion());


    }

    @Test
    void testDelete(){

        TipoApuesta tipoApuestaGuardada = tipoApuestaRepository.save(new TipoApuesta()
                .setDescripcion("TipoApuestaDelete"));

        tipoApuestaRepository.delete(tipoApuestaGuardada.getId());

        Assertions.assertNull(tipoApuestaRepository.findByID(tipoApuestaGuardada.getId()));


    }

    @Test
    void testFindAll(){

        Assertions.assertNotNull(tipoApuestaRepository.findAll());

    }

}
