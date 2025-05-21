/**
 * 버튼 뿐만 아니라, 눈에 보여지는 모든 컴포넌트는 실행 시 스스로를 그린다.
 * 따라서 개발자가 원하는 그림으로 커스텀하려면, paint()메서드를 오버라이딩하면 된다.
 * java에서 클래스가 final 선언되어 있지 않으면 언제나 상속이 가능
 */

package gui.graphic;

import javax.swing.JButton;
import java.awt.Graphics;

class MyButton extends JButton{    
    public MyButton(){}

    public MyButton(String title){
        super(title);
    }

    //컴포넌트들이 보유한 paint 메서드 오버라이딩
    //Graphics는 그림 그리는 도구다(도형, 이미지, 글씨 ...)
    // 컴포넌트 중 본래의 그래픽을 사용해야 하는 경우: 
    // 개발자가 오버라이딩으로 커스텀해서 그려야 하는 경우:
    public void paint(Graphics g){
        g.drawRect(0, 0, 50, 50);
    }

}