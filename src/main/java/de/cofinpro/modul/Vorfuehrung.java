package de.cofinpro.modul;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "verfuehrung")

public class Vorfuehrung implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id = 0;
	@Column(name = "dauer")
	private int dauer;
	@Column(name = "anzKundenL")
	private int anzKundenL;
	@Column(name = "anzKundenP")
	private int anzKundenP;
	@Column(name = "startzeit")
	private LocalTime startzeit;
	@Column(name = "kosten")
	private BigDecimal kosten;
	@Column(name = "ue")
	private BigDecimal ue;
	@Column(name = "gewinn")
	private BigDecimal gewinn;
	@Column(name = "ticketPreisL")
	private BigDecimal ticketPreisL;
	@Column(name = "TicketPreisP")
	private BigDecimal ticketPreisP;

	@ManyToOne(fetch = FetchType.EAGER)
	@Column(name = "film")
	private Film film;

	@ManyToMany(mappedBy = "vorfuehrungen", fetch = FetchType.EAGER)
	@Column(name = "werbespots")
	private List<Werbespot> werbespots = new ArrayList<Werbespot>();

	@ManyToOne(fetch = FetchType.EAGER)
	@Column(name = "kinosaal")
	private Kinosaal kinosaal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_programm")
	private Programm programm;

	public Vorfuehrung() {
		// TODO Auto-generated constructor stub
	}

	private int vId = 0;

	public Vorfuehrung(final int id, final int dauer, final int anzKundenL, final int anzKundenP,
			final LocalTime startzeit, final BigDecimal kosten, final BigDecimal ue, final BigDecimal gewinn,
			final BigDecimal ticketPreisL, final BigDecimal ticketPreisP, final Film film,
			final List<Werbespot> werbespots, final Kinosaal kinosaal, final Programm programm, final int vId) {
		super();
		this.id = id;
		this.dauer = dauer;
		this.anzKundenL = anzKundenL;
		this.anzKundenP = anzKundenP;
		this.startzeit = startzeit;
		this.kosten = kosten;
		this.ue = ue;
		this.gewinn = gewinn;
		this.ticketPreisL = ticketPreisL;
		this.ticketPreisP = ticketPreisP;
		this.film = film;
		this.werbespots = werbespots;
		this.kinosaal = kinosaal;
		this.programm = programm;
		this.vId = vId;
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anzKundenL;
		result = prime * result + anzKundenP;
		result = prime * result + dauer;
		result = prime * result + ((film == null) ? 0 : film.hashCode());
		result = prime * result + ((gewinn == null) ? 0 : gewinn.hashCode());
		result = prime * result + id;
		result = prime * result + ((kinosaal == null) ? 0 : kinosaal.hashCode());
		result = prime * result + ((kosten == null) ? 0 : kosten.hashCode());
		result = prime * result + ((programm == null) ? 0 : programm.hashCode());
		result = prime * result + ((startzeit == null) ? 0 : startzeit.hashCode());
		result = prime * result + ((ticketPreisL == null) ? 0 : ticketPreisL.hashCode());
		result = prime * result + ((ticketPreisP == null) ? 0 : ticketPreisP.hashCode());
		result = prime * result + ((ue == null) ? 0 : ue.hashCode());
		result = prime * result + vId;
		result = prime * result + ((werbespots == null) ? 0 : werbespots.hashCode());
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
		Vorfuehrung other = (Vorfuehrung) obj;
		if (anzKundenL != other.anzKundenL)
			return false;
		if (anzKundenP != other.anzKundenP)
			return false;
		if (dauer != other.dauer)
			return false;
		if (film == null) {
			if (other.film != null)
				return false;
		} else if (!film.equals(other.film))
			return false;
		if (gewinn == null) {
			if (other.gewinn != null)
				return false;
		} else if (!gewinn.equals(other.gewinn))
			return false;
		if (id != other.id)
			return false;
		if (kinosaal == null) {
			if (other.kinosaal != null)
				return false;
		} else if (!kinosaal.equals(other.kinosaal))
			return false;
		if (kosten == null) {
			if (other.kosten != null)
				return false;
		} else if (!kosten.equals(other.kosten))
			return false;
		if (programm == null) {
			if (other.programm != null)
				return false;
		} else if (!programm.equals(other.programm))
			return false;
		if (startzeit == null) {
			if (other.startzeit != null)
				return false;
		} else if (!startzeit.equals(other.startzeit))
			return false;
		if (ticketPreisL == null) {
			if (other.ticketPreisL != null)
				return false;
		} else if (!ticketPreisL.equals(other.ticketPreisL))
			return false;
		if (ticketPreisP == null) {
			if (other.ticketPreisP != null)
				return false;
		} else if (!ticketPreisP.equals(other.ticketPreisP))
			return false;
		if (ue == null) {
			if (other.ue != null)
				return false;
		} else if (!ue.equals(other.ue))
			return false;
		if (vId != other.vId)
			return false;
		if (werbespots == null) {
			if (other.werbespots != null)
				return false;
		} else if (!werbespots.equals(other.werbespots))
			return false;
		return true;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final int getDauer() {
		return dauer;
	}

	public final void setDauer(int dauer) {
		this.dauer = dauer;
	}

	public final int getAnzKundenL() {
		return anzKundenL;
	}

	public final void setAnzKundenL(int anzKundenL) {
		this.anzKundenL = anzKundenL;
	}

	public final int getAnzKundenP() {
		return anzKundenP;
	}

	public final void setAnzKundenP(int anzKundenP) {
		this.anzKundenP = anzKundenP;
	}

	public final LocalTime getStartzeit() {
		return startzeit;
	}

	public final void setStartzeit(LocalTime localTime) {
		this.startzeit = localTime;
	}

	public final BigDecimal getKosten() {
		return kosten;
	}

	public final void setKosten(BigDecimal kosten) {
		this.kosten = kosten;
	}

	public final BigDecimal getUe() {
		return ue;
	}

	public final void setUe(BigDecimal ue) {
		this.ue = ue;
	}

	public final BigDecimal getGewinn() {
		return gewinn;
	}

	public final void setGewinn(BigDecimal gewinn) {
		this.gewinn = gewinn;
	}

	public final BigDecimal getTicketPreisL() {
		return ticketPreisL;
	}

	public final void setTicketPreisL(BigDecimal ticketPreisL) {
		this.ticketPreisL = ticketPreisL;
	}

	public BigDecimal getTicketPreisP() {
		return ticketPreisP;
	}

	public void setTicketPreisP(BigDecimal ticketPreisP) {
		this.ticketPreisP = ticketPreisP;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public List<Werbespot> getWerbespots() {
		return werbespots;
	}

	public void setWerbespots(List<Werbespot> werbespots) {
		this.werbespots = werbespots;
	}

	public Kinosaal getKinosaal() {
		return kinosaal;
	}

	public void setKinosaal(Kinosaal kinosaal) {
		this.kinosaal = kinosaal;
	}

	public Programm getProgramm() {
		return programm;
	}

	public void setProgramm(Programm programm) {
		this.programm = programm;
	}

	public int getvId() {
		return vId;
	}

	public void setvId(int vId) {
		this.vId = vId;
	}

	@Override
	public final String toString() {
		return "Vorfuehrung [id=" + id + ", dauer=" + dauer + ", anzKundenL=" + anzKundenL + ", anzKundenP="
				+ anzKundenP + ", startzeit=" + startzeit + ", kosten=" + kosten + ", ue=" + ue + ", gewinn=" + gewinn
				+ ", ticketPreisL=" + ticketPreisL + ", ticketPreisP=" + ticketPreisP + ", film=" + film
				+ ", werbespots=" + werbespots + ", kinosaal=" + kinosaal + " ]";
	}

}
