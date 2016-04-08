package sqlpack;
import java.sql.*;
import java.util.Vector;
//import tester.firstclasses.*;
//import tester.firstclasses.CreatingPanel.QuesPage;

import sqlpack.AbsTable;;
public class Table_ques extends AbsTable{
	public Table_ques(Connection conn,Statement stmt) {
		super(conn,stmt);
	}
	public void delRec(int id){
		String sql="DELETE FROM ques WHERE id="+id;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException exx) {
			exx.printStackTrace();
		}
	}

	public void showTable() {
		String sql;
		sql = "SELECT * FROM ques";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			// ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				int id = rs.getInt("id");
				String text = rs.getString("text");
				int type = rs.getInt("type");
				System.out.println(id + "  " + text + "  " + type);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	public void recToDB(int id, String text, int type) {
		String sql;
		sql = "INSERT INTO ques VALUES (" + id + ", '" + text + "', " + type + ")";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException exx) {
			if (exx.getErrorCode() == 1062) {
				sql = "UPDATE ques SET text='" + text + "', type=" + type + " WHERE id=" + id;
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException exxx) {
					exxx.printStackTrace();
				}
			} else {
				exx.printStackTrace();

			}
		}
	}
}
