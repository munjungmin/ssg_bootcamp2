/*
    모든 Gui 프로그램은 컴포넌트들을 조합하여 화면을 구성하기 때문에 각자의 배치(Layout) 방법을 지원한다.

    AWT의 배치방법은 다음과 같다
    1) BorderLayout
        동서남북과 중앙(센터)의 방위를 갖는 배치 방법
        Frame은 개발자가 아무것도 지정하지 않으면 디폴트로 BorderLayout이 적용된다.
    2) FlowLayout
    3) GridLayout  
 */
package test;
import java.awt.*;

class BorderLayoutTest{
    public static void main(String[] args){
        /*
            UI 컴포넌트는 다른 컴포넌트를 포함할 수 있는 능력을 기준으로 2가지 유형
            1) 컨테이너 유형: 다른 컴포넌트를 포함할 수 있다. 예) div, span,...
                            대표적인 컴포넌트가 바로 윈도우인 Frame
                            배치 능력이 있으므로, 여러 유형의 배치관리자를 적용할 수 있다.
            2) 비주얼 컴포넌트: 컨테이너 안에 소속되는 대상. 예) button, checkbox, choice ... 
        */

        Frame frame = new Frame();
        BorderLayout border = new BorderLayout();

        //프레임에 보더배치 적용
        frame.setLayout(border);


        Button bt_east = new Button("동쪽");
        Button bt_west = new Button("서쪽");
        Button bt_south = new Button("남쪽");
        Button bt_north = new Button("북쪽");
        Button bt_center = new Button("센터");

        frame.add(bt_north, BorderLayout.NORTH);//북쪽에 버튼 부착
        frame.add(bt_south, BorderLayout.SOUTH);//북쪽에 버튼 부착
        frame.add(bt_east, BorderLayout.EAST);//북쪽에 버튼 부착
        frame.add(bt_west, BorderLayout.WEST);//북쪽에 버튼 부착
        frame.add(bt_center, BorderLayout.CENTER);

        frame.setSize(500, 450);
        frame.setVisible(true);
    }    
}