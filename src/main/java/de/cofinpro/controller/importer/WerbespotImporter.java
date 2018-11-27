package de.cofinpro.controller.importer;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import de.cofinpro.controller.dao.impl.WerbespotDaoStaticImpl;
import de.cofinpro.modul.Werbespot;

public class WerbespotImporter extends Importer {

	private int id = 60000;

	//private ArrayList<Werbespot> werbespotListe = new ArrayList<Werbespot>();

	@Override
	public final File readFile() {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("werbespots.csv").getFile());

		return file;
	}

	@Override
	public final void transform(final String[] input) {
		BigDecimal verguetung = checkBigDecimal(input[1]);
		Integer laufzeit = intCheck(input[2]);

		Werbespot werbespot1 = new Werbespot();
		werbespot1.setId(id);
		werbespot1.setName(input[0]);
		werbespot1.setVerguetung(verguetung);
		werbespot1.setLaufzeit(laufzeit);

		BigDecimal ramEff = new BigDecimal("1");
		ramEff = checkBigDecimal(input[2]);
		final int vier = 4;
		werbespot1.setEff(verguetung.divide(ramEff, vier, RoundingMode.HALF_UP));

		WerbespotDaoStaticImpl sWerbespotListe = new WerbespotDaoStaticImpl();

		sWerbespotListe.createWerbespot(werbespot1);

		id++;
	}
}
