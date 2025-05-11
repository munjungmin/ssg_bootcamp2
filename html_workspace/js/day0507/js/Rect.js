class Rect{
    constructor(container, x, y, width, height, bg){
        this.container = container;
        this.div = document.createElement("div");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bg = bg;
        this.a = 0.01;   
        this.targetH = 400;  

        //style
        this.div.style.position = "absolute";
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";
        this.div.style.width = this.width + "px";
        this.div.style.height = height + "px";
        this.div.style.background = this.bg;
        
        this.container.appendChild(this.div);
        this.move();
    }

    move(){
        console.log("move()...");

        // 막대의 크기를 감속도 공식을 적용하여 움직이자
        // 높이 = 현재 높이 + a * (목표 높이 - 현재 높이)
        this.height = this.height + this.a * (this.targetH - this.height);
        this.div.style.height = this.height + "px";

        setTimeout( () => {
            this.move();
        }, 10);
    }
}
