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
    
    /**
     * 构建池
     */
    public void buildSocketPool(){
    	
    	pool.buildPoolPart();
    }
    /**
     * 销毁池
     */
    public void distroySocketPool()
    {
    	pool.distroy();
    }
    
    /**
     * 判断是否池化
     * @return
     */
    public boolean isIniPool()
    {
    	return pool.isInitSocketContainer();
    }
    
    
  /**
      * 生成一个tcp的socket，并用来发送请求与接收反馈信息
   * @param socketMember 从池中获取到的
   * @return TCPSocketExecutor
   */
    public TCPSocketExecutor getTCPSocketExcutor(SocketMember socketMember) 
    {
    	TCPSocketExecutor tcpSocketExecutor=null;
    	tcpSocketExecutor=new TCPSocketExecutor(socketMember);
    	return tcpSocketExecutor;
    }
    
    
    /**
     * 生成一个tcp的socket，此时的tcpsocket还没有socketmember，需要手动设置，并用来发送请求与接收反馈信息
  * @return TCPSocketExecutor
  */
    public TCPSocketExecutor getTCPSocketExcutor() 
    {
    	TCPSocketExecutor tcpSocketExecutor=null;
    	tcpSocketExecutor=new TCPSocketExecutor();
    	return tcpSocketExecutor;
    }
    
    /**
          * 从池中获取一个MemberSocket
     * @return
     * @throws Exception 如果没有可用的，则会重构池，如果这个时候服务端是关闭的，那会出现重构异常
     */
    public SocketMember getMemberSocket() throws Exception
    {
    	return pool.getMemberSocketFromPool();
    }

}
