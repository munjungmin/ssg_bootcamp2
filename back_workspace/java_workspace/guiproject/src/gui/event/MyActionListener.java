/**
 * 모든 GUI 프로그래밍에서, ui 컴포넌트에 대한 이벤트가 발생했을때
 * 제일 먼저 이벤트를 감지하는 주체는 사실 OS이다.
 * OS는 자신이 감지한 이벤트에 대해 정보객체를 만든 후 해당 프로그램에 전달함
 * 이때 해당 프로그램은 os가 전달한 이벤트 정보에 대해 1:1 대응하는 객체를 인스턴스화시켜 메모리에 올리고,
 * 이 인스턴스를 해당 프로그램으로 전달한다.
 *  */

package gui.event;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import gui.chat.*;

public class MyActionListener implements ActionListener{
    Button bt;
    public MyActionListener(Button bt){
        this.bt = bt;
    }

    public void actionPerformed(ActionEvent e){

        System.out.println("버튼 누름");
		
		if(e.getSource() == bt){
                new ChatB();
			
		}
    }
}