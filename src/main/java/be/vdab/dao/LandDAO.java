package be.vdab.dao;

import java.util.List;

import be.vdab.entities.Land;

public class LandDAO extends AbstractDAO {
	public Land read(long id) {
		return getEntityManager().find(Land.class, id);
	}

	public List<Land> findAll() {
		return getEntityManager()
				.createNamedQuery("Land.findAll", Land.class).getResultList();
	}
}
