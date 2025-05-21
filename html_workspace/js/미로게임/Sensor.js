class Sensor extends GameObject{
    // 부모 클래스에는 속도가 있지만, 센서는 속도가 필요 없기 때문에 
    // 부모 생성자 호출에만 속도를 명시하고, 나의 클래스에는 속도를 제거한다. 
    constructor(container, hero, x, y, width, height, bg, border, borderColor){
        super(container, x, y, width, height, 0, 0, bg, border, borderColor);
        
        //부모 코드에 존재하지 않는 것만 처리하면 됨 
        this.hero = hero;
    }
}