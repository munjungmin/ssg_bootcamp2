package gui.chat;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class ChatA2 extends Chat implements ActionListener{
    Button bt_open;
	ChatB2 b;
	
    public ChatA2(){
        super();
        bt_open = new Button("send");
		p_south.add(bt_open);
        bt_open.addActionListener(this);
        t_input.addKeyListener(this);
		setTitle("A2");
    }

	// action 이벤트리스너 오버라이딩
    public void actionPerformed(ActionEvent e){
		if(b == null){
			b = new ChatB2(this);
		} 
		b.setTextArea(t_area.getText());
    }   
	
	//key이벤트리스너 오버라이딩
	public void keyPressed(KeyEvent e){
		super.keyPressed(e);
		if(b != null){
			b.setTextArea(t_area.getText());
		}
    }
	
    public static void main(String[] args){
        ChatA2 a = new ChatA2();
    }
}

