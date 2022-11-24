package es.iespuertodelacruz.yt.porradeportes.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.yt.porradeportes.Repositories.ApuestaRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.EventoRepository;
import es.iespuertodelacruz.yt.porradeportes.entities.Equipo;
import es.iespuertodelacruz.yt.porradeportes.entities.Evento;

/**
 * Servlet implementation class ServletApuestasFormula
 */
@WebServlet("/ServletApuestasFormula")
public class ServletApuestasFormula extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletApuestasFormula() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
        EventoRepository eventoRepository = new EventoRepository(emf);
        ApuestaRepository apuestaRepository = new ApuestaRepository(emf);
        
        //Integer idEvento = Integer.parseInt(request.getParameter("id_Evento"));
        
        Evento evento = eventoRepository.findByID(1);
        ArrayList<Equipo> participantes = new ArrayList<Equipo>(evento.getParticipantes());
        
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
