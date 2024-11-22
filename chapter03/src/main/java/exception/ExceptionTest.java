package exception;

public class ExceptionTest {

	public static void main(String[] args) {
		int a = 10;
		int b = 10-a;
		
		System.out.println("some code1...");
		
		try {
			System.out.println("some code2...");
			System.out.println("some code3...");

			int result = (1+2+3) / b;
			
			System.out.println("some code4...");
			System.out.println("some code5...");
			
		} catch(ArithmeticException e) { //catch문 안에 반드시코드 작성
			// 예외 처리
			// 1. 로깅
			System.out.println("error:"+e);
			
			// 2. 사과 (사용자에게 사과)
			System.out.println("미안합니다...");
			
			//3. 정상종료
			return;
			
		} finally {
			System.out.println("자원정리 : ex) close file, socket, db connection");
		}
		
		System.out.println("some code6...");
		System.out.println("some code7...");

	}

}
