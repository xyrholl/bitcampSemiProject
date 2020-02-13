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

import dto.ReviewDTO;
import singleton.Singleton;

@WebServlet("/reviewfoward")
public class FowardReviewController extends HttpServlet {

	public void forward(String link, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(link);
		dispatch.forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Singleton s = Singleton.getInstance();
		String selectIndex = req.getParameter("selectIndex");
		String searchText = req.getParameter("searchText");
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();

		if (selectIndex == null) {
			list = s.reviewService.reviewPageList();
			req.setAttribute("list", list);
		} else if (Integer.parseInt(selectIndex) == 1 || Integer.parseInt(selectIndex) == 2
				|| Integer.parseInt(selectIndex) == 3 || Integer.parseInt(selectIndex) == 4) {
			list = s.reviewService.reviewPageList(Integer.parseInt(selectIndex), searchText);
			req.setAttribute("list", list);
		}
		forward("/JSP/review.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
