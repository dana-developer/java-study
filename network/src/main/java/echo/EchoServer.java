package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {
	public static final int PORT = 7100;
	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			log("starts...[port:"+PORT+"]");
			
			Socket socket = serverSocket.accept();
			
			try {
				InetSocketAddress inetRemoteAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
				inetRemoteAddress.getAddress().getHostAddress();
				int remotePort = inetRemoteAddress.getPort();
				
				log("[connected by client["+inetRemoteAddress+":"+remotePort+"]");
				
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
				
				while(true) {
					String data = br.readLine();
					if(data == null) {
						log("closed by client");
						break;
					}
					
					log("receive : " + data);
					pw.println(data + "\n");
				}
				
			} catch (SocketException e) {
				log("Socket Exception : "+ e);
			} catch (IOException e) {
				log("error : "+ e);
			} finally {
				try {
					if(socket != null && !socket.isClosed()) {
						socket.close();
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			
			
		} catch (IOException e) {
			log("error: " + e);
		} finally {
			try {
				if(serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void log(String message) {
		System.out.println("[Echo server] : "+message);
	}
}
