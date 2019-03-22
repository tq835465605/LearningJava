package com.foxhis.timer;

import java.util.Timer;
import java.util.TimerTask;

public class FoxTimerTask extends TimerTask{

	
	private CTimer ctimer;
	private IEventListener callback;
	
	public void setCallback(IEventListener callback)
	{
		this.callback = callback;
	}
	
	public FoxTimerTask(CTimer cTimer) {
		// TODO Auto-generated constructor stub
		ctimer = cTimer;
	}
	
	public void execute()
	{
		Timer timer = new Timer(true);
		timer.schedule(this, ctimer.getDeploy(), ctimer.getTimer()*1000);
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		callback.trigger(new Object[] {});
	}

}
