package gui.layout;

import java.awt.*;
import gui.layout.MemberListener;

class JoinForm extends Frame{
    //필요한 재료들을 has a 관계로 보유하자
    Label lb_title, lb_id, lb_pwd, lb_name;
    TextField tf_id, tf_pwd, tf_name;
    Panel p_center, p_south;
    Button bt_login, bt_join;

    public JoinForm(){
        lb_title = new Label("회원가입");
        lb_id = new Label("ID");
        lb_pwd = new Label("Password");
        lb_name = new Label("Name");

        tf_id = new TextField();
        tf_pwd = new TextField();
        tf_name = new TextField();

        p_center = new Panel();
        p_south = new Panel();

        bt_login = new Button("login");
        bt_join = new Button("join");

        //스타일 적용
        lb_title.setBackground(Color.pink);
        tf_id.setBackground(Color.pink);
        tf_pwd.setBackground(Color.pink);
        tf_name.setBackground(Color.pink);


        // 조립
        add(lb_title, BorderLayout.NORTH);

        //센터 
        Dimension d = new Dimension(110, 25);        
        lb_id.setPreferredSize(d);
        tf_id.setPreferredSize(d);
        lb_pwd.setPreferredSize(d);
        tf_pwd.setPreferredSize(d);
        lb_name.setPreferredSize(d);
        tf_name.setPreferredSize(d);

        p_center.add(lb_id);
        p_center.add(tf_id);
        p_center.add(lb_pwd);
        p_center.add(tf_pwd);
        p_center.add(lb_name);
        p_center.add(tf_name);
        add(p_center, BorderLayout.CENTER);

        //south 
        p_south.add(bt_join);
        p_south.add(bt_login);
        add(p_south, BorderLayout.SOUTH);

		//버튼과 리스너 연결
		//MemberListener memberListener = new MemberListener(this, bt_login, bt_join);
		MemberListener memberListener = new MemberListener(this);
		bt_join.addActionListener(memberListener);
		bt_login.addActionListener(memberListener);
		
        setSize(300, 320);
        setVisible(true);
    }


    //회원가입과 관련된 컴포넌트가 전부 회원가입 폼 클래스에 존재하므로 폼에 대한 유효성 체크 또한 
    // 가입폼 클래스에서 진행하는게 효율적 
    public void checkForm(){
        if(tf_id.getText().length() < 1){ // String 반환
            System.out.println("id 길이가 짧다");
        }
        else{
            System.out.println("id 사용가능");
        }
    }

    public static void main(String[] args){
        new JoinForm();
    }
}