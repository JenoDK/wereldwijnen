package be.vdab.servlets.bestelbonnen;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bestelbonnen/succes.htm")
public class SuccesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bestelbonnen/succes.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getQueryString() != null) {
			request.setAttribute("bestelbonId", Long.parseLong(request.getParameter("id")));
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
