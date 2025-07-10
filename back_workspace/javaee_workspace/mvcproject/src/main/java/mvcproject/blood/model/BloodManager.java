package mvcproject.blood.model;

//MVC에서 모델은 비즈니스 로직이 구현된 코드이므로, 재사용의 대상이 된다. 
//MVC에서 개발자의 노력이 가장 많이 들어가는 영역
public class BloodManager {

	public String getAdvice(String blood) {
		String msg = null;
		
		if(blood.equals("A")){
			msg = "신중하고 꼼꼼함";
		}else if(blood.equals("B")){
			msg = "자유롭고 개성 강함";
		}else if(blood.equals("O")){
			msg = "외향적이고 리더십 있음";
		}else if(blood.equals("AB")){
			msg = "이성적이고 독특함";
		}
		
		return msg;
	}
}
