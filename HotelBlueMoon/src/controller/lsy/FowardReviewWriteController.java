package controller.lsy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ResvDTO;
import singleton.Singleton;

@WebServlet("/fowardreviewwrite")
public class FowardReviewWriteController extends HttpServlet {

	public void forward(String link, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(link);
		dispatch.forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String seq = "1";
				//req.getParameter("seq");
		Singleton s = Singleton.getInstance();
		ResvDTO dto = s.reviewService.ResvSelectOne(Integer.parseInt(seq));
		req.setAttribute("resvDTO", dto);
		forward("/JSP/reviewwritedetail.jsp", req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
