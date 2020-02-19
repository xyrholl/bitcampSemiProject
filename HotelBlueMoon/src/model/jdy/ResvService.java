package model.jdy;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;
import dto.BM_MemberDTO;
import dto.HotelDTO;
import dto.ResvDTO;
import dto.RoomDTO;

public class ResvService {
	ResvDAO resvDAO = new ResvDAO();
	public HotelDTO getHotelInfo( int seq ) {
		HotelDTO hotel = null;
		hotel = resvDAO.getHotelInfo(seq);
		return hotel;
	}
	
	public ResvDTO getResvInfo(int seq ) {
		ResvDTO resv = null;
		resv = resvDAO.getResvInfo(seq);
		return resv;
	}
	
	public RoomDTO getRoomInfo( int seq ) {
		RoomDTO room = null;
		room = resvDAO.getRoomInfo(seq);
		return room; 
	}
	public BM_MemberDTO getMemberInfo(String loginId) {
		BM_MemberDTO member = null;
		member = resvDAO.getMemberInfo(loginId);
		return member;
	}
	
	public boolean addResv( ResvDTO resv ) {
		boolean b = resvDAO.addResv(resv);
		return b;
	}
	
	public ResvDTO selectResvAddInfo(int hotelSeq, int memberSeq, int roomSeq, String checkIn, String checkOut ) {
		ResvDTO resv = null;
		resv = resvDAO.selectResvAddInfo(hotelSeq, memberSeq, roomSeq, checkIn, checkOut);
		return resv;
	}
	
	public boolean addSchedule(String checkin, String checkout, int hotelSeq, int roomSeq) {
		boolean b = resvDAO.addSchedule(checkin, checkout, hotelSeq, roomSeq);
		return b;
	}
	
	public boolean updatePaymentIs(int resvSeq) {
		boolean b = resvDAO.updatePaymentIs(resvSeq);
		return b;
	}


	
	

	
}
