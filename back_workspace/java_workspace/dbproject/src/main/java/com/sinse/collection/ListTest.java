package com.sinse.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * [사물이 모여진 모습 유형]
 * 2가지
 * - 순서 있는 모습(중복 가능): List / Queue(FIFO)
 * - 순서 없는 모습(중복 불가): Set / Map<key-value>
 */
public class ListTest {
	// 자바의 컬렉션 프레임웍은 java.util 패키지에서 지원하며 그 중, 순서있는 집합을 처리하는데 대표적인 List에 대해 알아보자
	// List vs 배열 
	// 공통점: 순서를 가지며 인덱스로 접근 가능
	// 차이점: 배열은 생성 시 반드시 크기 지정, 기본 자료형도 담을 수 있다. 컬렉션의 대상은 오직 객체만 담을 수 있다.
	// 컬렉션 프레임웤은, 최상위 인터페이스들의 메서드를 주로 사용하기 때문에 하위의 어떠한 구현 객체를 사용하더라도, 메서드 사용이 일관성이 있다.
	// 담을때는 거의 add(), 길이는 거의 size()
	
	//<자료형> 을 명시하면, 컴파일러가 다른 자료형을 거부한다. 컴파일 타임에 자료형 체크를 해줌 => 제네릭타입(Generic) 
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();    
		list.add("apple");
		list.add("grape");
		list.add("orange");
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		//improved for문
		//속도는 매우 느리다. 
		for(Object obj: list) {
			System.out.println(obj);
		}
	}
	
	
	
	
}
