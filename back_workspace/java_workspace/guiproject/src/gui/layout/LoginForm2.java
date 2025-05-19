package gui.layout;

import java.awt.*;
public class LoginForm2{

    public static void main(String[] args){
        Frame frame = new Frame("로그인 폼");

        Panel p1 = new Panel();
        Panel p2 = new Panel();

        TextField tf_id = new TextField(20);
        TextField tf_pwd = new TextField(20);
        Label lb_id = new Label("ID");
        Label lb_pwd = new Label("Password");
        Button bt_login = new Button("login");
        Button bt_join = new Button("join");
		
		//크기 설정
		//처음보는 객체에 대한 대처법 
		//1) 객체명으로 기능을 예측하자. 예측이 안되면 조사하자
		// 2) 사용하기 위해 메모리 올리는 법 파악(객체 유형 3가지)
		/**
			- 일반 클래스: new 생성자()
			- 추상클래스: 자식으로 구현한 후 자식을 new()
			- 인터페이스: 자식으로 구현한후 자식을 new()
		*/
		Dimension d = new Dimension(105, 25);
		lb_id.setPreferredSize(d);

	    tf_id.setPreferredSize(d);
		lb_pwd.setPreferredSize(d);
		tf_pwd.setPreferredSize(d);
		
		//조립
        p1.add(lb_id);
        p1.add(tf_id);
        p1.add(lb_pwd);
        p1.add(tf_pwd);
        
        p2.add(bt_login);
        p2.add(bt_join);

        frame.add(p1);
        frame.add(p2, BorderLayout.SOUTH);

        //윈도우 설정
        frame.setSize(220, 135);
        frame.setVisible(true);
    }
}