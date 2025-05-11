class Hero{
    constructor(container, x, y, width, height, velX, velY){
        this.container = container;
        this.img = document.createElement("img");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;

        // 주인공의 sprite 이미지명 배열 선언 
        this.imgArray = [];
        this.index = 0;  // 이미지 배열의 index 결정 

        for(let i=1; i<=18; i++){
            this.imgArray.push(`../../images/hero/image${i}.png`);
        }

        //style
        this.img.src = "../../images/hero/image1.png";
        this.img.style.position = "absolute";
        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";

        this.img.style.width = this.width + "px";
        this.img.style.height = this.height + "px";

        this.container.appendChild(this.img);

        this.doIdle();  // 생성되자마자 움직임
    }

    //주인공 펄럭임 동작 
    // 게임루프와 상관없이 자체적으로 끝없는 루프로 움직임 표현
    doIdle(){
        this.index++;
        this.img.src = this.imgArray[this.index];

        if(this.index >= 17) this.index = 0;

        setTimeout(() => {
            this.doIdle();  // 여기서 this는 상위 스코프인 객체의 인스턴스를 나타냄
        }, 50);
    }

    move(){
        this.x += this.velX;
        this.y += this.velY;

        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";
    }
}