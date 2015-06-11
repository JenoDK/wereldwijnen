package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.entities.Bestelbon;
import be.vdab.entities.Wijn;

@Embeddable
public class BestelbonLijn implements Serializable {

	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "wijnid")
	private Wijn wijn;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "bonid")
	private Bestelbon bestelbon;
	private int aantal;

	public BestelbonLijn(Wijn wijn, Bestelbon bestelbon, int aantal) {
		this.wijn = wijn;
		this.bestelbon = bestelbon;
		this.aantal = aantal;
	}

	public BestelbonLijn() {
	}

	public Wijn getWijn() {
		return wijn;
	}

	public Bestelbon getBestelbon() {
		return bestelbon;
	}

	public int getAantal() {
		return aantal;
	}

	public void setWijn(Wijn wijn) {
		this.wijn = wijn;
	}

	public void setBestelbon(Bestelbon bestelbon) {
		this.bestelbon = bestelbon;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aantal;
		result = prime * result
				+ ((bestelbon == null) ? 0 : bestelbon.hashCode());
		result = prime * result + ((wijn == null) ? 0 : wijn.hashCode());
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
		BestelbonLijn other = (BestelbonLijn) obj;
		if (aantal != other.aantal)
			return false;
		if (bestelbon == null) {
			if (other.bestelbon != null)
				return false;
		} else if (!bestelbon.equals(other.bestelbon))
			return false;
		if (wijn == null) {
			if (other.wijn != null)
				return false;
		} else if (!wijn.equals(other.wijn))
			return false;
		return true;
	}

}
