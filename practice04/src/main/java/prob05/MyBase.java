package prob05;

public class MyBase extends Base {
	
	@Override
	public void service(String state) {
		if(state.equals("오후")) {
			afternoon();
			return;
		}
		super.service(state);
	}
	
	public void afternoon() {
		System.out.println("오후도 낮과 마찬가지로 일해야 합니다.");
	}
	
	@Override()
	public void day() {
		System.out.println("낮에는 열심히 일하자!");
	}
}
