package com.foxhis.itf.socketpool;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

/**
  * 抽象出socket服务
 * @author Administrator
 *
 */
public class AbstractSocketExecutor {
	
	protected SocketMember socketMember=null;  
	protected Socket socket=null;  
    protected String host;  
    protected int port;  
    protected InputStream in;
    protected OutputStream out;
    
	
    public SocketMember getSocketMember() {
		return socketMember;
	}

	public void setSocketMember(SocketMember socketMember) {
		this.socketMember = socketMember;
		if(socketMember!=null){  
            this.socket=socketMember.getSocket();  
        }  
		host=socket.getInetAddress().getHostName();  
        port=socket.getPort();
       
	}

    public AbstractSocketExecutor(SocketMember socketMember){  
        setSocketMember(socketMember);
    }  
    
    public AbstractSocketExecutor()
    {
    	
    }
    
    
    /** 
     * 发送请求 
      * 
      * @param request 
      * @param charset     
     * @throws IOException 
     * @throws UnsupportedEncodingException 
      * 
      * @Description: TODO 
     */  
    protected void request(String request,String charset) throws UnsupportedEncodingException, IOException{  
    	
    	out=socket.getOutputStream();  
    	out.write(request.getBytes(charset));      
        out.flush();

    }  
    /** 
     * 返回响应stream
      * 
      * @return     
     * @throws IOException 
      * 
      * @Description: TODO 
     */  
    protected InputStream getResStream() throws IOException{  
    	
    	in = socket.getInputStream();  

    	return in;  
    }  
    /** 
     * 设置状态为未使用 
      *     
      * 
      * @Description: TODO 
     */  
    protected void back(){  
        //不能关闭流，否则socket会被关闭  
//        try { 
//            socket.getOutputStream().close(); 
//            socket.getInputStream().close(); 
//            //socket.close();
//        } 
//        catch (IOException e) { 
//            e.printStackTrace(); 
//        }
//    	IOUtils.closeQuietly(in);
//    	IOUtils.closeQuietly(out);
//    	IOUtils.closeQuietly(socket);
//    	in=null;
//    	out=null;
//    	socket=null;
        socketMember.setInUse(false);  
    }  
    
    protected void hasbreak(){  
        //不能关闭流，否则socket会被关闭  
        /*try { 
            socket.getOutputStream().close(); 
            socket.getInputStream().close(); 
        } 
        catch (IOException e) { 
            e.printStackTrace(); 
        }*/
        socketMember.setBreak(true);  
    }  

}
