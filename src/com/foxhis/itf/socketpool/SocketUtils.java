package com.foxhis.itf.socketpool;

import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketUtils {
	
	
	private static Socket creatSocket(String host,int port,int connectTimeout,int readTimeout ) throws Exception
	{
		Socket socket = new Socket();        
		socket.connect(new InetSocketAddress(host, port), connectTimeout);
		socket.setTcpNoDelay(true);
		socket.setKeepAlive(true);
		socket.setSoTimeout(readTimeout);
		return socket;
	}
	
	
	public static SocketMember getSocketMember(String host,int port,int connectTimeout,int readTimeout ) throws Exception
	{
		 Socket socket = creatSocket(host,port,connectTimeout,readTimeout);
		 SocketMember member=new SocketMember();  
		 member.setSocket(socket);  
		 return member;
	}

	
	
}
