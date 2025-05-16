package test;

/*
    GridLayout: 행과 열(층수, 호수)을 지원하는 배치방법
 */
import java.awt.*;

public class GridTest{
    public static void main(String[] args){
        Frame f = new Frame("그리드 배치");
        f.setLayout(new GridLayout(3, 4));
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                Button bt = new Button("버튼");
                bt.setBackground(Color.YELLOW);
                f.add(bt);
            }
        }


        f.setSize(500, 400);
        f.setVisible(true);
    }
}