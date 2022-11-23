package es.iespuertodelacruz.yt.porradeportes.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@javax.servlet.annotation.WebListener
public class WebListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public WebListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PorraDeportes");
        sce.getServletContext().setAttribute("emf", emf);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
