package es.iespuertodelacruz.yt.porradeportes.controller;

import es.iespuertodelacruz.yt.porradeportes.Repositories.EventoRepository;
import es.iespuertodelacruz.yt.porradeportes.entities.Evento;

import javax.persistence.EntityManagerFactory;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletPrincipal", value = "/ServletPrincipal")
public class ServletPrincipal extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
        EventoRepository eventoRepository = new EventoRepository(emf);

        List<Evento> eventosFootball = new ArrayList<>();
        List<Evento> eventosTenis = new ArrayList<>();
        List<Evento> eventosFormula = new ArrayList<>();
        List<Evento> eventosActivos = eventoRepository.findActive();

        for (Evento eventoActivo : eventosActivos) {

            if(eventoActivo.getIdDeporte().getId() == 1){
                eventosFootball.add(eventoActivo);
            } else if (eventoActivo.getIdDeporte().getId() == 2) {
                eventosTenis.add(eventoActivo);
            } else if(eventoActivo.getIdDeporte().getId() == 3){
                eventosFormula.add(eventoActivo);
            }
        }

        request.setAttribute("eventosFutbol", eventosFootball);
        request.setAttribute("eventosTenis", eventosTenis);
        request.setAttribute("eventosFormula", eventosFormula);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Principal.jsp");
        requestDispatcher.forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
