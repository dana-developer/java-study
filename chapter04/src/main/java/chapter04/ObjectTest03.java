package chapter04;

public class ObjectTest03 {

	public static void main(String[] args) {
		String s1 = new String("hello"); //객체로 생성
		String s2 = new String("hello");
		
		System.out.println(s1 == s2); 		//false
		System.out.println(s1.equals(s2));	//true (String에 Override 되어 있다)
		System.out.println(s1.hashCode() + " : "+s2.hashCode()); // 둘 다 같다.(hashCode와 equals 모두 override 되어 있다.)
		System.out.println(System.identityHashCode(s1) + ":" + System.identityHashCode(s2)); // Override하지 않은 hashCode 호출

		System.out.println("========================");
		
		String s3 = "hello"; // 리터럴 (String 사용할 때는 리터럴을 사용하기)
		String s4 = "hello";
				
		System.out.println(s3 == s4); 		//true
		System.out.println(s3.equals(s4));	//true 
		System.out.println(s3.hashCode() + " : "+s4.hashCode());
		System.out.println(System.identityHashCode(s3) + ":" + System.identityHashCode(s4)); // Override하지 않은 hashCode 호출
	}

}
