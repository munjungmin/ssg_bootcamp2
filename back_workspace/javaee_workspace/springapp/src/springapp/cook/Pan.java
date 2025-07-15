package springapp.cook;
/**
 * 애플리케이션의 규모가 큰 엔터프라이즈 급일때는, 유지보수을 높이는 것이 곧 비용과 직결됨 
 * 따라서 유지보수성을 높이기 위해서는 has a 관계에 있어, 정확한 자료형을 보유하기 보다는
 * 상위 개념의 자료형을 보유함으로써, 객체와 객체간의 관계를 느슨하게 하는게 좋다!
 */


// pan을 상속받는 객체가 이미 누군가의 자식일 수도 있어서 interface로 사용 
public interface Pan {
	public void boil();
}
