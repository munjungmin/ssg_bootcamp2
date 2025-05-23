package com.sinse.ioproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 1) 
 * 2) 데이터 처리 
 * 	- 바이트
 *  - 문자  
 *  - 
 * */

// 바이트 기반 스트림 특징: 입력 (~InputStream), 출력 (~OutputStream)
// 문자 기반 스트림: 입력(~Reader), 출력(~Writer)

public class CharacterStream {

	// 바이트 기반 -> 중간 과정을 눈으로 확인하지 않아도 되는 작업에 사용하면 좋다. ex) 파일 복사 (바이트가 복사되는걸 눈으로 확인하지 않아도 되고 복사가 완성된 파일을 보면 되니까..)
	FileInputStream fis;	//파일을 대상으로 한 바이트 기반의 입력 스트림 
	FileOutputStream fos;  //파일을 대상으로 한 바이트 기반의 출력 스트림
	InputStreamReader is;  //기존에 존재하는 바이트기반의 입력 스트림에 덧붙여 사용
	OutputStreamWriter os;  //기존에 존재하는 바이트 기반의 출력 스트림에 덧붙여 사용
	//결론: 모든 스트림의 기반 스트림은 바이트 기반
	// 바이트 기반의 스트림만 존재한다면, 얼마든지 문자 기반으로 업그레이드 할 수 있음 
	
	//아래 두 클래스는 파일에 국한되므로, 파일관련 작업에만 유용해 범용성이 떨어짐 
	FileReader reader;	//파일을 대상으로 한 문자기반 입력 스트림 (문자를 이해, 문자 안깨짐-한 바이트씩 읽는게 아니라 문자 단위로 읽기때문에, 복사작업에 사용 x, 바이트기반이 근본이고 더 많은 일을 함)   
	FileWriter writer;  // 
	
	
	String name = "C:\\lecture_workspace\\back_workspace\\java_workspace\\IOProject\\res\\memo.txt";
	String name2 = "C:\\lecture_workspace\\back_workspace\\java_workspace\\IOProject\\res\\memo_copy.txt";
	
	public CharacterStream() {
		try {
			fis = new FileInputStream(name);   
			fos = new FileOutputStream(name2);
			
			is = new InputStreamReader(fis);  //빨대가 2단계로 업그레이드
			os = new OutputStreamWriter(fos); 
			
			//실행중인 프로그램으로 메모장을 구성하는 데이터를 입력해보자
			int data = -1;
			
			while(true) {
				data = is.read();  //한 문자를 읽음
				if(data == -1) 
					break;
				os.write(data);
				System.out.println((char)data);
			}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(fis != null) {
				try {
					fis.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			if(fos != null) {
				try {
					fos.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new CharacterStream();
	}

}
