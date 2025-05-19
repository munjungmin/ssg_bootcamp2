package gui.layout;
/*
    GUI 프로그래밍 = 윈도우 프로그래밍 
    모든 UI 컴포넌트는 윈도우 안에서 구현되기 때문.

    컴포넌트란? 재사용 가능한 객체단위(예 - gui 분야에서는 버튼, 체크박스...)
    [java 컴포넌트의 유형] 
    - 남을 포함할 수 있는 유형 (컨테이너형)
        ex) Frame
        특징) 남을 포함하려다보니, 어떻게 배치할 지를 고민함
            따라서 컨테이너형에는 배치관리자(Layout Manager)가 지원됨
            컨테이너 유형은 개발자가 지정하지 않아도 디폴트로 적용되는 배치관리자가 있다. 
            Frame의 경우 BorderLayout Manager 
            배치관리자는 한 컴포넌트에 하나만 적용 가능 
        배치관리자 종류) 
            1. BorderLayout(동서남북중앙의 방향을 갖는 배치) 
                각 방향에 들어가는 컴포넌트가 자신의 크기를 잃어버리고, 확장해버림
                따라서 대왕 버튼이 만들어짐
            2. FlowLayout(위치가 결정되지 않고 컨테이너에 따라 흘러다님)
                단, 방향성이 있어서 수평방향의 흐름 또는 수직방향의 흐름 둘 중 하나다.
            3. GridLayout(행과 열의 배치방식)
                각 행, 열에 배치되는, 즉 셀에 들어가는 컴포넌트가 자신의 크기를 잃어버리고 확장해버림 (영역을 다 차지함)
            4. CardLayout(마치 카드처럼 오직 하나의 카드만 보여지는 방식)
                화면 전환 시 사용되는데, 직접 만들어 구현해도 되기 때문에 사용 안할 것

    - 남에게 포함되는 유형 (비쥬얼 컴포넌트형)
        ex) Button, Checkbox, Choice, ...

*/

import java.awt.Frame;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.Panel;  //div와 흡사
import java.awt.Color;

public class LayoutTest{
    public static void main(String[] args){
        //윈도우 생성
        Frame frame = new Frame("배치 학습");
        //윈도우 안에 소속되는 컨테이너형 컴포넌트 = 다른 컴포넌트를 포함 가능 
        //Panel도 컨테이너형이므로 배치관리자가 지원됨, 디폴트는 FlowLayout
        Panel panel = new Panel(); 
        Panel panel2 = new Panel();
        panel.setBackground(Color.lightGray);

        // 버튼 하나를 생성해서 부착해보자 (참고로 방향을 지정하지 않으면 디폴트는 중앙)
        Button bt_center = new Button("Center");
        Button bt_center2 = new Button("Center2");
		Button bt_west = new Button("West");
		Button bt_south = new Button("South");

        panel.add(bt_south);
        panel2.add(bt_center);
        panel2.add(bt_center2);


        //상수는 public static final로 선언되었고, 클래스 소속이므로 인스턴스 생성없이 사용 가능
        // 동서남북 방향을 갖는게 BorderLayout이므로 BorderLayout의 상수이다.
        frame.add(panel2);   
        frame.add(bt_west, BorderLayout.WEST);   
        frame.add(panel, BorderLayout.SOUTH);   
		
        frame.setSize(500, 400);
        frame.setVisible(true);
    }

}