package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class TCPClient {

	public static void main(String[] args) {
		Socket socket = null;
				
		try {
			// 1. 소켓 생성
			socket = new Socket();
			
			//1-1. 소켓버퍼 사이즈 확인
			int rcvBufferSize = socket.getReceiveBufferSize();
			int sndBufferSize = socket.getSendBufferSize();
			System.out.println(rcvBufferSize + ":" +sndBufferSize);
			
			//1-2. 소켓버퍼 사이즈 변경
			socket.setReceiveBufferSize(1024 * 10);
			socket.setSendBufferSize(1024 * 10);
			
			rcvBufferSize = socket.getReceiveBufferSize();
			sndBufferSize = socket.getSendBufferSize();
			System.out.println(rcvBufferSize + ":" +sndBufferSize);

			//1-3. SO_NODELAY(Nalge Algorithm OFF) : 소켓 옵션 
			socket.setTcpNoDelay(true);
			
			//1-4. SO_TIMEOUT
			socket.setSoTimeout(3000);
			
			// 2. 서버 연결
			socket.connect(new InetSocketAddress("0.0.0.0", 7100));
			
			// 3. IO Stream 받아오기
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			
			// 4. 쓰기
			String data = "Hello World";
			os.write(data.getBytes("utf-8"));
			
			// 5. 읽기
			byte[] buffer = new byte[256];
			int readByteCount = is.read(buffer);
			if(readByteCount == -1) {
				System.out.println("[client] closed by client");
				return;
			}
			
			data = new String(buffer, 0, readByteCount, "utf-8");
			System.out.println("[client] received : "+data);
			
		} catch(SocketTimeoutException e) {
			System.out.println("[client] Timeout!!");
		} catch (SocketException e) {
			System.out.println("[client] Socket Exception : "+ e);
		} catch (IOException e) {
			System.out.println("[client] error:"+e);
		} finally {
				try {
					if(socket != null && !socket.isClosed()) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
