package es.iespuertodelacruz.yt.porradeportes.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.yt.porradeportes.Repositories.*;
import es.iespuertodelacruz.yt.porradeportes.entities.*;
import org.jboss.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

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
        ApuestaRepository apuestaRepository = new ApuestaRepository(emf);


        List<Deporte> deportes = deporteRepository.findAll();
        request.setAttribute("deportes", deportes);

        List<Evento> eventos = eventoRepository.findAll();
        request.setAttribute("eventos", eventos);

        List<Equipo> equipos = equipoRepository.findAll();
        request.setAttribute("equipos", equipos);

        List<Usuario> usuarios = usuarioRepository.findAll();
        request.setAttribute("usuarios", usuarios);

        List<Apuesta> apuestas = apuestaRepository.findAll();
        System.out.println(apuestas);
        request.setAttribute("apuestas", apuestas);

        String tabE = (String) request.getAttribute("tabE");
        String tabA = (String) request.getAttribute("tabA");
        String tabU = (String) request.getAttribute("tabU");
        String tabD = (String) request.getAttribute("tabD");

        if (tabE == null && tabA == null && tabD == null && tabU == null) {
            request.setAttribute("tabE", "active show");
            request.setAttribute("tabA", "");
            request.setAttribute("tabD", "");
            request.setAttribute("tabU", "");
        }


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
        ApuestaRepository apuestaRepository = new ApuestaRepository(emf);
        Logger log = Logger.getLogger("log");

        //region Formularios de Eventos

        //Crear Evento
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
            Evento save = eventoRepository.save(evento);

            request.setAttribute("res", save + "");
            this.doGet(mostrarTab(request, response, 1), response);
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

            String nombre = request.getParameter("NombreModE");
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


            String resultado = request.getParameter("ResModE");
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
            if (!strGanador.equals("")) {
                evento.setIdEquipoGanador(equipo);
            }
            if (!resultado.equals("")) {
                evento.setResultado(resultado);
            }
            Boolean update = eventoRepository.update(evento);

            request.setAttribute("res", (update) ? "Se ha actualizado correctamente" : "No se ha actualizado correctamente" + "");
            this.doGet(mostrarTab(request, response, 1), response);
        }

        // eliminar Evento
        if (request.getParameter("DelE") != null) {

            String eventoid = request.getParameter("IdDelE");
            String[] split = eventoid.split("-");
            Boolean delete = null;
            try {
                delete = eventoRepository.delete(Integer.parseInt(split[1]));
            } catch (Exception e) {

            }
            request.setAttribute("tabE", "active");
            request.setAttribute("tabA", "");
            request.setAttribute("tabU", "");
            request.setAttribute("tabD", "");
            request.setAttribute("res", (delete) ? "Se ha borrado correctamente" : "No se ha borrado correctamente" + "");
            this.doGet(mostrarTab(request, response, 1), response);

        }

        // mostrar Evento
        if (request.getParameter("FindE") != null) {
            String eventoid = request.getParameter("IdFindE");
            String[] split = eventoid.split("-");
            Evento find = null;
            try {
                find = eventoRepository.findByID(Integer.parseInt(split[1]));
            } catch (Exception e) {

            }


            request.setAttribute("res", find + "");
            this.doGet(mostrarTab(request, response, 1), response);

        }

        // mostrar Todos
        if (request.getParameter("FindAllE") != null) {

            List<Evento> findAll = eventoRepository.findAll();




            request.setAttribute("res", findAll + "");
            this.doGet(mostrarTab(request, response, 1), response);

        }

        //endregion

        //region Formularios de Apuestas

        //Modificar Apuesta
        if (request.getParameter("ModA") != null) {
            String strApuesta = request.getParameter("IdModA");
            String[] splitApuesta = strApuesta.split("-");
            Apuesta apuesta = new Apuesta();
            try {
                apuesta = apuestaRepository.findByID(Integer.parseInt(splitApuesta[2]));
            } catch (Exception ex) {

            }

            String estado = request.getParameter("EstadoModA");
            apuesta.setEstado(estado);
            Boolean update = apuestaRepository.update(apuesta);

            request.setAttribute("res", (update) ? "Se ha actualizado correctamente" : "No se ha actualizado correctamente" + "");
            this.doGet(mostrarTab(request, response, 2), response);
        }

        //Mostrar Apuesta
        if (request.getParameter("FindA") != null) {
            String apuesta = request.getParameter("IdFindA");
            String[] split = apuesta.split("-");
            Apuesta find = null;
            try {

                find = apuestaRepository.findByID(Integer.parseInt(split[2]));
            } catch (Exception e) {

            }

            request.setAttribute("res", find + "");
            this.doGet(mostrarTab(request, response, 2), response);

        }

        //Cerrar Apuesta
        if (request.getParameter("DelA") != null) {
            String apuesta = request.getParameter("IdDelA");
            String[] split = apuesta.split("-");
            Boolean delete = null;
            try {
                Apuesta find = apuestaRepository.findByID(Integer.parseInt(split[2]));
                find.setEstado("Rechazada");

                delete = apuestaRepository.update(find);
            } catch (Exception e) {

            }

            request.setAttribute("res", (delete) ? "Se ha Cerrado correctamente" : "No se ha Cerrado Corectamente" + "");
            this.doGet(mostrarTab(request, response, 2), response);
        }

        //Mostrar Todos
        if (request.getParameter("FindAllA") != null) {
            String res = "";
            List<Apuesta> findAll = apuestaRepository.findAll();
            for (Apuesta a : findAll) {
                res += a.toString() + "\n";
            }

            request.setAttribute("res", res + "");
            this.doGet(mostrarTab(request, response, 2), response);

        }

        //endregion

        //region Formularios de Usuarios

        //Modificar Usuario
        if (request.getParameter("ModU") != null){
            String strUsuario = request.getParameter("IdModU");
            String[] split = strUsuario.split("-");
            Usuario usuario = null;
            try {
                usuario = usuarioRepository.findByID(Integer.parseInt(split[1]));
            } catch (Exception e) {

            }
            String mail = request.getParameter("EmailModU");
            String password = request.getParameter("PassModU");

            String strRol = request.getParameter("RolModU");
            Rol rol = null;
            if (!strRol.equals("")){
                String[] Rsplit = strRol.split("-");

                try {
                    rol = rolRepository.findByID(Integer.parseInt(split[1]));
                } catch (Exception e) {

                }
            }

            if (!password.equals("")){
                usuario.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            }
            if (!mail.equals("")){
                usuario.setEmail(mail);
            }
            if (rol != null){
                usuario.setRol(rol);
            }
            Boolean update = usuarioRepository.update(usuario);
            request.setAttribute("res", (update)?"Se ha actualizado correctamente":"No se ha actualizado correctamente" + "");
            this.doGet(mostrarTab(request, response, 3), response);
        }

        // Eliminar Usuario
        if (request.getParameter("DelU") != null){
            String strUsuario = request.getParameter("IdModU");
            String[] split = strUsuario.split("-");
            Usuario usuario = null;
            try {
                usuario = usuarioRepository.findByID(Integer.parseInt(split[1]));
            } catch (Exception e) {

            }
            Boolean delete = usuarioRepository.delete(usuario.getId());
            String res = "";
            if (delete == null){
                res = "El usuario no se puede eliminar";
            }else{
                res = "Se ha eliminado correctamente";
            }
            request.setAttribute("res", res);
            this.doGet(mostrarTab(request, response, 3), response);
        }

        //Mostrar Usuario
        if (request.getParameter("FindU") != null){
            String strUsuario = request.getParameter("IdFindU");
            String[] split = strUsuario.split("-");
            Usuario usuario = null;
            List<Apuesta> apuestas = null;
            try {
                usuario = usuarioRepository.findByID(Integer.parseInt(split[1]));
                apuestas = apuestaRepository.findAllById(usuario);
            } catch (Exception e) {

            }

            String res = usuario.toString() + "\nApuestas:\n\n";

                for (Apuesta apuesta: apuestas) {
                    res += apuesta.toString() + "\n\n";
                }

            request.setAttribute("res", res);
            this.doGet(mostrarTab(request, response, 3), response);

        }

        if (request.getParameter("FindAllU") != null ){
            String res = "";
            List<Usuario> findAll = usuarioRepository.findAll();
            for (Usuario u : findAll) {
                res += u.toString() + "\n";
            }
            request.setAttribute("res", res + "");
            this.doGet(mostrarTab(request, response, 3), response);
        }


        //endregion



    }



    public HttpServletRequest mostrarTab(HttpServletRequest request, HttpServletResponse response, int tab) throws ServletException, IOException {

        request.setAttribute("tabE", (tab == 1) ? "active show" : "");
        request.setAttribute("tabA", (tab == 2) ? "active show" : "");
        request.setAttribute("tabU", (tab == 3) ? "active show" : "");
        request.setAttribute("tabD", (tab == 4) ? "active show" : "");
        return request;
    }

    public void comprobarAsignarGanancias(Evento evento,HttpServletRequest request){
        Logger log = Logger.getLogger("log");
        EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
        ApuestaRepository apuestaRepository = new ApuestaRepository(emf);
        UsuarioRepository usuarioRepository = new UsuarioRepository(emf);

        List<Apuesta> findAllbyPrediccion1 = null;
        List<Apuesta> findAllbyPrediccion2 = null;
        Set<Apuesta> allbyEvento = evento.getApuestas();

        if (evento.getIdDeporte().getNombre().equals("Football")){

            findAllbyPrediccion2 = apuestaRepository.findAllbyPrediccion(evento.getId());
            findAllbyPrediccion1 = apuestaRepository.findAllbyEquipoGanador(evento.getId());

        }else{
            findAllbyPrediccion1 = apuestaRepository.findAllbyEquipoGanador(evento.getId());
        }


        for (Apuesta apuesta: allbyEvento) {

        }




        for (Apuesta apuesta: findAllbyPrediccion1) {
            if (allbyEvento.contains(apuesta)){

            }
            apuesta.setEstado("Aceptada");
            apuesta.getUsuario().setSaldo( apuesta.getUsuario().getSaldo().add(apuesta.getCuota().multiply(apuesta.getCantidad())));
            usuarioRepository.update(apuesta.getUsuario());
            apuestaRepository.update(apuesta);
        }
        for (Apuesta apuesta: findAllbyPrediccion2) {
            apuesta.setEstado("Aceptada");
            apuesta.getUsuario().setSaldo( apuesta.getUsuario().getSaldo().add(apuesta.getCuota().multiply(apuesta.getCantidad())));
            usuarioRepository.update(apuesta.getUsuario());
            apuestaRepository.update(apuesta);
        }

    }

}
