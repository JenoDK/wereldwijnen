package be.vdab.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import be.vdab.enums.Bestelwijze;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.BestelbonLijn;

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
	private int bestelwijze; //GEEN ENUM VARIABLE HIER OMDAT DATABASE EEN INT VRAAGT IN BESTELWIJZE COLUMN, 
							 //APP GEBRUIKT Bestelwijze ENUM WEL IN DE ToevoegenServlet WAAR HET DAN HET INT ATTRIBUUT VRAAGT VAN DE BIJBEHORENDE ENUM
	@ElementCollection
	@CollectionTable(name = "bestelbonlijnen", joinColumns = @JoinColumn(name = "bonid"))
	private Set<BestelbonLijn> bestelbonLijnen;

	public Bestelbon(Date besteld, String naam, Adres adres, int bestelwijze) {
		this.besteld = besteld;
		this.naam = naam;
		this.adres = adres;
		this.bestelwijze = bestelwijze;
		bestelbonLijnen = new LinkedHashSet<>();
	}
	
	public Bestelbon() {
	}

	public Set<BestelbonLijn> getBestelbonLijnen() {
		return Collections.unmodifiableSet(bestelbonLijnen);
	}

	public void addBestelbonLijn(BestelbonLijn bestelbonLijn) {
		bestelbonLijnen.add(bestelbonLijn);
	}

	public void removeBestelbonLijn(BestelbonLijn bestelbonLijn) {
		bestelbonLijnen.remove(bestelbonLijn);
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

	public void setBestelwijze(Bestelwijze bestelwijze) {
		this.bestelwijze = bestelwijze.getBestelWijzeInt();
	}

	public static boolean isStringValid(String string) {
		return string != null && !string.isEmpty();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adres == null) ? 0 : adres.hashCode());
		result = prime * result
				+ ((bestelbonLijnen == null) ? 0 : bestelbonLijnen.hashCode());
		result = prime * result + ((besteld == null) ? 0 : besteld.hashCode());
		result = prime * result + bestelwijze;
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
		if (bestelbonLijnen == null) {
			if (other.bestelbonLijnen != null)
				return false;
		} else if (!bestelbonLijnen.equals(other.bestelbonLijnen))
			return false;
		if (besteld == null) {
			if (other.besteld != null)
				return false;
		} else if (!besteld.equals(other.besteld))
			return false;
		if (bestelwijze != other.bestelwijze)
			return false;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}
	
	private String bestelwijzeToString(){
		if (this.bestelwijze == 0){
			return "Afhalen";
		}else {
			return "Leveren";
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(500);
		sb.append("Bestelbon id= " + id + ", ");
		sb.append("besteld= " + besteld + ", ");
		sb.append("naam= "+ naam + ", ");
		sb.append("adres= " + adres + ", ");
		sb.append("bestelwijze=" + bestelwijzeToString());
		for (BestelbonLijn a : bestelbonLijnen){
			sb.append(", Bestelbonlijn= " + a);
		}
		return sb.toString();
	}
	
}