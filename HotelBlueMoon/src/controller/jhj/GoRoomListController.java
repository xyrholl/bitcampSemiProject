package controller.jhj;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/goRoom")
public class GoRoomListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8"); 
		req.setCharacterEncoding("utf-8");
		String hotelSeq = req.getParameter("hotelSeq");
		String checkin = req.getParameter("checkin");
		String checkout = req.getParameter("checkout");
		String guest = req.getParameter("guest");
		String hotelName = URLEncoder.encode(req.getParameter("hotelName"), "UTF-8");
		
		resp.sendRedirect(req.getContextPath() + "/JSP/roomList.jsp?hotelSeq=" + hotelSeq + "&checkin=" + checkin
        		+ "&checkout=" + checkout + "&guest=" + guest + "&hotelName=" + hotelName);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
