package de.cofinpro.controller.exporter;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
public class FileDownloadView {
     
    private StreamedContent file;
     
    public FileDownloadView() {        
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("ProgrammExport.xlsx");
        file = new DefaultStreamedContent(stream, "xlsx", "ProgrammExport.xlsx");
    }
 
    public StreamedContent getFile() {
        return file;
    }
}