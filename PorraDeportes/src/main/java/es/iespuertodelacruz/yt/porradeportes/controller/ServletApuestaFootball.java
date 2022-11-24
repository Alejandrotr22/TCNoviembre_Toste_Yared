package es.iespuertodelacruz.yt.porradeportes.controller;

import es.iespuertodelacruz.yt.porradeportes.Repositories.ApuestaRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.EventoRepository;
import es.iespuertodelacruz.yt.porradeportes.entities.Apuesta;
import es.iespuertodelacruz.yt.porradeportes.entities.Equipo;
import es.iespuertodelacruz.yt.porradeportes.entities.Evento;
import es.iespuertodelacruz.yt.porradeportes.entities.Usuario;

import javax.persistence.EntityManagerFactory;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet(name = "ServletApuestaFootball", value = "/ServletApuestaFootball")
public class ServletApuestaFootball extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
        EventoRepository eventoRepository = new EventoRepository(emf);
        ApuestaRepository apuestaRepository = new ApuestaRepository(emf);

        //Integer idEvento = Integer.parseInt(request.getParameter("id_Evento"));
        Evento eventoApostar = eventoRepository.findByID(/*idEvento*/1);
        ArrayList<Equipo> participantes = new ArrayList<Equipo>(eventoApostar.getParticipantes());
        List<Equipo> participantesOrdenados = participantes.stream().sorted((Comparator.comparing(Equipo::getId))).collect(Collectors.toList());
        request.setAttribute("equipo1", participantesOrdenados.get(0).getNombre());
        request.setAttribute("cuotaEquipo1", "1.25");
        request.setAttribute("equipo2", participantesOrdenados.get(1).getNombre());
        request.setAttribute("cuotaEquipo2", "2.50");
        request.setAttribute("empate", participantesOrdenados.get(2).getNombre());
        request.setAttribute("cuotaEmpate", "1.85");


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ApuestaFootball.jsp");
        requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger logger = Logger.getLogger("ganador");
        logger.log(Level.INFO, request.getParameter("ganador"));
        logger.log(Level.INFO, request.getParameter("cuota"+request.getParameter("ganador")));

        EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
        EventoRepository eventoRepository = new EventoRepository(emf);
        ApuestaRepository apuestaRepository = new ApuestaRepository(emf);

        Evento eventoApostar = eventoRepository.findByID(/*idEvento*/1);
        String ganador = request.getParameter("ganador");
        Usuario usuario = (Usuario) request.getSession().getAttribute("user");
        String prediccion = ganador;
        BigDecimal cantidad = BigDecimal.valueOf(Double.valueOf(request.getParameter("CuantiaGanador")));
        BigDecimal cuota = BigDecimal.valueOf(Double.valueOf(request.getParameter("cuota"+request.getParameter("ganador"))));

        Apuesta apuesta = new Apuesta();
        apuesta.setUsuario(usuario);
        apuesta.setEvento(eventoApostar);
        apuesta.setPrediccion(prediccion);
        apuesta.setCantidad(cantidad);
        apuesta.setEstado("Realizada");
        apuesta.setCuota(cuota);
        apuestaRepository.save(apuesta);



    }
}
