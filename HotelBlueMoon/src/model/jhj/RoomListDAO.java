package model.jhj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBClose;
import db.DBConnection;
import dto.ResvDTO;

public class RoomListDAO {
	public ArrayList<ResvDTO> getRoomList(String guest, String checkin, String checkout, String hotelSeq, int timeCount) {
		ArrayList<ResvDTO> list = new ArrayList<ResvDTO>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			
				String sql = "";
				
					sql = " SELECT SEQ, NAME, HOTELSEQ, MAX_GUEST, ROOM_IMG "
							+ "FROM ROOM "
							+ "WHERE HOTELSEQ = '" + hotelSeq + "' AND " + 
							" (SEQ, " + timeCount + ") IN " + 
							" (SELECT S.ROOMSEQ, COUNT(S.ROOMSEQ) FROM SCHEDULE S INNER JOIN ROOM R ON S.ROOMSEQ=R.SEQ " + 
							"WHERE R.MAX_GUEST >= " + guest + " AND S.RESVDATE >= '" + checkin + "' AND S.RESVDATE < '" + checkout + "' AND S.USE=0 GROUP BY S.ROOMSEQ)"; 

				System.out.println("sql :" + sql);

				psmt = conn.prepareStatement(sql);

				rs = psmt.executeQuery();

				while (rs.next()) {
					ResvDTO dto = new ResvDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
					list.add(dto);
				}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		return list;
	}
}
