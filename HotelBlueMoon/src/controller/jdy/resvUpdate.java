package controller.jdy;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/resvUpdate")
public class resvUpdate extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		+"/resvUpdate?"
// 		+"hotelArea="+<%=resv.getHotelPlace()%>
//		+"&checkin="+"<%=resv.getCheckIn()%>"
//		+"&checkout="+"<%=resv.getCheckOut()%>"
//		+"&guest="+<%=resv.getCurrent_guest()%>; 
		String hotelArea = req.getParameter("hotelArea");
		String checkin= req.getParameter("checkin");
		String checkout = req.getParameter("checkout");
		String sguest = req.getParameter("guest");
		int guest = Integer.parseInt(sguest);
		System.out.println("예약수정후 메인:"+hotelArea+checkin + checkout+guest);
		
		resp.sendRedirect(req.getContextPath() 
							+"/JSP/resvUpdate.jsp?hotelArea="+URLEncoder.encode(hotelArea)
							+"&checkin="+URLEncoder.encode(checkin)
							+"&checkout="+URLEncoder.encode(checkout)
							+"&guest="+guest);
	}
	
}
