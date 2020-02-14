package model.jhj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBClose;
import db.DBConnection;
import dto.HotelDTO;

public class hotelListDAO {
	public ArrayList<HotelDTO> getHotelList(String guest, String area, String checkin, String checkout) {
		ArrayList<HotelDTO> list = new ArrayList<HotelDTO>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			
				String sql = "";
				if(area.equals("Area")) {
					sql = " SELECT H.SEQ, H.NAME, H.ADDR, R.MAX_GUEST, H.RATING FROM HOTEL H " + 
							"INNER JOIN ROOM R ON H.SEQ = R.hotelSEQ WHERE (H.SEQ, 1) IN " + 
							"(SELECT S.HOTELSEQ, COUNT(S.HOTELSEQ) FROM SCHEDULE S INNER JOIN HOTEL H ON S.HOTELSEQ = H.SEQ "  + 
							"WHERE S.RESVDATE >= '" + checkin + "' AND S.RESVDATE < '" + checkout + "' AND S.USE=0 GROUP BY S.HOTELSEQ) " + 
							"AND MAX_GUEST >= '" + guest + "' ";
				}
				else {
					sql = " SELECT H.SEQ, H.NAME, H.ADDR, R.MAX_GUEST, H.RATING FROM HOTEL H " + 
							"INNER JOIN ROOM R ON H.SEQ = R.hotelSEQ WHERE (H.SEQ, 1) IN " + 
							"(SELECT S.HOTELSEQ, COUNT(S.HOTELSEQ) FROM SCHEDULE S INNER JOIN HOTEL H ON S.HOTELSEQ = H.SEQ "  + 
							"WHERE S.RESVDATE >= '" + checkin + "' AND S.RESVDATE < '" + checkout + "' AND S.USE=0 AND H.PLACE = '" + area +"' GROUP BY S.HOTELSEQ) " + 
							"AND MAX_GUEST >= '" + guest + "' ";
				}
				System.out.println("sql :" + sql);

				psmt = conn.prepareStatement(sql);

				rs = psmt.executeQuery();

				while (rs.next()) {
					HotelDTO dto = new HotelDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
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
