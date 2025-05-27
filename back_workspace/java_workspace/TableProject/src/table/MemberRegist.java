package table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class MemberRegist extends JFrame implements ActionListener, WindowListener{
	JPanel p_west;   //서쪽
	JTextField t_id;
	JTextField t_name;
	JTextField t_tel;
	JButton bt;
	
	JPanel p_center;  //센터
	JTable table;
	JScrollPane scroll;
	
	TableModel tableModel;   // 인터페이스
	int index = 0;  //model의 2차원 배열의 층수 index 
	
	Connection con = null;
	
	
	public MemberRegist() {
		// 생성
		p_west = new JPanel();
		t_id = new JTextField();
		t_name = new JTextField();
		t_tel = new JTextField();
		bt = new JButton("가입");
		
		p_center = new JPanel();
		//생성자에 2차원 배열 대입 방식은 불편하다. 생성하는 시점부터 데이터가 존재해야 하는 전제조건이 필요하기 때문
		// 생성자의 인수에 이 테이블에 보여줘야 할 데이터 또는 데이터처리 객체
		// JTable은 MVC 패턴을 어느 정도 반영한 컴포넌트이다. (모델 + 컨트롤러)
		tableModel = new MyModel();  // 보여주고 싶은 모델을 결정 (tableModel 혹은 AbstractTableModel을 상속받은 클래스)
		
		table = new JTable(tableModel);   //JTable은 껍데기일뿐, 실제 보여질 데이터는 tableModel이 결정한다...
		scroll = new JScrollPane(table);
		
		//스타일 적용
		p_west.setBackground(Color.orange);
		p_west.setPreferredSize(new Dimension(150, 500));
		
		Dimension d = new Dimension(146, 35);
		t_id.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_tel.setPreferredSize(d);
		
		scroll.setPreferredSize(new Dimension(425, 480));
		//조립
		p_west.add(t_id);
		p_west.add(t_name);
		p_west.add(t_tel);
		p_west.add(bt);
		add(p_west, BorderLayout.WEST);
		
		p_center.add(scroll);		
		add(p_center);
		
		bt.addActionListener(this);
		//윈도우 창과 리스너 연결 (나 자신이 윈도우이자 리스너)
		this.addWindowListener(this);
		
		setBounds(1000, 100, 600, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//DB 접속
		connect();
		selectAll();
		
	}
	
	public void connect() {
		try {
			String url = "jdbc:mysql://localhost:3306/dev";
			String id = "java";
			String pwd = "1234";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(url, id, pwd);
			if(con != null) {
				this.setTitle("접속 성공");
			}
			
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//회원 한 명 등록 (model이 보유한 2차원 배열에 들어갈 예정)
	public void regist() {
		//회원 1명을 1차원 배열에 담아 그 1차원 배열을 모델이 보유한 2차원 배열의 가장 최근 위치에 밀어넣자
//		String[] member = {t_id.getText(), t_name.getText(), t_tel.getText()};
//		((MyModel)tableModel).rows[index++] = member;
//		table.updateUI();
		
		String sql = "insert into member4(id, name, tel) ";
		sql += "values('"+ t_id.getText() + "', '" + t_name.getText() + "', '" + t_tel.getText() + "')";
		System.out.println(sql);
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			int result = pstmt.executeUpdate(); //DML (insert, update, delete)
			if(result > 0) {
				JOptionPane.showMessageDialog(this, "등록 성공");
				selectAll();
			} else {
				JOptionPane.showMessageDialog(this, "등록 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	public void selectAll() {
		String sql = "select * from member4";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//rs가 여러칸씩 자유자재로 이동 가능하게 함 
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			
			rs.last();
			int total = rs.getRow();
			// rs 자체는 MyModel이 보유하고 있는 2차원 배열이 아니므로, rs의 데이터를 2차원 배열로 변환해 MyModel의 배열을 대체해야함
			((MyModel)tableModel).rows = new String[total][3];
			
			rs.beforeFirst();
			while(rs.next()) {
				String[] record = {
						rs.getString("id"),
						rs.getString("name"),
						rs.getString("tel")
				};
				((MyModel)tableModel).rows[rs.getRow()-1] = record;
			}
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		regist();
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("windowOpened()");
	}

	//윈도우 창을 닫는 순간 호출되는 메서드이므로, 연결되어있던 자원을 해제하는 용도로 적합하다.
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("windowClosing");
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("windowClosed");
	}

	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("windowIconified");
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("windowDeiconified");
	}

	@Override
	public void windowActivated(WindowEvent e) {
		System.out.println("windowActivated");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		System.out.println("windowDeactivated");
	}
	

	public static void main(String[] args) {
		new MemberRegist();
	}

}
