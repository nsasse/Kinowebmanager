package de.cofinpro.controller.importer;

import java.io.File;
import java.util.ArrayList;
import de.cofinpro.controller.dao.impl.KinosaalDaoStaticImpl;
import de.cofinpro.modul.Kinosaal;

public class KinosaalImporter extends Importer {

	int id = 0;
	
	public ArrayList<Kinosaal> kinosaeleListe = new ArrayList<Kinosaal>();

	@Override
	public final File readFile() {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("saele.csv").getFile());

		return file;
	}

	@Override
	public final void transform(String[] input) {

		Integer id = intCheck(input[0]);
		Integer anzSitzeP = intCheck(input[1]);
		Integer anzSitzeL = intCheck(input[2]);
		String dreiDS = input[3];
		//dreiDS.toLowerCase(); //Anpassung des Input-Strings
		//Boolean Werte werden nicht erkannt
		Boolean dreiD = booleanCheck(dreiDS);

		Kinosaal kinosaal = new Kinosaal();
		kinosaal.setId(id);
		kinosaal.setAnzSitzeP(anzSitzeP);
		kinosaal.setAnzSitzeL(anzSitzeL);
		kinosaal.setDreiD(dreiD);

		KinosaalDaoStaticImpl sKinosaalListe = new KinosaalDaoStaticImpl();
		sKinosaalListe.createKinosaal(kinosaal);
	}
}
