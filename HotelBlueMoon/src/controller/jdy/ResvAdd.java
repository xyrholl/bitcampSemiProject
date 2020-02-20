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
		resp.setContentType("text/html; charset=UTF-8");
		System.out.println("ResvAdd호출");

//	    <input type="hidden" name="memberSeq" value="<%=resv.getMemberSeq() %>">
//	    <input type="hidden" name="roomSeq" value="<%=resv.getRoomSeq() %>">
//	    <input type="hidden" name="hotelSeq" value="<%=resv.getHotelSeq() %>">
//	    <input type="hidden" name="checkIn" value="<%=resv.getCheckIn() %>">
//	    <input type="hidden" name="checkOut" value="<%=resv.getCheckOut() %>">
//	    <input type="hidden" name="resvDate" value="<%=resv.getResvDate() %>">
//	    <input type="hidden" name="totalPrice" value="<%=resv.getTotalPrice() %>">
//	    <input type="hidden" name="cancel" value="<%=resv.getCancel() %>">
//	    <input type="hidden" name="current_guest" value="<%=resv.getCurrent_guest() %>">
//		<input type="hidden" name="loginId" value="<%=loginId%>"> 

		String loginId = req.getParameter("loginId");

		Singleton s = Singleton.getInstance();

		String sroomseq = req.getParameter("roomSeq");
		int roomSeq = Integer.parseInt(sroomseq);
		String shotelseq = req.getParameter("hotelSeq");
		int hotelSeq = Integer.parseInt(shotelseq);
		String checkIn = req.getParameter("checkIn");
		String checkOut = req.getParameter("checkOut");

		String stotalprice = req.getParameter("totalPrice");
		int totalPrice = Integer.parseInt(stotalprice);

		String scurrentguest = req.getParameter("current_guest");
		int current_guest = Integer.parseInt(scurrentguest);

		BM_MemberDTO member = s.resvSerivce.getMemberInfo(loginId);

		ResvDTO resv = new ResvDTO(hotelSeq, member.getSeq(), roomSeq, checkIn, checkOut, totalPrice, current_guest);
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
		System.out.println("resvadd함수 실행하기전" + resv.toString());
		boolean check = s.resvSerivce.resvScheduleCheck(checkIn, checkOut, hotelSeq, roomSeq);

		String addfunc = "function getContextPath() { var hostIndex = location.href.indexOf( location.host ) + location.host.length;"
				+ "return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );}";

		if (!check) {
			boolean add = s.resvSerivce.addSchedule(checkIn, checkOut, hotelSeq, roomSeq);
			if (add) {
				System.out.println("스케줄업데이트 성공");
			} else {
				System.out.println("스케줄업데이트 실패");
			}
			boolean b = s.resvSerivce.addResv(resv);
			if (b) {
				resv = s.resvSerivce.selectResvAddInfo(hotelSeq, member.getSeq(), roomSeq, checkIn, checkOut);
				resv.setId(member.getEmail());
				resv.setPwd(member.getPwd());
				resv.setMemName(member.getName());
				resv.setPhoneNum(member.getPhoneNum());
				resv.setEmail(member.getEmail());
				resv.setRoomName(room.getName());
				resv.setRoomMax_guest(room.getMax_guest());
				resv.setRoomPrice(room.getPrice());
				resv.setHotelName(hotel.getName());
				resv.setHotelAddr(hotel.getAddr());
				resv.setHotelPlace(hotel.getPlace());
				System.out.println("resvadd함수 실행하고나서 스케줄추가하고나서" + resv.toString());
				req.setAttribute("resv", resv);

				forward("JSP/resvPayment.jsp", req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/hotelResvInfo?hotelSeq=" + hotelSeq + "&checkin=" + checkIn
						+ "&checkout=" + checkOut + "&guest=" + current_guest + "&roomSeq=" + roomSeq);
			}
		} else {
			resp.getWriter().println("<script> alert('이미 예약이 완료된 방입니다.\\n마이페이지에서 확인 또는 예약을 다시 진행해 주세요.'); " + addfunc
					+ " location.href=getContextPath() + '/hotelResvInfo?hotelSeq=" + hotelSeq + "&checkin=" + checkIn
					+ "&checkout=" + checkOut + "&guest=" + current_guest + "&roomSeq=" + roomSeq + "' </script>");
		}

	}

	public void forward(String link, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(link);
		dispatch.forward(req, resp);
	}

}
