package com.sinse.networkapp.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class StreamStudy {
	
	String path = "C:\\public\\test.txt";
	//바이트 기반의 스트림으로 읽기
	public void readByte() {
		//바이트 기반 스트림을 이용하여, 문서 파일을 읽고 그 내용을 출력!
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			int data = -1;
			while(true) {
				data = fis.read();   //1byte 읽기
				if(data == -1) break;
				
				System.err.print((char)data);
					
			}
		}catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	//문자 기반 스트림으로 읽기
	public void readReader() {
		FileInputStream fis;
		InputStreamReader reader;
		
		try {
			fis = new FileInputStream(path);
			reader = new InputStreamReader(fis);
			int data = -1;

			while(true) {
				
				try {
					data = reader.read();
					if(data == -1) break;
					System.err.print(data);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} //바이트 기반
		
		
	}
	
	
	public void readBuffer() {
		FileInputStream fis = null;
		InputStreamReader reader = null;
		BufferedReader buffr = null;
		
		try {
			fis = new FileInputStream(path);
			reader = new InputStreamReader(fis);
			buffr = new BufferedReader(reader);
			
			while(true) {
				String data = null;
				data = buffr.readLine();
				
				if(data == null) break;
				System.out.println(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(buffr != null) {
				try {
					buffr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		StreamStudy ss = new StreamStudy();
		ss.readByte();
		
	}
}
