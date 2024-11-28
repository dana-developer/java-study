package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();
			
			// 2. 바인딩(binding)
			//	  Socket에 InetSocketAddress[InetAddress(IPAddress) + port)]를 바인딩한다.
			//	  IPAddress : 0.0.0.0 : 특정 호스트 IP를 바인딩하지 않는다.
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 7100));
			
			// 3. accept
			Socket socket = serverSocket.accept(); // blocking
			
			try {
				InetSocketAddress inetRemoteAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
				inetRemoteAddress.getAddress().getHostAddress();
				int remotePort = inetRemoteAddress.getPort();
				
				System.out.println("[server] connected by client["+inetRemoteAddress+":"+remotePort+"]");
				
				// 4. IO Stream 받아오기
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				
				// 5. 데이터 읽기
				while(true) {
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer); // blocking
					
					if(readByteCount == -1) {
						// closed by client
						System.out.println("[server] closed by client");
						break;
					}
					
					String data = new String(buffer, 0, readByteCount, "utf-8");
					System.out.println("[server] receive : " + data);
					
					// 6. 데이터 쓰기
					os.write(data.getBytes("utf-8"));
				}
				
			} catch (IOException e) {
				System.out.println("error : "+ e);
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
			System.out.println("[server] error: " + e);
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

}
