package de.cofinpro.controller.service;

import java.util.Comparator;

import de.cofinpro.modul.Kinosaal;

public class KinosaalComparator implements Comparator<Kinosaal> {

	public final int compare(final Kinosaal o1, final Kinosaal o2) {
		Integer o1AS = o1.getAnzSitzeL();
		Integer o2AS = o2.getAnzSitzeL();
		int value = o1AS.compareTo(o2AS);

		/*
		 * if (value == 0) { Integer o1ASP = o1.getAnzSitzeP(); Integer o2ASP =
		 * o2.getAnzSitzeP(); value = o1ASP.compareTo(o2ASP); }
		 */
		return value;
	}

}
