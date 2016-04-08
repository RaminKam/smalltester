package sqlpack;
import java.sql.*;
import java.util.Vector;


public class Table_cans extends Table_SeveralAnswers{
	public Table_cans(Connection conn,Statement stmt){
		super(conn,stmt);
	}
	public void showTable(){
		super.showTable(ANS_TYPE.CHECK);
	}
	public void recToDB(int id, String anstext, short mode, int q_id, short corr){
		super.recToDB(ANS_TYPE.CHECK, id, anstext, mode, q_id, corr);
	}
	public void delRec(int id){
		String sql="DELETE FROM cans WHERE id="+id;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException exx) {
			exx.printStackTrace();
		}
	}
}
