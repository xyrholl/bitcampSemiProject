package model.lsy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.ResvDTO;
import dto.ReviewDTO;
import sun.nio.ch.SelChImpl;

/*
CREATE TABLE REVIEW(
SEQ NUMBER(8) PRIMARY KEY,
HotelSEQ NUMBER(8) NOT NULL,
RoomSEQ NUMBER(8) NOT NULL,
ResvSEQ NUMBER(8) NOT NULL,
MemberSEQ NUMBER(8) NOT NULL,
TITLE VARCHAR2(100) NOT NULL,
CONTENT VARCHAR2(500) NOT NULL,
RATING NUMBER(3,2) NOT NULL,
DEL NUMBER(1) NOT NULL
);
*/

public class ReviewDAO {

	public ReviewDTO ReviewSelectOne(int seq) {
		ReviewDTO dto = null;

		String sql = " SELECT r.SEQ, r.RATING, m.ID, rm.NAME, rs.CURRENT_GUEST, r.WRITEDATE, r.TITLE, r.CONTENT, h.RATING, rs.CHECKIN, rs.CHECKOUT, h.NAME"
				+ " FROM REVIEW r, HOTEL h, ROOM rm, RESV rs, BM_MEMBER m"
				+ " WHERE r.HOTELSEQ = h.SEQ AND r.ROOMSEQ = rm.SEQ AND r.RESVSEQ = rs.SEQ  AND r.MEMBERSEQ = m.SEQ AND r.SEQ = ? AND r.DEL = 0 ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			rs = psmt.executeQuery();

			if (rs.next()) {
				int i = 1;
				dto = new ReviewDTO();
				dto.setSeq(rs.getInt(i++));
				dto.setRating(rs.getDouble(i++));
				dto.setMemberId(rs.getString(i++));
				dto.setRoomName(rs.getString(i++));
				dto.setCurrent_guest(rs.getInt(i++));
				dto.setWriteDate(rs.getString(i++));
				dto.setTitle(rs.getString(i++));
				dto.setContent(rs.getString(i++));
				dto.setHotelRating(rs.getDouble(i++));
				dto.setCheckInDate(rs.getString(i++));
				dto.setCheckOutDate(rs.getString(i++));
				dto.setHotelName(rs.getString(i++));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
	}

	public List<ReviewDTO> reviewList() {
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();

		String sql = " SELECT ROW_NUMBER()OVER(ORDER BY WRITEDATE DESC), SEQ, RATING, NAME, TITLE, CONTENT, WRITEDATE "
				+ " FROM( "
				+ " SELECT r.SEQ AS SEQ, r.RATING AS RATING, h.NAME AS NAME, TITLE, CONTENT, r.writedate AS WRITEDATE "
				+ " FROM REVIEW r, HOTEL h " + " WHERE r.HotelSEQ = h.SEQ " + " AND DEL = 0) ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				int i = 1;
				ReviewDTO dto = new ReviewDTO();
				dto.setRowNum(rs.getInt(i++));
				dto.setSeq(rs.getInt(i++));
				dto.setRating(rs.getDouble(i++));
				dto.setHotelName(rs.getString(i++));
				dto.setTitle(rs.getString(i++));
				dto.setContent(rs.getString(i++));
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}

	public List<ReviewDTO> reviewList(int selectIndex, String text) {
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();

		String sql1 = " SELECT ROW_NUMBER()OVER(ORDER BY WRITEDATE DESC)AS RNUM, REVIEWSEQ, RATING, HOTELNAME, REVIEWTITLE, REVIEWCONTENT, WRITEDATE"
				+ " FROM( "
				+ " SELECT r.SEQ AS REVIEWSEQ, r.RATING AS RATING, h.NAME AS HOTELNAME, r.TITLE AS REVIEWTITLE, r.CONTENT AS REVIEWCONTENT, r.writedate AS WRITEDATE, m.ID AS MEMBERID "
				+ " FROM REVIEW r, HOTEL h, BM_MEMBER m "
				+ " WHERE r.HotelSEQ = h.SEQ AND r.MemberSEQ = m.SEQ AND r.DEL = 0) ";

		if (selectIndex == 1) {
			sql1 = sql1 + " WHERE HOTELNAME LIKE '%'||?||'%' ";
		} else if (selectIndex == 2) {
			sql1 = sql1 + " WHERE REVIEWTITLE LIKE '%'||?||'%' ";
		} else if (selectIndex == 3) {
			sql1 = sql1 + " WHERE REVIEWCONTENT LIKE '%'||?||'%' ";
		} else if (selectIndex == 4) {
			sql1 = sql1 + " WHERE MEMBERID LIKE '%'||?||'%' ";
		}
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, text);
			rs = psmt.executeQuery();

			while (rs.next()) {
				int i = 1;
				ReviewDTO dto = new ReviewDTO();
				dto.setRowNum(rs.getInt(i++));
				dto.setSeq(rs.getInt(i++));
				dto.setRating(rs.getDouble(i++));
				dto.setHotelName(rs.getString(i++));
				dto.setTitle(rs.getString(i++));
				dto.setContent(rs.getString(i++));
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}

	public int updateOneWrite(int seq, String strRating, String title, String content) {

		Double rating = Double.parseDouble(strRating);
		String sql = " UPDATE REVIEW " + " SET RATING= ?, TITLE=?, CONTENT=? " + " WHERE SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setDouble(1, rating);
			psmt.setString(2, title);
			psmt.setString(3, content);
			psmt.setInt(4, seq);
			count = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return count;
	}

	public List<ResvDTO> writeReviewCheck(String loginId) {

		List<ResvDTO> list = new ArrayList<ResvDTO>();

		String sql = " SELECT h.NAME, h.RATING, r.NAME, re.CURRENT_GUEST, re.SEQ, re.REVIEWIS, re.CHECKIN, re.CHECKOUT, h.PLACE, re.CANCEL "
				+ " FROM RESV re, BM_MEMBER m, HOTEL h, ROOM r" + " WHERE re.MemberSEQ = m.SEQ "
				+ " AND re.HotelSEQ = h.SEQ " + " AND re.RoomSEQ = r.SEQ " + " AND  = 0 " + " AND m.ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, loginId);
			rs = psmt.executeQuery();

			while (rs.next()) {
				int i = 1;
				ResvDTO dto = new ResvDTO();
				dto.setHotelName(rs.getString(i++));
				dto.setHotelRating(rs.getDouble(i++));
				dto.setRoomName(rs.getString(i++));
				dto.setCurrent_guest(rs.getInt(i++));
				dto.setSeq(rs.getInt(i++));
				dto.setReviewIs(rs.getInt(i++));
				dto.setCheckIn(rs.getString(i++));
				dto.setCheckOut(rs.getString(i++));
				dto.setHotelPlace(rs.getString(i++));
				dto.setCancel(rs.getInt(i++));
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		return list;
	}

	public List<ResvDTO> writeReviewCheck(String loginId, int selectIndex, String hotelName, String place) {

		List<ResvDTO> list = new ArrayList<ResvDTO>();

		String sql = " SELECT h.NAME, h.RATING, r.NAME, re.CURRENT_GUEST, re.SEQ, re.REVIEWIS, re.CHECKIN, re.CHECKOUT, h.PLACE, re.CANCEL "
				+ " FROM RESV re, BM_MEMBER m, HOTEL h, ROOM r" + " WHERE re.MemberSEQ = m.SEQ "
				+ " AND re.HotelSEQ = h.SEQ " + " AND re.RoomSEQ = r.SEQ " + " AND m.ID = ? ";

		if (selectIndex == 1) {
			sql = sql + " AND h.NAME LIKE '%'||?||'%' ";
		} else if (selectIndex == 2) {
			sql = sql + " AND h.PLACE LIKE '%'||?||'%' ";
		}

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, loginId);
			rs = psmt.executeQuery();

			while (rs.next()) {
				int i = 1;
				ResvDTO dto = new ResvDTO();
				dto.setHotelName(rs.getString(i++));
				dto.setHotelRating(rs.getDouble(i++));
				dto.setRoomName(rs.getString(i++));
				dto.setCurrent_guest(rs.getInt(i++));
				dto.setSeq(rs.getInt(i++));
				dto.setReviewIs(rs.getInt(i++));
				dto.setCheckIn(rs.getString(i++));
				dto.setCheckOut(rs.getString(i++));
				dto.setHotelPlace(rs.getString(i++));
				dto.setCancel(rs.getInt(i++));
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		return list;
	}

	public ResvDTO ResvSelectOne(int seq) {
		ResvDTO dto = null;

		String sql = " SELECT * " + " FROM RESV " + " WHERE SEQ = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			rs = psmt.executeQuery();

			if (rs.next()) {
				int i = 1;
				dto = new ResvDTO();
				dto.setCancel(i++);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		return dto;
	}

}
