package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {	//과제 1-1 NSLookup 구현하기

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.print("> ");
			
			String str = scanner.nextLine();
			
			if(str.equals("exit")) {
				break;
			}
			
			try {
				InetAddress[] InetAddresses = InetAddress.getAllByName(str);
				
				for(InetAddress inetAddress : InetAddresses) {
					System.out.print(str + " : ");
					System.out.println(inetAddress.getHostAddress());
				}
				
			} catch (UnknownHostException e) {
				System.out.println("error : "+ e);
			}
		}
		
		scanner.close();
		
	}

}
