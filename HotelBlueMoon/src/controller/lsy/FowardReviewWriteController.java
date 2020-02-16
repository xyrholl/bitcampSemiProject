package controller.lsy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ResvDTO;
import dto.ReviewDTO;
import singleton.Singleton;
import sun.reflect.generics.visitor.Reifier;

@WebServlet("/fowardreviewwrite")
public class FowardReviewWriteController extends HttpServlet {

	public void forward(String link, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(link);
		dispatch.forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String seq = req.getParameter("seq");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String rating = req.getParameter("rating");

		Singleton s = Singleton.getInstance();
		ResvDTO ResvDto = s.reviewService.ResvSelectOne(Integer.parseInt(seq));
		ReviewDTO reviewDto = new ReviewDTO();
		req.setAttribute("resvDTO", ResvDto);

		forward("/JSP/reviewwritedetail.jsp", req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
