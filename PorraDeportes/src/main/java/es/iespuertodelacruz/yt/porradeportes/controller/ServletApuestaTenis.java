package es.iespuertodelacruz.yt.porradeportes.controller;

import es.iespuertodelacruz.yt.porradeportes.Repositories.ApuestaRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.EventoRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.UsuarioRepository;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ServletApuestaTenis", value = "/ServletApuestaTenis")
public class ServletApuestaTenis extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
        EventoRepository eventoRepository = new EventoRepository(emf);
        ApuestaRepository apuestaRepository = new ApuestaRepository(emf);

        Evento eventoApostar = eventoRepository.findByID(/*idEvento*/2);
        ArrayList<Equipo> participantes = new ArrayList<Equipo>(eventoApostar.getParticipantes());
        List<Equipo> participantesOrdenados = participantes.stream().sorted((Comparator.comparing(Equipo::getId))).collect(Collectors.toList());
        request.setAttribute("equipo1", participantesOrdenados.get(0).getNombre());
        request.setAttribute("cuotaEquipo1", "1.65");
        request.setAttribute("equipo2", participantesOrdenados.get(1).getNombre());
        request.setAttribute("cuotaEquipo2", "1.85");



        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ApuestaTenis.jsp");
        requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
        EventoRepository eventoRepository = new EventoRepository(emf);
        ApuestaRepository apuestaRepository = new ApuestaRepository(emf);
        UsuarioRepository usuarioRepository = new UsuarioRepository(emf);
        Integer eventoID = null;
        Evento eventoApostar = null;

        if(request.getParameter("elegirEvento") != null) {
            eventoID = Integer.parseInt(request.getParameter("evento"));
            eventoApostar = eventoRepository.findByID(eventoID);
            request.getSession().setAttribute("evento", eventoApostar);
            ArrayList<Equipo> participantes = new ArrayList<Equipo>(eventoApostar.getParticipantes());
            List<Equipo> participantesOrdenados = participantes.stream().sorted((Comparator.comparing(Equipo::getId))).collect(Collectors.toList());
            List<Evento> ganadoEquipo1 = eventoRepository.findGanado(participantesOrdenados.get(0).getId());
            List<Evento> ganadoEquipo2 = eventoRepository.findGanado(participantesOrdenados.get(1).getId());

            if(ganadoEquipo1.size() > ganadoEquipo2.size()){

                request.setAttribute("cuotaEquipo1", "1.55");
                request.setAttribute("cuotaEquipo2", "1.95");


            } else if (ganadoEquipo1.size() < ganadoEquipo2.size()) {

                request.setAttribute("cuotaEquipo1", "1.95");
                request.setAttribute("cuotaEquipo2", "1.55");


            }else{

                request.setAttribute("cuotaEquipo1", "1.75");
                request.setAttribute("cuotaEquipo2", "1.75");

            }

            request.setAttribute("equipo1", participantesOrdenados.get(0).getNombre());
            request.setAttribute("equipo2", participantesOrdenados.get(1).getNombre());

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("ApuestaTenis.jsp");
            requestDispatcher.forward(request, response);

        }else if(request.getParameter("ApuestaGanador") != null) {

            eventoApostar = (Evento) request.getSession().getAttribute("evento");
            String ganador = request.getParameter("ganador");
            Usuario usuario = (Usuario) request.getSession().getAttribute("user");
            String prediccion = ganador;
            BigDecimal cantidad = BigDecimal.valueOf(Double.valueOf(request.getParameter("CuantiaGanador")));
            BigDecimal cuota = BigDecimal.valueOf(Double.valueOf(request.getParameter("cuota" + request.getParameter("ganador"))));
            Apuesta apuesta = new Apuesta();
            apuesta.setUsuario(usuario);
            apuesta.setEvento(eventoApostar);
            apuesta.setPrediccion(prediccion);
            apuesta.setCantidad(cantidad);
            apuesta.setEstado("Realizada");
            apuesta.setCuota(cuota);
            apuestaRepository.save(apuesta);

            Usuario usuarioApuesta = new Usuario(usuario);
            usuarioApuesta.setSaldo(BigDecimal.valueOf(usuarioApuesta.getSaldo().doubleValue() - cantidad.doubleValue()));
            usuarioRepository.update(usuarioApuesta);
            request.getSession().setAttribute("user", usuarioApuesta);

            response.sendRedirect("ServletPrincipal");

        }

    }
}
