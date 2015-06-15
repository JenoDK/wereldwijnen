package be.vdab.dao;

import be.vdab.entities.Bestelbon;

public class BestelbonDAO extends AbstractDAO {

	public void create(Bestelbon bestelbon) {
		getEntityManager().persist(bestelbon);
	}

	public void verhoogInBestelling(int factor, long wijnid) {
		getEntityManager().createNamedQuery("Wijn.verhoogInBestelling")
				.setParameter("factor", factor).setParameter("wijnid", wijnid)
				.executeUpdate();
	}

}
