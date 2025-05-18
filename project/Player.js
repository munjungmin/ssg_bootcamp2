
//import { ATTACK_RANGE } from "./constants.js";
const ATTACK_RANGE = 300;

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

        //game 
        this.attackRange = ATTACK_RANGE;  //100px
        this.targetX;   //
        this.targetY;  // 이동하려는 좌표
        this.target;   // 공격하려는 대상 
    }
    
    look(){

    }

    move(){

        this.look();

        let dx = this.targetX - this.x;
        let dy = this.targetY - this.y;

        const distance = Math.sqrt(dx * dx + dy * dy);
        const speed = 2; // 한 번 이동할 때 거리

        if (distance > speed) {
        // 방향 벡터 정규화 후 속도 곱해서 이동
            this.x += (dx / distance) * speed;
            this.y += (dy / distance) * speed;
        } else {
        // 거리가 짧으면 그냥 타겟에 도달
            this.x = this.targetX;
            this.y = this.targetY;
        }
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";
    }

    // 공격 메서드가 딱 공격을 나타내는게 아니라, 범위가 아니면 move()인게 껴있어서 살짝.. 
    attack(){
        if(this.isInRange()){
            console.log(this.target.bg);
            
        }
        else{
            console.log("공격불가")
            this.move();
        }
    }

    // 클릭한 적 오브젝트의 중점 필요 
    isInRange(){
        let distance = Math.sqrt(
            Math.pow((this.x + this.width/2 - this.target.x + this.target.width/2), 2) +
            Math.pow((this.y + this.height/2 - this.target.y + this.target.height/2), 2)
        )
        console.log(distance);
        return distance < this.attackRange ? true : false;        
    }
}

