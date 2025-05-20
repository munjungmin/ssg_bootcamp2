package gui.chat;

import java.awt.*;
import java.awt.event.*;
//import java.awt.event.KeyListener;
//import java.awt.event.KeyEvent;

public class ChatB extends Frame implements KeyListener{

    TextArea t_area;
    Panel p_south;
    TextField t_input;
	ChatA a;

    public ChatB(ChatA a){
		t_area = new TextArea(10, 20);
        p_south = new Panel();
        t_input = new TextField(20);
		this.a = a;

        add(this.t_area);
        p_south.add(t_input);
        add(p_south, BorderLayout.SOUTH);
			
			
	//	if(obj instanceOf ChatA){
		//	a = (ChatA)obj;	
		//}
		
		t_input.addKeyListener(this);
		
		
		t_area.setFocusable(false);
		setTitle("B");
        setSize(300, 400);
        setVisible(true);    
    }
	
	  public void keyPressed(KeyEvent e){
	      if(e.getKeyCode() == KeyEvent.VK_ENTER){
			    t_area.append(t_input.getText() + "\n");
				t_input.setText("");
			
				if(a != null){
					a.t_area.setText(t_area.getText());
				}
		}
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
	
	
	public void setTextArea(String text){
		t_area.setText(text);
	}
}