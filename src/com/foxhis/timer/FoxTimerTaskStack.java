package com.foxhis.timer;

import java.util.HashMap;
import java.util.Map;


public class FoxTimerTaskStack {
	
	
	private static FoxTimerTaskStack foxTimerTaskStack;

	private Map<String, FoxTimerTask> taskmap;
	
	private FoxTimerTaskStack() {
		
		taskmap = new HashMap<String, FoxTimerTask>();
		
	}
	public static FoxTimerTaskStack getInstance()
	{
		if(foxTimerTaskStack==null)
			foxTimerTaskStack = new FoxTimerTaskStack();
		return foxTimerTaskStack;
	}
	
	public void regsiterTask_second(String task_name,IEventListener callback,int timer,int deploy)
	{
		CTimer cTimer = new CTimer();
		cTimer.setTask_name(task_name);
		cTimer.setTimer(timer);
		cTimer.setDeploy(deploy);
		FoxTimerTask foxTimerTask = new FoxTimerTask(cTimer);
		foxTimerTask.setCallback(callback);
		taskmap.put(task_name, foxTimerTask);
	}
	
	public void executeTask(String taskname)
	{
		if(taskmap.containsKey(taskname))
		{
			taskmap.get(taskname).execute();
		}
	}

}
