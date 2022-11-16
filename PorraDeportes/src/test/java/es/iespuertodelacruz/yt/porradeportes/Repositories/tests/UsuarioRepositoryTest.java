package es.iespuertodelacruz.yt.porradeportes.Repositories.tests;

import es.iespuertodelacruz.yt.porradeportes.Repositories.RolRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.UsuarioRepository;
import es.iespuertodelacruz.yt.porradeportes.entities.Rol;
import es.iespuertodelacruz.yt.porradeportes.entities.Usuario;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManagerFactory;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.fail;


class UsuarioRepositoryTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {

        EMFhelper emfHelper = EMFhelper.getSingleton();
        EntityManagerFactory emf = emfHelper.getEMF();
        usuarioRepository = new UsuarioRepository(emf);
        rolRepository = new RolRepository(emf);
    }

    static UsuarioRepository usuarioRepository;
    static RolRepository rolRepository;

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @Test
    void testFindById() {
        fail("Not yet implemented");
    }

    @Test
    void testSave() {

        Rol rol = rolRepository.findByID(1);
        Usuario usuario = new Usuario();
        usuario.setNombre("yared");
        usuario.setEmail("email@gmail.com");
        usuario.setSaldo(BigDecimal.valueOf(3.23));
        usuario.setIdRol(rol);
        usuario.setPassword("pass");
        Assertions.assertNotNull(usuarioRepository.save(usuario));

        Usuario usuario2 = new Usuario();
        usuario2.setNombre("toste");
        usuario2.setEmail("email@gmail.com");
        usuario2.setSaldo(BigDecimal.valueOf(4.5));
        usuario2.setIdRol(rol);
        usuario2.setPassword("pass");
        Assertions.assertNotNull(usuarioRepository.save(usuario2));
        rol = rolRepository.findByID(1);

        System.out.println(usuario.toString());
        System.out.println(usuario2.toString());
        System.out.println(rol.toString());


    }

    @Test
    void testUpdate() {
        fail("Not yet implemented");
    }

    @Test
    void testDelete() {
        fail("Not yet implemented");
    }

}
