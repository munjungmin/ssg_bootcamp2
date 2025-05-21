class Brick extends GameObject{
    //js는 java처럼 super()를 자동으로 호출해주지 않는다. 따라서 개발자가 무조건 호출해야함
    constructor(container, x, y, width, height, velX, velY, bg, border, borderColor){
        super(container, x, y, width, height, velX, velY, bg, border, borderColor);
        
    }
     
}