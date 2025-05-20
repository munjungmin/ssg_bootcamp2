package gui.chat;

import java.awt.*;
import java.awt.event.*;

public class Chat extends Frame implements KeyListener{

    TextArea t_area;
    Panel p_south;
    TextField t_input;

    public Chat(){

        t_area = new TextArea(10, 20);
        p_south = new Panel();
        t_input = new TextField(20);

        add(t_area);
        p_south.add(t_input);
        add(p_south, BorderLayout.SOUTH);

        t_area.setFocusable(false);
        setSize(300, 400);
        setVisible(true);    
    }
	
	public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
	        t_area.append(t_input.getText() + "\n");
		    t_input.setText("");
        }
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
	

    public void setTextArea(String text){
		t_area.setText(text);
	}

}

