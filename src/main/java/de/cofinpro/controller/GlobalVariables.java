package de.cofinpro.controller;

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

public class GlobalVariables {

	public final static int FONT_TF = 22; // Schriftgröße Textfeld
	public final static Color BUTTON_FONT_COLOR = Color.WHITE;
	public final static Color COFINPRO = new Color(15564544);
	public final static Font FONT = new Font("Agency FB", 0, GlobalVariables.FONT_TF);

	// Datenbankzugriff
	public final static String HOST = new String("localhost:3306");
	public final static String DATABASE = new String("kinowebmanager");
	public final static String USER = new String("admin");
	public final static String PASSWORD = new String("admin");
	public final static String DATABASE_TABLE = new String("filme");

	// Tage & Wochenparameter
	public final static int DURCHSCHNITT_TAGE_MONAT = 30;
	public final static int TAGE_FEBRUAR = 28;
	public final static int TAGE_JAN_MRZ_MAI_JUL = 31;
	public static Calendar KALENDER;
	public static Date DATUM;
	public static int DAY;
	// Note: +1 the month for current month
	public static int MONTH;
	public static int YEAR;
	public static int DAY_OF_WEEK;
	public static int DAY_OF_MONTH;
	public int DAY_OF_YEAR;

	// Film und Programmzeiten
	public static final int ANZ_FILM_START_ZEITEN = 4;
	public static final int ANZ_TAGE_PROGRAMM = 21;
	public static final int FILM_START_I0 = 15;
	public static final int FILM_START_I1_H = 17;
	public static final int FILM_START_I1_MIN = 30;
	public static final int FILM_START_I2 = 20;
	public static final int FILM_START_I3 = 23;
	public static final int FILM_START_I023_MIN = 0;

	public GlobalVariables() {
		DATUM = KALENDER.getTime();
		DAY = KALENDER.get(Calendar.DATE);
		// Note: +1 the month for current month
		MONTH = KALENDER.get(Calendar.MONTH) + 1;
		YEAR = KALENDER.get(Calendar.YEAR);
		DAY_OF_WEEK = KALENDER.get(Calendar.DAY_OF_WEEK);
		DAY_OF_MONTH = KALENDER.get(Calendar.DAY_OF_MONTH);
		DAY_OF_YEAR = KALENDER.get(Calendar.DAY_OF_YEAR);
	}
}
