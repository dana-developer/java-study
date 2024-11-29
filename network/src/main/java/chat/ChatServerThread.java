package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Base64;
import java.util.List;

public class ChatServerThread extends Thread {
	private String nickname;
	private Socket socket;
	private List<PrintWriter> listPrintWriters;
	
	public ChatServerThread(Socket socket, List<PrintWriter> listPrintWriters) {
		this.socket = socket;
		this.listPrintWriters = listPrintWriters;
	}
	
	@Override
	public void run() {
		try {
			InetSocketAddress inetRemoteAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			inetRemoteAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteAddress.getPort();
			
			ChatServer.log("connected by client["+inetRemoteAddress+":"+remotePort+"]");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			
			while(true) {
				String request = br.readLine();
				
				if(request == null) {
					ChatServer.log("closed by client");
					doQuit(pw);
					break;
				}
				
				String[] tokens = request.split(":");
				
				if("join".equals(tokens[0])) {
					doJoin(decoding(tokens[1]), pw);
				} else if("message".equals(tokens[0])) {
					doMessage(decoding(tokens[1]));
				} else if("quit".equals(tokens[0])) {
					doQuit(pw);
					break;
				} else {
					ChatServer.log("에러 알 수 없는 요청("+tokens[0]+")");
				}				
			}
		} catch (UnsupportedEncodingException e) {
			ChatServer.log(e.toString());
		} catch (IOException e) {
			ChatServer.log(e.toString());
		} finally {
			try {
				if(socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void doJoin(String nickname, PrintWriter printWriter) {
		this.nickname = nickname;
		
		String data = nickname + "님이 참여하였습니다.";
		broadcast(data);
		
		addWriter(printWriter);
		
		//ack
		printWriter.println("join:ok");
		printWriter.flush();
	}
	
	private void addWriter(PrintWriter printWriter) {
		synchronized(listPrintWriters) { // 동기화
			listPrintWriters.add(printWriter);
		}
	}
	
	private void broadcast(String data) {
		synchronized(listPrintWriters) { // 동기화
			for(PrintWriter pw : listPrintWriters) {
				PrintWriter printWriter = pw;
				printWriter.println(data);
				printWriter.flush();
			}
		}
	}
	
	private void doMessage(String message) {
		String data = nickname + ":"+ message;
		broadcast(data);
	}
	
	private void doQuit(PrintWriter printWriter) {
		removeWriter(printWriter);
		String data = nickname + "님이 퇴장하였습니다.";
		broadcast(data);
	}
	
	private void removeWriter(PrintWriter printWriter) {
		synchronized(listPrintWriters) { // 동기화
			listPrintWriters.remove(printWriter);
		}
	}
	
	private String decoding(String message) { // Base64로 decode하기
		return new String(Base64.getDecoder().decode(message));
		
	}
}
