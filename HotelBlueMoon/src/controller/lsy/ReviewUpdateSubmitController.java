package controller.lsy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ReviewDTO;
import singleton.Singleton;

@WebServlet("/updatesubmit")
public class ReviewUpdateSubmitController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String seq = req.getParameter("seq");
		String rating = req.getParameter("rating");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		Singleton s = Singleton.getInstance();
		boolean isTrue = s.reviewService.updateWritePage(seq, rating, title, content);
		if (isTrue) {
			resp.getWriter().print("servsuccess");
		} else {
			resp.getWriter().print("servfail");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
