package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientThread extends Thread {
	private BufferedReader bufferedReader;
	private Socket socket; //ChatClient의 socket 참조 변수
	private Scanner scanner;

	public ChatClientThread(BufferedReader bufferedReader, Socket socket, Scanner scanner) {
		this.bufferedReader = bufferedReader;
		this.socket = socket;
		this.scanner = scanner;
	}
	
	@Override
	public void run() {		
		String data = null;
		
		try {
			while(true) {
				data = bufferedReader.readLine();
				
				if(data == null) {
					ChatClient.log("closed by server");
					break;
				}
				
				if(data.equals("join:ok")) {
					System.out.println("입장하셨습니다. 즐거운 채팅 되세요");
				} else {
					System.out.println(data);
				}
			}		
		} catch (IOException e) {
			ChatClient.log(e.toString());
		} finally {
			try {				
				socket.close();
				scanner.close();
				
			} catch (IOException e) {
				ChatClient.log(e.toString());
			}
		}
	}
}
