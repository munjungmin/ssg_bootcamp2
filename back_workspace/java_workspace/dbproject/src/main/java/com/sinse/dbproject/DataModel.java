package com.sinse.dbproject;

import javax.swing.table.AbstractTableModel;

/**
 * JTable은 실제 데이터를 가진 주체가 아니고, 단지 사용자들이 보게되는 UI에 지나지 않으므로 
 * JTable에 보여질 데이터는, 모델에 의존하게 됨, 이와 같이 디자인 영역(view)과 데이터 및 그 처리 로직(Model)을 분리시켜 개발하는 방법을 가리켜 MVC 개발 방법론, 패턴이라 한다.  
 */

public class DataModel extends AbstractTableModel{
	
	String[][] data; //데이터를 표현하는 이차원배열
	String[] title; //컬럼을 표현하는 일차원배열 
	
	// 층수를 반환해 JTable이 참조할 수 있도록 제공되는 메서드
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return title.length;
	}

	
	// JTable에 의해 row x col 수만큼 아래의 메서드 호출
	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	
	public String getColumnName(int column) {
		return title[column];
	}

}
