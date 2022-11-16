package es.iespuertodelacruz.yt.porradeportes.Repositories.tests;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EMFhelper {

	//Singleton
	
	private static EMFhelper emfHelper;
	private EntityManagerFactory emf;
	
	private EMFhelper() {
		
		emf = Persistence.createEntityManagerFactory("PorraDeportes");
		
	}
	
	public static EMFhelper getSingleton() {
		
		if(emfHelper == null) {
			emfHelper = new EMFhelper();
		}
		
		return emfHelper;
		
	}
	
	public EntityManagerFactory getEMF() {
		
		return emf;
		
	}
	
}
