class Enemy{
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
        //this.attackRange = 100;  //100px
        this.div.classList.add('enemy');
        this.div.gameObject = this;

        this.hp = 300;
        this.gold = 21;

    }

    isDead(){
        return (this.hp > 0 ? false : true);
    }

}