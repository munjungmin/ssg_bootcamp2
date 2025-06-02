package com.sinse.dbproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*JTable은 껍데기에 불과하므로, 연동할 테이블이 수백개라 할지라도 TableModel은 1개면 충분하다.. 
 * 쿼리문만 바뀔 뿐
 * 
 * WindowListener처럼, 재정의할 메서드가 너무 많은 경우, 사용하지도 않는 부모의 메서드를 클래스 코드 안에 남겨놔야 하는 상황이 발생 
 * 
 * 
 * */
public class EmpLoad extends JFrame {
	JPanel p_north;
	JButton bt_emp;
	JButton bt_dept;
	JButton bt_excel;
	
	JTable table;
	JScrollPane scroll;
	DataModel model;  //본체
	
	// 윈도우 프레임이 뜰 때 한 번 접속하고, 윈도우 닫을 때 db 닫기
	String url = "jdbc:mysql://localhost:3306/dev";
	String user = "java";
	String pwd = "1234";
	
	Connection con;
	
	JFileChooser chooser = new JFileChooser(); 
	
	public EmpLoad() {
		p_north = new JPanel();
		bt_emp = new JButton("사원테이블 로드");
		bt_dept = new JButton("부서테이블 로드");
		bt_excel = new JButton("엑셀에서 로드");
		
		table = new JTable(); //테이블과 모델 연결은 반드시 생성자에서만 할 수 있는 건 아니다.
		scroll = new JScrollPane(table);
		
		
		//style
		p_north.setPreferredSize(new Dimension(800, 30));
		
		//assemble
		p_north.add(bt_emp);
		p_north.add(bt_dept);
		p_north.add(bt_excel);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		//이벤트 구현 시 정의되는 리스너 클래스는 재사용성이 없으므로, 굳이 .java 파일까지 정의해가면서 개발할 필요가 있는가?
		// 내부 클래스 중 이름없는 클래스를 가리켜 익명 내부 클래스라 한다. 
		// 주로 일회성 객체 사용 시(이벤트) 
		// 익명 내부 클래스는, 자신을 감싸고 있는 바깥쪽 외부 클래스의 멤버들을 같이 사용할 수 있다. 즉 접근할 수 있다는 점이 장점  
		bt_emp.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadEmp("select * from emp");
			}
		});
		
		bt_dept.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadEmp("select * from dept");
			}
		});
		
		bt_excel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 내부클래스는 외부클래스의 인스턴스 접근 시, 클래스명.this
				// this: ActionListener()라서 EmpLoad접근이 안됨 
				// EmpLoad.this 
				int result = chooser.showOpenDialog(EmpLoad.this);
				
				if(result == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					loadExcel(file);
				}
			}
		});

		
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				System.exit(0);  //process kill
			}
		});
		
		
		connect(); // db 접속
		
		setSize(800, 630);
		setVisible(true);
	}
	
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			
			if(con != null) {
				this.setTitle("접속 성공");
			}else {
				this.setTitle("접속 실패");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 사원 테이블 가져오기
	public void loadEmp(String query) {
		String sql = query;
		
		//쿼리 수행 객체 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//현재의 rs 자체는 JTable이 이해할 수 없는 상태이므로, TableModel에 rs를 가공하여 넣어주면 된다. 
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();  // 테이블 반환, 커서의 초기 위치는 첫번째 레코드보다 더 위쪽
			
			//현재 select문의 대상이 되는 테이블의 컬럼 정보를 프로그래밍에서 얻어오려면 ResultSetMetaData라는 객체를 이용하면 된다.
			ResultSetMetaData metaData = pstmt.getMetaData();
			int colCount = metaData.getColumnCount();
			
			rs.last();  // 가장 마지막 row로 이동 
			int rowCount = rs.getRow();
			
			//레코드 수와 컬럼수를 알아냈으니 모델객체가 보유한 현재 null인 이차원 배열을 메모리에 올리자
			model = new DataModel();
			model.data = new String[rowCount][colCount];
			model.title = new String[colCount]; // 컬럼 배열 생성
			
			for(int i = 0; i < colCount; i++) {  // 컬럼 수만큼 반복문 수행 
				model.title[i] = metaData.getColumnName(i + 1);
			}
			
			//rs의 데이터를 2차원 배열로 옮겨 심기
			rs.beforeFirst();   // rs 원위치 
			
			int index = 0;  // 층수를 접근하기 위한 index
			while(rs.next()) {
				//어떤 테이블인지는 모르나, 그 테이블의 컬럼 수만큼 접근 
				for(int i = 0; i < colCount; i++) {
					model.data[index][i] = rs.getString(i+1);
				}
				index++;
			}
			
			//모든 데이터가 완성되었으므로, JTable에 모델을 동적으로 적용 
			table.setModel(model);
			table.updateUI();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	// java 자체적인 api에는 엑셀을 연동하는 기능이 없기 때문에, apache에서 배포하는 POI라는 패키지를 사용해보자
	// 엑셀파일(WorkBook) > WorkSheet > Row > Cell	
	public void loadExcel(File file) {
		//Excel 97 ~ 2001 구버전 확장자 .xls -> HSSFWorkBook
		// 이후 버전 확장자 .xslx  -> XSSFWorkbook
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			//시트에 접근하자 
			XSSFSheet sheet = workbook.getSheetAt(0);  //첫번째 시트 얻기
			
			//Row에 접근하자 
			XSSFRow row = sheet.getRow(0);
			
			//Model의 컬럼제목 배열인 title 배열에 채워넣기 
			XSSFCell cell = row.getCell(0);  //ID
			
			
			
			model = new DataModel();
			model.title = new String[row.getLastCellNum()];
			model.data = new String[sheet.getLastRowNum()][row.getLastCellNum()];
			System.out.println(model.data.length);
			
			for(int i = 0; i < row.getLastCellNum(); i++) {
				model.title[i] = row.getCell(i).getStringCellValue();
				System.out.println(model.title[i]);
			}
			
			
			//2번째 행부터 데이터를 접근하여 Model의 data에 대입하자
			System.out.println(sheet.getFirstRowNum());
			for(int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {   // getFirstRowNum이 열 이름 
				XSSFRow r = sheet.getRow(i);
				//하나의 row를 이루고 있는 셀만틈 반복
				for(int a = 0; a < r.getLastCellNum(); a++) {
					XSSFCell c = r.getCell(a);
					model.data[i-1][a] = c.getStringCellValue();
					System.out.print(model.data[i-1][a] + " ");
					
				}
				System.out.println();
			}
			table.setModel(model);
			table.updateUI();
			
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new EmpLoad();
	}

}

/**
 * 엑셀파일(workbook)안에 여러 시트(worksheet) 가능 
 * workbook > sheet > row > cell
 */
