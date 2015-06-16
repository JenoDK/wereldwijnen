package be.vdab.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.services.LandService;
import be.vdab.services.SoortService;
import be.vdab.services.WijnService;

@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/index.jsp";
	private final transient LandService landService = new LandService();
	private final transient SoortService soortService = new SoortService();
	private final transient WijnService wijnService = new WijnService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String landid = request.getParameter("landid");
		//IF LANDID EXISTS SEND SOORTEN ATTRIBUTE TO JSP
		if (landid != null && (!landid.isEmpty())) {
			long landidLong = Long.parseLong(landid);
			request.setAttribute("soorten",
					soortService.findAllByLandId(landidLong));
		}
		String soortid = request.getParameter("soortid");
		//IF SOORTID EXISTS SEND WIJNEN ATTRIBUTE TO JSP
		if (soortid != null && (!soortid.isEmpty())) {
			long soortidLong = Long.parseLong(soortid);
			request.setAttribute("wijnen",
					wijnService.findAllBySoortId(soortidLong));
		}
		HttpSession session = request.getSession(false);
		//SEND MANDJE TO JSP, CHECK ON EMPTY OR NOT WILL HAPPEN IN JSP
		if (session != null) {
			@SuppressWarnings("unchecked")
			Map<Long, Integer> wijnIdsEnAantalInMandje = (Map<Long, Integer>) session
					.getAttribute("mandje");
			request.setAttribute("wijnenInMandje", wijnIdsEnAantalInMandje);
		}
		request.setAttribute("landen", landService.findAll());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
