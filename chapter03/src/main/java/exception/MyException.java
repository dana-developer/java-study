package exception;

@SuppressWarnings("serial")
public class MyException extends Exception {
	public MyException() {
		super("MyException Throw");
	}
	
	public MyException(String message) {
		super(message);
	}
}
