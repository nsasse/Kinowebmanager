package de.cofinpro.controller.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

import de.cofinpro.controller.GlobalVariables;
import de.cofinpro.controller.dao.impl.KinosaalDaoStaticImpl;
import de.cofinpro.controller.dao.impl.ProgrammDaoStaticImpl;
import de.cofinpro.controller.dao.impl.VorfuehrungDaoStaticImpl;
import de.cofinpro.modul.Kinosaal;
import de.cofinpro.modul.Programm;
import de.cofinpro.modul.Vorfuehrung;

// TODO: Auto-generated Javadoc
/**
 * The Class ErstellungProgramm.
 */

public class ErstellungProgramm {
	
	/** The kinosaele liste dao. */
	private KinosaalDaoStaticImpl kinosaeleListeDao = new KinosaalDaoStaticImpl();

	/** The vorfuehrungen liste dao. */
	private VorfuehrungDaoStaticImpl vorfuehrungenListeDao = new VorfuehrungDaoStaticImpl();

	/** The programm liste dao. */
	private ProgrammDaoStaticImpl programmListeDao = new ProgrammDaoStaticImpl();

	// Kinoschluss beachten!
	// private static final int MaxFilmLaenge3InMin = 150;

	/** vorfuehrungen */
	private ArrayList<Vorfuehrung> vorfuehrungen = new ArrayList<Vorfuehrung>();

	/**
	 * Generate programm.
	 */
	public final void generateProgramm() {

		ArrayList<Kinosaal> kinosaeleDao = new ArrayList<Kinosaal>();
		ArrayList<Vorfuehrung> vorfuehrungFsk12 = listeVorfuehrungenFsk12();
		ArrayList<Vorfuehrung> vorfuehrungFsk16 = listeVorfuehrungenFsk16();
		ArrayList<Programm> programmListe = new ArrayList<Programm>();

		vorfuehrungen = vorfuehrungenListeDao.getAllVorfuehrungSortByGewinn();
		kinosaeleDao = kinosaeleListeDao.getAllKinosaalSortSize();

		int aktuellerTag = GlobalVariables.DAY;
		int aktuellerMonat = GlobalVariables.MONTH;
		final int aktJahr = GlobalVariables.YEAR;
		int anzKinosaele = kinosaeleListeDao.getAllKinosaalSortSize().size();
		int id = 90000; // ID Counter

		for (int i = 0; i < GlobalVariables.ANZ_TAGE_PROGRAMM; i++) {
			Programm programmTag = new Programm();
			LocalDate programmTagDatum = LocalDate.of(aktJahr, aktuellerMonat, aktuellerTag);

			programmTag.setStart(programmTagDatum);

			for (int j = 0; j < GlobalVariables.ANZ_FILM_START_ZEITEN; j++) {
				for (int k = 0; k < anzKinosaele; k++) {
					Vorfuehrung programmTagVorfuehrung = new Vorfuehrung();
					int anzSitzeL = kinosaeleDao.get(k).getAnzSitzeL();
					int anzSitzeP = kinosaeleDao.get(k).getAnzSitzeP();

					programmTagVorfuehrung.setStartzeit(startVorfuehrung(j));

					if (programmTagVorfuehrung.getStartzeit().getHour() == GlobalVariables.FILM_START_I0) {

						programmTagVorfuehrung = setVorfuehrungKinosaal(vorfuehrungFsk12, anzSitzeL, anzSitzeP,
								kinosaeleDao, k);
						programmTagVorfuehrung.setStartzeit(startVorfuehrung(j));

					} else if (programmTagVorfuehrung.getStartzeit().getHour() == GlobalVariables.FILM_START_I1_H) {
						programmTagVorfuehrung = setVorfuehrungKinosaal(vorfuehrungFsk12, anzSitzeL, anzSitzeP,
								kinosaeleDao, k);
						programmTagVorfuehrung.setStartzeit(startVorfuehrung(j));

					} else if (programmTagVorfuehrung.getStartzeit().getHour() == GlobalVariables.FILM_START_I2) {
						programmTagVorfuehrung = setVorfuehrungKinosaal(vorfuehrungFsk16, anzSitzeL, anzSitzeP,
								kinosaeleDao, k);
						programmTagVorfuehrung.setStartzeit(startVorfuehrung(j));

					} else if (programmTagVorfuehrung.getStartzeit().getHour() == GlobalVariables.FILM_START_I3) {
						programmTagVorfuehrung = setVorfuehrungKinosaal(vorfuehrungen, anzSitzeL, anzSitzeP,
								kinosaeleDao, k);
						programmTagVorfuehrung.setStartzeit(startVorfuehrung(j));

					} else {
						System.out.println("Fehlerhafte Startzeit");
					}

					// LocalTime endzeit = berechnungEndzeit(programmTag.getStart(), X);

					// Beachtung der Schließzeit des Kinos

					programmTagVorfuehrung.setKinosaal(kinosaeleDao.get(k));

					programmTag.getVorfuehrungen().add(programmTagVorfuehrung);
				}

			}

			BigDecimal kosten = BigDecimal.ZERO;
			BigDecimal ue = BigDecimal.ZERO;
			BigDecimal gewinn = BigDecimal.ZERO;
			kosten = berechnungKosten(programmTag.getVorfuehrungen());
			ue = berechnungUe(programmTag.getVorfuehrungen());
			gewinn = berechnungGewinn(programmTag.getVorfuehrungen());
			programmTag.setKosten(kosten);
			programmTag.setUe(ue);
			programmTag.setGewinn(gewinn);
			programmTag.setId(id);

			programmListe.add(programmTag);
			id++;

			if (aktuellerTag > GlobalVariables.DURCHSCHNITT_TAGE_MONAT) {
				if (aktuellerMonat == 4 || aktuellerMonat == 6) {
					aktuellerTag = 1;
					aktuellerMonat++;
				}
			} else if (aktuellerTag > GlobalVariables.TAGE_FEBRUAR) {
				if (aktuellerMonat == 2) {
					aktuellerTag = 1;
					aktuellerMonat++;
				}
			} else if (aktuellerTag > GlobalVariables.TAGE_JAN_MRZ_MAI_JUL) {
				if (aktuellerMonat == Calendar.JANUARY || aktuellerMonat == Calendar.MARCH
						|| aktuellerMonat == Calendar.MAY) {
					aktuellerTag = 1;
					aktuellerMonat++;
				}
			} else {
				aktuellerTag++;
			}

		}

		programmListeDao.setProgramm(programmListe);

	}

	private Vorfuehrung setVorfuehrungKinosaal(final ArrayList<Vorfuehrung> vorfuehrungenListe, final int anzSitzeL,
			final int anzSitzeP, final ArrayList<Kinosaal> kinosaalDao, final int k) {
		Vorfuehrung vorfuehrungKinoWahl = new Vorfuehrung();
		for (int l = 0; l < vorfuehrungenListe.size(); l++) {
			int anzKundenL = vorfuehrungenListe.get(l).getAnzKundenL();
			int anzKundenP = vorfuehrungenListe.get(l).getAnzKundenP();

			if (anzKundenL < anzSitzeL) {
				if (anzKundenP < anzSitzeP) {
					vorfuehrungKinoWahl.setAnzKundenL(vorfuehrungenListe.get(l).getAnzKundenL());
					vorfuehrungKinoWahl.setAnzKundenP(vorfuehrungenListe.get(l).getAnzKundenP());
					vorfuehrungKinoWahl.setDauer(vorfuehrungenListe.get(l).getDauer());
					vorfuehrungKinoWahl.setFilm(vorfuehrungenListe.get(l).getFilm());
					vorfuehrungKinoWahl.setGewinn(vorfuehrungenListe.get(l).getGewinn());
					vorfuehrungKinoWahl.setId(vorfuehrungenListe.get(l).getId());
					vorfuehrungKinoWahl.setKosten(vorfuehrungenListe.get(l).getKosten());
					vorfuehrungKinoWahl.setTicketPreisL(vorfuehrungenListe.get(l).getTicketPreisL());
					vorfuehrungKinoWahl.setTicketPreisP(vorfuehrungenListe.get(l).getTicketPreisP());
					vorfuehrungKinoWahl.setUe(vorfuehrungenListe.get(l).getUe());
					vorfuehrungKinoWahl.setvId(vorfuehrungenListe.get(l).getvId());
					vorfuehrungKinoWahl.setWerbespots(vorfuehrungenListe.get(l).getWerbespots());
					vorfuehrungKinoWahl.setKinosaal(kinosaalDao.get(k));
					break;
				}
			}
		}

		return vorfuehrungKinoWahl;

	}

	private LocalTime startVorfuehrung(final int j) {
		LocalTime startzeit = LocalTime.now();
		if (j == 0) {
			startzeit = LocalTime.of(GlobalVariables.FILM_START_I0, GlobalVariables.FILM_START_I023_MIN);
		} else if (j == 1) {
			startzeit = LocalTime.of(GlobalVariables.FILM_START_I1_H, GlobalVariables.FILM_START_I1_MIN);
		} else if (j == 2) {
			startzeit = LocalTime.of(GlobalVariables.FILM_START_I2, GlobalVariables.FILM_START_I023_MIN);
		} else if (j == 3) {
			startzeit = LocalTime.of(GlobalVariables.FILM_START_I3, GlobalVariables.FILM_START_I023_MIN);
		}

		return startzeit;
	}

	private ArrayList<Vorfuehrung> listeVorfuehrungenFsk12() {
		ArrayList<Vorfuehrung> vorfuehrungenfsk12 = new ArrayList<Vorfuehrung>();
		vorfuehrungen = vorfuehrungenListeDao.getAllVorfuehrungSortByGewinn();
		for (int i = 0; i < vorfuehrungen.size(); i++) {
			int filmFsk = vorfuehrungen.get(i).getFilm().getFsk();
			if (filmFsk <= 12) {
				Vorfuehrung vorfuehrung = new Vorfuehrung();
				vorfuehrung = vorfuehrungen.get(i);
				vorfuehrungenfsk12.add(vorfuehrung);
			}
		}
		return vorfuehrungenfsk12;
	}

	private ArrayList<Vorfuehrung> listeVorfuehrungenFsk16() {
		ArrayList<Vorfuehrung> vorfuehrungenfsk16 = new ArrayList<Vorfuehrung>();
		vorfuehrungen = vorfuehrungenListeDao.getAllVorfuehrungSortByGewinn();
		for (int i = 0; i < vorfuehrungen.size(); i++) {
			int filmFsk = vorfuehrungen.get(i).getFilm().getFsk();
			if (filmFsk <= 16) {
				Vorfuehrung vorfuehrung = new Vorfuehrung();
				vorfuehrung = vorfuehrungen.get(i);
				vorfuehrungenfsk16.add(vorfuehrung);
			}
		}
		return vorfuehrungenfsk16;
	}

	private BigDecimal berechnungKosten(final ArrayList<Vorfuehrung> vorfuehrungenListe) {
		BigDecimal kosten = BigDecimal.ZERO;
		for (int i = 0; i < vorfuehrungenListe.size(); i++) {
			kosten = kosten.add(vorfuehrungenListe.get(i).getKosten());
		}
		return kosten;
	}

	private BigDecimal berechnungUe(final ArrayList<Vorfuehrung> vorfuehrungenListe) {
		BigDecimal ue = BigDecimal.ZERO;
		for (int i = 0; i < vorfuehrungenListe.size(); i++) {
			ue = ue.add(vorfuehrungenListe.get(i).getUe());
		}
		return ue;
	}

	private BigDecimal berechnungGewinn(final ArrayList<Vorfuehrung> vorfuehrungenListe) {
		BigDecimal gewinn = BigDecimal.ZERO;
		for (int i = 0; i < vorfuehrungenListe.size(); i++) {
			gewinn = gewinn.add(vorfuehrungenListe.get(i).getGewinn());
		}
		return gewinn;
	}

	@SuppressWarnings("unused")
	private LocalTime berechnungEndzeit(final LocalTime startzeit, final int laengeVorfuehrung) {

		LocalTime endzeit = LocalTime.of(0, 0);
		int laengeStunden = (int) laengeVorfuehrung / 60;
		int laengeMinuten = laengeVorfuehrung % 60;

		int hour = startzeit.getHour() + laengeStunden;
		int min = startzeit.getMinute() + laengeMinuten;
		endzeit = LocalTime.of(hour, min);

		// Schlusszeit beachten

		return null;
	}

}
