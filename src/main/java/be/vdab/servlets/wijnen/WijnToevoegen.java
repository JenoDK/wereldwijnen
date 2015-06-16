package be.vdab.servlets.wijnen;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.services.WijnService;

@WebServlet("/wijnen/toevoegen.htm")
public class WijnToevoegen extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/wijnen/toevoegen.jsp";
	private static final String REDIRECT_SUCCES = "%s/index.htm";
	private static final String REDIRECT_IF_WIJN_EXISTS = "%s/wijnen/beslissing.htm?id=%d&aantal=%d";
	private boolean REDIRECT;
	private final transient WijnService wijnService = new WijnService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String wijnid = request.getParameter("wijnid");
		if (wijnid != null && (!wijnid.isEmpty())) {
			long wijnidLong = Long.parseLong(wijnid);
			request.setAttribute("wijn", wijnService.read(wijnidLong));
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//GET PARAMETERS HERE SO APP IS ABLE TO SEND THEM WITH BESLISSING REDIRECT IF NECESSARY
		int aantal = Integer.parseInt(request.getParameter("aantal"));
		long wijnid = Long.parseLong(request.getParameter("id"));
		if (request.getParameter("id") != null) {
			if (request.getParameter("aantal") != null && !request.getParameter("aantal").isEmpty()) {
				if (aantal >= 1) {
					HttpSession session = request.getSession();
					if (session != null) {
						@SuppressWarnings("unchecked")
						Map<Long, Integer> wijnIdsEnAantalInMandje = (Map<Long, Integer>) session
								.getAttribute("mandje");
						if (wijnIdsEnAantalInMandje == null) {
							wijnIdsEnAantalInMandje = new HashMap<Long, Integer>();
						}
						//CHECK IF WIJN ALLREADY EXISTS IN MANDJE AND CHANGE BOOLEAN SO APP KNOWS WHICH REDIRECT TO TAKE (naar beslissingservlet of niet?)
						if (wijnIdsEnAantalInMandje.containsKey(wijnid)){
							REDIRECT = false;
						}else {
							wijnIdsEnAantalInMandje.put(wijnid, aantal);
							session.setAttribute("mandje", wijnIdsEnAantalInMandje);
							REDIRECT = true;
						}
					}
				} else {
					request.setAttribute("fout",
							"Aantal moet een positief geheel getal zijn");
					request.setAttribute("wijn", wijnService.read(wijnid));
					request.getRequestDispatcher(VIEW).forward(request, response);
				}
			} else {
				request.setAttribute("fout", "Vul iets in");
				request.setAttribute("wijn", wijnService.read(wijnid));
				request.getRequestDispatcher(VIEW).forward(request, response);
			}
		}
		if (REDIRECT){
			response.sendRedirect(response.encodeRedirectURL(String.format(
					REDIRECT_SUCCES, request.getContextPath())));
		}else {
			//REDIRECT NAAR BESLISSINGSERVLET MET DE PARAMETERS
			response.sendRedirect(response.encodeRedirectURL(String.format(
					REDIRECT_IF_WIJN_EXISTS, request.getContextPath(), wijnid, aantal)));
		}
	}

}
