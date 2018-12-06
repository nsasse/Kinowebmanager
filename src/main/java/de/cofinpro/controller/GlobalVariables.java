package de.cofinpro.controller;

import java.awt.Color;
import java.awt.Font;
import javax.faces.bean.ManagedBean;

@ManagedBean

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

	// Film und Programmzeiten
	public static final int ANZ_FILM_START_ZEITEN = 4;
	public static final int ANZ_TAGE_PROGRAMM = 21;
	public static final int FILM_START_I0 = 15;
	public static final int FILM_START_I1_H = 17;
	public static final int FILM_START_I1_MIN = 30;
	public static final int FILM_START_I2 = 20;
	public static final int FILM_START_I3 = 23;
	public static final int FILM_START_I023_MIN = 0;

	//Ticketpreise
	public static final int TICKETPREIS_LOGE = 2;
	public static final int TICKETPREIS_PARKETT = 3;
	public static final int UMSATZERLOESE_LOGE = 0;
	public static final int UMSATZERLOESE_PARKETT = 1;
	
	//FSK Regelungen
	public static final int FSK_18 = 18;
	public static final int FSK_16 = 16;
	public static final int FSK_12 = 12;
	public static final int FSK_0 = 0;
	
	//Counter stehen bald in der Datenbank
	
	public GlobalVariables() {
		
	}
}
