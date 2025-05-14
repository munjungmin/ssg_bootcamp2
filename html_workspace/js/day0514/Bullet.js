class Bullet extends GameObject{
    constructor(container, src, x, y, width, height, velX, velY){
        super(container, src, x, y, width, height, velX, velY);
    }
    
    removeObject(array, target){
        
        //1) 화면에서 제거
        console.log(array);
        let obj = array[array.indexOf(target)];
        this.container.removeChild(obj.img);

        //2) 총알이 있던 배열에서도 제거
        array.splice(array.indexOf(obj), 1);
    }

    tick(){
        this.x += this.velX;
    }
    
    render(){
        // 총알이 한걸음씩 나아갈 때마다, 총알과 적군과의 충돌을 체크해서 제거처리
        for(let i = 0; i < enemyArray.length; i++){
            let enemy = enemyArray[i]
            if(collisionCheck(this.img, enemy.img)){
                this.removeObject(enemyArray, enemy);
                this.removeObject(bulletArray, this);
                setScore(10);
            }
        }

        //적군에 충돌하지 않은 총알 자동 제거. 1000px 넘어갈 때 제거처리
        console.log(this.container.style.width);
        
        if(this.x > 900){
            this.removeObject(bulletArray, this);
        }

        this.img.style.left = this.x + "px";
    }
}