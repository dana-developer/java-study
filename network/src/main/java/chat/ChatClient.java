package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

public class ChatClient {
	
	public static void main(String[] args) {
		Socket socket = null;
		Scanner scanner = null;
		
		try {
			socket = new Socket();
			scanner = new Scanner(System.in);
			
			socket.connect(new InetSocketAddress(ChatServer.SERVER_IP, ChatServer.PORT));
			
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			// join 프로토콜
			System.out.print("닉네임>>");
			String nickname = scanner.nextLine();
			
			String encodedNickname = encoding(nickname); 
			pw.println("join:"+encodedNickname);
			pw.flush();
			
			new ChatClientThread(br).start();
			
			while(true) {
				String input = scanner.nextLine();
				
				if("quit".equals(input)) {
					pw.println("quit:");
					pw.flush();
					break;
				} else if(!"".equals(input)){
					String encodedMessage = encoding(input); 
					pw.println("message:" + encodedMessage);
					pw.flush();
				}
			}
		} catch (IOException e) {
			log(e.toString());
		} finally {
				try {
					if(scanner != null) {
						scanner.close();
					}
					if(socket != null && !socket.isClosed()) {
						socket.close();
					}
				} catch (IOException e) {
					log(e.toString());
				}
		}
	}
	
	public static void log(String message) {
		System.out.println("[chat client] : "+message);
	}
	
	public static String encoding(String message) {
		return Base64.getEncoder().encodeToString(message.getBytes());
	}
}
