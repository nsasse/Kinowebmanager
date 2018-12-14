package de.cofinpro.controller.data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;
import de.cofinpro.modul.Film;
import de.cofinpro.modul.Kinosaal;
import de.cofinpro.modul.Werbespot;

@ManagedBean(name = "createFilm")
@SessionScoped

public class Creator {

	public void createFilm(Film film) {

		Session sessionObj = HibernateUtils.getSessionFactory().openSession();

		try {
			sessionObj.beginTransaction();
			sessionObj.save(film);
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
	
	public void createWerbespot(Werbespot werbespot) {

		Session sessionObj = HibernateUtils.getSessionFactory().openSession();

		try {
			sessionObj.beginTransaction();
			sessionObj.save(werbespot);
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
	
	public void createKinosaal(Kinosaal kinosaal) {

		Session sessionObj = HibernateUtils.getSessionFactory().openSession();

		try {
			sessionObj.beginTransaction();
			sessionObj.save(kinosaal);
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
