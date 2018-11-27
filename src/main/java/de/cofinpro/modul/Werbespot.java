package de.cofinpro.modul;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
@SuppressWarnings("serial")
@Entity
@Table(name = "werbespot")

public class Werbespot implements Serializable {

	@Id
	@GeneratedValue

	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "laufzeit")
	private int laufzeit;

	@Column(name = "verguetung")
	private BigDecimal verguetung;
	@Column(name = "eff")
	private BigDecimal eff;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "werbespot_vorfuerung", joinColumns = {
			@JoinColumn(name = "werbespot_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "vorfuehrung_id", nullable = false, updatable = false) })
	private List<Vorfuehrung> vorfuehrungen = new ArrayList<Vorfuehrung>();

	public Werbespot() {
		// TODO Auto-generated constructor stub
	}

	public Werbespot(final int id, final String name, final int laufzeit, final BigDecimal verguetung,
			final BigDecimal eff) {
		super();
		this.id = id;
		this.name = name;
		this.laufzeit = laufzeit;
		this.verguetung = verguetung;
		this.eff = eff;
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eff == null) ? 0 : eff.hashCode());
		result = prime * result + id;
		result = prime * result + laufzeit;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((verguetung == null) ? 0 : verguetung.hashCode());
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
		Werbespot other = (Werbespot) obj;
		if (eff == null) {
			if (other.eff != null)
				return false;
		} else if (!eff.equals(other.eff))
			return false;
		if (id != other.id)
			return false;
		if (laufzeit != other.laufzeit)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (verguetung == null) {
			if (other.verguetung != null)
				return false;
		} else if (!verguetung.equals(other.verguetung))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLaufzeit() {
		return laufzeit;
	}

	public void setLaufzeit(int laufzeit) {
		this.laufzeit = laufzeit;
	}

	public BigDecimal getVerguetung() {
		return verguetung;
	}

	public void setVerguetung(BigDecimal verguetung) {
		this.verguetung = verguetung;
	}

	public BigDecimal getEff() {
		return eff;
	}

	public void setEff(BigDecimal eff) {
		this.eff = eff;
	}

	@Override
	public final String toString() {
		return "Werbespot [id=" + id + ", name=" + name + ", laufzeit=" + laufzeit + ", verguetung=" + verguetung
				+ ", eff=" + eff + "]";
	}

}
