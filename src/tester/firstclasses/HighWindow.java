package tester.firstclasses;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import sqlpack.DBacts;
public class HighWindow extends JFrame {
	public HighWindow(String s,DBacts sqldb){
		super(s);
		int sizex=1024;
		int sizey=400;
		setLayout(new FlowLayout());
		setSize(sizex,sizey);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel cardPan=new JPanel();											//Main Panel over JFrame is needed to layout CardPanel
		add(cardPan);
		CardLayout cardLay=new CardLayout();
		cardPan.setLayout(cardLay);
		
		CreatingPanel crp=new CreatingPanel(sizex,sizey,sqldb);								//Panel for creating test
		ExecutionPanel exp=new ExecutionPanel(sizex,sizey,crp);								//Panel for execution test
		
		
		JPanel p1=exp;		//new ExecutionPanel(sizex,sizey);								//Panel for execution test
		JPanel p2=crp;		//new CreatingPanel(sizex,sizey);								//Panel for creating test
		JMenuBar mb=new ThemeTesterMenu(cardPan,cardLay,crp,exp);
		setJMenuBar(mb);
		
	
		cardPan.add(p1,"1");
		cardPan.add(p2,"2");
		cardLay.show(cardPan,"2");

	



	
		
		setVisible(true);
	}
}
