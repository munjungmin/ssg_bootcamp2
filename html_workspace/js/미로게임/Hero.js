class Hero extends GameObject{
    //js는 java처럼 super()를 자동으로 호출해주지 않는다. 따라서 개발자가 무조건 호출해야함
    constructor(container, x, y, width, height, velX, velY, bg, border, borderColor){
        super(container, x, y, width, height, velX, velY, bg, border, borderColor);

        //4개의 센서를 보유하자 
        this.upSensor;
        this.rightSensor = new RightSensor(this.div, this, this.width - 3, 3, 3, this.height - 6, "blue", 0, "");
        this.downSensor;
        this.leftSensor;
    }

    //부모의 메서드 오버라이딩 
    tick(){
        this.x += this.velX;
        this.y += this.velY;
    }

    render(){
        //현재 화면에 등장한 벽돌과 플레이어와의 충돌체크 
        for(let i = 0; i < brickArray.length; i++){
            for(let j = 0; j < brickArray[i].length; j++){
                if(brickArray[i][j] != 0) continue;
                if(checkCollision(this.div, brickArray[i][j].div)){
                    this.div.style.background = "pink";
                }            
            }
        }

        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";
    }
     
}