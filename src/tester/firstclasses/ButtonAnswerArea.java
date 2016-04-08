package tester.firstclasses;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;
import myutils.IdGen;
class ButtonAnswerArea{
	public IdGen idGen_rans;
	public IdGen idGen_cans;
	public Vector<Integer> ids=new Vector<Integer>();							//id-s
	//-----------------------
	public ButtonGroup bg;
	public enum Types{RADIO,CHECK}
	public Types type;
	public Vector<JTextField> fields=new Vector<JTextField>();
	public Vector<JToggleButton> buts=new Vector<JToggleButton>();

	public Vector<JPanel> panels=new Vector<JPanel>();
	public int len=0;
	public final static int fieldSize=10;
	public JPanel leftBotGroupPan=new JPanel();
	public JPanel botGroupPan=new JPanel();	
	
	public CardLayout outLay;
	public JPanel outPan;
	ButtonAnswerArea (Types type,CardLayout outLay,JPanel outPan,IdGen idGen_rans,IdGen idGen_cans){
		this.type=type;
		this.outLay=outLay; 
		this.outPan=outPan;
		this.idGen_rans=idGen_rans;
		this.idGen_cans=idGen_cans;
		
		if(type==Types.RADIO){
			bg=new ButtonGroup();
		}
		fields.setSize(0);
		buts.setSize(0);
		panels.setSize(0);
		ids.setSize(0);
		//2-Panel of answer type: one of group
		
		botGroupPan.setLayout(new FlowLayout());
		//2-Panel: Left Panel
		//JPanel leftBotGroupPan=new JPanel();
		leftBotGroupPan.setLayout(new BoxLayout(leftBotGroupPan,BoxLayout.Y_AXIS));
		botGroupPan.add(leftBotGroupPan);									//add
		
		
		//2-Panel: Right Panel
		JPanel rightBotGroupPan=new JPanel();
		botGroupPan.add(rightBotGroupPan);									//add
		class HandlerAdd implements ActionListener{
			public void actionPerformed(ActionEvent e){
				ButtonAnswerArea.this.addLine();
				ButtonAnswerArea.this.outPan.revalidate();
			}
		}
		class HandlerDel implements ActionListener{
			public void actionPerformed(ActionEvent e){
				ButtonAnswerArea.this.delLine();
				ButtonAnswerArea.this.outPan.revalidate();
			}
		}

		JButton butAddGroupPan=new JButton("Add");
		butAddGroupPan.addActionListener(new HandlerAdd());
		JButton butDelGroupPan=new JButton("Delete");
		butDelGroupPan.addActionListener(new HandlerDel());
		rightBotGroupPan.add(butAddGroupPan);
		rightBotGroupPan.add(butDelGroupPan);
	}
	public void addLine(){
		JPanel p=new JPanel();
		p.setLayout(new FlowLayout());
		if(type==Types.RADIO){
			JRadioButton r=new JRadioButton();
			bg.add(r);
			p.add(r);
			buts.add(r);
			//for id-s:
			ids.add(idGen_rans.getId());
		}
		if(type==Types.CHECK){
			JCheckBox bt=new JCheckBox();
			p.add(bt);
			buts.add(bt);
			//for id-s:
			ids.add(idGen_cans.getId());
		}
			JTextField t=new JTextField(fieldSize);
			p.add(t);
			panels.add(p);
			len++;
			fields.add(t);
			leftBotGroupPan.add(p);
		
	}
	public void delLine(){
		if(len>0){
			len--;
			JTextField txt=(JTextField)fields.get(len);
			JToggleButton bt=(JToggleButton)buts.get(len);
			JPanel pn=(JPanel)panels.get(len);
			pn.remove(txt);
			pn.remove(bt);
			fields.remove(len);
			buts.remove(len);
			leftBotGroupPan.remove(pn);
			panels.remove(len);
			//for id-s:
			if(type==Types.RADIO){
				idGen_rans.retId(ids.get(len));
				ids.remove(len);
			}
			if(type==Types.CHECK){
				idGen_cans.retId(ids.get(len));
				ids.remove(len);
			}
		}
	}
}