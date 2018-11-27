package de.cofinpro.controller.dao.impl;

import java.util.ArrayList;
import de.cofinpro.controller.dao.KinosaalDAO;
import de.cofinpro.controller.service.KinosaalComparator;
import de.cofinpro.modul.Kinosaal;

public class KinosaalDaoStaticImpl implements KinosaalDAO {

	private static ArrayList<Kinosaal> kinosaaldb = new ArrayList<Kinosaal>();

	public KinosaalDaoStaticImpl() {
		super();
	}

	  
	public final void deleteAll() {
		KinosaalDaoStaticImpl.kinosaaldb.clear();
		System.out.println("Alle Kinosäle werden aus der Datenbank gelöscht.");
	}

	  
	public final ArrayList<Kinosaal> getAllKinosaalSortSize() {

		ArrayList<Kinosaal> kinosaeleSortDb = new ArrayList<Kinosaal>();
		for (int i = 0; i < kinosaaldb.size(); i++) {
			kinosaeleSortDb.add(kinosaaldb.get(i));
		}

		kinosaeleSortDb.sort(new KinosaalComparator());

		return kinosaeleSortDb;
	}

	  
	public final ArrayList<Kinosaal> getAllKinosaal() {
		return kinosaaldb;

	}

	  
	public final Kinosaal getKinosaal(final int id) {
		return kinosaaldb.get(id);
	}

	  
	public final void createKinosaal(final Kinosaal kinosaal) {
		KinosaalDaoStaticImpl.kinosaaldb.add(kinosaal);
	}
}
