package chat.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Base64;

import chat.ChatServer;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;

	public ChatWindow(String name) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
	}

	public void show() { // 소켓 생성
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		buttonSend.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				sendMessage();
			}
		});
// 		위와 같은 역할 (추론, 객체 생성을 자동화(자바는 함수를 파라미터로 넘길 수 없다)))
//		buttonSend.addActionListener((ActionEvent actionEvent) -> {});
		

		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if(keyChar == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		
		frame.setVisible(true);
		frame.pack();
				
		
		try {
			// 1. 서버 연결 작업			
			socket = new Socket();
			
			socket.connect(new InetSocketAddress(ChatServer.SERVER_IP, ChatServer.PORT));
			
			// 2. IO Stream Set
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			// 3. JOIN Protocol			
			String encodedNickname = encoding(frame.getTitle());
			
			pw.println("join:"+ encodedNickname);
			pw.flush();
			
			// 4. ChatClientThread 생성
			new ChatClientThread().start();
						
		} catch (IOException e) {
			log(e.toString());
		}
	}
	
	private void sendMessage() {
		String message = textField.getText();
		
		textField.setText("");	//채팅창 지워주기
		textField.requestFocus();
		
		if(!"".equals(message)){
			String encodedMessage = encoding(message); 
			pw.println("message:" + encodedMessage);
			pw.flush();
		}
	}
	
	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}
	
	public static void log(String message) {
		System.out.println("[chat client] : "+message);
	}
	
	public static String encoding(String message) {
		return Base64.getEncoder().encodeToString(message.getBytes());
	}
	
	private void finish() {
		// quit 프로토콜 구현
		pw.println("quit:");
		pw.flush();
		
		try {
			if(socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch (IOException e1) {
			log(e1.toString());
		}
		
		// exit java application
		System.exit(0);
	}
	
	private class ChatClientThread extends Thread { //inner class
		
		@Override
		public void run() {
			
			String data = null;
			
			try {
				while(true) {
					data = br.readLine();
					
					if(data == null) {
						log("closed by server");
						break;
					}
					
					if(data.equals("join:ok")) {
						updateTextArea("입장하셨습니다. 즐거운 채팅 되세요.");
					} else {
						updateTextArea(data);
					}
				}		
			} catch (IOException e) {
				log(e.toString());
			}
		}
	}
}
