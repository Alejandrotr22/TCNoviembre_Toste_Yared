package es.iespuertodelacruz.yt.porradeportes.controller;

import es.iespuertodelacruz.yt.porradeportes.Repositories.RolRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.UsuarioRepository;
import es.iespuertodelacruz.yt.porradeportes.entities.Usuario;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.EntityManagerFactory;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ServletLoginRegister", value = "/ServletLoginRegister")
public class ServletLoginRegister extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
        UsuarioRepository usuarioRepository = new UsuarioRepository(emf);
        RolRepository rolRepository = new RolRepository(emf);

        if(request.getParameter("login") != null){

            Usuario usuarioDDBB = usuarioRepository.findByEmail(request.getParameter("email"));
            if(usuarioDDBB != null){

                String passForm = request.getParameter("password");
                boolean checkpw = BCrypt.checkpw(passForm, usuarioDDBB.getPassword());
                if(checkpw) {
                    request.getSession().setAttribute("user", usuarioDDBB);
                    /*RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ServletApuestaFootball");
                    requestDispatcher.forward(request, response);
                     */
                    response.sendRedirect("/ServletGestor");

                }else{
                    request.setAttribute("respuesta", "Contraseña Incorrecta");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
                    requestDispatcher.forward(request, response);
                }
            }else{
                request.setAttribute("respuesta", "Usuario no encontrado");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
                requestDispatcher.forward(request, response);
            }

        }else if(request.getParameter("registro") != null){

            String user = request.getParameter("usuario");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if(usuarioRepository.findByUser(user) == null && usuarioRepository.findByEmail(email) == null){

                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setNombre(user);
                nuevoUsuario.setRol(rolRepository.findByID(1));
                nuevoUsuario.setSaldo(BigDecimal.valueOf(0));
                nuevoUsuario.setEmail(email);
                nuevoUsuario.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
                usuarioRepository.save(nuevoUsuario);
                request.getSession().setAttribute("user", nuevoUsuario);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.html");
                requestDispatcher.forward(request, response);

            }else{

                request.setAttribute("respuesta", "Ya existe una cuenta con ese usuario o email");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("registrarse.jsp");
                requestDispatcher.forward(request, response);

            }

        }


    }
}
