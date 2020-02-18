package controller.jhj;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import singleton.Singleton;

@WebServlet("/roomList")
public class RoomListController extends HttpServlet{

	Singleton s;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8"); 
		req.setCharacterEncoding("utf-8");
		String hotelSeq = req.getParameter("hotelSeq");
		String checkin = req.getParameter("checkin");
		String checkout = req.getParameter("checkout");
		String guest = req.getParameter("guest");
		
		String json = s.getInstance().getRoom(guest, checkin, checkout, hotelSeq);
		
		req.setAttribute("json", json);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/JSP/ajaxData/rs.jsp");
		dispatcher.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
}
