package sqlpack;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import tester.firstclasses.*;
import providingguibydata.QuesPage;

import sqlpack.Table_ques;
public class DBacts {
	   String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   String DB_URL = "jdbc:mysql://localhost/testdatabase";
	   String USER = "root";
	   String PASS = "root";
	   Connection conn = null;
	   Statement stmt = null;	   
	   public enum TABLE_NAME{QUES,FANS,RANS,CANS};
	   public Table_ques ques;
	   public Table_fans fans;
	   public Table_rans rans;
	   public Table_cans cans;
	   public void deleteQues(QuesPage pg){
		   try{
			   System.out.println("Delete page with: pgid="+pg.id);
			   Statement stmt1=conn.createStatement();
			   //Statement stmt2=conn.createStatement();
			   String sql1="DELETE FROM fans WHERE q_id="+pg.id;
			   String sql2="DELETE FROM rans WHERE q_id="+pg.id;
			   String sql3="DELETE FROM cans WHERE q_id="+pg.id;
			   String sql4="DELETE FROM ques WHERE id="+pg.id;
			   stmt1.executeUpdate(sql1);
			   stmt1.executeUpdate(sql2);
			   stmt1.executeUpdate(sql3);
			   stmt1.executeUpdate(sql4);
			   			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	   }
	   public void addPageToDB(QuesPage pg){
		   //delete all answers, appropriated old question id
		   try{
			   Statement stmt1=conn.createStatement();
			   //Statement stmt2=conn.createStatement();
			   String sql1="DELETE FROM fans WHERE q_id="+pg.id;
			   String sql2="DELETE FROM rans WHERE q_id="+pg.id;
			   String sql3="DELETE FROM cans WHERE q_id="+pg.id;
			   stmt1.executeUpdate(sql1);
			   stmt1.executeUpdate(sql2);
			   stmt1.executeUpdate(sql3);
			   ques.recToDB(pg.id, pg.text, pg.typeAnswer);
			   switch(pg.typeAnswer){
			   case 0:
				   fans.recToDB(pg.id_field, pg.fieldText, pg.id);
				   break;
			   case 1:
				   
				   for(int i=0;i<pg.ids_rans.size();i++){
					   short buf1=(short)(pg.radioBools.get(i) ? 1:0);
					   rans.recToDB(pg.ids_rans.get(i), pg.radioFields.get(i), (short)1, pg.id,buf1);
					   
				   }
				   break;
			   case 2:
				   for(int i=0;i<pg.ids_cans.size();i++){
					   short buf1=(short)(pg.checkBools.get(i) ? 1:0);
					   cans.recToDB(pg.ids_cans.get(i), pg.checkFields.get(i), (short)1, pg.id,buf1);
				   }
				   break;
			   }
			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	   }
	   public Vector<QuesPage> getData(){
		   Vector<QuesPage> questions=new Vector<QuesPage>();
		   questions.setSize(0);

		   int numLines=0;
		   String sql;
		   sql = "SELECT * FROM ques";
		   try{
			   ResultSet rs = stmt.executeQuery(sql);
			   while(rs.next()){
				   //fill answers for certain question
				   numLines++;
				   QuesPage pg=new QuesPage();
				   int id=rs.getInt("id");
				   pg.id=id;
				   String text=rs.getString("text");
				   pg.text=text.substring(0);
				   
				   int type=rs.getInt("type");
				   if(rs.wasNull()){
					   System.out.println("value last column was NULL!");
					   questions.add(pg);
					   continue;
					   }else
					   {
						   pg.typeAnswer=type;
					   }
				   //Handling answer-data
				   Statement stmt1 = conn.createStatement();
				   switch(type){
				   case 0:
					   String freq="SELECT * FROM fans WHERE q_id="+Integer.toString(id);
					   ResultSet frs = stmt1.executeQuery(freq);
					   if(frs.next()){
						   pg.id_field=frs.getInt("id");
						   pg.fieldText=frs.getString("anstext");
					   }else{
						   System.out.println("DB Error - for id from ques="+pg.id+" not found answer");
					   }
					   break;
				   case 1:
					   String rreq="SELECT * FROM rans WHERE q_id="+Integer.toString(id);
					   ResultSet rrs = stmt1.executeQuery(rreq);
					   while(rrs.next()){
							pg.ids_rans.add(rrs.getInt("id"));
							pg.radioFields.add(rrs.getString("anstext"));
							short rcorr=rrs.getShort("corr");
							if(rcorr>0)
								pg.radioBools.add(true);
							else
								pg.radioBools.add(false);
					   }
					   break;
				   case 2:
					   String creq="SELECT * FROM cans WHERE q_id="+Integer.toString(id);
					   ResultSet crs = stmt1.executeQuery(creq);
					   while(crs.next()){
							pg.ids_cans.add(crs.getInt("id"));
							pg.checkFields.add(crs.getString("anstext"));
							short ccorr=crs.getShort("corr");
							if(ccorr>0)
								pg.checkBools.add(true);
							else
								pg.checkBools.add(false);
					   }
					   break;
				   }
				   questions.add(pg);
  
				}
  
		   }catch(Exception e){
			   System.out.println(e);
			   e.printStackTrace();
		   }
		   return questions;
	   }
	   public DBacts(){
		   try{
			      Class.forName("com.mysql.jdbc.Driver");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);
			      stmt = conn.createStatement();
			      ques=new Table_ques(conn,stmt);
			      fans=new Table_fans(conn,stmt);
			      rans=new Table_rans(conn,stmt);
			      cans=new Table_cans(conn,stmt);
			   }catch(SQLException se){
			      se.printStackTrace();
			   }catch(Exception e){
			      e.printStackTrace();
			   }
	   }
	   public ArrayList<Integer> getUsedIdList(TABLE_NAME table_name){
		   ArrayList<Integer> arr=new ArrayList<Integer>();
		   arr.clear();
		   String strTableName="";
		   switch(table_name){
		   case QUES:
			   strTableName="ques";
			   break;
		   case FANS:
			   strTableName="fans";
			   break;
		   case RANS:
			   strTableName="rans";
			   break;
		   case CANS:
			   strTableName="cans";
			   break;
		   }
		   String sql="SELECT * FROM "+strTableName;
			try {
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					int id = rs.getInt("id");
					arr.add(id);
				}
				rs.close();
			} catch (SQLException e) {
				System.out.println(e);

			}
		   return arr;
		   
	   }
	   public void readFromDB(CreatingPanel pan){
		   
	   }
	   

	   


	   public void freeRes(){
		   try{
		      stmt.close();
		      conn.close();
	   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }finally{
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
	   }
	   
}
