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

        Usuario usuario = null;
        Assertions.assertNotNull(usuario = usuarioRepository.findByID(1), "No se ha podido encontrar al usuario");
        System.out.println(usuario.toString());

    }

    @Test
    void testSave() {

        Rol rol = rolRepository.findByID(1);
        Usuario usuario = new Usuario();
        usuario.setNombre("yared");
        usuario.setEmail("email@gmail.com");
        usuario.setSaldo(BigDecimal.valueOf(3.23));
        usuario.setRol(rol);
        usuario.setPassword("pass");
        Assertions.assertNotNull(usuarioRepository.save(usuario), "No se ha guardado el usuario");

        Usuario usuario2 = new Usuario();
        usuario2.setNombre("toste");
        usuario2.setEmail("email@gmail.com");
        usuario2.setSaldo(BigDecimal.valueOf(4.5));
        usuario2.setRol(rol);
        usuario2.setPassword("pass");
        Assertions.assertNotNull(usuarioRepository.save(usuario2), "No se ha guardado el usuario");
        rol = rolRepository.findByID(1);

        System.out.println(usuario.toString());
        System.out.println(usuario2.toString());
        System.out.println(rol.toString());


    }

    @Test
    void testUpdate() {

        Usuario usuario = usuarioRepository.findByID(1);
        System.out.println(usuario.toString());
        usuario.setSaldo(BigDecimal.valueOf(125.25));

        usuarioRepository.update(usuario);

        Assertions.assertEquals(BigDecimal.valueOf(125.25), usuarioRepository.findByID(1).getSaldo(), "El saldo" +
                " del usuario no es 125.25");


    }

    @Test
    void testDelete() {

        Usuario usuarioGuardado = usuarioRepository.save(new Usuario().setNombre("testDelete").setEmail("email@gmail.com").setPassword("pass")
                .setRol(rolRepository.findByID(1)).setSaldo(BigDecimal.valueOf(50)));

        System.out.println(usuarioRepository.findByID(usuarioGuardado.getId()).toString());

        usuarioRepository.delete(usuarioGuardado.getId());
        Assertions.assertNull(usuarioRepository.findByID(usuarioGuardado.getId()), "El usuario sigue existiendo " +
                "tras el delete");

    }

    @Test
    void testFindAll(){

        Assertions.assertNotNull(usuarioRepository.findAll(), "El findAll ha devuelto un nulo");

    }

}
