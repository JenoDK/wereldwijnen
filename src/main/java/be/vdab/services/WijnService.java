package be.vdab.services;

import java.util.List;

import be.vdab.dao.WijnDAO;
import be.vdab.entities.Wijn;

public class WijnService {
	private final WijnDAO wijnDAO = new WijnDAO();

	public Wijn read(long id) {
		return wijnDAO.read(id);
	}

	public List<Wijn> findAllBySoortId(long soortid) {
		return wijnDAO.findAllBySoortId(soortid);
	}
	
}
