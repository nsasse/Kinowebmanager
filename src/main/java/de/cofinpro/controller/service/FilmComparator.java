package de.cofinpro.controller.service;

import java.util.Comparator;

import de.cofinpro.modul.Film;

public class FilmComparator implements Comparator<Film> {

	public final int compare(final Film o1, final Film o2) {
		int value = o1.getEff().compareTo(o2.getEff());
		return value;
	}

}
