class Bullet{

    constructor(bg, y){
        this.div = document.createElement("div");
        this.x = 0;
        this.y = y;
        this.velX = 10;  //총알 속도
        this.bg = bg;

        //style
        this.div.style.width = 20 + "px";
        this.div.style.height = 20 + "px";
        this.div.style.borderRadius = 50 + "%";
        this.div.style.background = this.bg;
        this.div.style.position = "absolute";
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";

        document.body.appendChild(this.div);
    }

    move(){
        this.x += this.velX;  //등속도 운동
        this.div.style.left = this.x + "px";

        // setTimeout(() => {
        //     this.move()
        // }, 10);
    }

}