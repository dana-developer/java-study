package prob04;

public class MyStack02 {
	private int top;
	private Object[] buffer;

	public MyStack02(int capacity) {
		buffer = new Object[capacity];
		top = 0;
	}

	public void push(Object s) {
		if(top >= buffer.length) {
			resize();
		}
		buffer[top] = s;
		top++;
	}

	public Object pop() throws MyStackException {
		Object result = null;
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
		Object[] newBuffer = new Object[buffer.length * 2];
		
		for(int i=0; i<buffer.length; i++) {
			newBuffer[i] = buffer[i];
		}
		
		buffer = newBuffer;
	}	
}
