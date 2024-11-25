package chapter04;

public class ObjectTest01 {

	public static void main(String[] args) {
		Point point = new Point(); 				// loading 된다.
		
		Class kclass = point.getClass(); 		// reflection 
		
		System.out.println(kclass); 			// kclass.toString()과 동일. 자동으로 toString 호출
		
		System.out.println(point.hashCode());	// address 기반의 해싱값 (자바는 주소를 직접 보여주지 않음)
												// address X
												// reference 값 X
		
		System.out.println(point.toString());	// getClass().toString() + "@" + hashCode()
		System.out.println(point);				
	}

}
