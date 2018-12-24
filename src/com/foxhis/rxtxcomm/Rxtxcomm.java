package com.foxhis.rxtxcomm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.RXTXPort;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class Rxtxcomm {

	private static final String COM = "COM1";
	private static final int BAUD = 9600;
	private static final int DATABITS = RXTXPort.DATABITS_8;
	private static final int STOPBITS = RXTXPort.STOPBITS_1;
	private static final int PARITY  =RXTXPort.PARITY_NONE;
	private static InputStream inputStream;
	private static OutputStream outputStream;
	private static Object object = new Object();
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		CommPortIdentifier commPortIdentifier = CommPortIdentifier.getPortIdentifier(COM);
		if(commPortIdentifier.isCurrentlyOwned())
		{
			System.out.println("Error: Port is currently in use");
			System.exit(-1);
			return ;
		}
		CommPort commPort = commPortIdentifier.open(Rxtxcomm.class.getName(),1000);
		SerialPort serialPort =null;
		if(commPort instanceof SerialPort)
		{
			serialPort = (SerialPort) commPort;
			serialPort.setSerialPortParams(BAUD, DATABITS, STOPBITS, PARITY);
			serialPort.addEventListener(new PortListener());
			serialPort.notifyOnDataAvailable(true);
			serialPort.notifyOnDSR(true);
			serialPort.notifyOnCTS(true);
			inputStream = serialPort.getInputStream(); 
			outputStream = serialPort.getOutputStream();
			
			MessageDO messageDO = new MessageDO(serialPort);
			ExecutorService meService = Executors.newFixedThreadPool(1);
			meService.submit(messageDO);
			meService.shutdown();
			
		}
		
	}
	
	
	static class MessageDO implements Runnable{

		SerialPort serialport;
		
		public MessageDO(SerialPort serialPort) {
			// TODO Auto-generated constructor stub
			this.serialport = serialPort;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true)
			{
				synchronized (object) {
					try {
						System.out.println("进入等待接收数据...");
						object.wait();
						System.out.println("有数据待接收...");
						String reMsg = readInfo(serialport);
						System.out.println("返回数据:"+reMsg);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		private String readInfo(SerialPort serialPort) throws IOException
		{
			String msString="";
			int a = inputStream.available();
			int threshold = serialPort.getReceiveThreshold();
			System.out.println("threshold="+threshold);
			while(a>0)
			{
				byte[] buff = new byte[1024];
				inputStream.read(buff);
				msString+=new String(buff,"gbk");
				a  = inputStream.available();
			}
			return msString;
		}
		
	}

	static class PortListener implements SerialPortEventListener{

		@Override
		public void serialEvent(SerialPortEvent ev) {
			// TODO Auto-generated method stub
			int key = ev.getEventType();
			switch (key) {
			//有数据时候
			case SerialPortEvent.DATA_AVAILABLE:
				synchronized (object) {
					System.out.println("有数据了啊！！！");
					object.notify();
				}
				break;
			//数据准备就绪
			case SerialPortEvent.DSR:
				synchronized (object) {
					System.out.println("数据准备啊！！！");
					//object.notify();
				}
				break;
				//数据准备就绪
			case SerialPortEvent.CTS:
				synchronized (object) {
					System.out.println("终端数据准备啊！！！");
					//object.notify();
				}
				break;
					
			default:
				break;
			}
			
		}
		
	}
}
