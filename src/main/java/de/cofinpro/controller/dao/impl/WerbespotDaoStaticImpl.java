package de.cofinpro.controller.dao.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import de.cofinpro.controller.dao.WerbespotDAO;
import de.cofinpro.controller.service.WerbespotComparator;
import de.cofinpro.modul.Werbespot;

public class WerbespotDaoStaticImpl implements WerbespotDAO {

	private static ArrayList<Werbespot> werbespotsdb = new ArrayList<Werbespot>();
	
	public WerbespotDaoStaticImpl() {
		super();
	}
	
	public final void deleteAll(){
		WerbespotDaoStaticImpl.werbespotsdb.clear();
		System.out.println("Alle Werbespots werden aus der Datenbank gelöscht.");	
	}
	
	public final ArrayList<Werbespot> getAllWerbespotSortByEff() {
		ArrayList<Werbespot> sWerbespotsdb = werbespotsdb;
		
		sWerbespotsdb.sort(new WerbespotComparator());
		return sWerbespotsdb;
	}
	
	public final ArrayList<Werbespot> getAllWerbespot() {
		return werbespotsdb;	
	}
	
	public final Werbespot getWerbespot(final int id) {
		return werbespotsdb.get(id);
	}
	
	public final void createWerbespot(final Werbespot werbespots) {
		WerbespotDaoStaticImpl.werbespotsdb.add(werbespots);
		//System.out.println("Der Werbespot Nr." +werbespots.getId() +", " +werbespots.getName() +", wird in der Datenbank hinzugefügt.");
	}
	
	
	public final Werbespot findWerbespot(final int id) {
		Werbespot gefunden = new Werbespot();
		for (int i = 0; i < werbespotsdb.size(); i++) {
			if (id == werbespotsdb.get(i).getId()) {
				gefunden = werbespotsdb.get(i);
				System.out.println("Der Werbespot mit der ID " +werbespotsdb.get(i).getId() +" ist: " +werbespotsdb.get(i).getName() +", " +  +werbespotsdb.get(i).getLaufzeit() +" Minuten.");
			}
		}
		if (gefunden == null) {
				System.out.println("Den Werbespot mit der ID " +id +" gibt es nicht!");
			}
		
		return gefunden;
	}

	public final ArrayList<Werbespot> getWerbespotsdb() {
		return werbespotsdb;
	}

	public final void setWerbespotsdb(final ArrayList<Werbespot> werbespotsdb) {
		WerbespotDaoStaticImpl.werbespotsdb = werbespotsdb;
	}
	
	
	public final void setAllEff() {

		for (int i = 0; i < WerbespotDaoStaticImpl.werbespotsdb.size(); i++) {

			WerbespotDaoStaticImpl.werbespotsdb.get(i);
			int laufzeit = WerbespotDaoStaticImpl.werbespotsdb.get(i).getLaufzeit();
			BigDecimal verguetung = BigDecimal.ZERO;
			verguetung = WerbespotDaoStaticImpl.werbespotsdb.get(i).getVerguetung();

			BigDecimal eff = BigDecimal.ZERO;
			eff = verguetung.divide(BigDecimal.valueOf(laufzeit), 2, RoundingMode.HALF_DOWN);
			WerbespotDaoStaticImpl.werbespotsdb.get(i).setEff(eff);
		}

	}
	
}
