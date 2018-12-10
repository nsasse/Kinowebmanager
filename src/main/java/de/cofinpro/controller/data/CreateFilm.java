package de.cofinpro.controller.data;

import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.cofinpro.modul.Film;

@ManagedBean(name = "createFilm")
@SessionScoped

public class CreateFilm {

	public void create() {
		
		BigDecimal kosten = new BigDecimal(120);
		BigDecimal eff = new BigDecimal(50);
		
		// Datenbank
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("KinoPersistence");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		//Zuweisung der Werte
		Film film = new Film();

		film.setBeliebtheit(50);
		film.setDreiD(false);
		film.setEff(eff);
		film.setErscheinungsjahr(2000);
		film.setErscheinungsland("Deutschland");
		film.setFsk(6);
		film.setGenre("Drama");
		film.setId(99);
		film.setKosten(kosten);
		film.setName("TEST!12345");
		film.setRegisseur("TESTReges");
		film.setSpieldauer(120);
		film.setSprache("deutsch");

		
		entitymanager.persist(film);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}

}
