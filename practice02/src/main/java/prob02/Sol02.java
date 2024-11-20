package prob02;

import java.util.Scanner;

public class Sol02 {
	public static void main(String[] args) {
		System.out.println("5개 숫자를 입력하세요.");
		Scanner scanner = new Scanner(System.in);
		
		int[] intArray = new int[5];
		double sum = 0;
		
		//배열에 값 입력받기
		for(int i=0; i<intArray.length; i++) {
			int number = scanner.nextInt();
			intArray[i] = number;
		}
		
		//배열의 값 합계 구하기
		for(int i=0; i<intArray.length; i++) {
			sum = sum + intArray[i];
		}
		
		double average = sum / intArray.length;
		
		System.out.println("평균은 "+average+"입니다.");
		
		scanner.close();
	}
}
