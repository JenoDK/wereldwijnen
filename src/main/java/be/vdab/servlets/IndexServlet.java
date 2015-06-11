package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.LandService;
import be.vdab.services.SoortService;

@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/index.jsp";
	private final transient LandService landService = new LandService();
	private final transient SoortService soortService = new SoortService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("landid");
		if (id != null && ( id.isEmpty())){
			long landid = Long.parseLong(id);
			request.setAttribute("soorten", soortService.findAllByLandId(landid));
		}
		request.setAttribute("landen", landService.findAll());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
