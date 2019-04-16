package com.foxhis.thread;


/**
 * 无yield
I am Consumer : Consumed Item 0
I am Consumer : Consumed Item 1
I am Consumer : Consumed Item 2
I am Consumer : Consumed Item 3
I am Consumer : Consumed Item 4
I am Producer : Produced Item 0
I am Producer : Produced Item 1
I am Producer : Produced Item 2
I am Producer : Produced Item 3
I am Producer : Produced Item 4

有yield
yield方法只能让拥有相同优先级的线程有获取CPU执行时间的机会。
yield方法并不会让线程进入阻塞状态，而是让线程重回就绪状态
I am Consumer : Consumed Item 0
I am Consumer : Consumed Item 1
I am Consumer : Consumed Item 2
I am Consumer : Consumed Item 3
I am Producer : Produced Item 0
I am Consumer : Consumed Item 4
I am Producer : Produced Item 1
I am Producer : Produced Item 2
I am Producer : Produced Item 3
I am Producer : Produced Item 4

 * @author Administrator
 *
 */
public class YieldDemo {

	public static void main(String[] args) {

		Thread producer = new Producer();
		Thread consumer = new Consumer();

		producer.setPriority(Thread.MIN_PRIORITY); //Min Priority
		//优先级越大，越提前运行
		consumer.setPriority(Thread.MAX_PRIORITY); //Max Priority

		producer.start();
		consumer.start();
	}
}

class Producer extends Thread
{
	public void run()
	{
		for (int i = 0; i < 10; i++)
		{
			System.out.println("I am Producer : Produced Item " + i);
			//Thread.yield();
		}
	}
}

class Consumer extends Thread
{
	public void run()
	{
		for (int i = 0; i < 10; i++)
		{
			System.out.println("I am Consumer : Consumed Item " + i);
			//Thread.yield();
		}
	}

}
