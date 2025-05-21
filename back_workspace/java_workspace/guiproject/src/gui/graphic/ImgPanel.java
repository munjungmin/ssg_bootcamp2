package gui.graphic;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Font;
//컴포넌트 중 주로 컨테이너형은 아무것도 그려지지 않은 투명도화지와 비슷하기때문에 개발자가 커스텀하기 좋다.
// JPanel, Canvas 등 JFrame 가능은 하다.

class ImgPanel extends JPanel{

    // 이미지를 얻는 것은 개발자의 능력 밖이므로, 시스템상의 이미지를 대신 구해주는 
    // 객체를 통해 추상클래스인 Image 객체의 인스턴스를 얻어와 보자
    Toolkit kit = Toolkit.getDefaultToolkit();  //이미지를 우리 대신 얻어옴
    Image image;  //추상클래스이므로 툴킷에서 얻어오자

    public ImgPanel(){
        setBackground(Color.YELLOW);
        //그림을 그리기 전에, 이미지를 먼저 얻어다 놓자
        image = kit.getImage("C:\\lecture_workspace\\back_workspace\\java_workspace\\guiproject\\res\\dog.png");

        //setPreferredSize(new Dimension(270, 350));  //배치관리자가 적용되어 있는 경우 setSize()는 무시해버리고, preferred는 최대한 이 크기를 따라하려고 노력함
        setPreferredSize(new Dimension(600, 450));  //배치관리자가 적용되어 있는 경우 setSize()는 무시해버리고, preferred는 최대한 이 크기를 따라하려고 노력함
    }
    
    public void paint(Graphics g){
       // g.drawImage(image, 0, 0, this);
		// g.setColor(Color.RED);
        // g.drawLine(100, 0, 50, 200);
        // g.drawOval(0, 0, 50, 50);
		
		// g.setFont(new Font("Courier", Font.BOLD, 40));
		// g.drawString("int a;", 100, 300);
		// g.fillRect(100, 100, 100, 100);
    }

}