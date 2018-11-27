package de.cofinpro.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ManagedBean(name = "MainBean")
// @RequestScoped
@SessionScoped
public class MainBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	public String hello() {

		System.out.println("Calling index.xhtml");

		return "success";

	}

	public String getCurrentTime() {

		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM-yyyy HH:mm:ss")); // Requires Java 8
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
