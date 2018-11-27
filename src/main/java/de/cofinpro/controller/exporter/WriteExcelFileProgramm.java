package de.cofinpro.controller.exporter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import de.cofinpro.modul.Programm;
import de.cofinpro.modul.Vorfuehrung;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class WriteExcelFileProgramm {

	private static String[] columns = { "Startzeit", "Film", "Länge (min)", "FSK", "Kinosaal", "Preis Loge (€)",
			"Preis Parkett (€)", "3D" };

	public static void exporterProgramm(final ArrayList<Programm> programmListe) throws IOException, InvalidFormatException {
		
		// Create a Workbook
		
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(); 

		for (int p = 0; p < programmListe.size(); p++) {
			// Create a Sheet
			Sheet sheet = workbook.createSheet("PT " + (p + 1));

			// Create a Font for styling header cells
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 10);
			headerFont.setColor(IndexedColors.BLACK.getIndex());
			headerFont.setFontName("Comic Sans MS");

			// Create a CellStyle with the font
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			CellStyle style = workbook.createCellStyle();
			Font bodyFont = workbook.createFont();
			style.setFillBackgroundColor(IndexedColors.CORAL.getIndex());
			bodyFont.setFontName("Times New Roman");
			bodyFont.setItalic(true);
			style.setFont(bodyFont);
			style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
			//style .setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
			//style.setBottomBorderColor(IndexedColors.GOLD.getIndex());
			//style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			
			// Create a Row
			Row headerRow = sheet.createRow(0);

			// Creating cells
			for (int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);

			}
			int rowNum = 1;

			Programm programmTag = programmListe.get(p);

			for (int i = 0; i < programmListe.get(p).getVorfuehrungen().size(); i++) {

				Row row = sheet.createRow(rowNum++);
				// UNVOLLSTÄNDIG!

				Vorfuehrung getVorfuehrungRowNum = programmTag.getVorfuehrungen().get(rowNum - 2);
				row.createCell(0).setCellValue(getVorfuehrungRowNum.getStartzeit().toString());
				
				//Zeichenfehler aus CSV beheben
				row.createCell(1).setCellValue(getVorfuehrungRowNum.getFilm().getName().replaceAll("Ã¼", "ü"));

				row.createCell(2).setCellValue(getVorfuehrungRowNum.getDauer());

				row.createCell(3).setCellValue(getVorfuehrungRowNum.getFilm().getFsk());

				row.createCell(4).setCellValue(getVorfuehrungRowNum.getKinosaal().getId());

				row.createCell(5).setCellValue(getVorfuehrungRowNum.getTicketPreisL().doubleValue());

				row.createCell(6).setCellValue(getVorfuehrungRowNum.getTicketPreisP().doubleValue());

				if (getVorfuehrungRowNum.getFilm().isDreiD() == false) {
					row.createCell(7).setCellValue("nein");
				} else {
					row.createCell(7).setCellValue("ja");
				}

				for (int j = 0; j < columns.length; j++) {
					row.getCell(j).setCellStyle(style);
				}
				

			}

			// Resize all columns to fit the content size
			for (int i = 0; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}

		}

		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream("C:/dev/ProgrammExport.xlsx");
		workbook.write(fileOut);
		fileOut.close();

		System.out.println("ProgrammExport.xlsx wurde erstellt.");
	}
}