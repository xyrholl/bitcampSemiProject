package model.sjh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.BM_MemberDTO;
import dto.ResvDTO;

public class MyPageDao {
	
	public MyPageDao() {
	
	}
	
	public BM_MemberDTO getMyPage(String loginId) {
		String sql = " SELECT ID, PWD, NAME, PHONENUM, EMAIL "
				   + " FROM BM_MEMBER "
				   + " WHERE ID=? ";
		Connection conn = null;
	    PreparedStatement psmt = null;
	    ResultSet rs = null;
	    BM_MemberDTO dto = null;
	    
	    try {
	         conn = DBConnection.getConnection();
	         System.out.println("1/6 getMyPage success");
	         psmt = conn.prepareStatement(sql);
	         psmt.setString(1, loginId);
	         System.out.println("2/6 getMyPage success");
	         
	         rs = psmt.executeQuery();
	         System.out.println("3/6 getMyPage success");
	         if(rs.next()) {
	            int i = 1;
	            dto = new BM_MemberDTO(rs.getString(i++),rs.getString(i++),rs.getString(i++),rs.getString(i++),rs.getString(i++) );
	         }
	         System.out.println("4/6 getMyPage success");
	      } catch (SQLException e) {
	         System.out.println("getMyPage fail");
	         e.printStackTrace();
	      }finally {
	         DBClose.close(psmt, conn, rs);
	      }
	      return dto;
	}
	
	
	public boolean myPageUpdate(String loginId, String pwd, String phoneNum, String email ) {
		String sql = " UPDATE BM_MEMBER "
				   + " SET PWD=?, PHONENUM=?, EMAIL=?"
				   + " WHERE SEQ=? ";
		
		Connection conn = null;
	    PreparedStatement psmt = null;
	  
	    int count = 0;
	      
	    try {
	         conn = DBConnection.getConnection();
	         System.out.println("1/6 myPageUpdate success");
	         psmt = conn.prepareStatement(sql);
	         psmt.setString(1, pwd);
	         psmt.setString(2, phoneNum);
	         psmt.setString(3, email);
	         psmt.setInt(4, getMemberseq(loginId));
	         System.out.println("2/6 myPageUpdate success");
	         
	         count = psmt.executeUpdate();
	         System.out.println("3/6 myPageUpdate success");
	         
	      } catch (SQLException e) {
	         System.out.println("myPageUpdate fail");
	         e.printStackTrace();
	      }finally {
	         DBClose.close(psmt, conn, null);
	      }
	         
	      return count>0?true:false;
	}
	
	public int getMyReviewCount(String loginId){

	      String sql = " SELECT count(*) FROM REVIEW WHERE MEMBERSEQ=? ";
	      
	      Connection conn = null;
	      PreparedStatement psmt = null;
	      ResultSet rs = null;
	      int count = 0;
	      
	      try {
	         conn = DBConnection.getConnection();
	         System.out.println("1/6 getMyReviewCount success");
	         psmt = conn.prepareStatement(sql);
	         psmt.setInt(1, getMemberseq(loginId));
	         System.out.println("2/6 getMyReviewCount success");
	         
	         rs = psmt.executeQuery();
	         System.out.println("3/6 getMyReviewCount success");
	         if(rs.next()) {
	           count = rs.getInt(1);
	         }
	         System.out.println("4/6 getMyReviewCount success");
	      } catch (SQLException e) {
	         System.out.println("getMyReviewCount fail");
	         e.printStackTrace();
	      }finally {
	         DBClose.close(psmt, conn, rs);
	      }
	         
	      return count;
	}
	
	public int getMyQnACount(String loginId){

	      String sql = " SELECT count(*) FROM QNA WHERE MEMBERSEQ=? ";
	      
	      Connection conn = null;
	      PreparedStatement psmt = null;
	      ResultSet rs = null;
	      int count = 0;
	      
	      try {
	         conn = DBConnection.getConnection();
	         System.out.println("1/6 getMyQnACount success");
	         psmt = conn.prepareStatement(sql);
	         psmt.setInt(1, getMemberseq(loginId));
	         System.out.println("2/6 getMyQnACount success");
	         
	         rs = psmt.executeQuery();
	         System.out.println("3/6 getMyQnACount success");
	         if(rs.next()) {
	           count = rs.getInt(1);
	         }
	         System.out.println("4/6 getMyQnACount success");
	      } catch (SQLException e) {
	         System.out.println("getMyQnACount fail");
	         e.printStackTrace();
	      }finally {
	         DBClose.close(psmt, conn, rs);
	      }
	         
	      return count;
	}
	
	public int getMyResvCount(String loginId) {
		String sql = " SELECT count(*) FROM RESV WHERE MEMBERSEQ=? ";
	      
	      Connection conn = null;
	      PreparedStatement psmt = null;
	      ResultSet rs = null;
	      int count = 0;
	      
	      try {
	         conn = DBConnection.getConnection();
	         System.out.println("1/6 getMyResvCount success");
	         psmt = conn.prepareStatement(sql);
	         psmt.setInt(1, getMemberseq(loginId));
	         System.out.println("2/6 getMyResvCount success");
	         
	         rs = psmt.executeQuery();
	         System.out.println("3/6 getMyResvCount success");
	         if(rs.next()) {
	           count = rs.getInt(1);
	         }
	         System.out.println("4/6 getMyResvCount success");
	      } catch (SQLException e) {
	         System.out.println("getMyResvCount fail");
	         e.printStackTrace();
	      }finally {
	         DBClose.close(psmt, conn, rs);
	      }
	         
	      return count;
	}
	
	public int getMemberseq(String loginId) {
		String sql = " SELECT SEQ " + " FROM BM_MEMBER " + " WHERE ID=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int memberseq = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getMemberseq success");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, loginId);
			System.out.println("2/6 getMemberseq success");
			rs = psmt.executeQuery();
			System.out.println("3/6 getMemberseq success");
			
			if (rs.next()) {
				memberseq = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("getMemberseq fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return memberseq;
	}
	
	public boolean checkPassword(String loginId, String pwd) {
		String sql = " SELECT PWD " + " FROM BM_MEMBER " + " WHERE ID=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String DB_pwd = "";
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 checkPassword success");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, loginId);
			System.out.println("2/6 checkPassword success");
			rs = psmt.executeQuery();
			System.out.println("3/6 checkPassword success");
			
			if (rs.next()) {
				DB_pwd = rs.getString(1);
			}
			
		} catch (SQLException e) {
			System.out.println("checkPassword fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return DB_pwd.equals(pwd)?true:false;
	}
}
