package graphic;

import java.awt.Graphics;

import javax.swing.JPanel;

// 그림을 뺏으려면, 메서드를 오버라이딩 해야하므로, .java로 별도 정의해야함 
public class MyPanel extends JPanel{
	AniTest aniTest;
	
	public MyPanel(AniTest aniTest) {
		this.aniTest = aniTest;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);  //지우지 말기 
		
		g.drawRect(aniTest.x, aniTest.y, 200, 200);
	}
	
}
