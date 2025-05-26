package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class EmpLoad extends JFrame{
	JTable table;
	JScrollPane scroll;
	String[][] data = null;
	String[] columns = { "empno", "ename", "job", "mgr", "hiredate", "sal", "comm", "deptno" };
	
	public EmpLoad() {
		loadData();
		
		table = new JTable(data, columns);
		scroll = new JScrollPane(table);
		
		add(scroll);
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void loadData() {
		String url = "jdbc:mysql://localhost:3306/dev";
		String user = "java";
		String pass = "1234";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver load success");
			con = DriverManager.getConnection(url, user, pass);
			
			if(con != null) {
				System.out.println("접속 성공");	
				String sql = "select * from emp order by empno asc;";
				
				//TYPE_SCROLL_INSENSITIVE: 커서가 전방향, 후방향 및 건너뛰기 가능한, 커서의 위치를 자유자재로 조절 가능한 옵션
				// 디폴트가 TYPE_FORWARD_ONLY: 한칸씩만 움직이는 커서여서 last()가 안됨
				//CONCUR_READ_ONLY: ResultSet에 담은 레코드를 읽기 전용으로만 쓰겠다.
				pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);				
				rs = pstmt.executeQuery();
				
				//rs가 db 연동 결과이므로, 바로 이 시점 이후부터 배열의 크기를 결정지을 수 있다.
				//java에서의 배열은 선언 시 반드시 그 크기를 결정해야함
				rs.last();  //레코드 내에서 마지막 행으로 강제 이동
				int len = rs.getRow();   
				data = new String[len][8];
				
				rs.beforeFirst();   
				System.out.println(rs.getRow());
				while(rs.next()) {
					int idx = rs.getRow() - 1;   //getRow()는 1부터 시작, 첫번째 레코드의 인덱스를 1로 반환, 컬럼명 위치한 곳이 0
					for(int i = 0; i < columns.length; i++) {
						data[idx][i] = rs.getString(columns[i]);
					}				
				}
			}else {
				System.out.println("접속 실패");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new EmpLoad();
	}
	
}
