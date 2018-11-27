package de.cofinpro.controller.importer;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import de.cofinpro.controller.XmlAusgabe;

@ManagedBean(name = "fileUploadView")
public class FileUploadView {
	     
	    private UploadedFile file;
	 
	    public UploadedFile getFile() {
	        return file;
	    }
	 
	    public void setFile(UploadedFile file) {
	        this.file = file;
	    }
	     
	    public void upload() {
	        if(file != null) {
	            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
	            FacesContext.getCurrentInstance().addMessage(null, message);
	        }
	    }
	     
	    public void handleFileUpload(FileUploadEvent event) throws InvalidFormatException, IOException {
	        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        
	    	XmlAusgabe ausgabe = new XmlAusgabe();
	    	
	    	ausgabe.start(event.getFile());
	    	
	    	System.out.println("XML wurde erstellt!");
	    	
	        
	    }
	}
