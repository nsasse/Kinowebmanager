package de.cofinpro.controller.importer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import de.cofinpro.controller.dao.impl.FilmDaoStaticImpl;
import de.cofinpro.controller.data.DbConnect;
import de.cofinpro.modul.Film;

public class FilmImporter extends Importer{

	int id = 20000;

	public ArrayList<Film> filmListe = new ArrayList<Film>();
	FilmDaoStaticImpl sFilmListe = new FilmDaoStaticImpl();
	DbConnect db = new DbConnect();

	@Override
	public void readCsvFile(BufferedReader br) throws IOException {

		db.connectToMysql();
		
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

		
		//VORHER new FILMDAOSTATICIMPL

		if (beliebtheit != 0) {
			sFilmListe.createFilm(filme1);
			sFilmListe.createFilm(db, filme1);
			id++;
		}

	}

}
