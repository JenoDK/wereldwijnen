package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "wijnen")
public class Wijn implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "soortid")
	private Soort soort;
	private int jaar;
	private int beoordeling;
	private BigDecimal prijs;
	private int inBestelling;
	@Transient
	private int aantal;

	public Wijn(Soort soort, int jaar, int beoordeling, BigDecimal prijs,
			int inBestelling) {
		this.soort = soort;
		this.jaar = jaar;
		this.beoordeling = beoordeling;
		this.prijs = prijs;
		this.inBestelling = inBestelling;
	}

	public int getAantal() {
		return aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public Wijn() {
	}

	public void setBeoordeling(int beoordeling) {
		this.beoordeling = beoordeling;
	}

	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}

	public void setInBestelling(int inBestelling) {
		this.inBestelling = inBestelling;
	}

	public long getId() {
		return id;
	}

	public Soort getSoort() {
		return soort;
	}

	public int getJaar() {
		return jaar;
	}

	public int getBeoordeling() {
		return beoordeling;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public int getInBestelling() {
		return inBestelling;
	}

	public void verhoogInBestelling(int aantal) {
		inBestelling += aantal;
	}

	public BigDecimal getTotaal() {
		return prijs.multiply(new BigDecimal(aantal));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aantal;
		result = prime * result + beoordeling;
		result = prime * result + inBestelling;
		result = prime * result + jaar;
		result = prime * result + ((prijs == null) ? 0 : prijs.hashCode());
		result = prime * result + ((soort == null) ? 0 : soort.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wijn other = (Wijn) obj;
		if (aantal != other.aantal)
			return false;
		if (beoordeling != other.beoordeling)
			return false;
		if (inBestelling != other.inBestelling)
			return false;
		if (jaar != other.jaar)
			return false;
		if (prijs == null) {
			if (other.prijs != null)
				return false;
		} else if (!prijs.equals(other.prijs))
			return false;
		if (soort == null) {
			if (other.soort != null)
				return false;
		} else if (!soort.equals(other.soort))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		sb.append("Soort: " + soort + ", ");
		sb.append("jaar: " + jaar + ", ");
		sb.append("beoordeling: " + beoordeling + ", ");
		sb.append("prijs: €" + prijs + ", ");
		sb.append("aantal in bestelling: " + inBestelling);
		return sb.toString();
	}
	
	

}
