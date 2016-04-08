package sqlpack;
import java.sql.*;
import java.util.Vector;


import tester.firstclasses.*;
public class Table_rans extends Table_SeveralAnswers{
	public Table_rans(Connection conn,Statement stmt){
		super(conn,stmt);
	}
	public void showTable(){
		super.showTable(ANS_TYPE.RADIO);
	}
	public void recToDB(int id, String anstext, short mode, int q_id, short corr){
		super.recToDB(ANS_TYPE.RADIO, id, anstext, mode, q_id, corr);
	}
	public void delRec(int id){
		String sql="DELETE FROM rans WHERE id="+id;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException exx) {
			exx.printStackTrace();
		}
	}

}
