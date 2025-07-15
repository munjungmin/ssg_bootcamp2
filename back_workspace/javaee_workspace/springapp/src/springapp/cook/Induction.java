package springapp.cook;

public class Induction implements Pan{

	@Override
	public void boil() {
		System.out.println("전기로 지지다");
	}
}
