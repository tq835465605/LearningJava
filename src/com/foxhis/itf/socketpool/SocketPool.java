package com.foxhis.itf.socketpool;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;




/**
 * socket池，默认大小是5，最大是1<<3=8
 */
public class SocketPool implements Serializable{
	

	private static final long serialVersionUID = -1L;
	//host
	private String host;
	//port
	private int port;
	//连接超时设置
	private int connectTimeout;
	//读取超时设置
	private int readTimeout;
	//初始容量大小
	private transient int iniSize = 1;
	//最大容量大小
	private transient int maxSize = 1<<8;
	//默认主机
	private static final String DEFAULT_HOST = "localhost";
	//默认端口
	private static final int DEFAULT_PORT = 6666;
	//默认初始容量
	private static final int DEFAULT_INI_SIZE = 1;
	//默认最大容量
	private static final int DEFAULT_MAX_SIZE = 10;
	//默认连接超时时间
	private static final int DEFAULT_CONNECT_TIMEOUT = (1<<1)*1000;
	//默认读超时时间
	private static final int DEFAULT_READ_TIMEOUT = (1<<5)*1000;
	
	private  ReentrantLock lock = new ReentrantLock();
	
	//socket容器，也可以采用队列queue,
	private transient List<SocketMember> socketContainer = new ArrayList<SocketMember>(iniSize);
	
	public SocketPool() {
		// TODO Auto-generated constructor stub
		this(DEFAULT_HOST, DEFAULT_PORT, DEFAULT_INI_SIZE, DEFAULT_MAX_SIZE,DEFAULT_CONNECT_TIMEOUT,DEFAULT_READ_TIMEOUT);
	}
	
	public SocketPool(String host) {
		// TODO Auto-generated constructor stub
		this(host, DEFAULT_PORT, DEFAULT_INI_SIZE, DEFAULT_MAX_SIZE,DEFAULT_CONNECT_TIMEOUT,DEFAULT_READ_TIMEOUT);
	}
	
	public SocketPool(String host,int port) {
		// TODO Auto-generated constructor stub
		this(host, port, DEFAULT_INI_SIZE, DEFAULT_MAX_SIZE,DEFAULT_CONNECT_TIMEOUT,DEFAULT_READ_TIMEOUT);
	}
	
	public SocketPool(String host,int port,int maxsize) {
		// TODO Auto-generated constructor stub
		this(host, port, DEFAULT_INI_SIZE, maxsize,DEFAULT_CONNECT_TIMEOUT,DEFAULT_READ_TIMEOUT);
	}
	
	public SocketPool(String host,int port,int connecttimeout,int readtimeout) {
		// TODO Auto-generated constructor stub
		this(host, port, DEFAULT_INI_SIZE, DEFAULT_MAX_SIZE,connecttimeout,readtimeout);
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public SocketPool(String host,int port,int inisize,int maxsize,int connecttimeout,int readtimeout) {
		// TODO Auto-generated constructor stub
		this.host = host;
		this.port = port;
		this.iniSize = inisize;
		this.maxSize = maxsize;
		this.connectTimeout = connecttimeout;
		this.readTimeout = readtimeout;
		//buildPoolPart();
		
	}
	
	public Socket creatSocket() throws Exception
	{
		Socket socket = new Socket();        

		socket.connect(new InetSocketAddress(host, port), connectTimeout);
		socket.setTcpNoDelay(true);
		socket.setKeepAlive(true);
		socket.setSoTimeout(readTimeout);

		return socket;
	}

	public List<SocketMember> buildPoolPart() throws Exception
	{
		 List<SocketMember> poolPart=new ArrayList<SocketMember>(iniSize);  
		 SocketMember member=null;  
		 for(int i=0;i<iniSize;i++)
		 {
			 Socket socket=null;
			 socket = creatSocket();
			 member=new SocketMember();  
			 member.setSocket(socket);  
			 poolPart.add(member);  

		 }
		 if(poolPart.size()>0){  
			 socketContainer.addAll(poolPart);  
		 }
		 else{  
			 try {  
				 throw new Exception("初始化socket池容量失败");  
			 }  
			 catch (Exception e) {  
				 e.printStackTrace();  
			 }  
		 }  
		 return poolPart;  
	}
	
	public SocketMember getMemberSocketFromPool() throws Exception
	{
		SocketMember socketMember=null;
		ReentrantLock thislock = this.lock;
		thislock.lock();
		for(int i=0;i<socketContainer.size();i++){  
            SocketMember memberInPool=socketContainer.get(i);  
            boolean inUse=memberInPool.isInUse();  
            if(inUse==false){  
                memberInPool.setInUse(true);  
                socketMember=memberInPool;  
                System.out.println("成功获取对象,在池中的位置为："+i);  
                System.out.println("此时容器大小为"+socketContainer.size());
                break;  
            } 
        } 
		//没有可用的时候，或者没有的情况下新建
		if(socketMember==null)
		{
			if(socketContainer.size()<maxSize){   
				//扩容一倍
				List<SocketMember> newpart =buildPoolPart();
				if(newpart.size()>0)
				{
					socketMember = newpart.get(0);
					socketMember.setInUse(true);
					socketMember.setBreak(false); 
					System.out.println("从扩容中获取一个来，此时容器大小为"+socketContainer.size());
				}
				else {
					throw new BuldPoolException("扩容失败");
				}
				
			}
		}
		thislock.unlock();
		//如果超过最大容量 等待 递归  
		if(socketMember==null){  
			try {  
				Thread.sleep(1000);  
			}  
			catch (InterruptedException e) {  
				e.printStackTrace();  
			}  
			socketMember=getMemberSocketFromPool();  
		}   
		return socketMember;
	}	
	
	public List<SocketMember> getSocketContainer()
	{
		return socketContainer;
	}
	
	public boolean isInitSocketContainer()
	{
		return socketContainer.size()>0?Boolean.TRUE:Boolean.FALSE;
	}
	
	public String getHost()
	{
		return host;
	}
	public int getPort()
	{
		return port;
	}
	
	public void distroy()
	{
		for(int i=0;i<socketContainer.size();i++)
		{
			socketContainer.remove(i);
		}
		socketContainer.clear();
	}
	
}
