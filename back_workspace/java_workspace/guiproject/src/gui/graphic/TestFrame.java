/**
  그래픽 프로그래밍 시, 알고 있으면 도움되는 비유

    [현실]                   [프로그래밍]
  1) 화가 -> 컴포넌트
  2) 그리는 행위 -> 컴포넌트가 보유한 메서드(paint())
  3) 팔레트, 기타 도구 -> paint(Graphics g)   그래픽스 객체
  4) 그리는 물체 -> 컴포넌트 자신
  5) 도화지 -> 컴포넌트 자신

  모든 컴포넌트는 스스로를 그린다. 
  버튼이 스스로 그림을 그릴때 뺏어보기 
 */

package gui.graphic;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;

class TestFrame extends JFrame{
    MyButton bt;
    ImgPanel ip; 
    public TestFrame(){
      bt = new MyButton("커스텀");
      ip = new ImgPanel();

      setLayout(new FlowLayout());
      add(bt);
      add(ip);
  
      setSize(300, 400);
      setVisible(true);
      //윈도우창을 닫을때, 프로세스 종료
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
    public static void main(String[] args){
      new TestFrame();
    }
}