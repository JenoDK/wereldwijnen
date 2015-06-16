package be.vdab.services;

import java.util.Iterator;

import be.vdab.dao.BestelbonDAO;
import be.vdab.entities.Bestelbon;
import be.vdab.valueobjects.BestelbonLijn;

public class BestelbonService {
	private final BestelbonDAO bestelbonDAO = new BestelbonDAO();

	public void create(Bestelbon bestelbon) {
		bestelbonDAO.beginTransaction();
		Iterator<BestelbonLijn> itr = bestelbon.getBestelbonLijnen().iterator();
		while(itr.hasNext()){
			BestelbonLijn bestelbonLijn = itr.next();
            long wijnid = bestelbonLijn.getWijn().getId();
            int aantal = bestelbonLijn.getAantal();
            bestelbonDAO.readWithLock(wijnid).verhoogInBestelling(aantal);
        }
		bestelbonDAO.create(bestelbon);
		bestelbonDAO.commit();
	}
}
