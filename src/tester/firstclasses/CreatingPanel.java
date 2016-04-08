package tester.firstclasses;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import sqlpack.*;

import java.util.*;
import myutils.IdGen;
import providingguibydata.QuesPage;
public class CreatingPanel extends JPanel{
	public IdGen idGen_rans=new IdGen(1000);
	public IdGen idGen_cans=new IdGen(1000);
	DBacts sqldb;
	public IdGen idGen=new IdGen(1000);
	public IdGen idGen_fans=new IdGen(1000);
	public int numOfQuestions=1;
	public int indRealQuestion=0;

	
	public Vector<QuesPage> questions=new Vector<QuesPage>();
	public class AddQuestionBut implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ansCardLay.show(cardAnsPan,"rgroup");
			cardAnsPan.revalidate();
		}
	}
	//--^^ this is ^^
	public JLabel lb1;
	public JTextArea quesTa;
	public JPanel radioButPan=new JPanel();
	public JPanel cardAnsPan=new JPanel();
	public CardLayout ansCardLay=new CardLayout();
	
	public JRadioButton type1;
	public JRadioButton type2;
	public JRadioButton type3;
	public ButtonGroup bg=new ButtonGroup();
	public ButtonAnswerArea radioCardArea;
	public ButtonAnswerArea checkCardArea;
	public JTextField ansField;
	//---------------------------------------
	public class HandlerNewQuesBut implements ActionListener{
		public void actionPerformed(ActionEvent e){
			QuesPage realPage=new QuesPage();
			//For ids:
			realPage.id=questions.get(indRealQuestion).id;
			realPage.id_field=questions.get(indRealQuestion).id_field;
			realPage.text=quesTa.getText();
			JRadioButton[] bufR=new JRadioButton[3];
			bufR[0]=type1;
			bufR[1]=type2;
			bufR[2]=type3;
			for(realPage.typeAnswer=0;realPage.typeAnswer<bg.getButtonCount();realPage.typeAnswer++){
				if(bufR[realPage.typeAnswer].isSelected())
					break;
			}
			realPage.fieldText=ansField.getText();
			
			int sizeOfRadio=radioCardArea.fields.size();
			

			for(int y=0;y<sizeOfRadio;y++){
				realPage.radioFields.add(radioCardArea.fields.get(y).getText());
				if(radioCardArea.buts.get(y).isSelected())
					realPage.radioBools.addElement(true);
				else
					realPage.radioBools.addElement(false);
				//Saving id-s:
				realPage.ids_rans.add(radioCardArea.ids.get(y));
			}
			
			int sizeOfCheck=checkCardArea.fields.size();

			for(int y=0;y<sizeOfCheck;y++){
				realPage.checkFields.add(checkCardArea.fields.get(y).getText());
				if(checkCardArea.buts.get(y).isSelected())
					realPage.checkBools.addElement(true);
				else
					realPage.checkBools.addElement(false);
				//Saving id-s:
				realPage.ids_cans.add(checkCardArea.ids.get(y));
			}
			
			
			//QuesPage oldPage=questions.get(indRealQuestion);
			questions.set(indRealQuestion, realPage);				//Replace last changed page on more later
			//Now all page saved in questions
	
			//Creating new page use old Panels
			
			quesTa.setText("");
			ansField.setText("");
			type3.setSelected(true);
			while(radioCardArea.len!=0)
				radioCardArea.delLine();
			while(checkCardArea.len!=0)
				checkCardArea.delLine();
			cardAnsPan.revalidate();
			
			//Create empty realPage
			realPage=new QuesPage();	
			realPage.id=idGen.getId();									//getting new id for new question						[GenId]
			realPage.id_field=idGen_fans.getId();						//getting new if for new question's answer field type	[GenId]
			questions.add(indRealQuestion+1,realPage);
			//Go in numbers:
			indRealQuestion++;
			numOfQuestions++;
			lb1.setText(Integer.toString(indRealQuestion));
			//And need to repaint answer panel(show card)
			switch(realPage.typeAnswer){
			case 0:
				ansCardLay.show(cardAnsPan,"field");
				break;
			case 1:
				ansCardLay.show(cardAnsPan,"rgroup");
				break;
			case 2:
				ansCardLay.show(cardAnsPan,"cgroup");
				break;
			}
			
			
			cardAnsPan.revalidate();
			
		}
	}
	public void savePage(int pageInd){						//Save all content of working page with index pageInd in vector questions
		QuesPage realPage=new QuesPage();
		//Rewriting id-s:
		realPage.id=this.questions.get(pageInd).id;
		realPage.id_field=this.questions.get(pageInd).id_field;
		
		realPage.text=quesTa.getText();
		JRadioButton[] bufR=new JRadioButton[3];
		bufR[0]=type1;
		bufR[1]=type2;
		bufR[2]=type3;
		for(realPage.typeAnswer=0;realPage.typeAnswer<bg.getButtonCount();realPage.typeAnswer++){
			if(bufR[realPage.typeAnswer].isSelected())
				break;
		}
		realPage.fieldText=ansField.getText();
		
		int sizeOfRadio=radioCardArea.fields.size();
		for(int y=0;y<sizeOfRadio;y++){
			realPage.radioFields.add(radioCardArea.fields.get(y).getText());
			if(radioCardArea.buts.get(y).isSelected())
				realPage.radioBools.addElement(true);
			else
				realPage.radioBools.addElement(false);
			//for id-s:
			realPage.ids_rans.add(radioCardArea.ids.get(y));
		}
		
		int sizeOfCheck=checkCardArea.fields.size();
		for(int y=0;y<sizeOfCheck;y++){
			realPage.checkFields.add(checkCardArea.fields.get(y).getText());
			if(checkCardArea.buts.get(y).isSelected())
				realPage.checkBools.addElement(true);
			else
				realPage.checkBools.addElement(false);
			//for id-s:
			realPage.ids_cans.add(checkCardArea.ids.get(y));
		}
		
		
		questions.set(pageInd, realPage);				//Replace last changed page on more later
		
	}
	public void viewPage(int pageInd){							//Clear old image of page and repaint page with index of vector
		//First - clear:
		lb1.setText(Integer.toString(pageInd));
		quesTa.setText("");
		ansField.setText("");
		while(radioCardArea.len!=0)
			radioCardArea.delLine();
		while(checkCardArea.len!=0)
			checkCardArea.delLine();
		QuesPage realPage=questions.get(pageInd);
		//System.out.println("pageInd="+pageInd+" (QuesPage)radio="+realPage.ids_rans.size()+" (QuesPage)check="+realPage.ids_cans.size());
		quesTa.setText(realPage.text);

		ansField.setText(realPage.fieldText);
		JRadioButton[] bufR=new JRadioButton[3];
		bufR[0]=type1;
		bufR[1]=type2;
		bufR[2]=type3;
		bufR[realPage.typeAnswer].setSelected(true);			//select choicer button type answer
		
		
		int sizeOfRadio=realPage.radioFields.size();
		for(int y=0;y<sizeOfRadio;y++){
			radioCardArea.addLine();
			radioCardArea.fields.get(y).setText(realPage.radioFields.get(y).toString());
			radioCardArea.buts.get(y).setSelected(realPage.radioBools.get(y).booleanValue());
			//For id-s(it's bad, nut it is)
			idGen_rans.retId(radioCardArea.ids.get(y));				//return created by addLine new id, for line was providing id in past
			radioCardArea.ids.set(y,realPage.ids_rans.get(y));		//set saved old id
		}
		int sizeOfCheck=realPage.checkFields.size();
		for(int y=0;y<sizeOfCheck;y++){
			checkCardArea.addLine();
			checkCardArea.fields.get(y).setText(realPage.checkFields.get(y).toString());
			checkCardArea.buts.get(y).setSelected(realPage.checkBools.get(y).booleanValue());
			
			idGen_cans.retId(checkCardArea.ids.get(y));				//return created by addLine new id, for line was providing id in past
			checkCardArea.ids.set(y,realPage.ids_cans.get(y));		//set saved old id
			
		}
		//And need to repaint answer panel(show card)
		switch(realPage.typeAnswer){
		case 0:
			ansCardLay.show(cardAnsPan,"field");
			break;
		case 1:
			ansCardLay.show(cardAnsPan,"rgroup");
			break;
		case 2:
			ansCardLay.show(cardAnsPan,"cgroup");
			break;
		}
		
		
		cardAnsPan.revalidate();								//review image of GUI
		
	}
	public class HandlerDelQuesBut implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(numOfQuestions==1)
				return;
			CreatingPanel.this.sqldb.deleteQues(CreatingPanel.this.questions.get(indRealQuestion));			//we must delete from db 
			idGen.retId(questions.get(indRealQuestion).id);									//return id(ques) after using in list vacant ids			[GenId]
			idGen_fans.retId(questions.get(indRealQuestion).id_field);						//return id(fans) after using in list vacant ids			[GenId]
			for(int i=0;i<radioCardArea.ids.size();i++)
				idGen_rans.retId(radioCardArea.ids.get(i));
			for(int i=0;i<checkCardArea.ids.size();i++)
				idGen_cans.retId(checkCardArea.ids.get(i));
			questions.remove(indRealQuestion);
			//Change data reference on vector appropriated to vector and to worked page
			if(numOfQuestions-1==indRealQuestion){
				numOfQuestions--;
				indRealQuestion--;
			}
			else
			{
				numOfQuestions--;
			}
			CreatingPanel.this.viewPage(indRealQuestion);
			System.out.println("HandlerDelQuesBut");
			
		}
	}
	public class HandlerNextQuesBut implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(indRealQuestion==numOfQuestions-1)
				return;
			CreatingPanel.this.savePage(indRealQuestion);
			indRealQuestion++;
			CreatingPanel.this.viewPage(indRealQuestion);
			
		}
	}
	public class HandlerPrevQuesBut implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(indRealQuestion==0)
				return;
			CreatingPanel.this.savePage(indRealQuestion);
			indRealQuestion--;
			CreatingPanel.this.viewPage(indRealQuestion);
		}
	}
	public class HandlerAddToDBBut implements ActionListener{//Add question to database
		public void actionPerformed(ActionEvent e){
			CreatingPanel.this.savePage(CreatingPanel.this.indRealQuestion);
			QuesPage pg=CreatingPanel.this.questions.get(CreatingPanel.this.indRealQuestion);
			//CreatingPanel.this.showMetaDataPage(pg);
			CreatingPanel.this.sqldb.addPageToDB(pg);

			
			


		}
	}
	public class HandlerRefreshBut implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("Hello");
//			QuesPage pg=CreatingPanel.this.questions.get(CreatingPanel.this.indRealQuestion);
//			CreatingPanel.this.showMetaDataPage(pg);
			CreatingPanel.this.updatingAllIds();
			CreatingPanel.this.downloadData();

		}
	}
	//---------------------------------------
	public class HandlerField implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ansCardLay.show(cardAnsPan,"field");
			cardAnsPan.revalidate();
		}
	}
	public class HandlerRadio implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ansCardLay.show(cardAnsPan,"rgroup");
			cardAnsPan.revalidate();
		}
	}
	public class HandlerCheck implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ansCardLay.show(cardAnsPan,"cgroup");
			cardAnsPan.revalidate();
		}
	}
	CreatingPanel(int sizex, int sizey,DBacts sqldb){
		super();
		this.sqldb=sqldb;
		questions.setSize(1);
		questions.set(0,new QuesPage());					//In begin only empty page
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		lb1=new JLabel("0");
		quesTa=new JTextArea(5,50);


		type1=new JRadioButton("Field");
		
		type1.addActionListener(new HandlerField());
		type2=new JRadioButton("Radio");
		type2.addActionListener(new HandlerRadio());
		type3=new JRadioButton("Check");
		type3.addActionListener(new HandlerCheck());
		type3.setSelected(true);

		radioButPan.setLayout(new BoxLayout(radioButPan,BoxLayout.X_AXIS));
		radioButPan.setBorder(BorderFactory.createEtchedBorder());
		
		bg.add(type1); bg.add(type2); bg.add(type3);										//! No Container method "add", only grouping
		radioButPan.add(type1); radioButPan.add(type2); radioButPan.add(type3);
		
		
		JButton bCreateQues=new JButton("New question");
		bCreateQues.addActionListener(new HandlerNewQuesBut());
		JButton bDeleteQues=new JButton("Delete question");
		bDeleteQues.addActionListener(new HandlerDelQuesBut());
		JButton bNextQues=new JButton("Next question");
		bNextQues.addActionListener(new HandlerNextQuesBut());
		JButton bPrevQues=new JButton("Previous question");
		bPrevQues.addActionListener(new HandlerPrevQuesBut());
		JButton bAddToDB=new JButton("Add to database");
		bAddToDB.addActionListener(new HandlerAddToDBBut());
		JButton bRefresh=new JButton("Refresh");
		bRefresh.addActionListener(new HandlerRefreshBut());
		JPanel actsPan=new JPanel();
		
		actsPan.setLayout(new GridLayout(6,1,0,0));
		
		actsPan.add(bCreateQues);
		actsPan.add(bDeleteQues);
		actsPan.add(bNextQues);
		actsPan.add(bPrevQues);
		actsPan.add(bAddToDB);
		actsPan.add(bRefresh);
		
		JPanel topPan=new JPanel();
		topPan.add(lb1);
		topPan.add(quesTa);
		topPan.add(actsPan);
		add(topPan);
		add(radioButPan);
		
		//At down panels:
		//1-Panel of answer type: field
		JPanel ansFieldPan=new JPanel();
		ansFieldPan.setLayout(new FlowLayout());
		ansField=new JTextField(20);
		ansFieldPan.add(ansField);											//add
		

		//CardPanel<-(1-Panel+2-Panel)

		add(cardAnsPan);
		
		radioCardArea=new ButtonAnswerArea(ButtonAnswerArea.Types.RADIO,ansCardLay,cardAnsPan,idGen_rans,idGen_cans);
		checkCardArea=new ButtonAnswerArea(ButtonAnswerArea.Types.CHECK,ansCardLay,cardAnsPan,idGen_rans,idGen_cans);
		cardAnsPan.setLayout(ansCardLay);
		cardAnsPan.add(ansFieldPan,"field");
		cardAnsPan.add(radioCardArea.botGroupPan,"rgroup");
		cardAnsPan.add(checkCardArea.botGroupPan,"cgroup");
		ansCardLay.show(cardAnsPan,"cgroup");
		
		updatingAllIds();
		downloadData();
		
	}
	void downloadData(){
		Vector<QuesPage> qss=CreatingPanel.this.sqldb.getData();
		if(qss.size()==0){
			System.out.println("Database hasn't questions!");
			return;
			}
		CreatingPanel.this.questions=qss;
		numOfQuestions=questions.size();
		CreatingPanel.this.viewPage(indRealQuestion);
	}
	void updatingAllIds(){								//update all ids appropriated PK in each table from database
		ArrayList<Integer> arr_ques=sqldb.getUsedIdList(DBacts.TABLE_NAME.QUES);
		ArrayList<Integer> arr_fans=sqldb.getUsedIdList(DBacts.TABLE_NAME.FANS);
		ArrayList<Integer> arr_rans=sqldb.getUsedIdList(DBacts.TABLE_NAME.RANS);
		ArrayList<Integer> arr_cans=sqldb.getUsedIdList(DBacts.TABLE_NAME.CANS);
		
		for(int i=0;i<arr_ques.size();i++)
			this.idGen.conf(arr_ques.get(i));
		for(int i=0;i<arr_fans.size();i++)
			this.idGen_fans.conf(arr_fans.get(i));
		for(int i=0;i<arr_rans.size();i++)
			this.idGen_rans.conf(arr_rans.get(i));
		for(int i=0;i<arr_cans.size();i++)
			this.idGen_cans.conf(arr_cans.get(i));
	}
	void showMetaDataPage(QuesPage pg){
		//QuesPage pg=CreatingPanel.this.questions.get(CreatingPanel.this.indRealQuestion);
		System.out.println("\n\nquestions.size="+CreatingPanel.this.questions.size()+" indRealQues="+CreatingPanel.this.indRealQuestion);
		System.out.println("pageid="+pg.id);
		//System.out.println("type="+pg.typeAnswer);
		switch(pg.typeAnswer){
		case 0:
			System.out.println("fans: id="+pg.id_field+" text="+pg.fieldText);
			break;
		case 1:
			System.out.println("rans");
			for(int i=0;i<pg.ids_rans.size();i++){
				
				System.out.println(pg.ids_rans.get(i)+" "+ pg.radioFields.get(i)+" "+ pg.radioBools.get(i));
			}
			break;
		case 2:
			System.out.println("cans");
			for(int i=0;i<pg.ids_cans.size();i++){
				System.out.println(pg.ids_cans.get(i)+" "+ pg.checkFields.get(i)+" "+ pg.checkBools.get(i));
			}
			break;
		}
	}
}