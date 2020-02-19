package model.jdy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.BM_MemberDTO;
import dto.HotelDTO;
import dto.ResvDTO;
import dto.RoomDTO;

public class ResvDAO {
	
	public HotelDTO getHotelInfo( int seq ) {

		String sql  = " SELECT SEQ, NAME, PLACE, ADDR, USE_COUNT, RATING, HOTEL_IMG "
					+ " FROM HOTEL "
					+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		HotelDTO hotel = null;
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getHotelInfo success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 getHotelInfo success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getHotelInfo success");
			
			if(rs.next()) {
				
				int i = 1;

				hotel = new HotelDTO(rs.getInt(i++), rs.getString(i++), rs.getString(i++), 
						rs.getString(i++), rs.getInt(i++), rs.getInt(i++), rs.getString(i++));
			}
			System.out.println("4/6 getHotelInfo success");
		} catch (SQLException e) {
			System.out.println("getHotelInfo fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		return hotel;
		
	}
	
	public RoomDTO getRoomInfo( int seq ) {

		String sql  = " SELECT SEQ, NAME, HOTELSEQ, PRICE, MAX_GUEST, ROOM_IMG "
				+ " FROM ROOM "
				+ " WHERE SEQ=? ";
	
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		RoomDTO room = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getRoomInfo success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 getRoomInfo success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getRoomInfo success");
			
			if(rs.next()) {
				
				int i = 1;

				room = new RoomDTO(rs.getInt(i++), rs.getString(i++), rs.getInt(i++),  rs.getInt(i++), rs.getInt(i++),rs.getString(i++));
			}
			System.out.println("4/6 getRoomInfo success");
		} catch (SQLException e) {
			System.out.println("getRoomInfo fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		return room;
	}
	

	
	
	public BM_MemberDTO getMemberInfo(String loginId) {
		
		String sql = " SELECT SEQ, ID, PWD, NAME, PHONENUM, EMAIL "
				+ "FROM BM_MEMBER WHERE ID=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		BM_MemberDTO member = null;
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getMemberInfo success");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, loginId);
			System.out.println("2/6 getMemberInfo success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getMemberInfo success");
			
			if(rs.next()) {
				int i = 1;
				member = new BM_MemberDTO(rs.getInt(i++), rs.getString(i++),  rs.getString(i++),  rs.getString(i++),  rs.getString(i++),  rs.getString(i++));
			}
			System.out.println("4/6 getMemberInfo success");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
	

	public ResvDTO getResvInfo(int seq ) {
		String sql  = " SELECT ROOMSEQ, HOTELSEQ, CHECKIN, CHECKOut, TOTALPRICE, CURRENT_GUEST "
				+ " FROM RESV "
				+ " WHERE SEQ=? ";
	
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		ResvDTO resv = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getResvInfo success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 getResvInfo success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getResvInfo success");
			
			if(rs.next()) {
				
				int i = 1;
//				(int roomSeq, int hotelSeq, String checkIn, String checkOut, int totalPrice, int current_guest)
				resv = new ResvDTO(rs.getInt(i++), rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getInt(i++), rs.getInt(i++));
						
			}
			System.out.println("4/6 getResvInfo success");
		} catch (SQLException e) {
			System.out.println("getResvInfo fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		return resv;
	}
	
	public boolean addResv( ResvDTO resv ) {
//		INSERT INTO RESV(SEQ, HOTELSEQ, MEMBERSEQ, ROOMSEQ, CHECKIN, CHECKOUT, RESVDATE, TOTALPRICE, CANCEL, CURRENT_GUEST, )
//		VALUES(3, 3, 3, '20200204', '20200205', SYSDATE, 50000, 0, 2);
		  String sql = " INSERT INTO RESV(SEQ, HOTELSEQ, MEMBERSEQ, ROOMSEQ, CHECKIN, CHECKOUT, RESVDATE, TOTALPRICE, CANCEL, CURRENT_GUEST, REVIEWIS, PAYMENTIS) "
	                  + " VALUES(SEQ_RESV.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, ?, 0, 4, 0, 0)  ";
	  
		  Connection conn = null;
		  PreparedStatement psmt = null;
		  int count = 0;
	      
	      try {
	         conn = DBConnection.getConnection();
	         System.out.println("1/6 addResv success");
	         
	         psmt = conn.prepareStatement(sql);
	         psmt.setInt(1, resv.getHotelSeq());
	         psmt.setInt(2, resv.getMemberSeq());
	         psmt.setInt(3, resv.getRoomSeq());
	         psmt.setString(4, resv.getCheckIn());
	         psmt.setString(5, resv.getCheckOut());
	         psmt.setInt(6, resv.getTotalPrice());
	         
	        
	         System.out.println("2/6 addResv success");
	         
	         count = psmt.executeUpdate();
	         System.out.println("3/6 addResv success");
	         
	      } catch (SQLException e) {
	         System.out.println("addResv fail");
	         e.printStackTrace();
	      }finally {
	         DBClose.close(psmt, conn, null);
	      }
	      return count > 0?true:false;
	   }
	
	
//	update schedule
//	set use = 1
//	where resvdate >='2020-02-26' 
//	and resvdate < '2020-02-28'
	public boolean addSchedule(String checkin, String checkout, int hotelSeq, int roomSeq) {
		String sql = " UPDATE SCHEDULE  "
					+ " SET USE = 1 "
					+ " WHERE RESVDATE >= ? "
					+ " AND RESVDATE < ? "
					+ " AND HOTELSEQ = ? "
					+ " AND ROOMSEQ = ?";
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
        
        try {
        	conn = DBConnection.getConnection();
        	System.out.println("1/6 addSchedule success");
        	
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, checkin);
			psmt.setString(2, checkout);
			psmt.setInt(3, hotelSeq);
			psmt.setInt(4, roomSeq);
			System.out.println("2/6 addSchedule success");
	         
	         count = psmt.executeUpdate();
	         System.out.println("3/6 addSchedule success");
	         
	      } catch (SQLException e) {
	         System.out.println("addSchedule fail");
	         e.printStackTrace();
	      }finally {
	         DBClose.close(psmt, conn, null);
	      }
	      return count > 0?true:false;
	   }
	
	public ResvDTO selectResvAddInfo(int hotelSeq, int memberSeq, int roomSeq, String checkIn, String checkOut ) {
		String sql  = " SELECT SEQ, HOTELSEQ, MEMBERSEQ, ROOMSEQ, CHECKIN, CHECKOUT, TOTALPRICE, CURRENT_GUEST "
				+ " FROM RESV "
				+ " WHERE HOTELSEQ = ? "
				+ " AND MEMBERSEQ = ? "
				+ " AND ROOMSEQ = ? "
				+ " AND CHECKIN = ? "
				+ " AND CHECKOUT = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		ResvDTO resv = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getResvInfo success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, hotelSeq);
			psmt.setInt(2, memberSeq);
			psmt.setInt(3, roomSeq);
			psmt.setString(4, checkIn);
			psmt.setString(5, checkOut);
			System.out.println("2/6 getResvInfo success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getResvInfo success");
			
			if(rs.next()) {
				
				int i = 1;
				//resv = new ResvDTO(seq, hotelSeq, memberSeq, roomSeq, checkIn, checkOut, totalPrice, current_guest);
				resv = new ResvDTO(rs.getInt(i++), rs.getInt(i++),rs.getInt(i++),rs.getInt(i++), 
						rs.getString(i++), rs.getString(i++), rs.getInt(i++), rs.getInt(i++));
						
			}
			System.out.println("4/6 getResvInfo success");
		} catch (SQLException e) {
			System.out.println("getResvInfo fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		return resv;
	}
	//update resv set paymentis = 1 where seq = 2;
	public boolean updatePaymentIs(int resvSeq) {
		String sql = " UPDATE RESV "
				+ " SET PAYMENTIS = 1 "
				+ " WHERE SEQ = ? ";

	Connection conn = null;
	PreparedStatement psmt = null;
	int count = 0;
	
    try {
    	conn = DBConnection.getConnection();
    	System.out.println("1/6 updatePaymentIs success");
    	
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, resvSeq);
	
		System.out.println("2/6 updatePaymentIs success");
         
         count = psmt.executeUpdate();
         System.out.println("3/6 updatePaymentIs success");
         
      } catch (SQLException e) {
         System.out.println("updatePaymentIs fail");
         e.printStackTrace();
      }finally {
         DBClose.close(psmt, conn, null);
      }
      return count > 0?true:false;
   }
	
}
	