package gui.event;
/**
 * java gui에서도 사용자의 반응에 대한 이벤트 처리가 가능함
 * 하지만 js에서의 처리보다 훨씬 복잡하다.
 * html에서는 클릭이벤트는 click
 * java에서는 클릭이벤트라는 용어 자체가 없다. action에 소속
 * 키보드 이벤트는 KeyListener
 */

import java.awt.*;

class ActionTest{
    public static void main(String[] args){
        Frame frame;
        Button bt;
        TextField tf;
		Choice ch;  //html에서의 select 박스
		
        frame = new Frame();
        bt = new Button("click me!");
        tf = new TextField(20);
		ch = new Choice();
		
		ch.addItem("@naver.com");
		ch.addItem("@google.com");
		ch.addItem("@daum.net");
			
		
        bt.addActionListener(new MyActionListener());
		tf.addKeyListener(new MyKeyListener());
		ch.addItemListener(new MyItemListener());
		frame.addMouseListener(new MyMouseListener());
		
        frame.setLayout(new FlowLayout());
        frame.add(bt);
        frame.add(tf);
		frame.add(ch);
        frame.setSize(300, 400);
        frame.setVisible(true);
    }   
}