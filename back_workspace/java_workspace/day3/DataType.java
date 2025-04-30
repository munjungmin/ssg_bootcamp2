class DataType{
    public static void main(String[] args){

        char c='강';
        System.out.println(c);

        //byte short int long 
        byte b = 100;
        //b = 300; // error
         // b = (byte)300; // overflow

        short s = 1000;
        
        //b = s; // error
        //b = (byte)s; // overflow
        
        //float f = 1.3; // error
        float f = 1.3f;
        f = 1;

        long l = 300000000000L;

        System.out.println(l);
        
        short s1 = 3;
        short s2 = 4;
        //short sum = s1 + s2;  // error

        short a = 7;
        byte bb = 9;
        long k = 10;


        int x = a + b;
        long y = k + a;
    }
}

/**
 * 정수 리터럴은 int형
 * 실수 리터럴은 double형 
 * 
 * 
 * int보다 작은 자료형을 연산에 사용할 경우 int형으로 변환되어 계산한다. 
 * (int) s1 + (int) s2 = 7 이런식으로 됨
 * short sum = 7(int) 
 * 따라서 short에 int형 리터럴을 담을수 없어서 에러가 난다. 
 * char는 음수를 표현하지 못하기때문에 short, int형이 대입될 수 없음
 * byte b = 1;
 * char a = b; 
 * 
 * 
 * 틀림 
 * 틀림
 * 틀림
 * 
 * 
 * 
 */