package prob01;

import java.util.Scanner;

public class Sol01 {
	public static void main(String[] args) {
		System.out.print("금액 : ");
		Scanner scanner = new Scanner(System.in);

		final int[] MONEYS = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1};

		int number = scanner.nextInt();
		for(int i=0; i<MONEYS.length; i++) {
			int cnt = number / MONEYS[i];
			System.out.print(MONEYS[i]);
			System.out.println("원 : "+cnt+"개");
			
			if(cnt > 0) {
				number =  number % MONEYS[i];
			} 
		}
		
		scanner.close();
 	}
}