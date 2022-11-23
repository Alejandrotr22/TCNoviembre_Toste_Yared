package es.iespuertodelacruz.yt.porradeportes.Repositories.tests;

import es.iespuertodelacruz.yt.porradeportes.Repositories.EquipoRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.RolRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.UsuarioRepository;
import es.iespuertodelacruz.yt.porradeportes.entities.Equipo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;

public class EquipoRepositoryTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {

        EMFhelper emfHelper = EMFhelper.getSingleton();
        EntityManagerFactory emf = emfHelper.getEMF();
        usuarioRepository = new UsuarioRepository(emf);
        rolRepository = new RolRepository(emf);
        equipoRepository = new EquipoRepository(emf);
    }

    static UsuarioRepository usuarioRepository;
    static RolRepository rolRepository;
    static EquipoRepository equipoRepository;

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @Test
    void testFindById(){

        Equipo equipo = null;
        Assertions.assertNotNull(equipo = equipoRepository.findByID(1),
                "No se ha podido encontrar al equipo");

    }

    @Test
    void testSave(){

        Equipo equipo = new Equipo();
        equipo.setNombre("Madrid");
        Assertions.assertNotNull(equipoRepository.save(equipo),
                "No se ha guardado correctamente el equipo");

    }

    @Test
    void testUpdate(){

        Equipo equipo = equipoRepository.findByID(1);
        equipo.setNombre("Barca");
        equipoRepository.update(equipo);

        Assertions.assertEquals("Barca", equipoRepository.findByID(1).getNombre());

    }

    @Test
    void testDelete(){

        Equipo equipoGuardado = equipoRepository.save(new Equipo().setNombre("ManCity"));

        equipoRepository.delete(equipoGuardado.getId());

        Assertions.assertNull(equipoRepository.findByID(equipoGuardado.getId()),
                "Aun existe el equipo tras el delete");

    }

    @Test
    void testFindAll(){

        Assertions.assertNotNull(equipoRepository.findAll(),
                "El findAll ha devuelto nulo");

    }


}
