package prob03;

import java.util.Scanner;

public class Sol03 {
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		while(true) {
			System.out.print("수를 입력하세요 : ");
			int number = scanner.nextInt();
			
			if(number == 0) { //종료 조건
				break;
			}
			
			int ret = 0;
		
			
			if(number % 2 == 1) { //홀수인 경우
				for(int i=1; i<=number; i+=2) {
					ret += i;
				}
			} else { //짝수인 경우
				for(int i=2; i<=number; i+=2) {
					ret += i;
				}
			}
			
			System.out.print("결과값: ");
			System.out.println(ret);
		}
		
		scanner.close();
	}
}
