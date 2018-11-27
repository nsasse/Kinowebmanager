package de.cofinpro.controller.importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Pattern;

public abstract class Importer {

	public void readCsvFile() {

		File fileRead = readFile();

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";

		try {

			br = new BufferedReader(new FileReader(fileRead));
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

	public abstract void transform(String[] input);

	public abstract File readFile();

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
		//if (text.equals("true")) {
		//	value = Boolean.TRUE;
		//	return value;
		//}
		
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
