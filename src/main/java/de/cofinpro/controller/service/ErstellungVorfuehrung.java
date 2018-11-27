package de.cofinpro.controller.service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import de.cofinpro.controller.dao.impl.FilmDaoStaticImpl;
import de.cofinpro.controller.dao.impl.KinosaalDaoStaticImpl;
import de.cofinpro.controller.dao.impl.WerbespotDaoStaticImpl;
import de.cofinpro.modul.Film;
import de.cofinpro.modul.Kinosaal;
import de.cofinpro.modul.Vorfuehrung;
import de.cofinpro.modul.Werbespot;

/**
 * The Class ErstellungVorfuehrung.
 */
public class ErstellungVorfuehrung {

	/** The Constant FSK_18. */
	public static final int FSK_18 = 18;
	/** The Constant FSK_16. */
	public static final int FSK_16 = 16;
	/** The Constant FSK_12. */
	public static final int FSK_12 = 12;
	/** The werbespot liste dao. */
	private WerbespotDaoStaticImpl werbespotListeDao = new WerbespotDaoStaticImpl();
	/** The film liste dao. */
	private FilmDaoStaticImpl filmListeDao = new FilmDaoStaticImpl();
	/** The kinosaal liste dao. */
	private KinosaalDaoStaticImpl kinosaalListeDao = new KinosaalDaoStaticImpl();
	/** The vorfuehrungen liste. */
	private ArrayList<Vorfuehrung> vorfuehrungenListe = new ArrayList<Vorfuehrung>();
	/** The werbespots db sort. */
	private ArrayList<Werbespot> werbespotsDbSort = new ArrayList<Werbespot>();
	/** The kinosaal db sort. */
	@SuppressWarnings("unused")
	private ArrayList<Kinosaal> kinosaalDbSort = new ArrayList<Kinosaal>();

	public final ArrayList<Vorfuehrung> generateVorfuehrung() {

		Integer id = 80000; // ID Counter

		filmListeDao.setAllEff();
		werbespotsDbSort = werbespotListeDao.getAllWerbespotSortByEff();
		kinosaalDbSort = kinosaalListeDao.getAllKinosaalSortSize();

		final int ticketPreisLoge = 2;
		final int ticketPreisParkett = 3;
		final int umsatzErloeseLoge = 0;
		final int umsatzEroeseParkett = 1;

		for (int i = 0; i < filmListeDao.getAllFilmSortByEff().size(); i++) {

			Vorfuehrung vorfuehrung = new Vorfuehrung();

			vorfuehrung.setId(id);
			id++;

			vorfuehrung.setFilm(filmListeDao.getFilm(i));

			ArrayList<Integer> anzKunden = new ArrayList<Integer>();
			anzKunden = getAnzKunden(vorfuehrung.getFilm());

			// Festlegung der Ticketpreise
			BigDecimal ticketPreisL = BigDecimal.ZERO;
			BigDecimal ticketPreisP = BigDecimal.ZERO;
			ticketPreisL = ticketKundenBerechnung(vorfuehrung.getFilm(), anzKunden).get(ticketPreisLoge);
			ticketPreisP = ticketKundenBerechnung(vorfuehrung.getFilm(), anzKunden).get(ticketPreisParkett);
			vorfuehrung.setTicketPreisL(ticketPreisL);
			vorfuehrung.setTicketPreisP(ticketPreisP);

			ArrayList<BigDecimal> ueTickets = new ArrayList<BigDecimal>();
			ueTickets = ticketKundenBerechnung(vorfuehrung.getFilm(), getAnzKunden(vorfuehrung.getFilm()));
			BigDecimal ueTicketGesamt = BigDecimal.ZERO;
			ueTicketGesamt = ueTickets.get(umsatzEroeseParkett).add(ueTickets.get(umsatzErloeseLoge));

			vorfuehrung.setAnzKundenL(anzKunden.get(0));
			vorfuehrung.setAnzKundenP(anzKunden.get(1));

			BigDecimal minUeWerbespots = BigDecimal.ZERO;

			minUeWerbespots = berechnungMinUeWerbespots(vorfuehrung.getFilm().getKosten(), ueTicketGesamt);

			ArrayList<Werbespot> werbespots = new ArrayList<Werbespot>();
			werbespots = generateWerbespots(minUeWerbespots, anzKunden);

			vorfuehrung.setWerbespots(werbespots);

			vorfuehrung.setStartzeit(festlegungStart(vorfuehrung.getFilm().getFsk()));

			// vorfuehrung.setKinosaal(setKinosaal(vorfuehrung.getAnzKundenL(),
			// vorfuehrung.getAnzKundenP()));

			vorfuehrung.setDauer(setLaufzeit(vorfuehrung.getFilm(), vorfuehrung.getWerbespots()));

			BigDecimal kosten = BigDecimal.ZERO;
			kosten = berechnungKosten(vorfuehrung.getFilm().getKosten());
			vorfuehrung.setKosten(kosten);

			BigDecimal erloese = BigDecimal.ZERO;
			erloese = berechnungErloese(ueTicketGesamt, vorfuehrung.getWerbespots(), anzKunden);
			vorfuehrung.setUe(erloese);

			BigDecimal gewinn = BigDecimal.ZERO;
			gewinn = berechnungGewinn(erloese, kosten);
			vorfuehrung.setGewinn(gewinn);

			vorfuehrungenListe.add(vorfuehrung);
		}
		return vorfuehrungenListe;

	}

	private BigDecimal berechnungKosten(final BigDecimal kostenFilm) {

		BigDecimal kosten = BigDecimal.ZERO;
		kosten = kosten.add(kostenFilm);

		return kosten;
	}

	public final BigDecimal berechnungGewinn(final BigDecimal erloese, final BigDecimal kosten) {

		BigDecimal gewinn = BigDecimal.ZERO;
		gewinn = gewinn.add(erloese);
		gewinn = gewinn.subtract(kosten);

		return gewinn;
	}

	public final LocalTime festlegungStart(final int fsk) {
		LocalTime startzeit = LocalTime.of(0, 0);

		if (fsk >= FSK_18) {
			startzeit = LocalTime.of(23, 0);
		} else if (fsk >= FSK_16) {
			startzeit = LocalTime.of(20, 0);
		} else {
			startzeit = LocalTime.of(15, 0);
		}
		return startzeit;
	}

	// gibt eine List von Anzahl der Kunden und den UE aus
	public final ArrayList<BigDecimal> ticketKundenBerechnung(final Film film, final ArrayList<Integer> anzKunden) {

		int anzL = anzKunden.get(0);
		int anzP = anzKunden.get(1);

		ArrayList<BigDecimal> ueTicketListe = new ArrayList<BigDecimal>();
		BigDecimal preisJeTicketL = new BigDecimal("6");
		BigDecimal preisJeTicketP = new BigDecimal("4");
		BigDecimal ueGesamtTicketL = new BigDecimal("6");
		BigDecimal ueGesamtTicketP = new BigDecimal("4");

		if (film.isDreiD() == true) {
			preisJeTicketL = new BigDecimal("8.50");
			preisJeTicketP = new BigDecimal("6.50");
			ueGesamtTicketL = new BigDecimal("8.50");
			ueGesamtTicketP = new BigDecimal("6.50");
		}

		ueGesamtTicketL = preisJeTicketL.multiply(BigDecimal.valueOf(anzL));
		ueGesamtTicketP = preisJeTicketP.multiply(BigDecimal.valueOf(anzP));

		//ue = Preis aller Tickets
		ueTicketListe.add(ueGesamtTicketL);
		ueTicketListe.add(ueGesamtTicketP);
		//preise = Preis für ein Ticket
		ueTicketListe.add(preisJeTicketL);
		ueTicketListe.add(preisJeTicketP);

		return ueTicketListe;
	}

	public final BigDecimal berechnungMinUeWerbespots(final BigDecimal filmkosten, final BigDecimal ueTicketsGesamt) {

		BigDecimal minUeWerbespots = BigDecimal.ZERO;

		minUeWerbespots = minUeWerbespots.add(filmkosten);
		minUeWerbespots = minUeWerbespots.subtract(ueTicketsGesamt);
		return minUeWerbespots;
	}

	/**
	 * Generate werbespots.
	 *
	 * @param minUeWerbespots
	 *            the min ue werbespots
	 * @param anzKundenGesamt
	 *            the anz kunden gesamt
	 * @return the array list
	 */
	public final ArrayList<Werbespot> generateWerbespots(BigDecimal minUeWerbespots,
			final ArrayList<Integer> anzKundenGesamt) {

		// Liste wird zu Beginn der Klasse erstellt und sortiert

		ArrayList<Werbespot> werbespotsVorfuehrung = new ArrayList<Werbespot>();

		BigDecimal anzKundenGesamtB = BigDecimal.ZERO;
		anzKundenGesamtB = anzKundenGesamtB.add(BigDecimal.valueOf(anzKundenGesamt.get(0)));
		anzKundenGesamtB = anzKundenGesamtB.add(BigDecimal.valueOf(anzKundenGesamt.get(1)));

		final int maxAnzWerbespots = 5;

		if (minUeWerbespots.doubleValue() < 0) {

			Werbespot werbespot = new Werbespot();
			werbespot = werbespotsDbSort.get(0);

			werbespotsVorfuehrung.add(werbespot);
		}

		if (minUeWerbespots.doubleValue() >= 0) {
			for (int i = 0; minUeWerbespots.doubleValue() > 0; i++) {
				Werbespot werbespot = new Werbespot();
				werbespot = werbespotsDbSort.get(i);

				werbespotsVorfuehrung.add(werbespot);

				BigDecimal verguetungWerbespot = BigDecimal.ZERO;
				verguetungWerbespot = werbespot.getVerguetung().multiply(anzKundenGesamtB);
				minUeWerbespots = minUeWerbespots.subtract(verguetungWerbespot);
				if (i >= maxAnzWerbespots) {
					break;
				}
			}

		}

		return werbespotsVorfuehrung;
	}

	public final ArrayList<Integer> getAnzKunden(final Film film) {

		KinosaalDaoStaticImpl sKinosaal = new KinosaalDaoStaticImpl();
		List<Kinosaal> kinosaalDb = sKinosaal.getAllKinosaalSortSize();

		int anzKinosaele = sKinosaal.getAllKinosaalSortSize().size();

		ArrayList<Integer> anzKunden = new ArrayList<Integer>();

		int anzL = kinosaalDb.get(anzKinosaele - 1).getAnzSitzeL();
		int anzP = kinosaalDb.get(anzKinosaele - 1).getAnzSitzeP();

		final int hundert = 100;

		anzL = anzL * film.getBeliebtheit() / hundert;
		anzP = anzP * film.getBeliebtheit() / hundert;

		anzKunden.add(anzL);
		anzKunden.add(anzP);

		return anzKunden;
	}

	public final Integer setLaufzeit(final Film film, final List<Werbespot> werbespot) {
		Integer laufzeit = 0;
		Integer laufzeitWerbung = 0;
		laufzeit = film.getSpieldauer();
		for (int i = 0; i < werbespot.size(); i++) {
			int ramL = werbespot.get(i).getLaufzeit();
			laufzeitWerbung = laufzeitWerbung + ramL;
		}

		return laufzeit;
	}

	public final BigDecimal berechnungErloese(final BigDecimal ueTicketsGesamt, List<Werbespot> werbespots,
			final ArrayList<Integer> anzKundenListe) {
		BigDecimal erloese = BigDecimal.ZERO;
		BigDecimal erloeseWerbespots = BigDecimal.ZERO;
		BigDecimal verguetungJeWerbespot = BigDecimal.ZERO;
		BigDecimal anzKundenGesamtBD = BigDecimal.ZERO;
		Integer anzKundenL = anzKundenListe.get(0);
		Integer anzKundenP = anzKundenListe.get(1);
		Integer anzKundenGesamt = anzKundenL + anzKundenP;
		anzKundenGesamtBD = BigDecimal.valueOf(anzKundenGesamt);

		for (int i = 0; i < werbespots.size(); i++) {
			verguetungJeWerbespot = werbespots.get(i).getVerguetung();

			verguetungJeWerbespot = verguetungJeWerbespot.multiply(anzKundenGesamtBD);
			erloeseWerbespots = erloeseWerbespots.add(verguetungJeWerbespot);
		}

		erloese = erloese.add(ueTicketsGesamt);
		erloese = erloese.add(erloeseWerbespots);

		return erloese;
	}

}
