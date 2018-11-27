package de.cofinpro.controller.dao;

import java.util.ArrayList;
import java.util.List;

import de.cofinpro.modul.Kinosaal;

public interface KinosaalDAO {

		List<Kinosaal> getAllKinosaalSortSize();
		Kinosaal getKinosaal(int id);
		void createKinosaal(Kinosaal kinosaal);
		void deleteAll();
		ArrayList<Kinosaal> getAllKinosaal();
	}


