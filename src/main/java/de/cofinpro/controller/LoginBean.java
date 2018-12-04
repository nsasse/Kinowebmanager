package de.cofinpro.controller;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
@ManagedBean(name = "loginBean")
@SessionScoped

public class LoginBean implements Serializable {

	private String user;
	private String password;

	private String userSecure;
	private String passwordSecure;

	public LoginBean() {
		userSecure = "admin";
		passwordSecure= "admin";
	}
	
	public void start() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/Kinowebmanager/login.xhtml");
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public void check() throws IOException {

		boolean status = false;
		
		if (userSecure.equals(user) && passwordSecure.equals(password)) {

			status = true;
		}
		
		forwarding(status);
	}
	
	private void forwarding(boolean status) throws IOException{
		
		if (status == true) {
			System.out.println("PW true");
			FacesContext.getCurrentInstance().getExternalContext().redirect("/Kinowebmanager/admin.xhtml"); 
		}
		else {
			System.out.println("PW false");
			//Fehlermeldung
		}
	}
}
