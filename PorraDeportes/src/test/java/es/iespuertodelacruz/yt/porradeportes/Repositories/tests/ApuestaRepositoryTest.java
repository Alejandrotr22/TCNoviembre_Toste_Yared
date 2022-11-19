package es.iespuertodelacruz.yt.porradeportes.Repositories.tests;

import es.iespuertodelacruz.yt.porradeportes.Repositories.ApuestaRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.RolRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.UsuarioRepository;
import es.iespuertodelacruz.yt.porradeportes.entities.Apuesta;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Table;

public class ApuestaRepositoryTest {

    static void setUpBeforeClass() throws Exception {

        EMFhelper emfHelper = EMFhelper.getSingleton();
        EntityManagerFactory emf = emfHelper.getEMF();
        apuestaRepository = new ApuestaRepository(emf);

    }

    static ApuestaRepository apuestaRepository;

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

        Apuesta apuesta = new Apuesta();



    }

}
