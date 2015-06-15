package be.vdab.servlets.wijnen;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/wijnen/beslissing.htm")
public class BeslissingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/wijnen/beslissing.jsp";
	private static final String REDIRECT = "%s/index.htm";

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("aantal", request.getParameter("aantal"));
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String knopWaarde = request.getParameter("toevoegknop");
		long wijnid = Long.parseLong(request.getParameter("wijnid"));
		int aantal = Integer.parseInt(request.getParameter("aantal"));
		if (knopWaarde == null) {
			request.setAttribute("id", wijnid);
			request.setAttribute("aantal", aantal);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
		HttpSession session = request.getSession();
		if (session != null) {
			@SuppressWarnings("unchecked")
			Map<Long, Integer> wijnIdsEnAantalInMandje = (Map<Long, Integer>) session
					.getAttribute("mandje");
			if (knopWaarde.equals("Aantal toevoegen")) {
				wijnIdsEnAantalInMandje.put(wijnid,
						wijnIdsEnAantalInMandje.get(wijnid) + aantal);
			} else {
				wijnIdsEnAantalInMandje.put(wijnid, aantal);
			}
			session.setAttribute("mandje", wijnIdsEnAantalInMandje);
		}
		response.sendRedirect(response.encodeRedirectURL(String.format(
				REDIRECT, request.getContextPath())));
	}

}
