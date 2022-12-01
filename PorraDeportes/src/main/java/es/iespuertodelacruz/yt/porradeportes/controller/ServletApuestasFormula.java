package es.iespuertodelacruz.yt.porradeportes.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.yt.porradeportes.Repositories.ApuestaRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.EventoRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.UsuarioRepository;
import es.iespuertodelacruz.yt.porradeportes.entities.Apuesta;
import es.iespuertodelacruz.yt.porradeportes.entities.Equipo;
import es.iespuertodelacruz.yt.porradeportes.entities.Evento;
import es.iespuertodelacruz.yt.porradeportes.entities.Usuario;

/**
 * Servlet implementation class ServletApuestasFormula
 */
@WebServlet(name = "ServletApuestaFormula", value= "/ServletApuestaFormula")
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

		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		EventoRepository eventoRepository = new EventoRepository(emf);
		ApuestaRepository apuestaRepository = new ApuestaRepository(emf);

		Logger logger = Logger.getLogger("Participantes");


		UsuarioRepository usuarioRepository = new UsuarioRepository(emf);
		Integer eventoID = null;
		Evento eventoApostar = null;

		if(request.getParameter("elegirEvento") != null) {
			Random rnd = new Random();

			eventoID = Integer.parseInt(request.getParameter("evento"));
			eventoApostar = eventoRepository.findByID(eventoID);
			request.getSession().setAttribute("evento", eventoApostar);
			ArrayList<Equipo> participantes = new ArrayList<Equipo>(eventoApostar.getParticipantes());
			List<Equipo> participantesOrdenados = participantes.stream().sorted((Comparator.comparing(Equipo::getId))).collect(Collectors.toList());
			List<String> participantesCuota = new ArrayList<>();
			double cuota;

			for (Equipo participantesOrdenado : participantesOrdenados) {

				cuota = (rnd.nextInt(46) + 140) / 100.0;
				participantesCuota.add(participantesOrdenado.getNombre() + "    " + cuota);

			}

			request.setAttribute("participantes", participantesCuota);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("ApuestaFormula.jsp");
			requestDispatcher.forward(request, response);

		}else if(request.getParameter("ApuestaGanador") != null) {
			eventoApostar = (Evento) request.getSession().getAttribute("evento");
			String ganador = request.getParameter("ganador");
			Usuario usuario = (Usuario) request.getSession().getAttribute("user");
			String[] splitGanadorCuota = ganador.split(" ");
			String prediccion = splitGanadorCuota[0];
			BigDecimal cantidad = BigDecimal.valueOf(Double.valueOf(request.getParameter("CuantiaGanador")));
			BigDecimal cuota = BigDecimal.valueOf(Double.parseDouble(splitGanadorCuota[1]));
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
