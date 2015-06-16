package be.vdab.dao;

import javax.persistence.LockModeType;

import be.vdab.entities.Bestelbon;
import be.vdab.entities.Wijn;

public class BestelbonDAO extends AbstractDAO {

	public void create(Bestelbon bestelbon) {
		getEntityManager().persist(bestelbon);
	}

	//staat hier om dan in bestbonservice alles in 1 commit te doen.
	public Wijn readWithLock(long id) {
		return getEntityManager().find(Wijn.class, id,
				LockModeType.PESSIMISTIC_WRITE);
	}

}
