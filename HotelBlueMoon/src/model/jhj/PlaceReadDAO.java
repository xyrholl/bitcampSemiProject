package model.jhj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBClose;
import db.DBConnection;

public class PlaceReadDAO {
	public ArrayList<String> getPlaceList() {
		ArrayList<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			
				String sql = "";
				sql = " SELECT PLACE FROM HOTEL GROUP BY PLACE ";
				System.out.println("sql :" + sql);

				psmt = conn.prepareStatement(sql);

				rs = psmt.executeQuery();

				while (rs.next()) {
					list.add(rs.getString(1));
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
