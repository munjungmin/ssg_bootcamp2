class Duck extends Bird{
    /* 
        js와 java는 둘 다 상속관계에서 자식의 인스턴스가 초기화 되기 전에 부모의 인스턴스 초기화가 앞서야 함은 동일하지만
        js는 개발자가 생성자를 자식의 클래스에서 생성자를 정의만 해도 부모 생성자 명시적 호출 필요함
    */
    constructor(color, age){
        super("red", age);  //부모를 먼저 초기화
        this.color = color;
        this.age = age;
    }

    fly(){
        console.log("오리가 날아요");
    }
}