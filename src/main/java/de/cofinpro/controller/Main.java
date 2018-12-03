package de.cofinpro.controller;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

@ManagedBean(name="mainBean")
@SessionScoped

public class Main {

	public static void main(String[] args) throws InvalidFormatException, IOException {

		@SuppressWarnings("unused")
		GlobalVariables fix = new GlobalVariables();
	}
	
	public void start() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/Kinowebmanager/index.xhtml");
	}
	
}
