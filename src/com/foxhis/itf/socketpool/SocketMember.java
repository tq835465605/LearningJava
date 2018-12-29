package com.foxhis.itf.socketpool;

import java.net.Socket;

public class SocketMember {
	
	//socket对象  
    private Socket socket;  
    //是否正被使用  
    private boolean inUse=false;  
    //是否已经坏了
    private boolean isBreak = false;
    
    public boolean isBreak() {
		return isBreak;
	}
	public void setBreak(boolean isBreak) {
		this.isBreak = isBreak;
	}
	public Socket getSocket() {  
        return socket;  
    }  
    public void setSocket(Socket socket) {  
        this.socket = socket;  
    }  
    public boolean isInUse() {  
        return inUse;  
    }  
    public void setInUse(boolean inUse) {  
        this.inUse = inUse;  
    }  
      

}
