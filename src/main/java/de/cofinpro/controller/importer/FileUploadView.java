package de.cofinpro.controller.importer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import de.cofinpro.controller.GlobalVariables;
import de.cofinpro.controller.XmlAusgabe;

@ManagedBean(name = "fileUploadView")
@SessionScoped
//@ApplicationScoped

public class FileUploadView {
	
	public FileUploadView() {
		System.out.println("Konstruktor");
		
	}

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void upload() {
		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	int counterFile = 0;
	UploadedFile filme;
	UploadedFile saele;
	UploadedFile werbespots;
	

	public void handleFileUpload(FileUploadEvent event) throws InvalidFormatException, IOException {
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		//System.out.println(event.getFile().getFileName());
		
		
		String name = new String();
		name = event.getFile().getFileName();
		file = event.getFile();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputstream(), "UTF-8"));

		if (name.equals("filme.csv")) {
			FilmImporter importFilme = new FilmImporter();
			importFilme.readCsvFile(br);
			GlobalVariables.DB_UPLOADER_COUNTER++;
			//System.out.println("Equals Film"); //CHECK
		}
		
		if (name.equals("saele.csv")) {
			KinosaalImporter importKinosaele = new KinosaalImporter();
			importKinosaele.readCsvFile(br);
			GlobalVariables.DB_UPLOADER_COUNTER++;
			//System.out.println("Equals Säle"); //CHECK
		}
		
		if (name.equals("werbespots.csv")) {
			WerbespotImporter importWerbespots = new WerbespotImporter();
			importWerbespots.readCsvFile(br);
			GlobalVariables.DB_UPLOADER_COUNTER++;
			//System.out.println("Equals Werbespots"); //CHECK
		}
		
		System.out.println(GlobalVariables.DB_UPLOADER_COUNTER);

		if (GlobalVariables.DB_UPLOADER_COUNTER == 3) {
			
			XmlAusgabe ausgabe = new XmlAusgabe();

			ausgabe.start();
			
			System.out.println("Weitergabe Daten");
			
			GlobalVariables.DB_UPLOADER_COUNTER = 0;
			
		}

	}
}
