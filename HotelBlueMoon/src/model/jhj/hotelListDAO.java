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
	public ArrayList<HotelDTO> getHotelList(ArrayList<String> hotelNameList, String guest, String area) {
		ArrayList<HotelDTO> list = new ArrayList<HotelDTO>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			for (int i = 0; i < hotelNameList.size(); i++) {
				String sql = "";
				if(area.equals("Area")) {
					sql = " SELECT hotel.NAME, hotel.ADDR, room.MAX_GUEST, hotel.RATING FROM HOTEL"
							+ " INNER JOIN ROOM ON HOTEL.SEQ = ROOM.hotelSEQ WHERE "
							+ " MAX_GUEST >= '" + Integer.parseInt(guest) + "' AND hotel.NAME = '"
							+ hotelNameList.get(i) + "'";
				}
				else {
					sql = " SELECT hotel.NAME, hotel.ADDR, room.MAX_GUEST, hotel.RATING FROM HOTEL"
							+ " INNER JOIN ROOM ON HOTEL.SEQ = ROOM.hotelSEQ WHERE PLACE = '" + area + "'"
							+ " AND MAX_GUEST >= '" + Integer.parseInt(guest) + "' AND hotel.NAME = '"
							+ hotelNameList.get(i) + "'";
				}
				System.out.println("sql :" + sql);

				psmt = conn.prepareStatement(sql);

				rs = psmt.executeQuery();

				while (rs.next()) {
					HotelDTO dto = new HotelDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
					list.add(dto);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		return list;
	}

	public ArrayList<String> getHotelTimeList(String checkin, String checkout, String area,
			int timeCount) {
		String sql = "";
		if(area.equals("Area")) {
			sql = " SELECT NAME, COUNT(NAME) " + "FROM SCHEDULE " + "WHERE RESVDATE BETWEEN '" + checkin
					+ " 00' AND '" + checkout + " 00' "
					+ "AND USE=0 GROUP BY NAME";
		}
		else {
			sql = " SELECT NAME, COUNT(NAME) " + "FROM SCHEDULE " + "WHERE RESVDATE BETWEEN '" + checkin
					+ " 00' AND '" + checkout + " 00' " + "AND PLACE = '" + area + "' "
					+ "AND USE=0 GROUP BY NAME";
		}
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		System.out.println("sql :" + sql);

		ArrayList<String> list = new ArrayList<String>();

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

			while (rs.next()) {
				if (rs.getInt(2) == timeCount) {
					System.out.println(rs.getInt(2));
					String hotelName = rs.getString(1);
					list.add(hotelName);
				}
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
