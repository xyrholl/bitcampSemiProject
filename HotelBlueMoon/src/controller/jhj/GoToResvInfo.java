package controller.jhj;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gotoRI")
public class GoToResvInfo extends HttpServlet{
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException
	    {
	    	System.out.println("hi");
	        req.setCharacterEncoding("utf-8");
	        String checkin = URLEncoder.encode(req.getParameter("checkin"), "UTF-8");
	        String checkout = URLEncoder.encode(req.getParameter("checkout"), "UTF-8");
	        String guest = URLEncoder.encode(req.getParameter("guest"), "UTF-8");
	        String hotelSeq = URLEncoder.encode(req.getParameter("hotelSeq"), "UTF-8");
	        
	        System.out.println(checkin);
	        System.out.println(checkout);
	        System.out.println(guest);
	        System.out.println(hotelSeq);
	        
		/*
		 * resp.sendRedirect(req.getContextPath() + "/JSP/hotelList.jsp?checkin=" +
		 * checkin + "&checkout=" + checkout + "&guest=" + guest + "&hotelSeq=" +
		 * hotelSeq);
		 */
	    }
}
