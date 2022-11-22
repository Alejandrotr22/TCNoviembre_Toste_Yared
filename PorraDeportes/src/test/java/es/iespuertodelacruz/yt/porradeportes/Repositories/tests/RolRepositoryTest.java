package es.iespuertodelacruz.yt.porradeportes.Repositories.tests;

import es.iespuertodelacruz.yt.porradeportes.Repositories.RolRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.UsuarioRepository;
import es.iespuertodelacruz.yt.porradeportes.entities.Rol;
import es.iespuertodelacruz.yt.porradeportes.entities.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import java.util.LinkedHashSet;
import java.util.Set;

public class RolRepositoryTest {

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
    void testFindById(){

        Rol rol = null;
        Assertions.assertNotNull(rol = rolRepository.findByID(1), "Find ha devuelto un nulo");
        System.out.println(rol.toString());

    }

    @Test
    void testSave(){

        Usuario usuario = usuarioRepository.findByID(1);
        Rol rol = new Rol();
        rol.setNombre("rolTest");
        Set<Usuario> usuariosRol = new LinkedHashSet<>();
        usuariosRol.add(usuario);
        rol.setUsuarios(usuariosRol);

        Assertions.assertNotNull(rolRepository.save(rol), "Save a devuelto un nulo");

        usuario = usuarioRepository.findByID(1);

        System.out.println(rol.toString());
        System.out.println(usuariosRol.toString());

    }

    @Test
    void testUpdate(){

        Rol rol = rolRepository.findByID(2);
        System.out.println(rol.toString());

        rol.setNombre("RolUpdate");

        rolRepository.update(rol);

        Assertions.assertEquals("RolUpdate", rolRepository.findByID(2).getNombre(), "El nombre del rol del" +
                "usuario no es RolUpdate");

    }

    @Test
    void testDelete(){

        Rol rolGuardado = rolRepository.save(new Rol().setNombre("rolTestDelete"));

        rolRepository.delete(rolGuardado.getId());

        Assertions.assertNull(rolRepository.findByID(rolGuardado.getId()), "Delete no ha devuelvo nulo");


    }

    @Test
    void testFindAll(){

        Assertions.assertNotNull(rolRepository.findAll(), "No se ha podido obtener todos los usuarios");

    }

}
