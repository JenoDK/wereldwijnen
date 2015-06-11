package be.vdab.dao;

import java.util.List;

import be.vdab.entities.Soort;

public class SoortDAO extends AbstractDAO {

	public List<Soort> findAllByLandId(long landid) {
		return getEntityManager()
				.createNamedQuery("Soort.findByLandid", Soort.class)
				.setParameter("landid", landid).getResultList();
	}
}
