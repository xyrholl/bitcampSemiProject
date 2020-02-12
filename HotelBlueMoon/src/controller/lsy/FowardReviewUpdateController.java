package controller.lsy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ReviewDTO;
import singleton.Singleton;

@WebServlet("/updatereviewfoward")
public class FowardReviewUpdateController extends HttpServlet {

	public void forward(String link, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(link);
		dispatch.forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String seq = req.getParameter("seq");
		Singleton s = Singleton.getInstance();
		ReviewDTO dto = s.reviewService.selectOne(Integer.parseInt(seq));
		req.setAttribute("reviewDTO", dto);
		System.out.println("updatereviewfoward" + seq);
		forward("/JSP/reviewupdate.jsp", req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
