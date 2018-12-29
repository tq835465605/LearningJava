package com.foxhis.itf.socketpool;

public class SocketExcutorFactory {
	
	private SocketPool pool=null; 
	
    public SocketExcutorFactory(String host,int port,int connecttimeout,int readtimeout ,boolean daemonFlag){  
        pool=new SocketPool(host, port,connecttimeout,readtimeout);  
        if(daemonFlag){  
        //守护线程  
          new CheckPoolSocketActiveThread(pool).start0();
        }  
    }  
    public SocketExcutorFactory(String host,int port,int connecttimeout,int readtimeout ){  
        this(host, port,connecttimeout,readtimeout ,true);  
    }  
    
    public void buildSocketPool() throws Exception{
    	
    	pool.buildPoolPart();
    }
    
    public void distroySocketPool()
    {
    	pool.distroy();
    }
    
    public boolean isIniPool()
    {
    	return pool.isInitSocketContainer();
    }
    
    
    /**
     * 从工厂里取出一个tcpsocket
     * @return
     */
    public TCPSocketExecutor getTCPSocketExcutor(SocketMember socketMember) 
    {
    	TCPSocketExecutor tcpSocketExecutor=null;
    	tcpSocketExecutor=new TCPSocketExecutor(socketMember);
    	return tcpSocketExecutor;
    }
    
    TCPSocketExecutor tcpSocketExecutor=null;
    public TCPSocketExecutor getTCPSocketExcutor() 
    {
    	TCPSocketExecutor tcpSocketExecutor=null;
    	tcpSocketExecutor=new TCPSocketExecutor();
    	return tcpSocketExecutor;
    }
    
    public SocketMember getMemberSocket() throws Exception
    {
    	return pool.getMemberSocketFromPool();
    }

}
