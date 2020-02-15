package controller.lsy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import singleton.Singleton;

@WebServlet("/reviewdelete")
public class ReviewDeleteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");

		Singleton s = Singleton.getInstance();
		boolean isS = s.reviewService.deleteOneReview(Integer.parseInt(seq));

		if (isS) {
			resp.sendRedirect(req.getContextPath() + "/reviewfoward");
		} else {
			resp.sendRedirect(req.getContextPath() + "/reviewdetailfoward?seq=" + seq);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
