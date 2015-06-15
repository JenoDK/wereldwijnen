package be.vdab.services;

import java.util.Iterator;
import java.util.Set;

import be.vdab.dao.BestelbonDAO;
import be.vdab.entities.Bestelbon;
import be.vdab.valueobjects.BestelbonLijn;

public class BestelbonService {
	private final BestelbonDAO bestelbonDAO = new BestelbonDAO();

	public void create(Bestelbon bestelbon, Set<BestelbonLijn> bestelbonLijnen) {
		bestelbonDAO.beginTransaction();
		Iterator<BestelbonLijn> itr = bestelbonLijnen.iterator();
		while(itr.hasNext()){
			BestelbonLijn bestelbonLijn = itr.next();
            bestelbon.addBestelbonLijn(bestelbonLijn);
            long wijnid = bestelbonLijn.getWijn().getId();
            int aantal = bestelbonLijn.getAantal();
            bestelbonDAO.verhoogInBestelling(aantal, wijnid);
        }
		bestelbonDAO.create(bestelbon);
		bestelbonDAO.commit();
	}
}
