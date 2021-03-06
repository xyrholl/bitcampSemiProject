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

	public ReviewDTO selectOne(int seq) {
		ReviewDTO dto = null;

		String sql = " SELECT r.SEQ, r.RATING, m.ID, rm.NAME, rs.CURRENT_GUEST, r.WRITEDATE, r.TITLE, r.CONTENT, h.RATING, rs.CHECKIN, rs.CHECKOUT, h.NAME, rm.ROOM_IMG, r.REVIEW_IMG_REAL "
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
				dto.setRoomImg(rs.getString(i++));
				dto.setFileRealName(rs.getString(i++));
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

		String sql = " SELECT ROW_NUMBER()OVER(ORDER BY WRITEDATE DESC), SEQ, RATING, NAME, TITLE, CONTENT, WRITEDATE, REVIEW_IMAGE, HOTEL_IMG "
				+ " FROM( "
				+ " SELECT r.SEQ AS SEQ, r.RATING AS RATING, h.NAME AS NAME, TITLE, CONTENT, r.writedate AS WRITEDATE, r.REVIEW_IMG_REAL AS REVIEW_IMAGE, h.HOTEL_IMG AS HOTEL_IMG "
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
				dto.setWriteDate(rs.getString(i++));
				dto.setFileRealName(rs.getString(i++));
				dto.setHotelImg(rs.getString(i++));
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ReviewDTO> reviewList(int selectIndex, String text) {
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();

		String sql1 = " SELECT ROW_NUMBER()OVER(ORDER BY WRITEDATE DESC)AS RNUM, REVIEWSEQ, RATING, HOTELNAME, REVIEWTITLE, REVIEWCONTENT, WRITEDATE, REVIEW_IMAGE, HOTEL_IMG "
				+ " FROM( "
				+ " SELECT r.SEQ AS REVIEWSEQ, r.RATING AS RATING, h.NAME AS HOTELNAME, r.TITLE AS REVIEWTITLE, r.CONTENT AS REVIEWCONTENT, r.writedate AS WRITEDATE, m.ID AS MEMBERID, r.REVIEW_IMG_REAL AS REVIEW_IMAGE, h.HOTEL_IMG AS HOTEL_IMG "
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
				dto.setWriteDate(rs.getString(i++));
				dto.setFileRealName(rs.getString(i++));
				dto.setHotelImg(rs.getString(i++));
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
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
		}

		return count;
	}

	public List<ResvDTO> writeReviewCheck(String loginId) {

		List<ResvDTO> list = new ArrayList<ResvDTO>();

		String sql = " SELECT h.NAME, h.RATING, r.NAME, re.CURRENT_GUEST, re.SEQ, re.REVIEWIS, re.CHECKIN, re.CHECKOUT, h.PLACE, re.CANCEL, re.PAYMENTIS "
				+ " FROM RESV re, BM_MEMBER m, HOTEL h, ROOM r" + " WHERE re.MemberSEQ = m.SEQ "
				+ " AND re.HotelSEQ = h.SEQ " + " AND re.RoomSEQ = r.SEQ " + " AND m.ID = ? "
				+ " ORDER BY re.SEQ DESC ";

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
				dto.setPaymentIs(rs.getInt(i++));
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<ResvDTO> writeReviewCheck(String loginId, int selectIndex, String searchText) {

		List<ResvDTO> list = new ArrayList<ResvDTO>();

		String sql = " SELECT h.NAME, h.RATING, r.NAME, re.CURRENT_GUEST, re.SEQ, re.REVIEWIS, re.CHECKIN, re.CHECKOUT, h.PLACE, re.CANCEL, re.PAYMENTIS "
				+ " FROM RESV re, BM_MEMBER m, HOTEL h, ROOM r" + " WHERE re.MemberSEQ = m.SEQ "
				+ " AND re.HotelSEQ = h.SEQ " + " AND re.RoomSEQ = r.SEQ " + " AND m.ID = ?";

		if (selectIndex == 1) {
			sql = sql + " AND h.NAME LIKE '%'||?||'%' " + " ORDER BY re.SEQ DESC ";
		} else if (selectIndex == 2) {
			sql = sql + " AND h.PLACE LIKE '%'||?||'%' " + " ORDER BY re.SEQ DESC ";
		}

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, loginId);
			psmt.setString(2, searchText);
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
				dto.setPaymentIs(rs.getInt(i++));
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public ResvDTO ResvSelectOne(int seq) {
		ResvDTO dto = null;

		String sql = " SELECT h.RATING, h.NAME, re.CURRENT_GUEST, r.NAME, re.SEQ, h.SEQ, r.SEQ "
				+ " FROM RESV re, HOTEL h, ROOM r " + " WHERE re.HotelSEQ = h.SEQ " + " AND re.RoomSEQ = r.SEQ "
				+ " AND re.SEQ = ? ";

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
				dto.setHotelRating(rs.getDouble(i++));
				dto.setHotelName(rs.getString(i++));
				dto.setCurrent_guest(rs.getInt(i++));
				dto.setRoomName(rs.getString(i++));
				dto.setSeq(rs.getInt(i++));
				dto.setHotelSeq(rs.getInt(i++));
				dto.setRoomSeq(rs.getInt(i++));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		return dto;
	}

	public int insertReview(ReviewDTO dto) {

		String sql1 = " INSERT INTO REVIEW(SEQ, HotelSEQ, RoomSEQ, ResvSEQ, MemberSEQ, TITLE, CONTENT, RATING, WRITEDATE, DEL, REVIEW_IMG, REVIEW_IMG_REAL) "
				+ " VALUES(SEQ_REVIEW.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, SYSDATE, 0, ?, ?)";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;

		String sql2 = " UPDATE RESV " + " SET REVIEWIS = 1 " + " WHERE SEQ = ? ";

		String sql3 = "  UPDATE HOTEL " + " SET RATING = (SELECT AVG(RATING) FROM REVIEW WHERE HOTELSEQ = ?) "
				+ " WHERE SEQ = ? ";

		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			psmt = conn.prepareStatement(sql1);
			psmt.setInt(1, dto.getHotleSeq());
			psmt.setInt(2, dto.getRoomSeq());
			psmt.setInt(3, dto.getResvSeq());
			psmt.setInt(4, dto.getMemberSeq());
			psmt.setString(5, dto.getTitle());
			psmt.setString(6, dto.getContent());
			psmt.setDouble(7, dto.getRating());
			psmt.setString(8, dto.getFileName());
			psmt.setString(9, dto.getFileRealName());
			System.out.println(sql1);
			count = psmt.executeUpdate();

			psmt.clearParameters();

			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1, dto.getResvSeq());
			System.out.println(sql2);
			count = psmt.executeUpdate();

			psmt.clearParameters();

			psmt = conn.prepareStatement(sql3);
			psmt.setInt(1, dto.getHotleSeq());
			psmt.setInt(2, dto.getHotleSeq());
			System.out.println(sql3);
			count = psmt.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();

			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBClose.close(psmt, conn, null);
		}
		return count;

	}

	public int fileUpload(String fileName, String fileRealName) {

		String sql = " INSERT INTO IMAGEFILE(SEQ, FILENAME, FILEREALNAME)" + " VALUES (SEQ_IMAGEFILE.NEXTVAL, ?, ?) ";

		Connection conn = null;
		PreparedStatement psmt = null;

		System.out.println(sql);

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, fileName);
			psmt.setString(2, fileRealName);

			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return -1;
	}

	public int deleteOne(int seq) {

		String sql = " UPDATE REVIEW " + " SET DEL = 1 " + " WHERE SEQ = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			return psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return -1;
	}

}
