package mvcproject.swing.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mvcproject.blood.model.ColorManager;

public class ColorWin extends JFrame{
	JComboBox box;
	JButton bt;
	ColorManager manager = new ColorManager();
	
	public ColorWin() {
		box = new JComboBox<>();
		bt = new JButton("판단요청");
		
		//style
		box.setPreferredSize(new Dimension(175, 30));
		
		//데이터 채우기
		box.addItem("red");
		box.addItem("blue");
		box.addItem("yellow");
		box.addItem("green");
		
		setLayout(new FlowLayout());
		add(box);
		add(bt);
		
		bt.addActionListener((e)->{
			JOptionPane.showMessageDialog(this, manager.getAdvice((String)box.getSelectedItem()));
		});
		
		setSize(200, 150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	
	public static void main(String[] args) {
		new ColorWin();
	}

}
