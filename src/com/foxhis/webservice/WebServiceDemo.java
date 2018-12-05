package com.foxhis.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
* Title: WebServiceDemo
* Description: 基于jdk1.6以上的javax.jws 发布webservice接口
                @WebService － 它是一个注解，用在类上指定将此类发布成一个ws。
                Endpoint – 此类为端点服务类，它的方法publish用于将一个已经添加了@WebService注解
                对象绑定到一个地址的端口上。 
* Version:1.0.0  
* @author tq
 */
@WebService  
public class WebServiceDemo {

	/** 供客户端调用方法  该方法是非静态的，会被发布
     * @param name  传入参数
     * @return String 返回结果
     * */
    public String getValue(String name){
        return "欢迎你！ "+name;
    }
    
    /**
          * 方法上加@WebMentod(exclude=true)后，此方法不被发布；
     * @param name
     * @return
     */
    @WebMethod(exclude=true)  
    public String getHello(String name){
        return "你好！ "+name;
    }

    /** 静态方法不会被发布
     * @param name
     * @return
     */
    public static String getString(String name){
        return "再见！"+name;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 Endpoint.publish("http://127.0.0.1:8080/Service/WebServiceDemo", new WebServiceDemo());
	     System.out.println("发布成功!");
	}

}
