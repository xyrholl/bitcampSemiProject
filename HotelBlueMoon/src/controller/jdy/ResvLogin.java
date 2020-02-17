package controller.jdy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.HotelDTO;
import dto.ResvDTO;
import dto.RoomDTO;
import singleton.Singleton;
@WebServlet("/resvlogin")
public class ResvLogin extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
		System.out.println("예약하다가 로그인하러온 Servlet");
		
		String checkin = URLEncoder.encode(req.getParameter("checkin"), "UTF-8");
		String checkout = URLEncoder.encode(req.getParameter("checkout"), "UTF-8");
		String sguest = URLEncoder.encode(req.getParameter("guest"), "UTF-8");
		String shotelSeq = URLEncoder.encode(req.getParameter("hotelSeq"), "UTF-8");
		int hotelSeq = Integer.parseInt(shotelSeq);
		int current_guest = Integer.parseInt(sguest);
		req.setAttribute("checkin", checkin);
		req.setAttribute("checkout", checkout);
		req.setAttribute("current_guest", current_guest);
		req.setAttribute("hotelseq", hotelSeq);
		
		resp.sendRedirect(req.getContextPath()+"/JSP/login.jsp?hotelSeq="+hotelSeq
				+"&checkin="+checkin
				+"&checkout="+checkout
				+"&geust="+current_guest);
		
		System.out.println(checkin);
		System.out.println(checkout);
		System.out.println(sguest);
		System.out.println(hotelSeq);	

	}
	
}
