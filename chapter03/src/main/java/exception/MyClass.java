package exception;

import java.io.IOException;

public class MyClass {
	 // exception을 던지는 메소드인지 명시해야 한다.
	public void danger() throws MyException, IOException {
		System.out.println("some code1...");
		System.out.println("some code2...");
		
		if(1-1 == 0) {
			throw new MyException();
		}
		
		if(2-2 == 0) {
			throw new IOException();
		}
		
		System.out.println("some code3...");
		System.out.println("some code4...");
	}
}
