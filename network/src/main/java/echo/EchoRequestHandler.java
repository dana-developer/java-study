package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoRequestHandler extends Thread {
	private Socket socket;
	
	public EchoRequestHandler(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			InetSocketAddress inetRemoteAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			inetRemoteAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteAddress.getPort();
			
			EchoServer.log("[connected by client["+inetRemoteAddress+":"+remotePort+"]");

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			while(true) {
				String data = br.readLine();
				if(data == null) {
					EchoServer.log("closed by client");
					break;
				}
				
				EchoServer.log("receive : " + data);
				
				pw.println(data + "\n");
			}
			
		} catch(SocketException e) {
			EchoServer.log("Socket Exception : "+ e);
		} catch (IOException e) {
			EchoServer.log("error : "+ e);
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
}
