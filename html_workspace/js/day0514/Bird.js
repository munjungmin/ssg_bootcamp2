class Bird{
    constructor(color, age){
        console.log("Bird 생성자 호출");
        this.color = color;
        this.age = age;
    }

    eat(){
        console.log("먹다");
    }

    fly(){
        console.log("날다");
    }
}