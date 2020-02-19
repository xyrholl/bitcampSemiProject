package controller.sjh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import dto.HotelDTO;
import dto.ResvDTO;
import dto.RoomDTO;
import singleton.Singleton;
@WebServlet("/myresvcancel")
public class MyResvCancel extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processFunc(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processFunc(req, resp);
	}

	
	public void processFunc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		
		String command = req.getParameter("command");
		String loginId = (String) session.getAttribute("loginId");
		Singleton s = Singleton.getInstance();
		int seq = Integer.parseInt(req.getParameter("detail_seq"));
		System.out.println("JAVA: myresvcancel seq= "+seq);
		ResvDTO dto = s.myPageService.getMyResv(seq);
		
		if(command.equals("cancelAf")) {
			System.out.println("command= cancelAf");
			String nowTime = (String)req.getParameter("nowTime");
			boolean isS = s.myPageService.resvCancel(seq);
			
			if(isS) {
				
				resp.sendRedirect(req.getContextPath()+"/fowardmyresvhistory?nowTime="+nowTime+"&loginId="+loginId);
			}else {
				
				resp.sendRedirect(req.getContextPath()+"/JSP/myresvhistory.jsp?nowTime="+nowTime+"&loginId="+loginId);
			}
			
		}else if(command.equals("paymentAf")) {
			System.out.println("command= paymentAf");
			//String loginId = dto.getId();
			int roomSeq = dto.getRoomSeq();
			int hotelSeq = dto.getHotelSeq();
			String checkIn = dto.getCheckIn();
			String checkOut = dto.getCheckOut();
			int totalPrice = dto.getTotalPrice();
			int current_guest = dto.getCurrent_guest();
			
			resp.sendRedirect(req.getContextPath()+"/resvAdd?loginId="+loginId+"&roomSeq="+roomSeq+
												   "&hotelSeq="+hotelSeq+"&checkIn="+checkIn+"&checkOut="+checkOut+
												   "&totalPrice="+totalPrice+"&current_guest="+current_guest);
			
		}else { // 그냥 detail
			System.out.println("command= x");
		System.out.println("JAVA cancel seq: "+seq +" dto: "+dto.toString());
		
		int hotelseq = dto.getHotelSeq();
		int roomseq = dto.getRoomSeq();
		int memseq = dto.getMemberSeq();
		
		HotelDTO hotelDto = s.myPageService.gethotelNameAddr(hotelseq);
		RoomDTO roomDto = s.myPageService.getroomNameImg(roomseq);
		String memname = s.myPageService.getmemName(memseq);
		
		req.setAttribute("hotelDto", hotelDto);
		req.setAttribute("roomDto", roomDto);
		req.setAttribute("memName", memname);
		req.setAttribute("dto", dto);
		forward("JSP/myresvdetail.jsp", req, resp);
		}
	}
	
	public void forward(String link, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(link);
		dispatch.forward(req, resp);
	}
}
