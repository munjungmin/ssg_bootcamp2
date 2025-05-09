/*
	클래스명 규칙 : 반드시 대문자로 시작, 두 단어의 조합일 경우, 낙타 등 기법으로 작성
	파일명 규칙 : Java는 클래스 명과 파일 명이 반드시 일치해야 한다.
					좋은 습관 파일 명 저장 시 직접 작성하지 말고, 복사해서 사용하자.
*/

class DataType{
	public static void main(String[] args){
		//이 클래스를 실행할 수 있는 클래스로 정의하려면, 
		//즉 java.exe의 대상이 되려면 반드시 main() 메서드를 정의해야 한다.
		
		//Java의 기본 자료형도 다른 언어와 마찬가지로, 3가지 종류(문자, 숫자, 논리값)
		int y = 7; //java는 컴파일 기반의 언어적 특성 때문에 변수를 선언할 때 자료형의 종류를 결정지어 줘야한다.
					   // 컴파일 기반언어의 특징
					   // 단점) 실행 전 컴파일 과정이 번거로울수 있음
					   // 장점) 일단 한번 자료형, 문법 검사(컴파일)가 끝나면  그 결과물을 바이너리 파일로 저장해놓고, 이후부터는 컴파일하지 않는다. 
					   // 컴파일 결과물로 생성된 기계어를 실행하기 때문에 이 시점부터는 인터프리터 언어에 비해 속도가 훨씬 빠르다 
		System.out.println("주원이는 " + y + "쨜~"); //ㅋㅋ
	}
}

