package gui.event;

import java.awt.Frame;
import java.awt.Button;
import java.awt.FlowLayout;
import gui.event.day0520.MyActionListener;

public class DoubleButton{
    public static void main(String[] args){
        Frame f = new Frame();

        Button b1, b2;
        b1 = new Button("Aa");
        b2 = new Button("Bb");
        
        MyActionListener listener = new MyActionListener(b1, b2);  // 생성자 주입 
        // listener.setBtn(b1, b2);  // 메서드 주입

        b1.addActionListener(listener);
        b2.addActionListener(listener);



        f.add(b1);
        f.add(b2);

        f.setLayout(new FlowLayout());
        f.setSize(300, 400);
        f.setVisible(true);
    }
}