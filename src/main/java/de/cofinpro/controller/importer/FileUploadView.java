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

import de.cofinpro.controller.GlobalVariables;
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

		String name = new String();
		long size = event.getFile().getSize();
		name = event.getFile().getFileName();
		file = event.getFile();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputstream(), "UTF-8"));

		if (name.equals("filme.csv")) {
			addItemToList(br, name, size, "Film");
			FilmImporter importFilme = new FilmImporter();
			importFilme.readCsvFile(br);
			GlobalVariables.DB_UPLOADER_COUNTER++;
			//System.out.println("Equals Film"); //CHECK
		}
		
		if (name.equals("saele.csv")) {
			addItemToList(br, name, size, "Saele");
			KinosaalImporter importKinosaele = new KinosaalImporter();
			importKinosaele.readCsvFile(br);
			GlobalVariables.DB_UPLOADER_COUNTER++;
			//System.out.println("Equals Säle"); //CHECK
		}
		
		if (name.equals("werbespots.csv")) {
			addItemToList(br, name, size, "Werbespot");
			
			WerbespotImporter importWerbespots = new WerbespotImporter();
			importWerbespots.readCsvFile(br);
			GlobalVariables.DB_UPLOADER_COUNTER++;
			//System.out.println("Equals Werbespots"); //CHECK
		}
		
		if (GlobalVariables.DB_UPLOADER_COUNTER == 3) {
			
			GlobalVariables.DB_UPLOADER_COUNTER = 0;
		}

	}
	
	
	private List<TableItem> itemList = new ArrayList<TableItem>();
	private List<DataTableColumn> dataTableColumns = new ArrayList<DataTableColumn>();

	@PostConstruct
	public void init() {
		// itemList.add(new TableItem("Test.csv", "br", 100, "Filme"));
		// prepare dynamic columns
		dataTableColumns.add(new DataTableColumn("Name", "fileName"));
		dataTableColumns.add(new DataTableColumn("Inhalt", "br"));
		dataTableColumns.add(new DataTableColumn("Size", "size"));
		dataTableColumns.add(new DataTableColumn("Type", "type"));
	}

	public void addItemToList(BufferedReader br, String name, long size, String type) {
		itemList.add(new TableItem(name, br, size,  type));
	}

	public List<TableItem> getItemList() {
		return itemList;
	}

	public List<DataTableColumn> getDataTableColumns() {
		return dataTableColumns;
	}
}
