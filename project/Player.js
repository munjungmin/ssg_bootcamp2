class Player{

    constructor(container, x,  y, width, height, bg){
        this.container = container;
        this.div = document.createElement("div");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bg = bg;

        //style
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";
        this.div.style.width = this.width + "px";
        this.div.style.height = this.height + "px";
        this.div.style.background = this.bg;

        this.div.style.position = "absolute";
        
        this.container.appendChild(this.div);
    }
    
    look(){}

    move(targetX, targetY){

        this.look();


        let dx = targetX - this.x;
        let dy = targetY - this.y;

        const distance = Math.sqrt(dx * dx + dy * dy);
        const speed = 2; // 한 번 이동할 때 거리

        if (distance > speed) {
        // 방향 벡터 정규화 후 속도 곱해서 이동
            this.x += (dx / distance) * speed;
            this.y += (dy / distance) * speed;
        } else {
        // 거리가 짧으면 그냥 타겟에 도달
            this.x = targetX;
            this.y = targetY;
        }
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";
    }
}

