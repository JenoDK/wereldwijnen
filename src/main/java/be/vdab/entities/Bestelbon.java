package be.vdab.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import be.vdab.valueobjects.Adres;

@Entity
@Table(name = "bestelbonnen")
public class Bestelbon implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	private Date besteld;
	private String naam;
	@Embedded
	private Adres adres;
	private int bestelwijze;

	public Bestelbon(Date besteld, String naam, Adres adres, int bestelwijze) {
		this.besteld = besteld;
		this.naam = naam;
		this.adres = adres;
		this.bestelwijze = bestelwijze;
	}

	public Bestelbon() {
	}

	public long getId() {
		return id;
	}

	public Date getBesteld() {
		return besteld;
	}

	public String getNaam() {
		return naam;
	}

	public Adres getAdres() {
		return adres;
	}

	public int getBestelwijze() {
		return bestelwijze;
	}

	public void setBesteld(Date besteld) {
		this.besteld = besteld;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public void setBestelwijze(int bestelwijze) {
		this.bestelwijze = bestelwijze;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adres == null) ? 0 : adres.hashCode());
		result = prime * result + ((besteld == null) ? 0 : besteld.hashCode());
		result = prime * result + bestelwijze;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
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
		Bestelbon other = (Bestelbon) obj;
		if (adres == null) {
			if (other.adres != null)
				return false;
		} else if (!adres.equals(other.adres))
			return false;
		if (besteld == null) {
			if (other.besteld != null)
				return false;
		} else if (!besteld.equals(other.besteld))
			return false;
		if (bestelwijze != other.bestelwijze)
			return false;
		if (id != other.id)
			return false;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}
	
	

}