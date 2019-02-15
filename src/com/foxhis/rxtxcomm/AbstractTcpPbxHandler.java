/*package com.foxhis.rxtxcomm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.foxhis.itf.handler.AbstractPbxHandler;
import com.foxhis.utils.SwingUtils;

public abstract class AbstractTcpPbxHandler extends AbstractPbxHandler{

	private String remoteip ;
	private int remoteport;
	private int contimeout;
	private int redtimeout;

	private volatile static String oldMsg = "";
	private SocketFactory socketFactory;
	protected String confirm;
	private volatile boolean isSvrClosed=false;

	public void deinitialize()
	{
		server_stop();
	}

	@Override
	public String getItfVersion() {
		// TODO Auto-generated method stub
		return "1.0";
	}

	public Map<String, Object> getMessageInternal()
	{
		try {
			if(socketFactory==null) return null;
			if(!isSvrClosed) {
				SwingUtils.log_debug(getClass(), "PBXServer exception");
				server_stop();
				server_init();
				server_restart();
				//5秒后继续重连
				TimeUnit.SECONDS.sleep(5);
			}
			else {
				String reMsg = socketFactory.readinfo();	
				if(StringUtils.isNotBlank(reMsg))
				{
					SwingUtils.log_debug(getClass(), "getMessage<<" + reMsg);
					Map<String, Object> retMap = new HashMap<String, Object> ();
					//万一多条数据，可分割
					List<String> msgList = validateBillMsg(reMsg);
					for (String bill : msgList) {
						if(isPhoneBill(bill))
						{
							int type = TYPE_PHONEBILL;
							retMap.put(TAG_TYPE, type);
							retMap.put(TAG_USEROBJECT, bill);
							return retMap;
						}
						if(isRmstaBill(bill))
						{
							int type = TYPE_RMSTA;
							retMap.put(TAG_TYPE, type);
							retMap.put(TAG_USEROBJECT, bill);
							return retMap;
						}
						if(isMinibaBill(bill))
						{
							int type = TYPE_MINIBILL;
							retMap.put(TAG_TYPE, type);
							retMap.put(TAG_USEROBJECT, bill);
							return retMap;
						}
						if(isResponse(bill))
						{
							int type = TYPE_RESPOND;
							retMap.put(TAG_TYPE, type);
							retMap.put(TAG_USEROBJECT, bill);
							return retMap;
						}
					}

				}
			}
			TimeUnit.MILLISECONDS.sleep(500);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			SwingUtils.log_error(getClass(), e.getMessage(), e);
		}

		return null;

	}

	*//**
	 * XMS处理话单后调用,参数返回值参见接口定义
	 *//*
	public void postPhoneBillResponseInternal(int result, Object userObject) throws Exception {
		// TODO 处理 
		int i = result;
		switch (i) {
		case 0:
			SwingUtils.log_debug(getClass(),"postBillRes>>"+userObject + "已接收，发送服务器成功！");
			break;
		case 5:
			SwingUtils.log_debug(getClass(),"postBillRes>>"+userObject + "已接收，呼出号码不存在,不进行处理！");
			break;
		case 7:
			SwingUtils.log_debug(getClass(),"postBillRes>>"+userObject + "已接收，话单分解异常！");
			break;
		case 255:
			SwingUtils.log_debug(getClass(),"postBillRes>>"+userObject + "UploadPhone异常！");
			break;
		default:
			SwingUtils.log_debug(getClass(),"postBillRes>>"+userObject + "已接收，JBOSS服务器无法接收!请检查费率等配置");
			break;
		}
	}

	public List<Map<String, String>> parsePhoneBillInternal(Object userObject)
			throws Exception
	{

		String srcMsg = (String)userObject;
		if(StringUtils.isBlank(srcMsg)) return null;
		System.out.println(userObject);
		if(oldMsg!=srcMsg)
		{
			List<Map<String, String>> ret = new ArrayList<Map<String,String>>();
			oldMsg = srcMsg;
			Map<String,String> mapPhoneBill  = splitMsg(srcMsg);
			ret.add(mapPhoneBill);
			return ret;
		}
		return null;
	}

	private Thread monitor;
	public void server_initInternal() throws Exception
	{
		try
		{
			SwingUtils.log_debug(getClass(), "TCP Reconnect...[" + this.remoteip + "," + this.remoteport + "]");
			socketFactory = new SocketFactory(remoteip, remoteport, contimeout,redtimeout);
			socketFactory.createSocket();
			isSvrClosed = true;
		} catch (Exception e) {
			SwingUtils.log_error(getClass(), "连接异常，请检查配置参数，并重新启动接口服务"+e);
			//JFoxOptionPane.showMessageDialog(null, "连接异常，请检查日志信息，并重新启动接口服务");
			return;
		}
		SwingUtils.log_debug(getClass(), "连接服务端成功");
	}

	public void server_restartInternal()
	{
		monitor = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(!Thread.currentThread().isInterrupted())
				{
					if(socketFactory==null) continue;
					isSvrClosed = socketFactory.checkConnected();
					try {
						TimeUnit.SECONDS.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						Thread.currentThread().interrupt();
					}
				}
			}
		});
		monitor.start();
		SwingUtils.log_debug(getClass(), "启动监控服务端线程");
	}

	public void msgSender(String content) throws Exception
	{
		if(socketFactory!=null)
			socketFactory.msgSender(content);
	}

	public void msgSender(String content,String charset) throws Exception
	{
		if(socketFactory!=null)
			socketFactory.msgSender(content,charset);
	}

	public void server_stopInternal() throws Exception {
		if(socketFactory!=null)
			socketFactory.closeSocket();
		monitor.interrupt();
		SwingUtils.log_debug(getClass(), "Stop TCPClient OK !");
	}


	public void setRemoteIp(String ip)
	{
		this.remoteip = ip;
	}

	public void setRemotePort(int port) {
		this.remoteport = port;
	}

	public void setRedTimeout(int timeout) {
		this.redtimeout = timeout;
	}
	public void setConTimeout(int timeout) {
		this.contimeout = timeout;
	}

	public abstract boolean isPhoneBill(String paramString);

	public abstract boolean isRmstaBill(String paramString);

	public abstract boolean isMinibaBill(String paramString);

	public abstract boolean isResponse(String paramString);

	public abstract List<String> validateBillMsg(String paramString);

	public abstract Map<String, String> splitMsg(String paramString);

	@Override
	public boolean receiveChangeInternal(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	class SocketFactory{

		String remoteip;
		int remoteport;
		int contimeout;
		int redtimeout;
		Socket socket;

		volatile InputStream ins;
		OutputStream outs;

		public SocketFactory(String ip,int port,int timeout,int redtimeout) {
			// TODO Auto-generated constructor stub
			remoteip = ip;
			remoteport = port;
			contimeout = timeout;
			this.redtimeout = redtimeout;
		}

		public Socket createSocket() throws IOException
		{
			socket = new Socket();
			socket.connect((new InetSocketAddress(remoteip, remoteport)),contimeout);
			socket.setSoTimeout(redtimeout);
			ins = socket.getInputStream();
			outs = socket.getOutputStream();
			socket.setTcpNoDelay(true);
			socket.setKeepAlive(true);
			return socket;
		}

		public String msgSender(String content) throws Exception {
			msgSender(content, "utf-8");
			return content;
		}

		public String msgSender(String content,String charset) throws Exception {
			if (outs != null)
			{
				outs.write((content + "").getBytes(charset));
				outs.flush();
			}
			return content;
		}

		public String readinfo()
		{
			String bill = "";
			byte[] buff = new byte[1024];
			try {
				if (ins != null )
				{
					int availables = ins.available();
					while (availables > 0)
					{
						ins.read(buff);
						bill +=  new String(buff);
						availables = ins.available();
					}
				}
			}
			catch (Exception e)
			{
				SwingUtils.log_error(getClass(), e.getMessage(), e);
			}
			return bill.trim();
		}


		public boolean checkConnected()
		{
			try {
				socket.sendUrgentData(0xFF);
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return false;
			}
		}
		public void closeSocket()
		{
			IOUtils.closeQuietly(ins);
			IOUtils.closeQuietly(outs);
			IOUtils.closeQuietly(socket);
			ins = null;
			outs = null;
			socket = null;
		}

	}

}
*/