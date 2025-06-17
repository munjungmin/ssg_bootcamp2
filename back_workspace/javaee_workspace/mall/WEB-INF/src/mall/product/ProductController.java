package mall.product;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

//http://localhost:8282/
public class ProductController extends HttpServlet{
	
	String url="jdbc:mysql://localhost:3306/shop";
	String user="shop";
	String pwd="1234";
	
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException,IOException{
		//response 객체로부터 스트림을 얻기 전에 한글 인코딩 지정해야 한다.
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		//클라이언트의 웹브라우저에 출력될 문자열을 스트림으로 준비해보자.
		PrintWriter out = response.getWriter();
		out.print("my shop application jihoon");
		
		//Product 테이블 연동하기
		//javaEE는 , javaSE를 포함
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<>();
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			out.print("드라이버 로드 성공");
			con = DriverManager.getConnection(url,user,pwd);
			if(con!=null){
				out.print("접속 성공<br>");
				StringBuffer sql = new StringBuffer();
				sql.append("select * from product");
				pstmt = con.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();
				
				
				while(rs.next()){
					Product product = new Product();
					
					product.setProduct_id(rs.getInt("product_id"));
					product.setProduct_name(rs.getString("product_name"));
					product.setBrand(rs.getString("brand"));
					product.setPrice(rs.getInt("price"));
					product.setDiscount(rs.getInt("discount"));
					product.setIntroduce(rs.getString("introduce"));
					product.setDetail(rs.getString("detail"));
					
					list.add(product);
				}
			}else{
				out.print("접속 실패<br>");
			}
			
		}catch(ClassNotFoundException e){
			out.print("드라이버 로드 실패");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try{
					rs.close();
				}catch(SQLException e){
					
				}
			}
				if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException e){
					
				}
				}
				if(con!=null){
				try{
					con.close();
				}catch(SQLException e){
					
				}
			}
		}
		
		//List에 들어있는 객체들을 html table로 출력해보기
		StringBuffer tag = new StringBuffer();
		
		tag.append("<table border='1px' width='800'>");
		tag.append("<tr>");
		tag.append("<td>상품명</td>");
		tag.append("<td>브랜드</td>");
		tag.append("<td>가격</td>");
		tag.append("<td>상할인가</td>");							
		tag.append("</tr>");
		
		for(Product product : list){
			tag.append("<tr>");
			tag.append("<td>"+product.getProduct_name()+"</td>");
			tag.append("<td>"+product.getBrand()+"</td>");
			tag.append("<td>"+product.getPrice()+"</td>");
			tag.append("<td>"+product.getDiscount()+"</td>");							
			tag.append("</tr>");
		}
		
		tag.append("</table>");
		
		out.print(tag);
	}
}