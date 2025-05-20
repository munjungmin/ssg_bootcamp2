package gui.chat;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class ChatA extends Frame implements ActionListener, KeyListener{
                    //is a         is a 
    Button bt_open;
    TextArea t_area;
    Panel p_south;
    TextField t_input;
	ChatB b;

    public ChatA(){
        t_area = new TextArea(10, 20);
        p_south = new Panel();
        t_input = new TextField(20);
        bt_open = new Button("send");

        add(t_area);
        add(p_south, BorderLayout.SOUTH);
        p_south.add(t_input);
		p_south.add(bt_open);
		
		//버튼 이벤트 리스너 연결 - 새창 띄우기
		//이벤트 종류마다 리스너를 구현해서 오버라이딩 하지 말고, 이 클래스 자체가 그냥 리스너의 역할을 하도록 함 
		//MyActionListener myActionListener = new MyActionListener(bt_open);
        bt_open.addActionListener(this);
		
        //MyKeyListener myKeyListener = new MyKeyListener(t_area, t_input);
       // t_input.addKeyListener(myKeyListener);
	   t_input.addKeyListener(this);
	   
//   	   t_area.setEditable(false);
	   t_area.setFocusable(false);

		setTitle("A");
        setSize(300, 400);
        setVisible(true);   
    }

	// action 이벤트리스너 오버라이딩
    public void actionPerformed(ActionEvent e){
		if(b == null){
			b = new ChatB(this);
		} else{
		
			b.setTextArea(t_area.getText());
		}
    }   
	
	//key이벤트리스너 오버라이딩
	public void keyPressed(KeyEvent e){
      if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(b== null){
				b = new ChatB(this);	
			}
		    t_area.append(t_input.getText() + "\n");
			t_input.setText("");
			
			if(b != null){
				b.setTextArea(t_area.getText());
			}
      }
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
	
	

    public static void main(String[] args){
        ChatA a = new ChatA();

    }
}

