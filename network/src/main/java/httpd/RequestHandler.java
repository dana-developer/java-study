package httpd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

public class RequestHandler extends Thread {
	private Socket socket;
	private final String DOCUMENT_ROOT = "./webapp";
	
	public RequestHandler(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			OutputStream outputStream = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			consoleLog("connected from " + inetSocketAddress.getAddress().getHostAddress() + ":" 
					+ inetSocketAddress.getPort());
			
			String request = null;
			
			while(true) {
				String line = br.readLine();
				
				// 브라우저가 연결을 끊으면...
				if(line == null) {
					break;
				}
				
				// SimpleHttpServer는 HTTP Header만 읽는다.
				if("".equals(line)) {
					break;
				}
				
				// Request Header의 첫 줄만 읽는다.
				if(request == null) {
					request = line;
					break;
				}
			}
			
			consoleLog(request);
			String[] tokens = request.split(" ");
			if("GET".equals(tokens[0])) {
				// tokens[1] : URI, tokens[2] : protocol
				responseStaticResponse(outputStream, tokens[1], tokens[2]); 
			} else {
				// methods : POST, DELETE, PUT, HEAD, CONNECT, ...
				// SimpleHttpServer에서는 무시(400 Bad Request)
//				errorResponse(outputStream, "400", "Bad Request", tokens[2]);
				response400error(outputStream, tokens[2]);
			}
					
			// 예제 응답입니다.
			// 서버 시작과 테스트를 마친 후, 주석 처리 합니다.
//			outputStream.write( "HTTP/1.1 200 OK\n".getBytes("UTF-8")); //header
//			outputStream.write("Content-Type:text/html; charset=utf-8\n".getBytes("UTF-8"));
//			outputStream.write("\n".getBytes());
//			outputStream.write( "<h1>이 페이지가 잘 보이면 실습과제 SimpleHttpServer를 시작할 준비가 된 것입니다.</h1>".getBytes("UTF-8"));

		} catch(Exception ex) {
			consoleLog("error:" + ex);
		} finally {
			// clean-up
			try{
				if(socket != null && socket.isClosed() == false) {
					socket.close();
				}
				
			} catch(IOException ex) {
				consoleLog( "error:" + ex );
			}
		}			
	}
	
	private void responseStaticResponse(OutputStream os, String url, String protocol) throws IOException {
		if("/".equals(url)) {
			url = "/index.html";
		}
		
		File file = new File(DOCUMENT_ROOT + url);
		
		if(!file.exists()) {
			// 404 response
			response404error(os, protocol);
//			errorResponse(os, "404", "Not Found", protocol);
			return;
		}
		
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		
		os.write((protocol+" 200 OK\n").getBytes("UTF-8")); //header
		os.write(("Content-Type:" + contentType + "; charset=utf-8\n").getBytes("UTF-8"));
		os.write("\n".getBytes());
		os.write(body);
	}

	public void consoleLog(String message) {
		System.out.println("[RequestHandler#" + getId() + "] " + message);
	}
	
//	private void errorResponse(OutputStream outputStream, String errorType, String errorMessage, String protocol) throws IOException {
//		File file = new File(DOCUMENT_ROOT+"/error/" + errorType + ".html");
//		String contentType = Files.probeContentType(file.toPath());
//		byte[] body = Files.readAllBytes(file.toPath());
//
//		outputStream.write((protocol+" "+errorType + " "+errorMessage+"\n").getBytes("UTF-8"));
//		outputStream.write(("Content-Type:" + contentType + "; charset=utf-8\n").getBytes("UTF-8"));
//		outputStream.write("\n".getBytes());
//		outputStream.write(body);
//	}
	
	private void response404error(OutputStream outputStream, String protocol) throws IOException {
		/*
		 HTTP/1.1 404 File Not Found\n
		 Content-type:text.html; charset=utf-8\n
		 */
		
		File file = new File(DOCUMENT_ROOT+"/error/404.html");
		String contentType = Files.probeContentType(file.toPath());
		byte[] body = Files.readAllBytes(file.toPath());
		
		outputStream.write((protocol + " 404 File Not Found\n").getBytes("UTF-8"));
		outputStream.write(("Content-Type:" + contentType + "; charset=utf-8\n").getBytes("UTF-8"));
		outputStream.write("\n".getBytes());
		outputStream.write(body);
	}
	
	private void response400error(OutputStream outputStream, String protocol) throws IOException {
		/*
		 HTTP/1.1 400 Bad Request\n
		 Content-type:text.html; charset=utf-8\n
		 */
		
		File file = new File(DOCUMENT_ROOT+"/error/400.html");
		String contentType = Files.probeContentType(file.toPath());
		byte[] body = Files.readAllBytes(file.toPath());
		
		outputStream.write((protocol + " 400 Bad Request\n").getBytes("UTF-8"));
		outputStream.write(("Content-Type:" + contentType + "; charset=utf-8\n").getBytes("UTF-8"));
		outputStream.write("\n".getBytes());
		outputStream.write(body);
	}
}
