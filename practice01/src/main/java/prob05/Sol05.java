package prob05;

public class Sol05 {
	public static void main(String[] args) {

		for(int i=1; i<=100; i++) {
			// 619 -> "619" 문자열로 변환 -> for문으로 3,6,9인지 비교
			String str = String.valueOf(i);
			int cnt = 0;
						
			for(int j=0; j<str.length(); j++) {
				char c = str.charAt(j);
				
				if(c == '3' || c == '6' || c == '9') {
					cnt++;
				}
			}
						
			if(cnt > 0) {
				System.out.print(i);
				
				for(int k=0; k<cnt; k++) {
					System.out.print(" 짝");
				}
				
				System.out.println();
			}
		}
	}
}
