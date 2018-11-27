package de.cofinpro.modul;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "Programm")

public class Programm implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(name = "start")
	private LocalDate start;
	@Column(name = "ende")
	private LocalDate ende;
	@Column(name = "ue")
	private BigDecimal ue;
	@Column(name = "gewinn")
	private BigDecimal gewinn;
	@Column(name = "kosten")
	private BigDecimal kosten;
	@OneToMany(mappedBy = "programm", fetch = FetchType.EAGER)
	private ArrayList<Vorfuehrung> vorfuehrungen = new ArrayList<Vorfuehrung>();

	public Programm() {
		// TODO Auto-generated constructor stub
	}

	public Programm(final int id, final LocalDate start, final LocalDate ende, final BigDecimal ue,
			final BigDecimal gewinn, final BigDecimal kosten, final ArrayList<Vorfuehrung> vorfuehrungen) {
		super();
		this.id = id;
		this.start = start;
		this.ende = ende;
		this.ue = ue;
		this.gewinn = gewinn;
		this.kosten = kosten;
		this.vorfuehrungen = vorfuehrungen;
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ende == null) ? 0 : ende.hashCode());
		result = prime * result + ((gewinn == null) ? 0 : gewinn.hashCode());
		result = prime * result + id;
		result = prime * result + ((kosten == null) ? 0 : kosten.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((ue == null) ? 0 : ue.hashCode());
		result = prime * result + ((vorfuehrungen == null) ? 0 : vorfuehrungen.hashCode());
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
		Programm other = (Programm) obj;
		if (ende == null) {
			if (other.ende != null)
				return false;
		} else if (!ende.equals(other.ende))
			return false;
		if (gewinn == null) {
			if (other.gewinn != null)
				return false;
		} else if (!gewinn.equals(other.gewinn))
			return false;
		if (id != other.id)
			return false;
		if (kosten == null) {
			if (other.kosten != null)
				return false;
		} else if (!kosten.equals(other.kosten))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (ue == null) {
			if (other.ue != null)
				return false;
		} else if (!ue.equals(other.ue))
			return false;
		if (vorfuehrungen == null) {
			if (other.vorfuehrungen != null)
				return false;
		} else if (!vorfuehrungen.equals(other.vorfuehrungen))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnde() {
		return ende;
	}

	public void setEnde(LocalDate ende) {
		this.ende = ende;
	}

	public BigDecimal getUe() {
		return ue;
	}

	public void setUe(BigDecimal ue) {
		this.ue = ue;
	}

	public BigDecimal getGewinn() {
		return gewinn;
	}

	public void setGewinn(BigDecimal gewinn) {
		this.gewinn = gewinn;
	}

	public BigDecimal getKosten() {
		return kosten;
	}

	public void setKosten(BigDecimal kosten) {
		this.kosten = kosten;
	}

	public ArrayList<Vorfuehrung> getVorfuehrungen() {
		return vorfuehrungen;
	}

	public void setVorfuehrungen(ArrayList<Vorfuehrung> vorfuehrungen) {
		this.vorfuehrungen = vorfuehrungen;
	}

	@Override
	public final String toString() {
		return "Programm [id=" + id + ", start=" + start  + ", ue=" + ue + ", gewinn=" + gewinn
				+ ", kosten=" + kosten + ", vorfuehrungen=" + vorfuehrungen + "]";
	}
	//+ ", ende=" + ende

}