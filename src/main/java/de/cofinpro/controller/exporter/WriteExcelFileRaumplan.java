package de.cofinpro.controller.exporter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import de.cofinpro.modul.Programm;
import de.cofinpro.modul.Vorfuehrung;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class WriteExcelFileRaumplan {

	private static String[] columns = { "Vorfuehrungs ID", "Film", "Länge (min)", "FSK", "Kinosaal", "Kunden Loge",
			"Kunden Parkett", "3D" };

	public static void exporterRaumplan(ArrayList<Programm> programmListe) throws IOException, InvalidFormatException {

		Programm programmTag = new Programm();
		Vorfuehrung programmVorfuehrung = new Vorfuehrung();

		programmTag = programmListe.get(1);
		Double zufallD = new Double(Math.random());
		zufallD = zufallD * 32;
		Integer zufall = zufallD.intValue();

		programmVorfuehrung = programmListe.get(1).getVorfuehrungen().get(zufall);
		Integer vId = programmVorfuehrung.getId();

		// Create a Workbook
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook();

		// Create a Sheet

		Sheet sheet = workbook.createSheet("Raumplan Vorführung " + vId);

		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 11);
		headerFont.setColor(IndexedColors.BLACK.getIndex());
		headerFont.setFontName("Calibri");

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// BackgroundStyle
		CellStyle backgroundStyleLogeEmpty = workbook.createCellStyle();
		backgroundStyleLogeEmpty.setFillForegroundColor(IndexedColors.BLUE.getIndex());

		CellStyle backgroundStyleLogeFull = workbook.createCellStyle();
		backgroundStyleLogeFull.setFillForegroundColor(IndexedColors.VIOLET.getIndex());

		CellStyle backgroundStyleParkettEmpty = workbook.createCellStyle();
		backgroundStyleParkettEmpty.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());

		CellStyle backgroundStyleParkettFull = workbook.createCellStyle();
		backgroundStyleParkettFull.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());

		// Create a Row
		Row headerRow = sheet.createRow(0);

		// Creating cells
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		int anzKundenL = programmVorfuehrung.getAnzKundenL();
		int anzKundenP = programmVorfuehrung.getAnzKundenP();

		int rowNum = 1;
		Row row = sheet.createRow(rowNum++);

		row.createCell(0).setCellValue(programmVorfuehrung.getId());
		row.getCell(0).setCellStyle(backgroundStyleLogeEmpty);

		row.createCell(1).setCellValue(programmVorfuehrung.getFilm().getName().replaceAll("Ã¼", "ü"));

		row.createCell(2).setCellValue(programmVorfuehrung.getDauer());

		row.createCell(3).setCellValue(programmVorfuehrung.getFilm().getFsk());

		row.createCell(4).setCellValue(programmVorfuehrung.getKinosaal().getId());

		row.createCell(5).setCellValue(anzKundenL);

		row.createCell(6).setCellValue(anzKundenP);

		if (programmTag.getVorfuehrungen().get(rowNum - 2).getFilm().isDreiD() == false) {
			row.createCell(7).setCellValue("nein");
		} else {
			row.createCell(7).setCellValue("ja");
		}

		// Resize all columns to fit the content size
		final int anzSitzeL = programmVorfuehrung.getKinosaal().getAnzSitzeL();
		int anzSitzeLCounter = anzSitzeL;
		final int anzSitzeP = programmVorfuehrung.getKinosaal().getAnzSitzeP();
		int anzSitzePCounter = anzSitzeP;

		int reihenL = 7;
		if (anzSitzeL + anzKundenP > 300) {
			reihenL = 9;
		}

		BigDecimal sitzeJeReiheBD = BigDecimal.ZERO;
		sitzeJeReiheBD = sitzeJeReiheBD.add(BigDecimal.valueOf(anzSitzeL));
		sitzeJeReiheBD = sitzeJeReiheBD.divide(BigDecimal.valueOf(reihenL), RoundingMode.HALF_UP);
		final int sitzeJeReihe = sitzeJeReiheBD.intValue();

		int indexReihe = 4;
		int anzKundenLCounter = anzKundenL;
		int anzKundenPCounter = anzKundenP;

		while (anzSitzeLCounter > 0) {
			row = sheet.createRow(indexReihe);

			for (int i = 0; i < sitzeJeReihe; i++) {
				Cell cell1 = row.createCell(i);
				if (anzKundenLCounter > 0) {
					cell1.setCellValue("BELEGT");
					cell1.setCellStyle(backgroundStyleLogeFull);
					anzKundenLCounter--;
					anzSitzeLCounter--;

				} else {
					cell1.setCellValue("FREI");
					cell1.setCellStyle(backgroundStyleLogeFull);
					anzSitzeLCounter--;
				}

			}
			indexReihe++;
		}
		// Leere Reihe
		row = sheet.createRow(indexReihe);
		Cell cellEmpty = row.createCell(0);
		cellEmpty.setCellValue("");
		indexReihe++;

		while (anzSitzePCounter > 0) {
			row = sheet.createRow(indexReihe);

			for (int i = 0; i < sitzeJeReihe; i++) {
				Cell cell1 = row.createCell(i);
				if (anzKundenPCounter > 0) {
					cell1.setCellValue("BELEGT");
					cell1.setCellStyle(backgroundStyleLogeFull);
					anzKundenPCounter--;
					anzSitzePCounter--;

				} else {
					cell1.setCellValue("FREI");
					cell1.setCellStyle(backgroundStyleLogeFull);
					anzSitzePCounter--;
				}

			}
			indexReihe++;
		}

		for (int i = 0; i < sitzeJeReihe; i++) {

			sheet.setColumnWidth(i, 1950);
		}

		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream("C:/dev/Raumplan_" + vId + "_Export.xlsx");
		workbook.write(fileOut);
		fileOut.close();
		
		System.out.println("Raumplan_" + vId + "_Export.xlsx wurde erstellt.");
	}
}
