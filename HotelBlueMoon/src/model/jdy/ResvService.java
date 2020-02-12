package model.jdy;

import java.util.ArrayList;
import java.util.List;

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
	public BM_MemberDTO getMemberInfo(int seq) {
		BM_MemberDTO member = null;
		member = resvDAO.getMemberInfo(seq);
		return member;
	}
	
	public boolean addResv( ResvDTO resv ) {
		boolean b = resvDAO.addResv(resv);
		return b;
	}
	

	
	public BM_MemberDTO getMemberInfo(String loginId) {
		BM_MemberDTO member = null;
		member = resvDAO.getMemberInfo(loginId);
		return member;
	}
	
}
