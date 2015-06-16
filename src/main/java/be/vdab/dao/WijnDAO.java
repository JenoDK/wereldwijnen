package be.vdab.dao;

import java.util.List;


import be.vdab.entities.Wijn;

public class WijnDAO extends AbstractDAO {

	public Wijn read(long id) {
		return getEntityManager().find(Wijn.class, id);
	}
	
	public List<Wijn> findAllBySoortId(long soortid) {
		return getEntityManager()
				.createNamedQuery("Wijn.findBySoortid", Wijn.class)
				.setParameter("soortid", soortid).getResultList();
	}

}
