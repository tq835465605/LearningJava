package com.foxhis.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SocketClientChannel {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Client.getInstance().getClient();
	}

}

/**
 * 简单实现socket的NIO方法
 * NIO:
 * 传统的IO是阻塞的，效率很低，NIO 的文件读写设计颠覆了传统 IO 的设计，采用通道+缓存区使得新式的 IO 操作直接面向缓存区，并且是非阻塞的，效率高
 * 
 * 1.通道Channel(双向)
 *   Channel-->Buffer
 *   Channel<--Buffer
 *   1.1 分类：FileChannel，DatagramChannel，SocketChannel，ServerSocketChannel涵盖了文件,UDP,TCP.
 *   1.2 Buffer:通道与缓存是并存的，以ByteBuff最为典型。
 *   1.3 JVM对缓存IO的处理：JVM 内存划分为栈和堆，堆具有gc操作，所以缓存需要用堆外内存，不被gc。
 *   
 * 2.选择器 Selector
 *   Selector 是 Java NIO 的一个组件，它用于监听多个 Channel 的各种状态（OP_READ ，OP_WRITE OP_CONNECT，OP_ACCEPT ）
 *   FileChannel 不支持注册选择器，Selector 一般被认为是服务于网络套接字通道的。
 *   创建一个选择器一般是通过 Selector 的工厂方法，Selector.open().
 *   
 * 3.步骤，以Socket为例
 *   1.SocketChannel打开通道
 *   2.设置该通道默认为非阻塞
 *   3.建一个选择器Seletctor
 *   4.将通道注册到选择器中，并选择需要监听的状态，如Op_read
 *   5.连接服务端，等待连接完毕，
 *   6.检查当前选择器注册的通道是否有就绪的事件，如果没有，则阻塞
 *   7.取出所有的就绪通道SelectionKey
 *   8.针对该就绪通道开始实现读写操作
 *   
 *  
 * @author Administrator
 *
 */
class Client 
{
	private static Client client = new Client();
	private Client() {
		// TODO Auto-generated constructor stub
	}
	public static Client getInstance()
	{
		return client;
	}
	
	public void getClient() throws IOException
	{
		//打开通道
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		Selector selector = Selector.open();
		//将通道注册到选择器中，并选择需要监听的状态，如Op_read
		channel.register(selector, SelectionKey.OP_READ);
		channel.connect(new InetSocketAddress("127.0.0.1", 6666));
		System.out.println("wating connect...");
		while(true)
		{
			if(channel.finishConnect())
			{
				break;
			}
		}
		System.out.println("finish connect...");
		//检查当前选择器注册的通道是否有就绪的事件，如果没有，则阻塞
		selector.select();
		//取出所有的就绪通道SelectionKey
		Set<SelectionKey> keys = selector.selectedKeys();
		Iterator<SelectionKey> sIterator =keys.iterator();
		while(sIterator.hasNext())
		{
			SelectionKey sKey =sIterator.next();
			if(sKey.isReadable())
			{
				System.out.println("has selectonkey readable");
				ByteBuffer buffer =ByteBuffer.allocate(1024);
				SocketChannel channel2  = (SocketChannel)sKey.channel();
				channel2.read(buffer);
				buffer.flip();
				System.out.println(buffer.toString());
				channel2.close();
			}
		}
		selector.close();
		channel.close();
	}
	 
}
