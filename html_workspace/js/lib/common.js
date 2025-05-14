function getRandom(max){
    return parseInt(Math.random() * (max+1));  
}

/**
 * 한자리수 정수에 대한 0처리
 */
function zeroString(n){
    let str = (n >= 0 && n < 10) ? ("0" + n) : n;
    return str;
}

/**
 * 해당 월의 시작 요일 구하기
 * API 사용 예) 2025년 5월 getStartDay(2025, 4)
 * mm은 0부터 시작 
 */

function getStartDay(yy, mm){
    let d = new Date(yy, mm, 1); 
    return d.getDay();
}

/**
 * 영어 또는 한국어로 요일을 변환하여 반환하기
 * API 사용 예) convertDay(1, "kor");
 */
function convertDay(n, lang){
    let korArray=["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    let engArray=["Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"];

    let day = (lang == "kor") ? korArray[n] : engArray[n];
    return day;
}

/**
 * 해당 월의 마지막 날 구하기
 * API 사용 예) getLastDate(year, month);
 * 6월의 마지막 날을 구하고 싶으면 7월 0일로 설정해야 해서 mm
 */
function getLastDate(yy, mm){
    let d = new Date(yy, mm + 1, 0);
    return d.getDate();
}

/**
 * 충돌 체크 함수
 * 
 */
function collisionCheck(me, target){
    //나에 대한 수치계산
    let me_x = parseFloat(me.style.left);
    let me_y = parseInt(me.style.top);
    let me_width = parseInt(me.style.width);
    let me_height = parseInt(me.style.height);

    let target_x = parseInt(target.style.left);
    let target_y = parseInt(target.style.top);
    let target_width = parseInt(target.style.width);
    let target_height = parseInt(target.style.height);

    // 충돌하지 않는 경우 (b물체가 a 물체를 중심으로 상하좌우에 위치했을때 충돌하지 않을 조건)
    return !(
        me_x + me_width < target_x  ||  
        me_x > target_x + target_width ||
        me_y + me_height < target_y ||
        me_y > target_y + target_height
    );

}


