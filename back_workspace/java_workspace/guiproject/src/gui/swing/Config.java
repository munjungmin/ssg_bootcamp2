package gui.swing;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Config extends JFrame implements ActionListener{
    JTextField t_size;
    JButton bt;
    //has a 관계는 멤버변수로 보유한 관계를 의미한다 
    MyWindow myWindow; //null

    public Config(MyWindow myWindow){
        this.myWindow = myWindow;
        t_size = new JTextField(10);
        bt = new JButton("설정 적용");

        // 컴포넌트들이 자신 본연의 크기를 유지
        setLayout(new java.awt.FlowLayout());  
        add(t_size);
        add(bt);

        bt.addActionListener(this);

        setBounds(1000 + 200, 100, 200, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        //myWindow가 보유한 area의 폰트 크기를 설정하자. 단 폰트의 크기값은 나의 TextField로부터 얻은 값이다.
        int size = Integer.parseInt(t_size.getText());
        myWindow.area.setFont(new Font(null, 0, size));
    }

}