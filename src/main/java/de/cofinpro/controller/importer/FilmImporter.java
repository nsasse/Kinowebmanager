package de.cofinpro.controller.importer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import de.cofinpro.controller.dao.impl.FilmDaoImpl;
import de.cofinpro.modul.Film;

public class FilmImporter extends Importer {

	int id = 20000;

	FilmDaoImpl filmDAO = new FilmDaoImpl();

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

	public final void transform(final String[] input) {

		Integer fsk = intFskCheck(input[2]);
		BigDecimal kosten = checkBigDecimal(input[4]);
		Integer beliebtheit = intCheck(input[5]);
		Integer spieldauer = intCheck(input[6]);
		Integer erscheinungsjahr = intCheck(input[9]);
		String dreiDS = input[10];
		dreiDS = dreiDS.toLowerCase(); // Anpassung des Input-Strings
		Boolean dreiD = booleanCheck(dreiDS);

		Film film = new Film();
		film.setId(id);
		film.setName(input[0]);
		film.setRegisseur(input[1]);
		film.setFsk(fsk);
		film.setGenre(input[3]);
		film.setKosten(kosten);
		film.setBeliebtheit(beliebtheit);
		film.setSpieldauer(spieldauer);
		film.setSprache(input[7]);
		film.setErscheinungsland(input[8]);
		film.setErscheinungsjahr(erscheinungsjahr);
		film.setDreiD(dreiD);

		if (beliebtheit != 0) {

			filmDAO.createFilm(film);

		}

	}

}
