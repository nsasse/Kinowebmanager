package de.cofinpro.controller.dao;

import java.util.ArrayList;
import java.util.List;

import de.cofinpro.modul.Werbespot;

public interface WerbespotDAO {

	List<Werbespot> getAllWerbespot();
	Werbespot getWerbespot(int id);
	void createWerbespot(Werbespot werbespots);
	Werbespot findWerbespot(int id);
	void deleteAll();
	ArrayList<Werbespot> getAllWerbespotSortByEff();
	void setAllEff();
}
