package tester.firstclasses;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import providingguibydata.QuesPage;

class ExecPage extends JPanel{									//Certain page of all pages as a panel 
	public CardLayout lay;
	public JPanel cards;
	public String name;
	public JButton butNext=new JButton("Next");
	public JButton butPrev=new JButton("Previous");
	public JLabel quesText=new JLabel();
	JTextField fieldAnswer=new JTextField(10);
	public CreatingPanel crpn;
	public int indx=0;
	public int typeAnswer;										//type of answer: 0-field, 1-radio, 2-check
	
	
	Vector<JToggleButton> buts=new Vector<JToggleButton>();
	Vector<JLabel> lbFields=new Vector<JLabel>();
	Vector<JPanel> panels=new Vector<JPanel>();
	ButtonGroup bg=new ButtonGroup();
	
	QuesPage pg;
	
	ExecPage(CardLayout lay, JPanel cards,String name,CreatingPanel crpn,int indx) {
		super();
		this.lay=lay;
		this.cards=cards;
		this.name=name;
		this.crpn=crpn;
		this.indx=indx;
		pg=crpn.questions.get(indx);								//Get reference on data of needed page
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JPanel highPan=new JPanel();
		JPanel lowPan=new JPanel();
		add(highPan);
		add(lowPan);
		
		highPan.setLayout(new BoxLayout(highPan,BoxLayout.X_AXIS));
		lowPan.setLayout(new BoxLayout(lowPan,BoxLayout.Y_AXIS));
		
		JPanel highPan_Left=new JPanel();
		JPanel highPan_Right=new JPanel();
		highPan.add(highPan_Left);
		highPan.add(highPan_Right);
		
		highPan_Left.add(quesText);
		quesText.setSize(500, 150);
		highPan_Right.add(butPrev);
		highPan_Right.add(butNext);
		butPrev.setSize(100,50);
		butNext.setSize(100,50);
		
		quesText.setText((String)Integer.toString(indx+1)+"/"+Integer.toString(crpn.questions.size())+":"+pg.text);
		butNext.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				ExecPage.this.viewNext();
			}
		});
		butPrev.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				ExecPage.this.viewPrev();
			}
		});
		
		
		typeAnswer=pg.typeAnswer;

		switch(typeAnswer){
		case 0:
			lowPan.add(fieldAnswer);
			break;
		case 1:
			int numOfLines=pg.radioBools.size();
			for(int i=0;i<numOfLines;i++){
				panels.add(new JPanel());
				JPanel npn=panels.get(i);
				npn.setLayout(new FlowLayout());
				lowPan.add(npn);
				
				buts.add(new JRadioButton());
				bg.add(buts.get(i));
				npn.add(buts.get(i));
				
				lbFields.add(new JLabel(pg.radioFields.get(i).toString()));
				npn.add(lbFields.get(i));
			}
			break;
		case 2:
			int numOfLinesC=pg.checkBools.size();
			for(int i=0;i<numOfLinesC;i++){
				panels.add(new JPanel());
				JPanel npn=panels.get(i);
				npn.setLayout(new FlowLayout());
				lowPan.add(npn);
				
				buts.add(new JCheckBox());
				npn.add(buts.get(i));
				
				lbFields.add(new JLabel(pg.checkFields.get(i).toString()));
				npn.add(lbFields.get(i));
			}
			break;
		}
		
	}
	public void viewThis(){
		lay.show(cards,name);
	}
	public void viewNext(){
		lay.next(cards);
	}
	public void viewPrev(){
		lay.previous(cards);
	}
}