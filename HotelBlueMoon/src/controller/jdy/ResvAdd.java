package controller.jdy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BM_MemberDTO;
import dto.HotelDTO;
import dto.ResvDTO;
import dto.RoomDTO;
import singleton.Singleton;
@WebServlet("/resvAdd")
public class ResvAdd extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ResvAdd호출");
//	    <input type="hidden" name="seq" value="<%=resv.getSeq() %>">
//	    <input type="hidden" name="memberSeq" value="<%=resv.getMemberSeq() %>">
//	    <input type="hidden" name="roomSeq" value="<%=resv.getRoomSeq() %>">
//	    <input type="hidden" name="hotelSeq" value="<%=resv.getHotelSeq() %>">
//	    <input type="hidden" name="checkIn" value="<%=resv.getCheckIn() %>">
//	    <input type="hidden" name="checkOut" value="<%=resv.getCheckOut() %>">
//	    <input type="hidden" name="resvDate" value="<%=resv.getResvDate() %>">
//	    <input type="hidden" name="totalPrice" value="<%=resv.getTotalPrice() %>">
//	    <input type="hidden" name="cancel" value="<%=resv.getCancel() %>">
//	    <input type="hidden" name="current_guest" value="<%=resv.getCurrent_guest() %>">
		BM_MemberDTO member = null;
		Singleton s = Singleton.getInstance();
		//(SEQ, MEMBERSEQ, CHECKIN, CHECKOUT, RESVDATE, TOTALPRICE, CURRENT_GUEST, CANCEL) 
		String sseq = req.getParameter("seq");
		int seq = Integer.parseInt(sseq);
		String smemseq = req.getParameter("memberSeq");
		int memberSeq = Integer.parseInt(smemseq);
		String sroomseq = req.getParameter("roomSeq");
		int roomSeq = Integer.parseInt(sroomseq);
		String shotelseq = req.getParameter("hotelSeq");
		int hotelSeq = Integer.parseInt(shotelseq);
		String checkIn = req.getParameter("checkIn");
		String checkOut = req.getParameter("checkOut");
		String resvDate = req.getParameter("resvDate");
		String stotalprice = req.getParameter("totalPrice");
		int totalPrice = Integer.parseInt(stotalprice);
		String scancle = req.getParameter("cancel");
		int cancel = Integer.parseInt(scancle);
		String scurrentguest = req.getParameter("current_guest");
		int current_guest = Integer.parseInt(scurrentguest);
		
		
		ResvDTO resv = new ResvDTO(seq, hotelSeq, memberSeq, roomSeq, checkIn, checkOut, resvDate, totalPrice, cancel, current_guest);
		
		member = s.resvSerivce.getMemberInfo(memberSeq);
		resv.setId(member.getId());
		resv.setPwd(member.getPwd());
		resv.setMemName(member.getName());
		resv.setPhoneNum(member.getPhoneNum());
		resv.setEmail(member.getEmail());
		
		RoomDTO room = s.resvSerivce.getRoomInfo(roomSeq);
		resv.setRoomName(room.getName());
		resv.setRoomMax_guest(room.getMax_guest());
		resv.setRoomPrice(room.getPrice());
		
		HotelDTO hotel = s.resvSerivce.getHotelInfo(hotelSeq);
		resv.setHotelName(hotel.getName());
		resv.setHotelAddr(hotel.getAddr());
		resv.setHotelPlace(hotel.getPlace());
		
		boolean b = s.resvSerivce.addResv(resv);
		if(b) {
			req.setAttribute("resv", resv);
			forward("JSP/resvPayment.jsp", req, resp);
		}else {
			resp.sendRedirect(req.getContextPath()+"/JSP/resvcheck.jsp");
		}
			
		
		
	}
	
	public void forward(String link, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		RequestDispatcher dispatch = req.getRequestDispatcher(link);
		dispatch.forward(req, resp);
	}
	
	
}
