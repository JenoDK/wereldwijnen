package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "landen")
public class Land implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	private String naam;

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Land)) {
			return false;
		}
		return ((Land) obj).naam.equalsIgnoreCase(this.naam);
	}

	@Override
	public int hashCode() {
		return naam.toUpperCase().hashCode();
	}

	@Override
	public String toString() {
		return naam;
	}
	
	
}
