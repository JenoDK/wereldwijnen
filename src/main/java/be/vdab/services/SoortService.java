package be.vdab.services;

import java.util.List;

import be.vdab.dao.SoortDAO;
import be.vdab.entities.Soort;

public class SoortService {
	private final SoortDAO soortDAO = new SoortDAO();
	
	public List<Soort> findAllByLandId(long landid) {
		return soortDAO.findAllByLandId(landid);
	}
}
