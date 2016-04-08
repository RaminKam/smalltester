package tester.firstclasses;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


class ThemeTesterMenu extends JMenuBar{

	public JPanel cardPan;
	public CardLayout cardLay;
	public CreatingPanel crpn;
	public ExecutionPanel expn;
	public ThemeTesterMenu(JPanel cardPan,CardLayout cardLay,CreatingPanel crpn,ExecutionPanel expn) {
		super();
		this.cardPan=cardPan; this.cardLay=cardLay;
		this.crpn=crpn; this.expn=expn;
		JMenu item1=new JMenu("Test");
		add(item1);
		JMenuItem item11=new JMenuItem("Execute test");
		JMenuItem item12=new JMenuItem("Create test");
		item1.add(item11);
		item1.add(item12);
		
		item11.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				
				ThemeTesterMenu.this.cardLay.show(ThemeTesterMenu.this.cardPan,"1");
				ThemeTesterMenu.this.crpn.savePage(ThemeTesterMenu.this.crpn.indRealQuestion);		//Purpose: Refresh member questions, which storage content of test
				ThemeTesterMenu.this.expn.refreshView();							//Create question pages appropriate member questions
				ThemeTesterMenu.this.expn.revalidate();
			}
		});
		item12.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				ThemeTesterMenu.this.cardLay.show(ThemeTesterMenu.this.cardPan,"2");

			}
		});

		
	}
}