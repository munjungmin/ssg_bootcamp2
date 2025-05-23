/*
    실행중인 프로그램이 파일 등의 자원의 데이터를 읽어들이기 위해서는 스트림 객체가 필요하다
    Stream이란? 현실에서의 물줄기,물의 흐름을 의미, 전산에서는 물이 아닌 데이터를 의미
    
    스트림의 방향은 2가지 유형이 있음 IO(입출력)
        1) 싱행중인 프로그램으로 들어오는 흐름을 Input (입력)
        2) 실행중인 프로그램에서 데이터가 나가면 Output(출력)
*/

package gui.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class  FileLoader{
	public static void main(String[] args) {
		//파일을 대상으로 한 입력 객체(파일을 읽어들일 수 있는 객체)
		String name="C:/lecture_workspace/back_workspace/java_workspace/guiproject/res/memo.txt";
		
		
		// 문법상의 문제 없지만 외부적인 문제 때문에 프로그램이 정상 수행이 될 수 없을 수 있음 
		// 이 상황을 방지하기 위해 컴파일러 차원에서 컴파일 거부중..
        FileInputStream fis = null;   //finally에서 close() 해줘야해서 try바깥 변수로 뺌
		try{
            //디스크에 있는 파일에 스트림이 생성되는 순간임
			fis = new FileInputStream(name);

            int data;
            while((data = fis.read()) != -1){
                System.out.print((char)data);
            }
            
            //DB와 스트림은 반드시 닫아야 한다.
		}catch(FileNotFoundException e){  //catch문의 소괄호 안에 에러의 원인이 되는 객체의 인스턴스를 생성하여 전달해준다..
            //만약 파일이 없다면, 파일을 복구하지 못하므로, 원인 등을 알려주거나 로그를 남기는 등의 처리...
			System.out.println("파일을 찾을 수 없습니다.");
		}catch(IOException e){
            System.out.println("입출력 도중 에러가 발생했습니다.");
        }finally{
            if(fis != null){  //잘못된 파일경로를 전달했다면 인풋스트림이 생성되지 않기 때문에 close도 할 수 없다. 그래서 null 검사를 해야함
                try{
                    fis.close();
                }catch(IOException e){
                    //일반 유저가 아닌 개발자를 위한 로그 출력
                    e.printStackTrace();
                }
            }
        }
	}
}