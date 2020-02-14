package model.sjh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.QnADTO;

public class QnADao {
	/*
	 * public List<QnADTO> getQnAList() { String sql =
	 * " SELECT q.seq, q.memberseq, b.id, REF, STEP, DEPTH, TITLE, CONTENT, WRITEDATE, Q.DEL, READCOUNT "
	 * + " FROM QNA q, BM_MEMBER b " + " WHERE Q.MEMBERSEQ = B.SEQ " +
	 * " ORDER BY SEQ DESC, REF DESC, STEP ASC ";
	 * 
	 * Connection conn = null; PreparedStatement psmt = null; ResultSet rs = null;
	 * 
	 * List<QnADTO> list = new ArrayList<QnADTO>();
	 * 
	 * try { conn = DBConnection.getConnection();
	 * System.out.println("1/6 getQnAList success"); psmt =
	 * conn.prepareStatement(sql); System.out.println("2/6 getQnAList success"); rs
	 * = psmt.executeQuery(); System.out.println("3/6 getQnAList success");
	 * while(rs.next()) { int i = 1; QnADTO dto = new QnADTO(rs.getInt(i++),
	 * rs.getInt(i++), rs.getString(i++), rs.getInt(i++), rs.getInt(i++),
	 * rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
	 * rs.getInt(i++), rs.getInt(i++)); list.add(dto);
	 * 
	 * } System.out.println("4/6 getQnAList success"); } catch (SQLException e) {
	 * System.out.println("getQnAList fail"); e.printStackTrace(); } finally {
	 * DBClose.close(psmt, conn, rs); } return list; }
	 */
	public QnADTO getQnADetail(int seq) {
		String sql = " SELECT q.seq, q.memberseq, b.id, REF, STEP, DEPTH, TITLE, CONTENT, WRITEDATE, q.DEL, READCOUNT "
				+ " FROM QNA q, BM_MEMBER b " + " WHERE Q.MEMBERSEQ = B.SEQ" + " AND q.seq =? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		QnADTO dto = null;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getQnADetail success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 getQnADetail success");
			rs = psmt.executeQuery();
			System.out.println("3/6 getQnADetail success");
			if (rs.next()) {
				int i = 1;
				dto = new QnADTO(rs.getInt(i++), rs.getInt(i++), rs.getString(i++), rs.getInt(i++), rs.getInt(i++),
						rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getInt(i++),
						rs.getInt(i++));

			}
			System.out.println("4/6 getQnADetail success");
		} catch (SQLException e) {
			System.out.println("getQnADetail fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
	}
	//TODO  QnA paging List
	public List<QnADTO> getQnAPagingList(String choice, String searchWord, int page) {

		String s = choice;
		String w = searchWord;
		System.out.println("DAO: " + s + " / " + w);

		String sql = " SELECT SEQ, MEMBERSEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WRITEDATE, DEL, READCOUNT "
				+ " FROM ";

		sql += " (SELECT ROW_NUMBER()OVER(ORDER BY REF DESC, STEP ASC) AS RNUM, "
				+ " q.seq, q.memberseq, b.id, REF, STEP, DEPTH, TITLE, CONTENT, WRITEDATE, q.DEL, READCOUNT "
				+ " FROM QNA q, BM_MEMBER b " + " WHERE Q.MEMBERSEQ = B.SEQ ";

		String sqlWord = "";
		if (choice.equals("title")) {
			sqlWord = " AND TITLE LIKE '%" + searchWord.trim() + "%' ";
		} else if (choice.equals("writer")) {
			sqlWord = " AND b.id='" + searchWord.trim() + "'";
		} else if (choice.equals("content")) {
			sqlWord = " AND CONTENT LIKE '%" + searchWord.trim() + "%' ";
		}
		sql += sqlWord;

		sql += " ORDER BY REF DESC, STEP ASC) ";
		sql += " WHERE RNUM >= ? AND RNUM <= ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<QnADTO> list = new ArrayList<QnADTO>();
		int start, end;
		start = 1 + 10 * page; // 0 -> 1
		end = 10 + 10 * page; // 0 -> 10

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getQnAPagingList success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			System.out.println("2/6 getQnAPagingList success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getQnAPagingList success");

			while (rs.next()) {
				int i = 1;
				QnADTO dto = new QnADTO(rs.getInt(i++), rs.getInt(i++), rs.getString(i++), rs.getInt(i++),
						rs.getInt(i++), rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
						rs.getInt(i++), rs.getInt(i++));
				list.add(dto);
			}
			System.out.println("4/6 getQnAPagingList success");

		} catch (SQLException e) {
			System.out.println("getQnAPagingList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		return list;

	}

	public int getAllQnA(String choice, String searchWord) {
		String sql = " SELECT COUNT(*) FROM QNA q, BM_MEMBER b " + " WHERE Q.MEMBERSEQ = B.SEQ ";

		String sqlWord = "";
		if (choice.equals("title")) {
			sqlWord = " AND TITLE LIKE '%" + searchWord.trim() + "%' ";
		} else if (choice.equals("writer")) {
			sqlWord = " AND b.ID='" + searchWord.trim() + "'";
		} else if (choice.equals("content")) {
			sqlWord = " AND CONTENT LIKE '%" + searchWord.trim() + "%' ";
		}
		sql += sqlWord;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int len = 0;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) {
				len = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("getAllQnA fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return len;
	}

	public void qnaReadCount(int seq) {
		String sql = " UPDATE QNA" + " SET READCOUNT = READCOUNT + 1" + " WHERE SEQ=? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);

			count = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
	}

	public boolean qnaUpdate(int seq, String title, String content) {
		String sql = " UPDATE QNA " + " SET TITLE=?, CONTENT=? " + " WHERE SEQ=? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setInt(3, seq);
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count > 0 ? true : false;
	}

	// TODO deleteQnA
	public boolean qnaDelete(int seq) {
		String sql = " UPDATE QNA" + " SET DEL=1" + " WHERE SEQ=? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count > 0 ? true : false;
	}

	// TODO QnA Write
	public boolean qnaWrite(int memberseq, String title, String content) {
		String sql = " INSERT INTO QNA(SEQ, MEMBERSEQ, REF, STEP, DEPTH, TITLE, CONTENT, WRITEDATE, DEL, READCOUNT)"
				+ " VALUES(SEQ_QNA.NEXTVAL, ?, (SELECT NVL(MAX(REF), 0)+1 FROM QNA), 0, 0, ?, ?, SYSDATE, 0, 0) ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, memberseq);
			psmt.setString(2, title);
			psmt.setString(3, content);

			count = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count > 0 ? true : false;
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

				// 부모글의 seq, 답글의 obj
	public boolean qnaComment(int seq, String loginId, String title, String content) {
		
		System.out.println("DAO: "+ seq + " / "+ loginId + " / " + title + " / " + content + " / memseq: "+ getMemberseq(loginId));
		//update : 밀어주기 먼저
		String sql1 = " UPDATE QNA " 
					+ " SET STEP = STEP + 1 " 
				    + " WHERE REF = (SELECT REF FROM QNA WHERE SEQ=?) "
				    + " AND STEP > (SELECT STEP FROM QNA WHERE SEQ=?) ";

		//insert : 새글 삽입
		String sql2 = " INSERT INTO QNA" 
				+ " (SEQ, MEMBERSEQ, REF, STEP, DEPTH," + " TITLE, CONTENT, WRITEDATE, DEL, READCOUNT)"
				+ " VALUES(SEQ_QNA.NEXTVAL, ?," 
				+ " (SELECT REF FROM QNA WHERE SEQ=?), "
				+ " (SELECT STEP FROM QNA WHERE SEQ=?)+1,"
				+ " (SELECT DEPTH FROM QNA WHERE SEQ=?)+1,"
				+ " ?, ?, SYSDATE, 0, 0) ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			System.out.println("1/6 qnaComment success");

			//update
			psmt = conn.prepareStatement(sql1);
			psmt.setInt(1, seq);
			psmt.setInt(2, seq);
			System.out.println("2/6 qnaComment success");
			count = psmt.executeUpdate();
			System.out.println("3/6 qnaComment success");

			//psmt 초기화: 한번에 두 개 이상 처리 할 때
			psmt.clearParameters();

			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1, getMemberseq(loginId));
			psmt.setInt(2, seq); // ref
			psmt.setInt(3, seq); // step
			psmt.setInt(4, seq); // depth
			psmt.setString(5, title);
			psmt.setString(6, content);
			System.out.println("4/6 qnaComment success");
			count = psmt.executeUpdate();
			System.out.println("5/6 qnaComment success");
			conn.commit();

		} catch (SQLException e) {
			System.out.println("qnaComment fail");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBClose.close(psmt, conn, null);
			System.out.println("6/6 qnaComment success");
		}
		return count > 0 ? true : false;
	}

}
