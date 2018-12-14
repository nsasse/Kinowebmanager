package de.cofinpro.controller.data;

import org.hibernate.Session;

import de.cofinpro.modul.Programm;
import de.cofinpro.modul.Vorfuehrung;

public class Generator {

	public void generateVorfuehrung(Vorfuehrung vorfuehrung) {

		Session sessionObj = HibernateUtils.getSessionFactory().openSession();

		try {
			sessionObj.beginTransaction();
			sessionObj.save(vorfuehrung);
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
	
	public void generateVorfuehrung(Programm programm) {

		Session sessionObj = HibernateUtils.getSessionFactory().openSession();

		try {
			sessionObj.beginTransaction();
			sessionObj.save(programm);
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
