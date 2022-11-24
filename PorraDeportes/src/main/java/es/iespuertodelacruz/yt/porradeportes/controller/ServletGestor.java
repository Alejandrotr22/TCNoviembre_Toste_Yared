package es.iespuertodelacruz.yt.porradeportes.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

import es.iespuertodelacruz.yt.porradeportes.Repositories.DeporteRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.EquipoRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.EventoRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.RolRepository;
import es.iespuertodelacruz.yt.porradeportes.Repositories.UsuarioRepository;
import es.iespuertodelacruz.yt.porradeportes.entities.Deporte;
import es.iespuertodelacruz.yt.porradeportes.entities.Equipo;
import es.iespuertodelacruz.yt.porradeportes.entities.Evento;

/**
 * Servlet implementation class ServletGestor
 */
@WebServlet("/ServletGestor")
public class ServletGestor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		Logger logger = Logger.getLogger("logger");
        UsuarioRepository usuarioRepository = new UsuarioRepository(emf);
        EventoRepository eventoRepository = new EventoRepository(emf);
        RolRepository rolRepository = new RolRepository(emf);
        EquipoRepository equipoRepository = new EquipoRepository(emf);
        DeporteRepository deporteRepository = new DeporteRepository(emf);
        
        
        List<Deporte> deportes = deporteRepository.findAll();
        request.setAttribute("deportes", deportes);
        
        List<Evento> eventos = eventoRepository.findAll();
        request.setAttribute("eventos", eventos);
        
        List<Equipo> equipos = equipoRepository.findAll();
        request.setAttribute("equipos", equipos);
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("vistaGestor.jsp");
        requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
        UsuarioRepository usuarioRepository = new UsuarioRepository(emf);
        EventoRepository eventoRepository = new EventoRepository(emf);
        RolRepository rolRepository = new RolRepository(emf);
        EquipoRepository equipoRepository = new EquipoRepository(emf);
        DeporteRepository deporteRepository = new DeporteRepository(emf);
		
		//Eventos
		
		// crear Evento
		if (request.getParameter("CrearE") != null) {
			String nombre = request.getParameter("nombreCrearE");
			String fecha1 = request.getParameter("FechaCrearE");
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			Date date1 = new Date();
			Date date2 = new Date(date1.getTime() + 86400000);
			try {
				date1 = formato.parse(fecha1);
			} catch (ParseException e) {
				
			}
			
			String strParticipantes = request.getParameter("PartCrearE");
			String[] split = strParticipantes.split(",");
			Set<Equipo> participantes = new LinkedHashSet<>();
			for (String id : split) {
				try {
					Equipo equipo = equipoRepository.findByID(Integer.parseInt(id));
					participantes.add(equipo);
				} catch (Exception e) {
					
				}
			}
			
			
			String strDeporte = request.getParameter("DeporteCrearE");
			String[] splitDeporte = strDeporte.split("-");
			Deporte deporte = new Deporte();
			try {
				deporte = deporteRepository.findByID(Integer.parseInt(splitDeporte[1])); 
			} catch (Exception e) {
				
			}
			Evento evento = new Evento();
			evento.setNombre(nombre);
			evento.setFechaInicio(date1.toInstant());
			evento.setFechaFin(date2.toInstant());
			evento.setIdDeporte(deporte);
			evento.setParticipantes(participantes);
			
			Evento save = eventoRepository.save(evento);
			
			request.setAttribute("res", save + "");
			this.doGet(request, response);
		}
		
		// modificar Evento
		if (request.getParameter("ModE") != null) {
			
			String strEvento = request.getParameter("IdModE");
			String[] splitEvento = strEvento.split("-");
			Evento evento = new Evento();
			try {
				evento = eventoRepository.findByID(Integer.parseInt(splitEvento[1])); 
			} catch (Exception e) {
				
			}
										
			String nombre = request.getParameter("nombreModE");
			String fecha1 = request.getParameter("FechaInicioModE");
			String fecha2 = request.getParameter("FechaFinModE");
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			Date date1 = new Date();
			Date date2 = new Date();
			try {
				date1 = formato.parse(fecha1);
				date2 = formato.parse(fecha2);
			} catch (ParseException e) {
				
			}
			
			String strParticipantes = request.getParameter("PartModE");
			String[] split = strParticipantes.split(",");
			Set<Equipo> participantes = new LinkedHashSet<>();
			for (String id : split) {
				try {
					Equipo equipo = equipoRepository.findByID(Integer.parseInt(id));
					participantes.add(equipo);
				} catch (Exception e) {
					
				}
			}
			
			String resultado = request.getParameter("EstadoModE");
			String strDeporte = request.getParameter("DeporteModE");
			String[] splitDeporte = strDeporte.split("-");
			Deporte deporte = new Deporte();
			try {
				deporte = deporteRepository.findByID(Integer.parseInt(splitDeporte[1])); 
			} catch (Exception e) {
				
			}
			
			String strGanador = request.getParameter("GanadorModE");
			String[] splitGanador = strGanador.split("-");
			Equipo equipo = new Equipo();
			try {
				equipo = equipoRepository.findByID(Integer.parseInt(splitGanador[1])); 
			} catch (Exception e) {
				
			}
			
			
			
			if (!nombre.equals("")) {
				evento.setNombre(nombre);
			}
			if (!fecha1.equals("")) {
				evento.setFechaInicio(date1.toInstant());
			}
			if (!fecha1.equals("")) {
				evento.setFechaFin(date2.toInstant());
			}
			if (!strDeporte.equals("")) {
				evento.setIdDeporte(deporte);
			}
			if (!strParticipantes.equals("")) {
				evento.setParticipantes(participantes);
			}
			if (!strGanador.equals("")) {
				evento.setIdEquipoGanador(equipo);
			}
			if	(!resultado.equals("")) {
				evento.setResultado(resultado);
			}
			
			
			Boolean update = eventoRepository.update(evento);
			
			request.setAttribute("res", (update)?"Se ha actualizado correctamente": "No se ha actualizado correctamente" + "");
			this.doGet(request, response);
		}
		
		// eliminar Evento
		if (request.getParameter("DelE") != null) {
										
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("vistaGestor.jsp");
            requestDispatcher.forward(request, response);
		
		}
		// mostrar Evento
		if (request.getParameter("FindE") != null) {
			String eventoid = request.getParameter("IdFindE");
			String[] split = eventoid.split("-");
			Evento save = null;
			try {
				save = eventoRepository.findByID(Integer.parseInt(split[1]));
			} catch (Exception e) {
				
			}
			
			request.setAttribute("res", save + "");
			doGet(request, response);
		
		}
		// mostrar Todos
		if (request.getParameter("FindAllE") != null) {
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("vistaGestor.jsp");
            requestDispatcher.forward(request, response);
		
		}
		
		// Apuestas
		
		
	}

}
