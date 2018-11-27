package de.cofinpro.controller.importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.primefaces.model.UploadedFile;

import de.cofinpro.controller.dao.impl.FilmDaoStaticImpl;
import de.cofinpro.modul.Film;

public class FilmImporter {

	int id = 50000;

	public ArrayList<Film> filmListe = new ArrayList<Film>();

	public void readCsvFile(UploadedFile uploadedFile) throws IOException {

		//InputStreamReader funktioniert nicht (UploadedFile != File)
		
		BufferedReader br = new BufferedReader(new InputStreamReader(uploadedFile.getInputstream(), "UTF-8"));

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

	public final void transform(final String[] input) {

		Integer fsk = intFskCheck(input[2]);
		BigDecimal kosten = checkBigDecimal(input[4]);
		Integer beliebtheit = intCheck(input[5]);
		Integer spieldauer = intCheck(input[6]);
		Integer erscheinungsjahr = intCheck(input[9]);
		String dreiDS = input[10];
		dreiDS = dreiDS.toLowerCase(); // Anpassung des Input-Strings
		Boolean dreiD = booleanCheck(dreiDS);

		Film filme1 = new Film();
		filme1.setId(id);
		filme1.setName(input[0]);
		filme1.setRegisseur(input[1]);
		filme1.setFsk(fsk);
		filme1.setGenre(input[3]);
		filme1.setKosten(kosten);
		filme1.setBeliebtheit(beliebtheit);
		filme1.setSpieldauer(spieldauer);
		filme1.setSprache(input[7]);
		filme1.setErscheinungsland(input[8]);
		filme1.setErscheinungsjahr(erscheinungsjahr);
		filme1.setDreiD(dreiD);

		FilmDaoStaticImpl sFilmListe = new FilmDaoStaticImpl();

		if (beliebtheit != 0) {
			sFilmListe.createFilm(filme1);
			id++;
		}

	}

	public File upleadFile(UploadedFile uploadedFile) {

		File file = (File) uploadedFile;

		return file;
	}

	public static final Integer intCheck(String text) {
		if (Pattern.compile("-?[0-9]+").matcher(text).find()) {
			Integer value = Integer.valueOf(text);
			return value;
		}
		Integer value = null;
		return value;
	}

	public static final Integer intFskCheck(final String text) {
		if (Pattern.compile("-?[0-9]+").matcher(text).find()) {
			Integer value = Integer.valueOf(text);
			if (value == 0 || value == 6 || value == 12 || value == 16 || value == 18) {
				return value;
			}
		}
		return null;
	}

	public static final Boolean booleanCheck(final String text) {
		Boolean value = Boolean.FALSE;
		// if (text.equals("true")) {
		// value = Boolean.TRUE;
		// return value;
		// }

		return value;
	}

	public static final BigDecimal checkBigDecimal(final String text) {
		if (Pattern.compile("-?[0-9]+").matcher(text).find()) {
			BigDecimal value = new BigDecimal(text);
			return value;
		}
		BigDecimal keinBelientheit = new BigDecimal("0");
		return keinBelientheit;
	}

}
