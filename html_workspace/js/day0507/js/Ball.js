class Ball{

    constructor(container, x, y, width, height, velX, velY, bg){
        this.container = container;  // 이 공을 어디에 붙일지 결정
        this.div = document.createElement("div");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;   // x축으로의 속도
        this.velY = velY;   // y축으로의 속도
        this.bg = bg;       //공의 색상

        //style
        this.div.style.position = "absolute";
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";
        this.div.style.width = this.width + "px";
        this.div.style.height = this.height + "px";
        this.div.style.background = this.bg;
        this.div.style.borderRadius = 50 + "%";

        //넘겨받을 대상 컨테이너가 document.body일 수도 있고, wrapper일 수도 있다.
        // 즉 결정되어 있지 않아 개발자가 호출시 결정해야함
        this.container.appendChild(this.div);
    }

    //현재 이 공의 움직임을 정의(메서드)
    move(){
        this.x += this.velX;
        this.y += this.velY;

        // 4면을 만날때마다 velX, velY를 양수로 부여할지, 음수로 부여할지 결정하여 방향을 바꾼다.
        if(this.x <= 0 || this.x >=  (600-this.width)){
            this.velX = -this.velX;
        }
        if(this.y <= 0 || this.y >= (600-this.height)){
            this.velY = -this.velY;
        }

        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";
    }


}