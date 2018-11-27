package de.cofinpro.controller.service;

import java.util.Comparator;
import de.cofinpro.modul.Werbespot;

public class WerbespotComparator implements Comparator<Werbespot> {

	public final int compare(final Werbespot o1, final Werbespot o2) {
		int value = o2.getEff().compareTo(o1.getEff());
		return value;
	}
}
