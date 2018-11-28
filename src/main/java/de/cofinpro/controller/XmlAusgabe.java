package de.cofinpro.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import de.cofinpro.controller.dao.impl.ProgrammDaoStaticImpl;
import de.cofinpro.controller.exporter.WriteExcelFileProgramm;
import de.cofinpro.controller.exporter.WriteExcelFileRaumplan;
import de.cofinpro.controller.service.ErstellungProgramm;
import de.cofinpro.modul.Programm;

public class XmlAusgabe {

	public XmlAusgabe() throws InvalidFormatException, IOException {

		System.out.println("XML wurde gestartet");

	}
	
	public void start() throws IOException {
		
		ProgrammDaoStaticImpl erstellungProgrammDao = new ProgrammDaoStaticImpl();
		ErstellungProgramm erstellungProgramm = new ErstellungProgramm();

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
