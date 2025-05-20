package gui.chat;

import java.awt.*;
import java.awt.event.*;
//import java.awt.event.KeyListener;
//import java.awt.event.KeyEvent;

public class ChatB2 extends Chat implements KeyListener {
	ChatA2 a;
	
    public ChatB2(ChatA2 a){
		this.a = a;

		t_input.addKeyListener(this);
		setTitle("B");
    }
	
	public void keyPressed(KeyEvent e){
		super.keyPressed(e);
		if(a != null){
			a.t_area.setText(t_area.getText());
		}
    }
}