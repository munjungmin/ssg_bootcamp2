package db;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableTest extends JFrame{
	JTable table;
	JScrollPane scroll;
	
	//데이터에 사용될 이차원 배열 
	String[][] data = {
			{"java", "python", "c#", "js", "R"},
			{"jsp", "servlet", "React", "Vue", "Node.js"},
			{"oracle", "mysql", "mariaDB", "db2", "h2"}
	};
	
	//컬럼에 사용될 일차원 배열
	String[] columns = {
			"과목1", "과목2", "과목3", "과목4", "과목5"
	};
	
	public TableTest() {
		table = new JTable(data, columns);  //row, column
		scroll = new JScrollPane(table);   //JTableHeader가 JScrollPane에 붙어있어서 컬럼명이 나오는 것임
		
		//BorderLayout에 붙여버리면, 테이블의 컬럼명이 안나옴
		setLayout(new FlowLayout());
		add(scroll);
		
		setSize(550, 550);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		new TableTest();
	}
}

