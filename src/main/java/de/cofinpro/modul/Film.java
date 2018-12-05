package de.cofinpro.modul;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name = "Film")
public class Film implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "genre")
	private String genre;
	@Column(name = "regisseur")
	private String regisseur;
	@Column(name = "sprache")
	private String sprache;
	@Column(name = "erscheinungsjahr")
	private Integer erscheinungsjahr;
	@Column(name = "erscheinungsland")
	private String erscheinungsland;
	@Column(name = "fsk")
	private Integer fsk;
	@Column(name = "spieldauer")
	private Integer spieldauer;
	@Column(name = "beliebtheit")
	private Integer beliebtheit;
	@Column(name = "kosten")
	private BigDecimal kosten;
	@Column (name = "dreiD")
	private boolean dreiD;
	@Column (name = "eff")
	private BigDecimal eff;
	@OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
	@JoinColumn(name = "fk>_vorfuehrung")
	private List<Vorfuehrung> vorfuehrung;
	
	
	public Film() {
		// TODO Auto-generated constructor stub
	}


	public Film(Integer id, String name, String genre, String regisseur, String sprache, Integer erscheinungsjahr,
			String erscheinungsland, Integer fsk, Integer spieldauer, Integer beliebtheit, BigDecimal kosten,
			boolean dreiD, BigDecimal eff) {
		super();
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.regisseur = regisseur;
		this.sprache = sprache;
		this.erscheinungsjahr = erscheinungsjahr;
		this.erscheinungsland = erscheinungsland;
		this.fsk = fsk;
		this.spieldauer = spieldauer;
		this.beliebtheit = beliebtheit;
		this.kosten = kosten;
		this.dreiD = dreiD;
		this.eff = eff;
	}


	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beliebtheit == null) ? 0 : beliebtheit.hashCode());
		result = prime * result + (dreiD ? 1231 : 1237);
		result = prime * result + ((eff == null) ? 0 : eff.hashCode());
		result = prime * result + ((erscheinungsjahr == null) ? 0 : erscheinungsjahr.hashCode());
		result = prime * result + ((erscheinungsland == null) ? 0 : erscheinungsland.hashCode());
		result = prime * result + ((fsk == null) ? 0 : fsk.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((kosten == null) ? 0 : kosten.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((regisseur == null) ? 0 : regisseur.hashCode());
		result = prime * result + ((spieldauer == null) ? 0 : spieldauer.hashCode());
		result = prime * result + ((sprache == null) ? 0 : sprache.hashCode());
		return result;
	}


	@Override
	public final boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (beliebtheit == null) {
			if (other.beliebtheit != null)
				return false;
		} else if (!beliebtheit.equals(other.beliebtheit))
			return false;
		if (dreiD != other.dreiD)
			return false;
		if (eff == null) {
			if (other.eff != null)
				return false;
		} else if (!eff.equals(other.eff))
			return false;
		if (erscheinungsjahr == null) {
			if (other.erscheinungsjahr != null)
				return false;
		} else if (!erscheinungsjahr.equals(other.erscheinungsjahr))
			return false;
		if (erscheinungsland == null) {
			if (other.erscheinungsland != null)
				return false;
		} else if (!erscheinungsland.equals(other.erscheinungsland))
			return false;
		if (fsk == null) {
			if (other.fsk != null)
				return false;
		} else if (!fsk.equals(other.fsk))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (kosten == null) {
			if (other.kosten != null)
				return false;
		} else if (!kosten.equals(other.kosten))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (regisseur == null) {
			if (other.regisseur != null)
				return false;
		} else if (!regisseur.equals(other.regisseur))
			return false;
		if (spieldauer == null) {
			if (other.spieldauer != null)
				return false;
		} else if (!spieldauer.equals(other.spieldauer))
			return false;
		if (sprache == null) {
			if (other.sprache != null)
				return false;
		} else if (!sprache.equals(other.sprache))
			return false;
		return true;
	}


	public final Integer getId() {
		return id;
	}


	public final void setId(final Integer id) {
		this.id = id;
	}


	public final String getName() {
		return name;
	}


	public final void setName(final String name) {
		this.name = name;
	}


	public final String getGenre() {
		return genre;
	}


	public final void setGenre(final String genre) {
		this.genre = genre;
	}


	public final  String getRegisseur() {
		return regisseur;
	}


	public final void setRegisseur(final String regisseur) {
		this.regisseur = regisseur;
	}


	public final String getSprache() {
		return sprache;
	}


	public final void setSprache(final String sprache) {
		this.sprache = sprache;
	}


	public final Integer getErscheinungsjahr() {
		return erscheinungsjahr;
	}


	public final void setErscheinungsjahr(final Integer erscheinungsjahr) {
		this.erscheinungsjahr = erscheinungsjahr;
	}


	public final String getErscheinungsland() {
		return erscheinungsland;
	}


	public final void setErscheinungsland(final String erscheinungsland) {
		this.erscheinungsland = erscheinungsland;
	}


	public final Integer getFsk() {
		return fsk;
	}


	public final void setFsk(final Integer fsk) {
		this.fsk = fsk;
	}


	public final Integer getSpieldauer() {
		return spieldauer;
	}


	public final void setSpieldauer(final Integer spieldauer) {
		this.spieldauer = spieldauer;
	}


	public final Integer getBeliebtheit() {
		return beliebtheit;
	}


	public final void setBeliebtheit(final Integer beliebtheit) {
		this.beliebtheit = beliebtheit;
	}


	public final BigDecimal getKosten() {
		return kosten;
	}


	public final void setKosten(final BigDecimal kosten) {
		this.kosten = kosten;
	}


	public final boolean isDreiD() {
		return dreiD;
	}


	public final void setDreiD(final boolean dreiD) {
		this.dreiD = dreiD;
	}


	public final BigDecimal getEff() {
		return eff;
	}


	public final void setEff( final BigDecimal eff) {
		this.eff = eff;
	}


	@Override
	public final String toString() {
		return "Film [id=" + id + ", name=" + name + ", genre=" + genre + ", regisseur=" + regisseur + ", sprache="
				+ sprache + ", erscheinungsjahr=" + erscheinungsjahr + ", erscheinungsland=" + erscheinungsland
				+ ", fsk=" + fsk + ", spieldauer=" + spieldauer + ", beliebtheit=" + beliebtheit + ", kosten=" + kosten
				+ ", dreiD=" + dreiD + ", eff=" + eff + "]";
	}

}