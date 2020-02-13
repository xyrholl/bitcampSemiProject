package controller.jdy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.HotelDTO;
import dto.ResvDTO;
import dto.RoomDTO;
import singleton.Singleton;
@WebServlet("/resvNullCheck")
public class ResvNullCheck extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("nullcheck");
	    String checkin = URLEncoder.encode(req.getParameter("checkin"), "UTF-8");
        String checkout = URLEncoder.encode(req.getParameter("checkout"), "UTF-8");
        String sguest = URLEncoder.encode(req.getParameter("guest"), "UTF-8");
        String shotelSeq = URLEncoder.encode(req.getParameter("hotelSeq"), "UTF-8");
        int hotelSeq = Integer.parseInt(shotelSeq);
        int current_guest = Integer.parseInt(sguest);
        
        
        System.out.println(checkin);
        System.out.println(checkout);
        System.out.println(sguest);
        System.out.println(hotelSeq);
        
        Singleton s = Singleton.getInstance();
      
        RoomDTO room = s.resvSerivce.getRoomInfo(hotelSeq);
        HotelDTO hotel  = s.resvSerivce.getHotelInfo(room.getSeq());
        long stay = s.hotelService.betweenTime(checkin, checkout);
        int totalprice = room.getPrice()*(int)stay;
        
       
    	   ResvDTO resv = new ResvDTO( hotelSeq, room.getSeq(), checkin, checkout, totalprice, current_guest);
           resv.setHotelAddr(hotel.getAddr());
           resv.setHotelName(hotel.getName());
           resv.setHotelPlace(hotel.getPlace());
           resv.setHotelRating(hotel.getRating());
           resv.setHotelUseCount(hotel.getUseCount());
           
           resv.setRoomName(room.getName());
           resv.setRoomPrice(room.getPrice());
           resv.setRoomMax_guest(room.getMax_guest());
           
           req.setAttribute("resv", resv);
    	   forward("resvlogin", req, resp);
           
           
   
	}
	
	public void forward(String link, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		RequestDispatcher dispatch = req.getRequestDispatcher(link);
		dispatch.forward(req, resp);
	}
}
