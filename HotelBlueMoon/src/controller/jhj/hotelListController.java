package controller.jhj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.HotelDTO;
import singleton.Singleton;

@WebServlet("/hotelList")
public class hotelListController extends HttpServlet{
	Singleton s;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  resp.setContentType("text/html;charset=UTF-8"); 
		  System.out.println(req.getParameter("checkin"));
		  System.out.println(req.getParameter("checkout"));
		  System.out.println(req.getParameter("guest"));
		  System.out.println(req.getParameter("area"));
		  
			  String json = s.getInstance().createJson(req.getParameter("guest"), req.getParameter("area"),
						req.getParameter("checkin"), req.getParameter("checkout"));
		 
		
		
		req.setAttribute("json", json);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/JSP/ajaxData/rs.jsp");
		dispatcher.forward(req, resp);
	
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doGet(req, resp);
	}
	
}
