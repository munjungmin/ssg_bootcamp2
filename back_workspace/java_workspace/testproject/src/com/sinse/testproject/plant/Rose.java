//package com.sinse.testproject.plant;

public class Rose{
    public void bloom(){
        System.out.println("장미꽃이 핀다.");
    }
}

// 1. 환경변수를 등록하기 ~~bin까지 
// 3. use.UseRose 클래스 생성해서 bloom 메서드 호출 

/**
 * classpath는 bin 폴더로
 * 내 위치: java_workspace/testproject
 * Rose class 생성 후 컴파일: javac -d $classpath com/sinse/testproject/plant/Rose.java  
 * UseRose class 생성 후 컴파일: javac -d $classpath com/sinse/testproject/use/UseRose.java  
 * 
 * 환경변수를 설정해놨기 때문에 어느 위치에서나 
 * java com/sinse/testproject/use/UseRose 하면 실행됨 
 * 
 * 패키지 선언 안해도 되네?
 */