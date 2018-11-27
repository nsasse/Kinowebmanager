package de.cofinpro.controller.dao;

import java.util.ArrayList;

import de.cofinpro.modul.Vorfuehrung;


public interface VorfuehrungDAO {

	ArrayList<Vorfuehrung> getAllVorfuehrung();
	Vorfuehrung getVorfuehrung(int id);
	void createVorfuehrung(Vorfuehrung vorfuehrung);
	void deleteVorfuehrung(Vorfuehrung vorfuehrung);
	ArrayList<Vorfuehrung> getAllVorfuehrungSortByGewinn();
}
