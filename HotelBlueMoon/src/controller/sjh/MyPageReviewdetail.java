package controller.sjh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import singleton.Singleton;
@WebServlet("/mypagereviewdetail")
public class MyPageReviewdetail extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processFunc(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processFunc(req, resp);
	}
	
	public void processFunc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int resvSeq = Integer.parseInt(req.getParameter("seq"));
		
		Singleton s = Singleton.getInstance();
		// resvseq -> reviewseq
		int reviewSeq = s.myPageService.getMyReviewSeq(resvSeq); 
		
		resp.sendRedirect(req.getContextPath()+"/reviewdetailfoward?seq="+reviewSeq);
		
	}
}
