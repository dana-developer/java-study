package prob01;

public class SmartPhone extends MusicPhone {

	@Override
	protected void playMusic() {
		System.out.println("다운로드해서 음악재생");
	}
	
	@Override
	public void execute(String function) {
		if(function.equals("앱")) {
			playApp();
			return;
		}

		super.execute(function);
	}
	
	public void playApp() {
		System.out.println("앱실행");
	}
}
