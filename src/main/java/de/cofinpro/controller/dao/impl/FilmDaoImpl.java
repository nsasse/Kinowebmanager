package de.cofinpro.controller.dao.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;

import de.cofinpro.controller.dao.FilmDAO;
import de.cofinpro.controller.data.HibernateUtils;
import de.cofinpro.controller.service.FilmComparator;
import de.cofinpro.modul.Film;

@ManagedBean(name = "filmDAO")
@SessionScoped

public class FilmDaoImpl implements FilmDAO {

	private static List<Film> filme = new ArrayList<Film>();
	
	public FilmDaoImpl() {
		super();
	}

	@SuppressWarnings("unlikely-arg-type")
	public final void deleteFilm(final Film film) {
		FilmDaoImpl.filme.remove(film.getId());
		System.out.println("Film Nr." + film.getId() + ", " + film.getName() + ", wird aus der Datenbank gelöscht.");
	}

	public final List<Film> getAllFilm() {
		
		@SuppressWarnings("unused")
		Session session = HibernateUtils.getSessionFactory().openSession();
		
		//Unfertig
		
		return filme;
	}

	public final List<Film> getAllFilmSortByEff() {

		List<Film> filmListeSort = filme;

		filmListeSort.sort(new FilmComparator());

		return filmListeSort;

	}

	public final Film getFilm(final int id) {
		
		// !!!!!!!!!!!!!!!!!!!!!!
		// Noch nicht getestet
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		
		Film film = null;
		film = (Film) session.get(Film.class, 100);
		System.out.println(film.getName());
		
		session.close();
				
		return filme.get(id);
	}

	//Film in Datenbank speichern
	public final void createFilm(final Film film) {
		
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

	public final List<Film> getFilme() {
		return filme;
	}

	public final void setFilm(final int id, final Film film) {
		FilmDaoImpl.filme.set(id, film);

	}

	public final void deleteAll() {
		FilmDaoImpl.filme.clear();
		System.out.println("Alle Filme wurden aus der Datenbank gelöscht.");
	}

	public final void setAllEff() {

		for (int i = 0; i < FilmDaoImpl.filme.size(); i++) {

			FilmDaoImpl.filme.get(i);
			int beliebtheit = FilmDaoImpl.filme.get(i).getBeliebtheit();
			BigDecimal kosten = BigDecimal.ZERO;
			kosten = FilmDaoImpl.filme.get(i).getKosten();

			BigDecimal eff = BigDecimal.ZERO;
			eff = kosten.divide(BigDecimal.valueOf(beliebtheit), 2, RoundingMode.HALF_DOWN);
			FilmDaoImpl.filme.get(i).setEff(eff);
		}

	}

}
