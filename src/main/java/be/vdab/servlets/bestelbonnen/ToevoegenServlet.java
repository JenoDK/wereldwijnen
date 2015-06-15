package be.vdab.servlets.bestelbonnen;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Bestelbon;
import be.vdab.entities.Wijn;
import be.vdab.services.BestelbonService;
import be.vdab.services.WijnService;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.BestelbonLijn;

@WebServlet("/bestelbonnen/toevoegen.htm")
public class ToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/wijnen/mandje.jsp";
	private static final String REDIRECT_URL = "%s/bestelbonnen/succes.htm?id=%d";
	private final transient WijnService wijnService = new WijnService();
	private final transient BestelbonService bestelbonService = new BestelbonService();

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String naam = request.getParameter("naam");
		if (!Bestelbon.isStringValid(naam)) {
			fouten.put("naam", "verplicht");
		}
		String straat = request.getParameter("straat");
		if (!Bestelbon.isStringValid(straat)) {
			fouten.put("straat", "verplicht");
		}
		String huisnummer = request.getParameter("huisnummer");
		if (!Bestelbon.isStringValid(huisnummer)) {
			fouten.put("huisnummer", "verplicht");
		}
		String postcode = request.getParameter("postcode");
		if (!Bestelbon.isStringValid(postcode)) {
			fouten.put("postcode", "verplicht");
		}
		String gemeente = request.getParameter("gemeente");
		if (!Bestelbon.isStringValid(gemeente)) {
			fouten.put("gemeente", "verplicht");
		}
		String bestelwijze = request.getParameter("bestelwijze");
		if (bestelwijze == null) {
			fouten.put("bestelwijze", "verplicht");
		}
		int bestelwijzeInt;
		if (bestelwijze.equals("AFHALEN")) {
			bestelwijzeInt = 0;
		} else {
			bestelwijzeInt = 1;
		}
		if (fouten.isEmpty()) {
			Date date = new Date();
			Adres adres = new Adres(straat, huisnummer, postcode, gemeente);
			Bestelbon bestelbon = new Bestelbon(date, naam, adres,
					bestelwijzeInt);
			HttpSession session = request.getSession();
			Set<BestelbonLijn> bestelbonLijnen = new LinkedHashSet<>();
			if (session != null) {
				@SuppressWarnings("unchecked")
				Map<Long, Integer> mandje = (Map<Long, Integer>) session
						.getAttribute("mandje");
				if (mandje != null) {
					for (Map.Entry<Long, Integer> entry : mandje.entrySet()) {
						Wijn a = wijnService.read(entry.getKey());
						int aantal = (entry.getValue());
						BestelbonLijn bestelbonLijn = new BestelbonLijn(a,
								aantal);
						bestelbonLijnen.add(bestelbonLijn);
					}
				}
			}
			bestelbonService.create(bestelbon, bestelbonLijnen);
			response.sendRedirect(response.encodeRedirectURL(String.format(
					REDIRECT_URL, request.getContextPath(), bestelbon.getId())));

		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
