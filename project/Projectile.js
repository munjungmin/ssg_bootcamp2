class Projectile extends GameObject{
    constructor(container, x, y, width, height, angle, createdAt, owner){
        super(container, x, y, width, height);

        this.div = document.createElement("div");

        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";
        this.div.style.width = this.width + "px";
        this.div.style.height = this.height + "px";
        this.div.style.position = "absolute";
        this.container.appendChild(this.div);


        //game
        this.damage = 50;
        this.speed = 10;
        this.angle = angle;
        this.lifetime = 1000;  //ms 단위 
        this.createdAt = createdAt;
        this.damage = 50;
        this.owner = owner;
    }

    // 투사체 지속가능한 
    remove(){
        this.div.remove(); //화면에서 제거 
    }

    move() {
        // 각도 방향으로 이동
        this.x += Math.cos(this.angle) * this.speed;
        this.y += Math.sin(this.angle) * this.speed;
    }   

    render(){
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";
    }

    onHit(target){
        console.log(target);
        target.hp -= this.damage;

    }

}