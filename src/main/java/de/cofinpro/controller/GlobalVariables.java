package de.cofinpro.controller;

import java.awt.Color;
import java.awt.Font;

import org.primefaces.model.UploadedFile;

public class GlobalVariables {

	public final static int FONT_TF = 22; // Schriftgröße Textfeld
	public final static Color BUTTON_FONT_COLOR = Color.WHITE;
	public final static Color COFINPRO = new Color(15564544);
	public final static Font FONT = new Font("Agency FB", 0, GlobalVariables.FONT_TF);
	
	public static UploadedFile DB_FILME;
	public static UploadedFile DB_SAELE;
	public static UploadedFile DB_WERBESPOTS;
	public static int DB_UPLOADER_COUNTER;
	
	
	public GlobalVariables() {

	}
}
