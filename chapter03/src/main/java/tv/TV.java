package tv;

public class TV {
	
	private int channel;
	private int volume;
	private boolean power;
	
	public TV() {
		// 기본 생성자 오버로딩
	}
	
	public TV(int channel, int volume, boolean power) {
		this.channel = channel;
		this.volume = volume;
		this.power = power;
	}

	public int getChannel() {
		return channel;
	}
	
	public int getVolume() {
		return volume;
	}
	
	public String isPower() { //power의 getter
		if(power) {
			return "on";
		} else {
			return "off";
		}
	}
	
	public void power(boolean on) {
		power = on;
	}
	
	public void channel(int channel) {
		this.channel = channel;
		
		if(this.channel > 255) {
			this.channel = 1;
		} else if(this.channel < 1) {
			this.channel = 255;
		}
	}
	
	public void channel(boolean up) {
		if(up) {
			channel(this.channel+1);
		} else {
			channel(this.channel-1);
		}
	}
	
	public void volume(int volume) {
		this.volume = volume;
		
		if(this.volume > 100) {
			this.volume = 0;
		} else if(this.volume < 0) {
			this.volume = 100;
		}
	}
	
	public void volume(boolean up) {
		if(up) {
			volume(this.volume+1);
		} else {
			volume(this.volume-1);
		}
	}
	
	public void status() {
		System.out.println(
				"TV[channel="+ channel +
				", volumne="+ volume +
				", power="+isPower()+"]");
	}
}
