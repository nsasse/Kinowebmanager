package de.cofinpro.controller.dao;

import java.util.List;

import de.cofinpro.modul.Film;


public interface FilmDAO {
	
	List<Film> getAllFilm();
	Film getFilm(int id);
	void createFilm(Film film);
	void deleteFilm(Film film);
	void deleteAll();
	void setAllEff();
	List<Film> getAllFilmSortByEff();
	List<Film> getFilme();
	void setFilm(int id, Film film);
}
