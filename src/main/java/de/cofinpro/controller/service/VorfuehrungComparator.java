package de.cofinpro.controller.service;

import java.util.Comparator;
import de.cofinpro.modul.Vorfuehrung;

public class VorfuehrungComparator implements Comparator<Vorfuehrung> {

	public final int compare(final Vorfuehrung o1, final Vorfuehrung o2) {
		int value = o2.getGewinn().compareTo(o1.getGewinn());
		return value;
	}

}
