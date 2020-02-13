package controller.sjh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/mypageidcheck")
public class MyPageIdCheck extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		String loginId = req.getParameter("loginId");
		
		System.out.println("JAVA: "+loginId);
		if(loginId == null || loginId.equals("null") || loginId.equals("")) {
			resp.getWriter().print("1");
		}else {
			resp.getWriter().print("2");
		}
			
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
