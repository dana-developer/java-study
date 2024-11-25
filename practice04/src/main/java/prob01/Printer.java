package prob01;

public class Printer {
	
//	public void println(int x) {
//		System.out.println(x);
//	}
//	
//	public void println(boolean x) {
//		System.out.println(x);
//	}
//	
//	public void println(double x) {
//		System.out.println(x);
//	}
//	
//	public void println(String x) {
//		System.out.println(x);
//	}
	
	public <T> void println(T t) {
		System.out.println(t);
	}
	
	public <T> void println(T... ts) {
		for(T t : ts) {
			System.out.print(t + " ");
		}
		System.out.print("\n");
	}
	
	public int sum(Integer... nums) {
		int s = 0;
		for(Integer n : nums) {
			s += n;
		}
		return s;
	}
}
