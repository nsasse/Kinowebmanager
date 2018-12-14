package de.cofinpro.controller.dao.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import de.cofinpro.controller.dao.FilmDAO;
import de.cofinpro.controller.service.FilmComparator;
import de.cofinpro.modul.Film;

public class FilmDaoStaticImpl implements FilmDAO {

	private static List<Film> filme = new ArrayList<Film>();
	private static int ID_COUNTER = 50000;

	public FilmDaoStaticImpl() {
		super();
	}

	@SuppressWarnings("unlikely-arg-type")
	public final void deleteFilm(final Film film) {
		FilmDaoStaticImpl.filme.remove(film.getId());
		System.out.println("Film Nr." + film.getId() + ", " + film.getName() + ", wird aus der Datenbank gelöscht.");
	}

	public final List<Film> getAllFilm() {
		return filme;
	}

	public final List<Film> getAllFilmSortByEff() {

		List<Film> filmListeSort = filme;

		filmListeSort.sort(new FilmComparator());

		return filmListeSort;

	}

	public final Film getFilm(final int id) {
		return filme.get(id);
	}

	//Film in statischer Klasse speichern
	public final void createFilm(final Film film) {
		film.setId(ID_COUNTER);
		FilmDaoStaticImpl.filme.add(film); // Static Class

		
		
		/*
		 * System.out.println("Der Name von Film Nr." + film.getId() + ", " +
		 * film.getName() + ", wird in der Datenbank hinzugefügt."); 
		 */
		
		/*
		 * JOptionPane.showMessageDialog(null, "Der Name von Film Nr." + film.getId() +
		 * ", " + film.getName() + ", wird in der Datenbank hinzugefügt.",
		 * "Hinzugefügt", JOptionPane.INFORMATION_MESSAGE);
		 */

	}

	public final List<Film> getFilme() {
		return filme;
	}

	public final void setFilm(final int id, final Film film) {
		FilmDaoStaticImpl.filme.set(id, film);

	}

	public final void deleteAll() {
		FilmDaoStaticImpl.filme.clear();
		System.out.println("Alle Filme wurden aus der Datenbank gelöscht.");
	}

	public final void setAllEff() {

		for (int i = 0; i < FilmDaoStaticImpl.filme.size(); i++) {

			FilmDaoStaticImpl.filme.get(i);
			int beliebtheit = FilmDaoStaticImpl.filme.get(i).getBeliebtheit();
			BigDecimal kosten = BigDecimal.ZERO;
			kosten = FilmDaoStaticImpl.filme.get(i).getKosten();

			BigDecimal eff = BigDecimal.ZERO;
			eff = kosten.divide(BigDecimal.valueOf(beliebtheit), 2, RoundingMode.HALF_DOWN);
			FilmDaoStaticImpl.filme.get(i).setEff(eff);
		}

	}

}
