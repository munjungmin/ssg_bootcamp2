class Bullet{
    constructor(container, x, y, width, height, velX, velY){
        this.container = container;
        this.div = document.createElement("div");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;

        //style
        this.div.style.background = "red";
        this.div.style.position = "absolute";
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";

        this.div.style.width = this.width + "px";
        this.div.style.height = this.height + "px";
        this.div.style.borderRadius = 50 + "%";

        this.div.style.filter = "blur(5px)";

        this.container.appendChild(this.div);
    }

    move(){
        // 총알이 적군에 맞지않고 화면 밖으로 나가는 경우, 메모리 관리를 위해 제거 (화면제거 + 배열제거)
        if(this.x >= 1500){
            this.container.removeChild(this.div);  //화면에서 제거

            //전역변수 접근 가능
            let index = bulletArray.indexOf(this);
            bulletArray.splice(index, 1);   // splice(index, count, item1 ... itemX)   index위치에서 count개수만큼 remove, items add 
            console.log("현재 총알 수: ", bulletArray.length);
        }

        this.x += this.velX;
        this.div.style.left = this.x + "px";
    }
}