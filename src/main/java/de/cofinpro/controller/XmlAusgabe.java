package de.cofinpro.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.primefaces.model.UploadedFile;

import de.cofinpro.controller.dao.impl.ProgrammDaoStaticImpl;
import de.cofinpro.controller.exporter.WriteExcelFileProgramm;
import de.cofinpro.controller.exporter.WriteExcelFileRaumplan;
import de.cofinpro.controller.importer.FilmImporter;
import de.cofinpro.controller.importer.KinosaalImporter;
import de.cofinpro.controller.importer.WerbespotImporter;
import de.cofinpro.controller.service.ErstellungProgramm;
import de.cofinpro.modul.Programm;

public class XmlAusgabe {

	public XmlAusgabe() throws InvalidFormatException, IOException {

		System.out.println("XML wurde gestartet");

	}
	
	public void start(UploadedFile uploadedFile) throws IOException {
		FilmImporter importFilme = new FilmImporter();
		KinosaalImporter importKinosaele = new KinosaalImporter();
		WerbespotImporter importWerbespots = new WerbespotImporter();

		ProgrammDaoStaticImpl erstellungProgrammDao = new ProgrammDaoStaticImpl();
		ErstellungProgramm erstellungProgramm = new ErstellungProgramm();

		importWerbespots.readCsvFile();
		importFilme.readCsvFile(uploadedFile);
		importKinosaele.readCsvFile();

		ArrayList<Programm> programmListe = new ArrayList<Programm>();
		erstellungProgramm.generateProgramm();

		programmListe = erstellungProgrammDao.getProgramm();

		try {
			WriteExcelFileProgramm.exporterProgramm(programmListe);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			WriteExcelFileRaumplan.exporterRaumplan(programmListe);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
