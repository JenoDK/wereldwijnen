package be.vdab.servlets.wijnen;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.services.WijnService;

@WebServlet("/wijnen/beslissing.htm")
public class BeslissingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/wijnen/beslissing.jsp";
	private static final String TOEVOEGENVIEW = "/WEB-INF/JSP/wijnen/toevoegen.jsp";
	private static final String REDIRECT = "%s/index.htm";
	private final transient WijnService wijnService = new WijnService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// PARAMETERS MEEGEVEN AAN BESLISSING JSP
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("aantal", request.getParameter("aantal"));
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String knopWaarde = request.getParameter("toevoegknop");
		// PARAMETERS TERUG OPHALEN
		long wijnid = Long.parseLong(request.getParameter("wijnid"));
		int aantal = Integer.parseInt(request.getParameter("aantal"));
		if (knopWaarde == null) {
			request.setAttribute("id", wijnid);
			request.setAttribute("aantal", aantal);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
		if (request.getParameter("id") != null) {
			if (request.getParameter("aantal") != null
					&& !request.getParameter("aantal").isEmpty()) {
				if (aantal >= 1) {
					HttpSession session = request.getSession();
					if (session != null) {
						@SuppressWarnings("unchecked")
						Map<Long, Integer> wijnIdsEnAantalInMandje = (Map<Long, Integer>) session
								.getAttribute("mandje");
						// CHECK WELKE KNOP INGEDRUKT IS EN AANTAL TOEVOEGEN/VERVANGEN AAN
						// MANDJE WHERE WIJNID=WIJNID
						if (knopWaarde.equals("Aantal toevoegen")) {
							wijnIdsEnAantalInMandje.put(wijnid,
									wijnIdsEnAantalInMandje.get(wijnid) + aantal);
						} else {
							wijnIdsEnAantalInMandje.put(wijnid, aantal);
						}
						session.setAttribute("mandje", wijnIdsEnAantalInMandje);
					}
				}else {
					request.setAttribute("fout",
							"Aantal moet een positief geheel getal zijn");
					request.setAttribute("wijn", wijnService.read(wijnid));
					request.getRequestDispatcher(TOEVOEGENVIEW).forward(request, response);
				}
			}else {
				request.setAttribute("fout", "Vul iets in");
				request.setAttribute("wijn", wijnService.read(wijnid));
				request.getRequestDispatcher(TOEVOEGENVIEW).forward(request, response);
			}
		}
		response.sendRedirect(response.encodeRedirectURL(String.format(
				REDIRECT, request.getContextPath())));
	}

}
