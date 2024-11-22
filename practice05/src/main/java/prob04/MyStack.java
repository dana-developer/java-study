package prob04;

public class MyStack {
	private int top;
	private String[] buffer;

	public MyStack(int capacity) {
		buffer = new String[capacity];
		top = 0;
	}

	public void push(String s) {
		if(top >= buffer.length) {
			resize();
		}
		buffer[top] = s;
		top++;
	}

	public String pop() throws MyStackException {
		String result = null;
		if(top > 0) {
			result = buffer[top-1];
			top--;
		} else {
			throw new MyStackException("stack is empty");
		}
		return result;
	}

	public boolean isEmpty() {
		if(top == 0) {
			return true;
		} else {
			return false;
		}
	}

	private void resize() {
		String[] newBuffer = new String[buffer.length * 2];
		
		for(int i=0; i<buffer.length; i++) {
			newBuffer[i] = buffer[i];
		}
		
		buffer = newBuffer;
	}	
}