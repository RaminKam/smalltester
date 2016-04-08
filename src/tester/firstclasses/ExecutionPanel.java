package tester.firstclasses;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import java.util.*;
import providingguibydata.QuesPage;

class ExecutionPanel extends JPanel{
	CreatingPanel crpn;
	CardLayout cardLay=new CardLayout();						//Provide viewing all pages of questions
	JPanel cards=new JPanel();									//On Execution panel be panel with CardLayout which names cards
	int numOfPages=0;
	JButton finish=new JButton("Finish test");
	Vector<ExecPage> pages=new Vector<ExecPage>();
	ExecutionPanel(int sizex, int sizey, CreatingPanel crpn){
		super();
		this.crpn=crpn;
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));		//setLayout(new FlowLayout());
		add(cards);
		cards.setLayout(cardLay);
		add(finish);

		finish.addActionListener(new HandlerFinish());

	}
	public void refreshView(){
		cards.removeAll();
		pages.clear();			
		numOfPages=crpn.questions.size();
		for(int i=0;i<numOfPages;i++){							//(CardLayout lay, JPanel cards,String name,CreatingPanel crpn,int indx) {
			pages.add(new ExecPage(cardLay,cards,Integer.toString(i),crpn,i));
			cards.add(pages.get(i),Integer.toString(i));
		}
		finish.setVisible(true);
	}
	class HandlerFinish extends JPanel implements ActionListener{
		int numCorrectAnswers=0;
		Vector<Boolean> crtAnsws=new Vector<Boolean>();
		JLabel result=new JLabel();
		HandlerFinish(){
			super();

			add(result);
		}
		public void checkAnswers(){
			for(int i=0;i<pages.size();i++){
				ExecPage mypg=pages.get(i);
				QuesPage mst=crpn.questions.get(i);
				int y;
				switch(mypg.typeAnswer){
				case 0:											//If user answer in field
					if(mst.fieldText.equals(mypg.fieldAnswer.getText())){
						crtAnsws.add(true);
						numCorrectAnswers++;
//						System.out.println(mst.fieldText);
//						System.out.println(mypg.fieldAnswer.getText());
						}
					else
						crtAnsws.add(false);
					break;
				case 1:
					for(y=0;y<mypg.buts.size();y++){
						if(!(mypg.buts.get(y).isSelected()==mst.radioBools.get(y).booleanValue()))
							break;
					}
					if(y==mypg.buts.size()){
						crtAnsws.add(true);
						numCorrectAnswers++;
					}
					else
						crtAnsws.add(false);
					break;
				case 2:
					for(y=0;y<mypg.buts.size();y++){
						if(!(mypg.buts.get(y).isSelected()==mst.checkBools.get(y).booleanValue()))
							break;
					}
					if(y==mypg.buts.size()){
						crtAnsws.add(true);
						numCorrectAnswers++;
					}
					else
						crtAnsws.add(false);
					break;
				}
				
			}
		}
		public void actionPerformed(ActionEvent e){
			finish.setVisible(false);
			checkAnswers();
			String str="Result="+Integer.toString(numCorrectAnswers)+" correct answers";
			result.setText(str);
			cards.add(this,"finish");
			cardLay.show(cards, "finish");
			numCorrectAnswers=0;

		}
	}

}