package de.cofinpro.controller.importer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import de.cofinpro.controller.dao.impl.WerbespotDaoStaticImpl;
import de.cofinpro.controller.data.Creator;
import de.cofinpro.modul.Werbespot;

public class WerbespotImporter extends Importer {

	private int id = 60000;
	
	//Datenbankimport starten
	Creator creator = new Creator();

	@SuppressWarnings("unused")
	private ArrayList<Werbespot> werbespotListe = new ArrayList<Werbespot>();

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
		creator.createWerbespot(werbespot1);

		id++;
	}
}
