package gui.graphic;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;

public class MyCanvas extends JPanel{
    Image image;
    public void paint(Graphics g){
        //ImageObserver란? 이미지 로드는 비동기이기 때문에 이미지가 완료되지 않은 상황에서 
        //옵저버에 의해 이미지가 완전히 로드될 수 있도록 내부적인 처리가 지원되는데
        //이때 옵저버 역할을 수행할 객체를 개발자가 선택할 수 있는데, 
        // JPanel은 ImageObserver 인터페이스를 구현한 객체이므로, 옵저버 역할이 가능하다.
        g.drawImage(image, 0, 0, 600, 450, this);
    }

    public void setImage(Image image){
        this.image = image;
    }


}