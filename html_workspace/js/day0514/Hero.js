class Hero extends GameObject{
    
    // js는 생성자를 작성하기만 하면 부모 생성자를 무조건 호출해야함(상속을 받은 경우)
    constructor(container, src, x, y, width, height, velX, velY){
        super(container, src, x, y, width, height, velX, velY);
    }

    //부모가 완성하지 못한 메서드를 자식이 자신의 상황에 맞게 커스텀하는 것 = 메서드 오버라이딩(Overriding)
    tick(){ 
        this.x += this.velX;
        this.y += this.velY;
    }
    
    render(){ 
        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";
    }
    
}