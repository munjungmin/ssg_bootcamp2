package table;

import javax.swing.table.AbstractTableModel;

/**
 * JTable은 개발 분야에서 전해내려오는..많이 알려진 개발 방법(=패턴) 중 하나인 MVC 패턴을 구현한 컴포넌트이다. 
 * 완벽하진 않다. MC의 역할을 동시에 수행하므로...데이터를 보유할 뿐만 아니라, 그 데이터를 디자인 영역에 반영하는 코드도 포함돼있어야 한다.
 * 결론) 중요한 것은 JTable과 데이터를 분리시켜놓은 기술임 
 */

public class MyModel extends AbstractTableModel{
	//회원정보 (층, 호를 표현하기 위한 이차원 배열 형태의 데이터가 필요)
	String[][] rows = new String[100][3]; //100은 임의로 정한 숫자, 3은 id/name/tel
	String[] columns = {"ID", "Name", "Tel"};

	//테이블에 보여질 총 레코드 수
	@Override
	public int getRowCount() {
		return rows.length;
	}

	//테이블을 구성하는 컬럼 수 
	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	//컬럼의 이름 반환해주는 메서드
	//아래의 메서드는 컬럼 수만큼 반복되면서 호출되는데, 이때 매개변수로 넘겨받는 col의 값은 자동 증가하면서 전달되어진다. 
	public String getColumnName(int column) {
		return columns[column];
	}
	
	//getValueAt()은 rowCount x columnCount만큼 호출되면서
	//표를 이루는 각 셀(행, 열)의 좌표마다 어떠한 값을 넣을지 return이 결정한다.
	@Override
	public Object getValueAt(int row, int col) {
		return rows[row][col];
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
//		return super.isCellEditable(rowIndex, columnIndex);
		System.out.println(rowIndex+ "행, " + columnIndex + "열은 수정 가능하다.");
		return true;
	}

}
