package structural.proxy;

import structural.proxy.context.Proxy;
//import structural.proxy.context.Context;
import structural.proxy.i.Subject;

public class Client {
	
	public static void main(String[] args) {
//		Context ctx = new Context(); Context는 같은 패키지내에서만 접근 가능 (Proxy를 통해서만 Context를 실행가능)
//		Subject subject = ctx.getSubject(); 
//		subject.doAction();
		
		Subject subject = new Proxy(); // 같은 패키지에서만 접근하도록
		subject.doAction();
	}
}
