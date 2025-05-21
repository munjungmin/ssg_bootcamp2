class GameObject{
    constructor(container, x, y, width, height, velX, velY, bg, border, borderColor){
        this.container = container;
        this.div = document.createElement("div");  // div를 유지하면서 이미지를 배경처리
                    // 이미지로 개발하지 못하는 건 부모 기능이 없어서 다른 요소를 넣지를 못함.
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;
        this.bg = bg;
        this.border = border;
        this.borderColor = borderColor;

        // style
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";
        this.div.style.width = this.width + "px";
        this.div.style.height = this.height + "px";
        this.div.style.background = this.bg;
        this.div.style.border = this.border + "px solid " + this.borderColor;
        
        this.div.style.position = "absolute";
        this.container.appendChild(this.div);
    }

    tick(){}

    render(){}
}