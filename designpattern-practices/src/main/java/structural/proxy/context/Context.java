package structural.proxy.context;

import structural.proxy.i.Subject;

class Context {
	private Subject realSubject;
	
	public Context() {
//		this(new Subject() {
//			@Override
//			public void doAction() {
//				
//			}
//		});
		this(() -> {System.out.println("Subject.doAction in Context done");});
	}
	
	private Context(Subject subject) {
		realSubject = subject;
	}

	Subject getSubject() {
		return realSubject;
	}
}
