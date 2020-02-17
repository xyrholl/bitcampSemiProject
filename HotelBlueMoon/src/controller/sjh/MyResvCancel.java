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

import dto.ResvDTO;
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
		
		if(command.equals("cancelAf")) {
			int seq = Integer.parseInt(req.getParameter("cancel_seq"));
			String nowTime = (String)req.getParameter("nowTime");
			boolean isS = s.myPageService.resvCancel(seq);
			
			if(isS) {
				
				resp.sendRedirect(req.getContextPath()+"/fowardmyresvhistory?nowTime="+nowTime+"&loginId="+loginId);
			}else {
				
				resp.sendRedirect(req.getContextPath()+"/JSP/myresvhistory.jsp?nowTime="+nowTime+"&loginId="+loginId);
			}
		}else {
			
		
		int seq = Integer.parseInt(req.getParameter("seq"));
		ResvDTO dto = s.myPageService.getMyResv(seq);
		System.out.println("JAVA cancel seq: "+seq +" dto: "+dto.toString());
		
		int hotelseq = dto.getHotelSeq();
		int roomseq = dto.getRoomSeq();
		int memseq = dto.getMemberSeq();
		
		String hotelname = s.myPageService.gethotelName(hotelseq);
		String roomname = s.myPageService.getroomName(roomseq);
		String memname = s.myPageService.getmemName(memseq);
		
		System.out.println(hotelname + " "+roomname+" "+memname);
		
		req.setAttribute("hotelName", hotelname);
		req.setAttribute("roomName", roomname);
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
