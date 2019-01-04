package com.foxhis.itf.socketpool;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;



public class TCPSocketExecutor extends AbstractSocketExecutor{

	
	private static final String DEFAULT_CHARSET = "UTF-8";
	private String charset;
	
	//构造静态加载socket
	public SocketMember getSocketMember() {
		return super.getSocketMember();
	}

	public void setSocketMember(SocketMember socketMember) {
		super.setSocketMember(socketMember);
	}

	//动态加载socket
	public TCPSocketExecutor(SocketMember socketMember) {
		super(socketMember);
		// TODO Auto-generated constructor stub
	}
	
	public TCPSocketExecutor() {
		// TODO Auto-generated constructor stub
		super();
	}
	/**
	 * 发送请求，默认编码
	 * @param str
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void setRequest(String str) throws UnsupportedEncodingException, IOException
	{
		super.request(str, DEFAULT_CHARSET);
		charset  = DEFAULT_CHARSET;
	}
	
	/**
	 * 发送请求，指定编码
	 * @param str
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void setRequest(String str,String charset) throws UnsupportedEncodingException, BuldPoolException, IOException
	{
		super.request(str, charset);
		this.charset=charset;
	}

	public String getResponse()
	{
		StringBuilder responseResult=new StringBuilder();  
		BufferedInputStream socketReader=null;  
		int line=-1;
        try {  
            socketReader=new BufferedInputStream((super.getResStream())) ;  
            byte[] buff = new byte[1024];
            while((line=socketReader.read(buff))!=-1){  
            	{
            		if(line>0)
            		{
            			responseResult.append(new String(buff,0,line,charset));
    					if (socketReader.available() == 0) {
    						break;
    					}
            		}
            		
            		//responseResult.append(new String(buff, DEFAULT_CHARSET));
            	}
            }
        }
        catch (Exception e) {
			// TODO: handle exception
        	System.out.println(""+e);
        	super.hasbreak();
		}
        finally {
			super.back();
			
		}
        return  responseResult.toString();
	}
}
