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
@WebServlet("/hotelResvInfo")
public class ResvInfo extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//method //다오가서-서비스보내주고-서비스에서 싱글턴으로 ㅁ ㅗ은고 -컨트롤러에서 뷰
		
		int seq = 3;
		int memberSeq = 3;
		ResvDTO resv = null;		
		Singleton s = Singleton.getInstance();
		
		resv = s.resvSerivce.getResvInfo(seq);
		//resv = new ResvDTO(memberSeq, hotelSeq, memberSeq, roomSeq, checkIn, checkOut, resvDate, totalPrice, cancel, current_guest)
		resv = new ResvDTO(seq, resv.getHotelSeq(), memberSeq, resv.getRoomSeq(), resv.getCheckIn(), resv.getCheckOut(),
				resv.getResvDate(), resv.getTotalPrice(), resv.getCancel(), resv.getCurrent_guest());
	
		
		RoomDTO room = s.resvSerivce.getRoomInfo(resv.getRoomSeq());
		resv.setRoomName(room.getName());
		resv.setRoomPrice(room.getPrice());
		resv.setRoomMax_guest(room.getMax_guest());
		

		HotelDTO hotel  = s.resvSerivce.getHotelInfo(room.getSeq());
		resv.setHotelSeq(hotel.getSeq());
		resv.setHotelName(hotel.getName());
		resv.setHotelPlace(hotel.getPlace());
		resv.setHotelAddr(hotel.getAddr());
		resv.setHotelUseCount(hotel.getUseCount());
		resv.setHotelRating(hotel.getRating());
		
		BM_MemberDTO member = s.resvSerivce.getMemberInfo(memberSeq);
		resv.setId(member.getId());
		resv.setPwd(member.getPwd());
		resv.setMemName(member.getName());
		resv.setPhoneNum(member.getPhoneNum());
		resv.setEmail(member.getEmail());
		
		req.setAttribute("resv", resv);
		forward("JSP/resvcheck.jsp", req, resp);
		

	}
	
	public void forward(String link, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		RequestDispatcher dispatch = req.getRequestDispatcher(link);
		dispatch.forward(req, resp);
	}

}
