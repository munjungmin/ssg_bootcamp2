class Word{
    constructor(container, x, y, text, color){
        this.container = container;
        //span인 이유: inline이므로 너비가 컨텐츠만큼만 확보되기 때문
        this.span = document.createElement("span");
        this.x = x;
        this.y = y;
        this.text = text;  // 이 span이 포함할 단어
        this.color = color; // 폰트 색상 

        //style
        this.span.style.left = this.x + "px";
        this.span.style.top = this.y + "px";
        this.span.style.position = "absolute";
        this.span.style.display = "inline-block";
        this.span.innerText = this.text;
        this.span.style.color = this.color;

        this.container.appendChild(this.span);
    }

    tick(){
        this.y += 5;
    }

    render(){   
        this.span.style.top = this.y + "px";
    }
}