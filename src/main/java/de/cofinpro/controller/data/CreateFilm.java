package de.cofinpro.controller.data;

import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;
import de.cofinpro.modul.Film;

@ManagedBean(name = "createFilm")
@SessionScoped

public class CreateFilm {

	public void create() {

		BigDecimal kosten = new BigDecimal(120);
		Film userObj = new Film();

		Session sessionObj = HibernateUtils.getSessionFactory().openSession();

		try {
			sessionObj.beginTransaction();

			userObj.setBeliebtheit(50);
			userObj.setErscheinungsjahr(2000);
			userObj.setErscheinungsland("Deutschland");
			userObj.setFsk(6);
			userObj.setGenre("Drama");
			userObj.setKosten(kosten);
			userObj.setName("TEST!12345");
			userObj.setRegisseur("TESTReges");
			userObj.setSpieldauer(120);
			userObj.setSprache("deutsch");

			sessionObj.save(userObj);

			sessionObj.getTransaction().commit();

		}

		catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}
}
