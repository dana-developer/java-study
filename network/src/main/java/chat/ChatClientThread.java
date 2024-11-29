package chat;

import java.io.BufferedReader;
import java.io.IOException;

public class ChatClientThread extends Thread {
	private BufferedReader bufferedReader;

	public ChatClientThread(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
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
					System.out.println("입장하셨습니다. 즐거운 채팅 되세요.");
				} else {
					System.out.println(data);
				}
			}		
		} catch (IOException e) {
			ChatClient.log(e.toString());
		}
	}
}
