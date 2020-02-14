package controller.lsy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.ResvDTO;
import singleton.Singleton;

@WebServlet("/fowardmyresvhistory")
public class FowardMyResvHistoryController extends HttpServlet {

	public void forward(String link, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(link);
		dispatch.forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Singleton s = Singleton.getInstance();
		HttpSession session = req.getSession(false);
		String selectIndex = req.getParameter("selectIndex");
		String hotelName = req.getParameter("hotelName");
		String place = req.getParameter("place");
		String loginId = (String) session.getAttribute("loginId");
		List<ResvDTO> list = new ArrayList<ResvDTO>();

		if (selectIndex == null) {
			list = s.reviewService.resvList(loginId);
			req.setAttribute("resvList", list);
		} else if (Integer.parseInt(selectIndex) == 1 || Integer.parseInt(selectIndex) == 2) {
			list = s.reviewService.resvList(loginId, Integer.parseInt(selectIndex), hotelName, place);
			req.setAttribute("resvList", list);
		}

		forward("/JSP/reviewwrite.jsp", req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
