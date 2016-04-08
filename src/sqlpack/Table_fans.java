package sqlpack;
import java.sql.*;
import java.util.Vector;
//import tester.firstclasses.*;
//import tester.firstclasses.CreatingPanel.QuesPage;
public class Table_fans extends AbsTable{
	Table_fans(Connection conn,Statement stmt){
		super(conn,stmt);
	}
	public void delRec(int id){
		String sql="DELETE FROM fans WHERE id="+id;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException exx) {
			exx.printStackTrace();
		}
	}
	public void showTable() {
		String sql;
		sql = "SELECT * FROM fans";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			// ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				int id = rs.getInt("id");
				String anstext = rs.getString("anstext");
				int q_id = rs.getInt("q_id");
				System.out.println(id + "  " + anstext + "  " + q_id);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	public void recToDB(int id, String anstext, int q_id) {
		String sql;
		sql = "INSERT INTO fans VALUES (" + id + ", '" + anstext + "', " + q_id + ")";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException exx) {
			if (exx.getErrorCode() == 1062) {
				sql = "UPDATE fans SET anstext='" + anstext + "', q_id=" + q_id + " WHERE id=" + id;
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
