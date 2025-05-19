package gui.layout;

import java.awt.*;
public class LoginForm{

    public static void main(String[] args){
        Frame frame = new Frame("로그인 폼");

        Panel p1 = new Panel(new GridLayout(2, 2));
        Panel p2 = new Panel();

        TextField tf_id = new TextField(20);
        TextField tf_pwd = new TextField(20);
        Label lb_id = new Label("ID");
        Label lb_pwd = new Label("Password");
        Button bt_login = new Button("login");
        Button bt_join = new Button("join");
		
		//조립
        p1.add(lb_id);
        p1.add(tf_id);
        p1.add(lb_pwd);
        p1.add(tf_pwd);

        p2.add(bt_login);
        p2.add(bt_join);

        frame.add(p1);
        frame.add(p2, BorderLayout.SOUTH);

        frame.setSize(220, 135);
        frame.setVisible(true);
    }
}