package de.cofinpro.controller.importer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import de.cofinpro.controller.data.DbConnect;
import de.cofinpro.controller.dataView.DataTableColumn;
import de.cofinpro.controller.dataView.TableItem;

@SuppressWarnings("restriction")
@ManagedBean(name = "fileUploadView")
@SessionScoped

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

	public void handleFileUpload(FileUploadEvent event) throws InvalidFormatException, IOException {
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		String type = new String();
		String name = new String();
		long size = event.getFile().getSize();
		name = event.getFile().getFileName();
		file = event.getFile();

		if (name.equals("filme.csv")) {
			type = "Filme";
		}

		else if (name.equals("saele.csv")) {
			type = "Saele";
		}

		else if (name.equals("werbespots.csv")) {
			type = "Werbespots";
		} else {
			System.out.println("Fehlerhafte Dateibenennung");
			type = "Sonstiges";
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputstream(), "UTF-8"));
		addItemToList(br, name, size, type);

	}

	// Erzeugung der Tabel

	private List<TableItem> itemList = new ArrayList<TableItem>();
	private List<DataTableColumn> dataTableColumns = new ArrayList<DataTableColumn>();

	@PostConstruct
	public void init() {
		// Spalten erzeugen
		dataTableColumns.add(new DataTableColumn("Name", "fileName"));
		dataTableColumns.add(new DataTableColumn("Inhalt", "br"));
		dataTableColumns.add(new DataTableColumn("Size (kb)", "size"));
		dataTableColumns.add(new DataTableColumn("Type", "type"));
	}

	public void addItemToList(BufferedReader br, String name, long size, String type) {
		itemList.add(new TableItem(name, br, size, type));
	}

	public List<TableItem> getItemList() {
		return itemList;
	}

	public List<DataTableColumn> getDataTableColumns() {
		return dataTableColumns;
	}

	// Übertragung der Daten in der Datenbank
	public void saveFiles() throws IOException {

		for (int i = 0; i < itemList.size(); i++) {

			String type = itemList.get(i).getType();
			BufferedReader br = itemList.get(i).getBr();
			
			
			if (type.equals("Filme")) {
				FilmImporter importFilme = new FilmImporter();
				importFilme.readCsvFile(br);
			}

			else if (type.equals("Saele")) {
				KinosaalImporter importKinosaele = new KinosaalImporter();
				importKinosaele.readCsvFile(br);
			}

			else if (type.equals("Werbespots")) {
				WerbespotImporter importWerbespots = new WerbespotImporter();
				importWerbespots.readCsvFile(br);
			}

			else {
				System.out.println("Fehlerhafte Dateibenennung (NUR: Filme.csv, Saele.csv, Werbespots.csv)");
			}

		}

	}
	
	public void dbCheck() {
		
		String host = new String("localhost:3306");
		String database = new String("kinowebmanager");
		String user = new String("admin");
		String passwd = new String("admin");
		
		DbConnect db = new DbConnect();
		db.connectToMysql(host, database, user, passwd);
	}
}
