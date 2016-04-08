package providingguibydata;

import java.util.Vector;

public class QuesPage{
	public int id=0;
	public int typeAnswer=2;								//0-field,1-radio,2-check
	public String text=new String("");						//Question as is
	public String fieldText=new String("");					//answer field if type answer field
	public Vector<String> radioFields=new Vector<String>();
	public Vector<String> checkFields=new Vector<String>();
	public Vector<Boolean> radioBools=new Vector<Boolean>();
	public Vector<Boolean> checkBools=new Vector<Boolean>();
	//for id-s:
	public int id_field=0;									//One textfield from 3 answers types-panels
	public Vector<Integer> ids_rans=new Vector<Integer>();
	public Vector<Integer> ids_cans=new Vector<Integer>();
	
}
