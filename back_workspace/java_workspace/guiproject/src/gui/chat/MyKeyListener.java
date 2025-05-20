package gui.chat;
/**
 * OS를 거쳐 JVM으로부터 전달되는 키보드 이벤트를 청취하기 위한 객체인 KeyListener를 재정의해보자
 */
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.TextArea;
import java.awt.TextField;

public class MyKeyListener implements KeyListener{
    TextArea ta;
    TextField tf;

    public MyKeyListener(TextArea ta, TextField tf){
      this.ta = ta;
      this.tf = tf;
    }
    public void keyPressed(KeyEvent e){
      if(e.getKeyCode() == 10){
		    ta.append(tf.getText() + "\n");
        tf.setText("");
      }
    }
	
	 //키보드 눌렀다 뗄 때
    public void keyReleased(KeyEvent e){
		  System.out.println("눌렀다 뗌 Released");
    }

    public void keyTyped(KeyEvent e){

    }

}