package behavioral.templatemethod;

public class Client {
	public static void main(String[] args) {
		new Calculator().add();
		new Calculator().subtract(); // 단점 : 클래스가 점차 커져야 하고 모두 구현되어 있어야 한다.
		
		AbstractCalculate ac = new MultiplyCalculate();
		ac.templateMethod();
		
		ac = new DivideCalculate();
		ac.templateMethod();
	}
}
