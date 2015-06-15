package be.vdab.servlets.wijnen;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Wijn;
import be.vdab.services.WijnService;

@WebServlet("/wijnen/mandje.htm")
public class WijnMandje extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/wijnen/mandje.jsp";
	private final transient WijnService wijnService = new WijnService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null) {
			@SuppressWarnings("unchecked")
			Map<Long, Integer> mandje = (Map<Long, Integer>) session
					.getAttribute("mandje");
			if (mandje != null) {
				List<Wijn> wijnenInMandje = new ArrayList<>();
				BigDecimal totaal = new BigDecimal(0);
				for (long id : mandje.keySet()) {
					Wijn a = wijnService.read(id);
					a.setAantal(mandje.get(id));
					totaal = totaal.add(a.getTotaal());
					wijnenInMandje.add(a);
				}
				request.setAttribute("mandjeTotaal", totaal);
				request.setAttribute("wijnenInMandje", wijnenInMandje);
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("verwijderknop") != null) {
			if (request.getParameterValues("id") != null) {
				HttpSession session = request.getSession();
				@SuppressWarnings("unchecked")
				Map<Long, Integer> mandje = (Map<Long, Integer>) session
						.getAttribute("mandje");
				for (String nummerAlsString : request.getParameterValues("id")) {
					long id = Long.parseLong(nummerAlsString);
					mandje.remove(id);
				}
				session.setAttribute("mandje", mandje);
			}
		}
		response.sendRedirect(response.encodeRedirectURL(request
				.getRequestURI()));
	}

}
