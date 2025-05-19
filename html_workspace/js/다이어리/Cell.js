class Cell{
    constructor(container, x, y, width, height, bg, borderColor, date){
        //[다이어리 관련 내용]
        this.year;
        this.month;
        this.date = date; 
        this.icon;

        //[UI 관련 내용]
        this.container = container;
        this.div = document.createElement("div");      //셀 자체 바스
        this.numDiv = document.createElement("div");   //달력 날짜 출력할 영역
        this.titleDiv = document.createElement("div"); //일기 제목 
        this.iconDiv = document.createElement("div");  // 아이콘 위치할 영역
        
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bg = bg;
        this.borderColor = borderColor;

       // cell style
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";
        this.div.style.width = this.width + "px";
        this.div.style.height = this.height + "px";
        this.div.style.background = this.bg;
        this.div.style.borderRadius = 5 + "px";
        this.div.style.border = "1px solid " + this.borderColor;
        //border, margin, padding에 의한 박스의 크기가 outside 쪽으로 커지지 않고 inside 쪽으로 적용되어 너비에 영향을 주지 않음
        this.div.style.boxSizing = "border-box"; 
        this.div.style.position = "absolute";

        // 날짜 출력 div
        this.numDiv.style.width = 100 + "%";
        this.numDiv.style.height = 25 + "px";
        this.numDiv.style.textAlign = "right";
        this.numDiv.style.padding = "0px 5px 0px 0px"; //top right bottom left
        this.numDiv.style.boxSizing = "border-box";
        
        // 다이어리 제목 div style
        this.titleDiv.style.width = 100 + "%";
        this.titleDiv.style.height = 25 + "px";

        //아이콘 영역 div style
        this.iconDiv.style.width = 100 + "%";
        this.iconDiv.style.height = 50 + "%";

        //셀에 3층 div 각각 부착
        this.div.appendChild(this.numDiv);
        this.div.appendChild(this.titleDiv);
        this.div.appendChild(this.iconDiv);

        this.container.appendChild(this.div);
        
        //현재 이 셀에 클릭 이벤트 구현
        // 화살표 함수는 this를 가질 수 없으므로, 바깥쪽 상위 스코프인 객체를 가리키려면 화살표 함수를 사용하겠음 
        // this.div가 필요한게 아니라 this(cell)가 필요함
        // this.div는 x, y가 명시되어있지 않음 
        this.div.addEventListener("click", ()=>{
            // 창을 띄울거임 
            openDialog(this);
        });
    }

    setDate(year, month, date){
        this.year = year;
        this.month = month;
        this.date = date;

        this.numDiv.innerText = date;
    }
    //셀이 자신이 보유한 아이콘을 그리기
    renderIcon(src, width){
        this.icon = document.createElement("img");
        this.icon.src = src;
        this.icon.style.width = width + "px";
        this.iconDiv.appendChild(this.icon);
    }
}