package com.foxhis.threadpoolexecutor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


//不可重入锁
public class Mutex implements Lock,java.io.Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 12345L;

	//自定义同步器
	private static class Sync extends AbstractQueuedSynchronizer
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 321L;

		//判断是否锁定
		protected boolean isHeldExclusively() {
			return getState()==1;
		}
		
		//尝试获取资源
		protected boolean tryAcquire(int acquires) 
		{
			 assert acquires == 1; // 这里限定只能为1个量
			 if(compareAndSetState(0, 1))
			 {
				 setExclusiveOwnerThread(Thread.currentThread());
				 return true;
			 }
			 return false;
		}
		
		//尝试释放资源，立即返回。成功则为true，否则false。
		protected boolean tryRelease(int releases) {
			assert releases == 1; // 限定为1个量
			if (getState() == 0)//既然来释放，那肯定就是已占有状态了。只是为了保险，多层判断！
				throw new IllegalMonitorStateException();
			setExclusiveOwnerThread(null);
			setState(0);//释放资源，放弃占有状态
			return true;
		}
	}
	
	private final Sync sync = new Sync();
	
	@Override
	public void lock() {
		// TODO Auto-generated method stub
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		sync.release(1);
	}
	
	public boolean isLocked()
	{
		return sync.isHeldExclusively();
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
