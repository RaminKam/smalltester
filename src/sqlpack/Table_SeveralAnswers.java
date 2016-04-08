package sqlpack;
import java.sql.*;
import java.util.Vector;
import tester.firstclasses.*;
//import tester.firstclasses.CreatingPanel.QuesPage;
abstract class Table_SeveralAnswers extends AbsTable{//For check or radio in one class, until not needed new class for each table
	Table_SeveralAnswers(Connection conn,Statement stmt){
		super(conn,stmt);
		
	}
	enum ANS_TYPE{RADIO,CHECK};
	void showTable(ANS_TYPE ans_type) {
		String sql; 
		String table_name="";
		if(ans_type==ANS_TYPE.RADIO){
			table_name="rans";
		}else if(ans_type==ANS_TYPE.CHECK){
			table_name="cans";
		}
		sql = "SELECT * FROM "+table_name;
		try {
			ResultSet rs = stmt.executeQuery(sql);
			// ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				int id = rs.getInt("id");
				String anstext = rs.getString("anstext");
				short mode=rs.getShort("mode");
				int q_id = rs.getInt("q_id");
				short corr=rs.getShort("corr");
				System.out.println(id + "  " + anstext + "  " + mode+" "+q_id+" "+corr);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	void recToDB(ANS_TYPE ans_type,int id, String anstext, short mode, int q_id, short corr) {
		String sql;
		String table_name="";
		if(ans_type==ANS_TYPE.RADIO){
			table_name="rans";
		}else if(ans_type==ANS_TYPE.CHECK){
			table_name="cans";
		}
		sql = "INSERT INTO "+table_name+" VALUES (" + id + ", '" + anstext + "', "+mode+", "+q_id +", "+corr+")";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException exx) {
			if (exx.getErrorCode() == 1062) {
				sql = "UPDATE "+table_name+" SET anstext='" + anstext + "', mode="+mode+", q_id=" + q_id + ", corr="+corr+" WHERE id=" + id;
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
