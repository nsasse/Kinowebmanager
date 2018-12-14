package de.cofinpro.controller.importer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import de.cofinpro.controller.dao.impl.KinosaalDaoStaticImpl;
import de.cofinpro.controller.data.Creator;
import de.cofinpro.modul.Kinosaal;

public class KinosaalImporter extends Importer {

	int id = 10000;
	
	//Datenbankimport starten
	Creator creator = new Creator();

	public ArrayList<Kinosaal> kinosaeleListe = new ArrayList<Kinosaal>();

	@Override
	public void readCsvFile(BufferedReader br) throws IOException {
	
		String line = "";
		String cvsSplitBy = ";";

		try {

			while ((line = br.readLine()) != null) {
				String[] stringDateityp = line.split(cvsSplitBy);

				transform(stringDateityp);
			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public final void transform(String[] input) {

		Integer id = intCheck(input[0]);
		Integer anzSitzeP = intCheck(input[1]);
		Integer anzSitzeL = intCheck(input[2]);
		String dreiDS = input[3];
		// dreiDS.toLowerCase(); //Anpassung des Input-Strings
		// Boolean Werte werden nicht erkannt
		Boolean dreiD = booleanCheck(dreiDS);

		Kinosaal kinosaal = new Kinosaal();
		kinosaal.setId(id);
		kinosaal.setAnzSitzeP(anzSitzeP);
		kinosaal.setAnzSitzeL(anzSitzeL);
		kinosaal.setDreiD(dreiD);

		KinosaalDaoStaticImpl sKinosaalListe = new KinosaalDaoStaticImpl();
		sKinosaalListe.createKinosaal(kinosaal);
		
		creator.createKinosaal(kinosaal);
	}
}
