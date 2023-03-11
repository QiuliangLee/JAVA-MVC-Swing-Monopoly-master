package model;


public class Tick {
	
	protected long nowTick;
	
	
	protected long startTick;
	
	protected long lastTime;
	
	protected long nextTick;
	
	public long getLastTime() {
		return lastTime;
	}
	
	public long getNowTick() {
		return nowTick;
	}

	public long getStartTick() {
		return startTick;
	}

	public long getNextTick() {
		return nextTick;
	}
	

	public void setNowTick(long nowTick) {
		this.nowTick = nowTick;
	}

	public void setStartTick(long startTick) {
		this.startTick = startTick;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public void setNextTick(long nextTick) {
		this.nextTick = nextTick;
	}

}
