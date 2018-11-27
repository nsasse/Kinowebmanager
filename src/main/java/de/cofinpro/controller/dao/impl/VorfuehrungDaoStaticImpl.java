package de.cofinpro.controller.dao.impl;

import java.util.ArrayList;
import java.util.List;
import de.cofinpro.controller.dao.VorfuehrungDAO;
import de.cofinpro.controller.service.ErstellungVorfuehrung;
import de.cofinpro.controller.service.VorfuehrungComparator;
import de.cofinpro.modul.Vorfuehrung;

public class VorfuehrungDaoStaticImpl implements VorfuehrungDAO{
	
	private static ArrayList<Vorfuehrung> vorfuehrung;
	
	public VorfuehrungDaoStaticImpl() {
	super();
	}
	
	
	public final void deleteVorfuehrung(final Vorfuehrung vorfuehrung) {
		VorfuehrungDaoStaticImpl.vorfuehrung.remove(vorfuehrung.getId());
		System.out.println("Vorfuehrung Nr." +vorfuehrung.getId() +", mit der Startzeit " +vorfuehrung.getStartzeit() +", wird aus der Datenbank gelöscht.");	
	}

	
	public final ArrayList<Vorfuehrung> getAllVorfuehrungSortByGewinn() {
		ErstellungVorfuehrung erstVf = new ErstellungVorfuehrung();
		ArrayList<Vorfuehrung> vorfuehrungenListe = new ArrayList<Vorfuehrung>();
		vorfuehrungenListe = erstVf.generateVorfuehrung();
		vorfuehrungenListe.sort(new VorfuehrungComparator());
		return vorfuehrungenListe;
	}
	
	
	public final ArrayList<Vorfuehrung> getAllVorfuehrung() {
		return vorfuehrung;	
	}
	
	
	public final Vorfuehrung getVorfuehrung(final int id) {
		return vorfuehrung.get(id);
	}
	
	
	public void createVorfuehrung(final Vorfuehrung vorfuehrung) {
		VorfuehrungDaoStaticImpl.vorfuehrung.add(vorfuehrung);
		System.out.println("Die Vorfuehrung mit der Nr." + vorfuehrung.getId() + " wird in der Datenbank hinzugefügt.");	
	}

	public final List<Vorfuehrung> getVorfuehrung() {
		return vorfuehrung;
	}

	public final void setVorfuehrung(final ArrayList<Vorfuehrung> vorfuehrung) {
		VorfuehrungDaoStaticImpl.vorfuehrung = vorfuehrung;
	}
	
}
