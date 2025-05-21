
package gui.graphic;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;

public class Gallery extends JFrame implements ActionListener{
    JButton bt_prev;
    JButton bt_next;
    JLabel la_title;
    JPanel p_north;
    MyCanvas myCanvas;
    Toolkit kit; //시스템의 이미지를 개발자 대신 얻어다 주는 객체
    int index = 0;
    
    Image[] imgArray = new Image[9];

    public Gallery(){
        bt_prev = new JButton("prev");
        bt_next = new JButton("next");
        la_title = new JLabel("temp");
        p_north = new JPanel();
        myCanvas = new MyCanvas();
        kit = Toolkit.getDefaultToolkit();

        myCanvas.setPreferredSize(new Dimension(600, 450));
        myCanvas.setBackground(Color.YELLOW);
        
        p_north.add(bt_prev);
        p_north.add(la_title);
        p_north.add(bt_next);

        add(p_north, BorderLayout.NORTH);
        add(myCanvas);

        createImage();

        //패널에 초기 이미지 1개를 전달해주자 
        myCanvas.setImage(imgArray[index]);

        bt_prev.addActionListener(this);
        bt_next.addActionListener(this);

        setSize(600, 500);
        setVisible(true);
    }

    //멤버 변수로 선언된 이미지 배열에, 이미지 인스턴스 9개를 준비해놓아야 프로그램 가동과 동시에 사용자가 사용할 수 있다.
    public void createImage(){
        String[] imgPath = {
            "animal1.jpg",
            "animal2.jpg",
            "animal3.jpg",
            "animal4.jpg",
            "animal5.jpg",
            "animal6.jpg",
            "animal7.jpg",
            "animal8.jpg",
            "animal9.jpg"
        };

        for(int i = 0; i < imgPath.length; i++){
            imgArray[i] = kit.getImage("C:\\lecture_workspace\\back_workspace\\java_workspace\\guiproject\\res\\geographic\\" + imgPath[i]);    
        }
    }

    //myCanvas 영역에 이미지 출력하기
    /**
     * 그래픽 프로그래밍에서 컴포넌트에 약간의 변화라도 생기면, 그 그림은 지워지고 다시 그려야 하는데, 
     * 개발자가 처리하는게 아니라, 시스템이 알아서 렌더링을 한다.
     * 컴포넌트에 변경이 생기면 다음의 과정을 거쳐서 그래픽이 구현된다.
     * 
     * [AWT]
     * 최초 컴포넌트 등장 시 paint()에 의해 눈에 보여짐
     * 사용자가 컴포넌트의 그림에 변화를 주면, jvm은 기존 그림을 지워야함으로 
     * update()를 호출하여 그림을 지운 후 paint()를 다시 호출하여 변경된 그림을 화면에 보여준다. 
     * 
     * [Swing]
     * 사용자가 컴포넌트에 변화를 주게 되면 update()말고 paintComponent()를 호출하여 화면을 지운다. 
     * 변경된 그림을 다시 보여주기 위해 스스로 paint()를 호출 
     * 
     * [주의!]
     * 개발자는 절대로 paint()를 직접 호출해서는 안된다.
     * 그림은 시스템이 알아서 그리기 때문임. 개발자는 시스템에게 부탁하는 메서드만 사용 가능하다.
     * repaint() -> update() -> paint()            //awt
     * repaint() -> paintComponent() -> paint()    //swing
     */

    public void showImage(){
        index += 9;
        index %= imgArray.length;
        myCanvas.setImage(imgArray[index]);
        myCanvas.repaint();  //dirty region이 다시 그릴 영역을 지정하는....
    }

    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();
        if(obj == bt_prev){
            index--;
        }else{
            index++;
        }
        showImage();
    }
    
    //공부목적 상 프레임의 그림을 뺏어서 그려보자
    // public void paint(Graphics g){
    //     System.out.println("나 그려짐");
    // }

    public static void main(String[] args){
        new Gallery();
    }
}

// gui 요소는 그림이다
// 그래서 컨테이너의 사이즈가 바뀌면 그림이 늘어나는게 아니라 그림을 다시 그려야 하는 것이다. 