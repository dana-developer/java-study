package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.List;

public class ChatServer {
	public static final int PORT = 7100;
	public static final String SERVER_IP = "0.0.0.0";

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			// 서버 소켓 생성
			serverSocket = new ServerSocket();
			
			serverSocket.bind(new InetSocketAddress(SERVER_IP, PORT));
			log("starts...[port:"+PORT+"]");
			
			List<PrintWriter> writerPool = new Vector<PrintWriter>(); //동기화 처리
			
			while(true) {
				Socket socket = serverSocket.accept();
				new ChatServerThread(socket, writerPool).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void log(String message) {
		System.out.println("[chat server] " + message);
	}
}
