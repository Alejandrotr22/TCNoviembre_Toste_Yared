package es.iespuertodelacruz.yt.porradeportes.controller;

import es.iespuertodelacruz.yt.porradeportes.Repositories.ApuestaRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.EventoRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.UsuarioRepository;
import es.iespuertodelacruz.yt.porradeportes.entities.Apuesta;
import es.iespuertodelacruz.yt.porradeportes.entities.Evento;
import es.iespuertodelacruz.yt.porradeportes.entities.Usuario;

import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletUsuario", value = "/ServletUsuario")
public class ServletUsuario extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
        ApuestaRepository apuestaRepository = new ApuestaRepository(emf);

        Usuario user = (Usuario) request.getSession().getAttribute("user");
        List<Apuesta> allById = apuestaRepository.findAllById(user);

        request.setAttribute("usuario",user);
        request.setAttribute("apuestas",allById);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("vistaUsuario.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
        UsuarioRepository usuarioRepository = new UsuarioRepository(emf);


        Usuario user = (Usuario) request.getSession().getAttribute("user");
        BigDecimal saldo = null;
        if (request.getParameter("actualizar")!= null){
            try {
                 saldo = new BigDecimal(Double.parseDouble(request.getParameter("saldo")));
            }catch (Exception ex){

            }
            if(saldo != null){
                user.setSaldo(saldo);
                usuarioRepository.update(user);
            }
            this.doGet(request,response);
        }
        this.doGet(request,response);
    }
}
