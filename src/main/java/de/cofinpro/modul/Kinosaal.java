package de.cofinpro.modul;

import java.io.Serializable;
import javax.persistence.*;

@SuppressWarnings("serial")
//@Entity
@Table(name = "Kinosaal")

public class Kinosaal implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(name = "anzSitzeL")
	private int anzSitzeL;
	@Column(name = "anzSitzeP")
	private int anzSitzeP;
	@Column(name = "dreiD")
	private boolean dreiD;
	// @OneToMany(mappedBy = "kinosaal", fetch = FetchType.LAZY)
	// @JoinColumn(name = "fk_vorfuehrung")
	//private List<Vorfuehrung> vorfuehrung;

	public Kinosaal() {
		// TODO Auto-generated constructor stub
	}

	public Kinosaal(int id, int anzSitzeL, int anzSitzeP, boolean dreiD) {
		super();
		this.id = id;
		this.anzSitzeL = anzSitzeL;
		this.anzSitzeP = anzSitzeP;
		this.dreiD = dreiD;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnzSitzeL() {
		return anzSitzeL;
	}

	public void setAnzSitzeL(int anzSitzeL) {
		this.anzSitzeL = anzSitzeL;
	}

	public int getAnzSitzeP() {
		return anzSitzeP;
	}

	public void setAnzSitzeP(int anzSitzeP) {
		this.anzSitzeP = anzSitzeP;
	}

	public boolean isDreiD() {
		return dreiD;
	}

	public void setDreiD(boolean dreiD) {
		this.dreiD = dreiD;
	}

	@Override
	public final String toString() {
		return "Kinosaal [id=" + id + ", anzSitzeL=" + anzSitzeL + ", anzSitzeP=" + anzSitzeP + ", dreiD=" + dreiD
				+ "]";
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kinosaal other = (Kinosaal) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
