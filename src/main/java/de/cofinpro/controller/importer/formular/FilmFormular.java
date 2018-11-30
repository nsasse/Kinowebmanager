package de.cofinpro.controller.importer.formular;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.cofinpro.controller.dao.impl.FilmDaoStaticImpl;
import de.cofinpro.modul.Film;

@SuppressWarnings("serial")
@ManagedBean(name = "filmFormular")
@SessionScoped

public class FilmFormular implements Serializable{
	
	private Integer id;
	private String name;
	private String genre;
	private String regisseur;
	private String sprache;
	private Integer erscheinungsjahr;
	private String erscheinungsland;
	private Integer fsk;
	private Integer spieldauer;
	private Integer beliebtheit;
	private BigDecimal kosten; //Erst String
	private boolean dreiD;
	private BigDecimal eff; //Erst String
	
	private String kostenS;
	private String effS;
	
	
	private Film film = new Film();
	
	public FilmFormular() {
		
	}

	public String getName() {
		return name;
	}

	public String getGenre() {
		return genre;
	}

	public String getRegisseur() {
		return regisseur;
	}

	public String getSprache() {
		return sprache;
	}

	public Integer getErscheinungsjahr() {
		return erscheinungsjahr;
	}

	public String getErscheinungsland() {
		return erscheinungsland;
	}

	public Integer getFsk() {
		return fsk;
	}

	public Integer getSpieldauer() {
		return spieldauer;
	}

	public Integer getBeliebtheit() {
		return beliebtheit;
	}

	public BigDecimal getKosten() {
		return kosten;
	}

	public boolean isDreiD() {
		return dreiD;
	}

	public BigDecimal getEff() {
		return eff;
	}

	public Film getFilm() {
		return film;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setRegisseur(String regisseur) {
		this.regisseur = regisseur;
	}

	public void setSprache(String sprache) {
		this.sprache = sprache;
	}

	public void setErscheinungsjahr(Integer erscheinungsjahr) {
		this.erscheinungsjahr = erscheinungsjahr;
	}

	public void setErscheinungsland(String erscheinungsland) {
		this.erscheinungsland = erscheinungsland;
	}

	public void setFsk(Integer fsk) {
		this.fsk = fsk;
	}

	public void setSpieldauer(Integer spieldauer) {
		this.spieldauer = spieldauer;
	}

	public void setBeliebtheit(Integer beliebtheit) {
		this.beliebtheit = beliebtheit;
	}

	public void setKosten(BigDecimal kosten) {
		this.kosten = kosten;
	}

	public void setDreiD(boolean dreiD) {
		this.dreiD = dreiD;
	}

	public void setEff(BigDecimal eff) {
		this.eff = eff;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Integer getId() {
		return id;
	}

	public String getKostenS() {
		return kostenS;
	}

	public String getEffS() {
		return effS;
	}

	public void setKostenS(String kostenS) {
		this.kostenS = kostenS;
	}

	public void setEffS(String effS) {
		this.effS = effS;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void fixBD() {
		kosten = new BigDecimal(kostenS);
		eff = new BigDecimal(effS);
	}
	
	//Funktion, um den neuen Film in die static Class zu schreiben
	//NULLPOINTER
	public void save() {
		
		//Konvertieren von String zu BigDecimal bei eff & kosten
		fixBD();
		
		film.setId(999999); //METHODE ERGÄNZEN
		film.setName(name);
		film.setGenre(genre);
		film.setRegisseur(regisseur);
		film.setSprache(sprache);
		film.setErscheinungsjahr(erscheinungsjahr);
		film.setErscheinungsland(erscheinungsland);
		film.setFsk(fsk);
		film.setSpieldauer(spieldauer);
		film.setBeliebtheit(beliebtheit);
		film.setKosten(kosten);
		film.setDreiD(dreiD);
		film.setEff(eff);
		
		
		FilmDaoStaticImpl filmDaoStaticImpl = new FilmDaoStaticImpl();
		filmDaoStaticImpl.createFilm(film);
		System.out.println("Done");
	}


	
	
	
	

	
}
