package prob04;

public class MyStack03<T> {
	private int top;
	private T[] buffer;

	public MyStack03(int capacity) {
		buffer = (T[]) new Object[capacity];
		top = 0;
	}

	public void push(T s) {
		if(top >= buffer.length) {
			resize();
		}
		buffer[top] = s;
		top++;
	}

	public T pop() throws MyStackException {
		T result = null;
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
		T[] newBuffer = (T[]) new Object[buffer.length * 2];
		
		for(int i=0; i<buffer.length; i++) {
			newBuffer[i] = buffer[i];
		}
		
		buffer = newBuffer;
	}	
}
