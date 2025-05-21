/**
 * AWT는 자바의 초창기 GUI 패키지임은 맞지만, os마다 다른 디자인으로 실행됨 
 * Swing은 os 즉 플랫폼에 상관없이 중립적인 Look&Feel 디자인을 유지한다.
 * 요즘은 swing처럼 os에 어울리지 않는 java에 최적화된 (너무 자바스러운) 디자인을 싫어함. 그래서 나온게 javaFX
 * Swing은 기존 awt를 계승했기 때문에 우리가 사용해왔던 awt 컴포넌트명에 'J' 접두어만 붙는다. JFrmae, JPanel ... 
 * 
 * 패키지도 javax.swing    x는 extends, 확장된 자바라는 뜻 
 */

package gui.swing;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyWindow extends JFrame implements ActionListener{
    JTextArea area;
    JPanel p_south;
    JButton bt;
    
    public MyWindow(){
        area = new JTextArea(4, 15);
        p_south = new JPanel();
        bt = new JButton("환경설정");

        //area에 배경 
        area.setBackground(Color.YELLOW);

        add(area); // 센터에 부착
        p_south.add(bt); // 남쪽 패널에 버튼 부착
        add(p_south, BorderLayout.SOUTH);

        bt.addActionListener(this);
        //setSize(300, 400);
        setBounds(1000, 200, 300, 400);  //x, y, w, h
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == bt){
            new Config(this);
        }
    }
    public static void main(String[] args){
        new MyWindow();
    }
}