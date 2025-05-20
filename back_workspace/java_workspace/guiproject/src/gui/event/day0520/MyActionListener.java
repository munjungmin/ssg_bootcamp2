package gui.event.day0520;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import gui.event.DoubleButton;

public class MyActionListener implements ActionListener{
    DoubleButton db = new DoubleButton();
    Button bt1;
    Button bt2;

    //생성자 주입
    public MyActionListener(Button bt1, Button bt2){
        this.bt1 = bt1;
        this.bt2 = bt2;
    }

    //메서드를 통해 다른 클래스에 존재하던 버튼들을 전달받음(메서드 주입 = injection)
    public void setBtn(Button bt1, Button bt2){
        this.bt1 = bt1;
        this.bt2 = bt2;
    }


    //사용자가 액션 이벤트를 일으키면 os로부터 이벤트 정보를 받은 jvm은 해당 이벤트 정보를 
    // 알맞은 이벤트 객체로 인스턴스화시킴 
    // 생성된 이벤트 인스턴스는 재정의 메서드인 actionPerformed 메서드 인자로 전달됨
    // 개발자는 발생한 이벤트 정보를 ActionEvent로부터 얻을 수 있음
    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();
        System.out.println(obj);

        if(obj == bt1){
            System.out.println("A");
        } else if(obj == bt2){
            System.out.println("B");
        }
    }
}