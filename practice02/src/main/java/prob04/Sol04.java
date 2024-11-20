package prob04;
public class Sol04 {

	public static void main(String[] args) {
		char[] c1 = reverse("Hello World");
		printCharArray(c1);
		
		char[] c2 = reverse("Java Programming!");
		printCharArray(c2);
	}
	
	public static char[] reverse(String str) {
		int len = str.length();
		char[] reverse_str = new char[len];
		
		for(int i=0; i < len; i++) {
			reverse_str[i] = str.charAt(len-i-1);
		}
		
		return reverse_str;
	}

	public static void printCharArray(char[] array){
		System.out.println(array);
	}
}