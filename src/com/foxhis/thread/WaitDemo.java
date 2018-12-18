package com.foxhis.thread;

public class WaitDemo {
	
   public static void main(String[] args) {
	
	   
	   ThreadA t1 = new ThreadA("t1");

       synchronized(t1) {
           try {
               // 启动“线程t1”
               System.out.println(Thread.currentThread().getName()+" start t1");
               t1.start();

               // 主线程等待t1通过notify()唤醒。
               System.out.println(Thread.currentThread().getName()+" wait()");
               t1.wait();

               System.out.println(Thread.currentThread().getName()+" continue");
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
       
}
	

}



class ThreadA extends Thread{

    public ThreadA(String name) {
        super(name);
    }

    public void run() {
        synchronized (this) {
        	while(true)
        	{
        		  System.out.println(Thread.currentThread().getName()+" call notify()");
        	}
          
            // 唤醒当前的wait线程
           // notify();
        }
    }
}