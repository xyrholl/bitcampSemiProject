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
@WebServlet("/myresvdetail")
public class MyResvDetail extends HttpServlet {

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
		String ctype = req.getParameter("ctype");
		String ptype = req.getParameter("ptype");
		
		
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

			req.setAttribute("resvSeq", dto.getSeq());
			req.setAttribute("name", dto.getMemName());
			req.setAttribute("phone", dto.getPhoneNum());
			req.setAttribute("email", dto.getEmail());
			req.setAttribute("totalprice", dto.getTotalPrice());
			forward("JSP/kakao.jsp", req, resp);
			
		}else { // 그냥 detail
			System.out.println("command= x");
		System.out.println("JAVA cancel seq: "+seq +" dto: "+dto.toString());
		
		int hotelseq = dto.getHotelSeq();
		int roomseq = dto.getRoomSeq();
		int memseq = dto.getMemberSeq();
		
		HotelDTO hotelDto = s.myPageService.gethotelNameAddr(hotelseq);
		RoomDTO roomDto = s.myPageService.getroomNameImg(roomseq);
		String memname = s.myPageService.getmemName(memseq);
		
		System.out.println("detail JAVA: ctype= "+ctype);
		req.setAttribute("ctype", ctype);
		req.setAttribute("ptype", ptype);
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
