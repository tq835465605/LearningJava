package com.foxhis.itf.socketpool;

import java.net.Socket;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 检查池中socket是否活跃
 * @author Administrator
 *
 */
public class CheckPoolSocketActiveThread implements Runnable{

	private SocketPool pool;
	private volatile boolean flg;
	private ThreadFactory threadFactory;
	
	public boolean isFlg() {
		return flg;
	}

	public void setFlg(boolean flg) {
		this.flg = flg;
	}

	public CheckPoolSocketActiveThread(SocketPool pool) {
		// TODO Auto-generated constructor stub
		this.pool = pool;
		this.flg = true;
	}
	

	public CheckPoolSocketActiveThread(SocketPool pool,ThreadFactory threadFactory) {
		// TODO Auto-generated constructor stub
		this.pool = pool;
		this.flg = true;
		this.threadFactory = threadFactory;
	}
	
	public void start0()
	{
		if(threadFactory==null)
			threadFactory = new DefaultThreadFactory();
		threadFactory.newThread(this).start();
	}
	
	public void stop0()
	{
		this.flg=false;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<SocketMember> socketMembers = pool.getSocketContainer();
		while(this.flg)
		{
			//10妙检查一次
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//遍历检查是否连接
			int size = socketMembers.size();
			for(int i=0;i<size;i++)
			{
				SocketMember socketMember = socketMembers.get(i);
				Socket socket = socketMember.getSocket();
				synchronized (socket) {
					if(socketMember.isBreak())
					{
						System.out.println("位置"+i+"socket已经坏了");
						try {
							socket = pool.creatSocket();
							socketMember.setSocket(socket);
							socketMember.setBreak(false);
							socketMember.setInUse(false);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							System.out.println("重建异常");
						}
						continue;
					}
					else {
						if(!socketMember.isInUse())
						{
							//socket没在用
							System.out.println("位置"+i+"socket没在用"+socket.getLocalPort());
							if(socket.isClosed() || !socket.isConnected())
							{
								System.out.println("socket关闭了");
								try {
									socket = pool.creatSocket();
									socketMember.setSocket(socket);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									System.out.println("重建异常");
								}
								
							}
						}
						else {
							//socket在用
							System.out.println(i+"socket在用"+socket.getLocalPort());
							
						}
					}
					
				}
			}
		}
	}

	private class DefaultThreadFactory implements ThreadFactory{

		private final AtomicLong threadNumberGenrator = new AtomicLong(0L);

		@Override
		public Thread newThread(Runnable r) {
			// TODO Auto-generated method stub
			long threadNumber = this.threadNumberGenrator.getAndIncrement();
			String threadName = "foxhis-asynchronized-socket-pool-thread-" + threadNumber;
			Thread defaultthread = new Thread(r,threadName);
			return defaultthread;
		}
		
	}
}
