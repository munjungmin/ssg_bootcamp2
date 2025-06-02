package com.sinse.dbproject;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 * KeyListener, WindowListener 등 재정의할 메서드가 3개 이상되는 인터페이스의 경우, 개발자 대신 java api 차원에서 대신 구현한 중간 객체들을 가리켜 어댑터라고 한다. 
 * (마치 전자제품과 220볼트 사이의 완충 장치의 역할) - 우리 대신, 메서드들을 재정의해놓았기 때문에 개발자의 구현의무가 사라짐 
 * 
 */

public class MyWindowAdapter extends WindowAdapter{
	
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("창 닫기");
	}
	

}
