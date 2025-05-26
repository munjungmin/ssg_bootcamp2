package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/dev";
		String user = "java";
		String pass = "1234";
		Connection con = null;  
		PreparedStatement pstmt = null;  //쿼리 수행 객체, 쿼리문마다 1:1대응됨 -> 3개 쿼리를 수행할 경우 3개를 만들어야함
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Load Success!!");
			
			
			//접속- 다른 언어와 달리 Connection 객체는 접속이 성공되면, 메모리에 올라오는 결과물, 접속 정보를 가진 객체이다. (접속 시도 객체가 아니다)
			con = DriverManager.getConnection(url, user, pass);
			
			if(con != null) {
				System.out.println("접속 성공");
				
				String sql = "select * from emp";
				pstmt = con.prepareStatement(sql);  //pstmt 객체 생성 
				
				//쿼리 실행(DML-insert, update, delete, DDL-create, alter, drop)
				// DML: executeUpdate(),  select: executeQuery()
				rs = pstmt.executeQuery();  //최초의 rs를 생성한 시점에는 커서가 첫번째 레코드보다 위에 위치(컬럼명 부분)
				
				while(rs.next()) {  //커서가 다음 레코드를 가리킴
					String ename = rs.getString("ename");  //현재 커서가 위치한 곳에서의 ename 값을 가져옴
					int sal = rs.getInt("sal");  
					String job = rs.getString("job");  
					Date hiredate = rs.getDate("hiredate");  
					
					System.out.println("ename: " + ename + " sal: " + sal + " job: " + job + " hiredate: " + hiredate);
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
}
