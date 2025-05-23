package com.sinse.ioproject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

//대량의 데이터를 읽어보자
public class StreamTuning {
	
	FileInputStream fis;
	//효율성을 떠나, 한글이 깨지지 않고 나오게 하기 위해 기존 스트림에 문자 기반 스트림을 덧씌우자
	InputStreamReader reader; //한 문자씩 읽음
	
	//한 줄을 만날때까지는 입력을 보류하다가, 한줄의 끝인 줄바꿈 특수문자를 만나면 그제서야 한 번 읽어들이는, 즉 버퍼를 이용한 문자열 처리 전용 입력스트림으로 업그레이드 
	BufferedReader br; 
	
	String name = "C:\\lecture_workspace\\back_workspace\\java_workspace\\IOProject\\res\\memo.txt";
	
	public StreamTuning() {
		
		try {
			fis = new FileInputStream(name);
			reader = new InputStreamReader(fis);  
			br = new BufferedReader(reader);
			
			int data = -1;
			int count = 0;  //read 횟수
			String str = null;
			
			while(true) {
				 str = br.readLine();
				if(str == null) break;
				count++;
				System.out.println(str);
			}
			System.out.println("읽은 횟수: " + count);
		}catch (FileNotFoundException e) {
			// TODO: handle exception
		}catch (IOException e) {
			// TODO: handle exception
		}finally {
			if(fis != null) {
				try {
					fis.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		new StreamTuning();
	}
}
