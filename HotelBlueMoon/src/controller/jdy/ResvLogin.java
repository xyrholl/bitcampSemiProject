package controller.jdy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ResvDTO;
@WebServlet("/resvlogin")
public class ResvLogin extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("resvlogin 서블릿");
		ResvDTO resv = (ResvDTO)req.getAttribute("resv");
		
		System.out.println("resvloginJAVA: "+resv.toString());
		if( resv.getId() == null ||( resv.getId()).equals("null") ||( resv.getId()).equals("") ) {
			resp.sendRedirect(req.getContextPath() + "/JSP/resvLogin.jsp");			
		}else {
    	   req.setAttribute("resv", resv);
    	   forward("JSP/resvcheck.jsp", req, resp);
		}
	}
	
	public void forward(String link, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		RequestDispatcher dispatch = req.getRequestDispatcher(link);
		dispatch.forward(req, resp);
	}
}
