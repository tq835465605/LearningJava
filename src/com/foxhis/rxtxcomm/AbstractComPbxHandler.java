/*package com.foxhis.rxtxcomm;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.foxhis.frame.JFoxOptionPane;
import com.foxhis.itf.handler.AbstractPbxHandler;
import com.foxhis.utils.SwingUtils;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public abstract class AbstractComPbxHandler extends AbstractPbxHandler
{
	private String com_port;
	private int baud_rate;
	private int data_bits = 8;
	private int stop_bits = 1;
	private int patiry = 0;
	private InputStream ins;
	private OutputStream outs;
	private volatile static String oldMsg = "";
	private SerialPort serialPort;
	private static Object object = new Object();
    protected String confirm;
	
	public void deinitialize()
	{
		server_stop();
	}

	@Override
	public String getItfVersion() {
		// TODO Auto-generated method stub
		return "1.0";
	}
	
	private String readinfo()
	{
		String bill = "";
		byte[] buff = new byte[1024];
		try {
			if (this.ins != null)
			{
				int availables = this.ins.available();
				while (availables > 0)
				{
					this.ins.read(buff);
					bill +=  new String(buff);
					availables = this.ins.available();
				}
			}
		}
		catch (Exception e)
		{
			SwingUtils.log_error(getClass(), e.getMessage(), e);
		}
		return bill;
	}

	public Map<String, Object> getMessageInternal()
	{
		synchronized (object) {
			try {
				object.wait();
				String reMsg = readinfo();	
				SwingUtils.log_debug(getClass(), "getMessageInternal <==" + reMsg);
				if(StringUtils.isNotBlank(reMsg))
				{
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
			catch (Exception e) {
				// TODO Auto-generated catch block
				SwingUtils.log_error(getClass(), e.getMessage(), e);
			}
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

	public void server_initInternal()
			throws Exception
	{
	}

	public void server_restartInternal()
	{
		SwingUtils.log_debug(getClass(), "Comport Reconnect...[" + this.com_port + "," + this.baud_rate + "]");

		CommPortIdentifier portIdentifier = null;
		try {
			portIdentifier = CommPortIdentifier.getPortIdentifier(this.com_port);
		}
		catch (NoSuchPortException e) {
			SwingUtils.log_error(getClass(), "没有" + this.com_port + "串口，请检查配置参数,并重新启动接口服务");
			JFoxOptionPane.showMessageDialog(null,  "没有" + this.com_port + "串口，请检查配置参数,并重新启动接口服务");
			return;
		}
		try {
			this.serialPort = ((SerialPort)portIdentifier.open(getClass().getName(), 2000));
		}
		catch (PortInUseException e) {
			SwingUtils.log_error(getClass(), this.com_port + "串口被占有，请关闭串口被占有程序，并重新启动接口服务");
			try {
				server_stopInternal();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				SwingUtils.log_error(getClass(), "关闭串口失败");
				return;
			}
		}
		try
		{
			this.serialPort.setSerialPortParams(this.baud_rate, this.data_bits, this.stop_bits, this.patiry);
			serialPort.addEventListener(new PortListener());
			serialPort.notifyOnDataAvailable(true);
			serialPort.notifyOnDSR(true);
			serialPort.notifyOnCTS(true);
			this.ins = this.serialPort.getInputStream();
			this.outs = this.serialPort.getOutputStream();

		} catch (Exception e) {
			SwingUtils.log_error(getClass(), "串口连接异常，请检查配置参数，并重新启动接口服务");
			return;
		}
		SwingUtils.log_debug(getClass(), "连接串口成功");
	}


	public void server_stopInternal() throws Exception {
		if (this.ins != null) {
			this.ins.close();
			this.ins=null;
		}
		if (this.outs != null) {
			this.outs.close();
			this.outs=null;
		}
		if (this.serialPort != null)
		{
			this.serialPort.close();
			this.serialPort = null;
		}
		SwingUtils.log_debug(getClass(), "Connect Comport OK !");
	}

	public String msgSender(String content) throws Exception {
		if (this.outs != null)
		{
			this.outs.write((content + "").getBytes());
			this.outs.flush();
		}
		SwingUtils.log_debug(getClass(), "Sent to the com port: " + content);
		return content;
	}

	public String msgSender(String content,String charset) throws Exception {
		if (this.outs != null)
		{
			this.outs.write((content + "").getBytes(charset));
			this.outs.flush();
		}
		SwingUtils.log_debug(getClass(), "Sent to the com port: " + content);
		return content;
	}

	public void setCom_port(String comPort)
	{
		this.com_port = comPort;
	}

	public void setBaud_rate(int baudRate) {
		this.baud_rate = baudRate;
	}

	public void setData_bits(int databits) {
		this.data_bits = databits;
	}
	public void setStop_bits(int stopbits) {
		this.stop_bits = stopbits;
	}
	public void setPatiry(int patirys) {
		this.patiry = patirys;
	}
	
	public abstract boolean isPhoneBill(String paramString);

	public abstract boolean isRmstaBill(String paramString);

	public abstract boolean isMinibaBill(String paramString);

	public abstract boolean isResponse(String paramString);

	public abstract List<String> validateBillMsg(String paramString);

	public abstract Map<String, String> splitMsg(String paramString);


	class PortListener implements SerialPortEventListener{

		@Override
		public void serialEvent(SerialPortEvent ev) {
			// TODO Auto-generated method stub
			int key = ev.getEventType();
			switch (key) {
			//有数据时候
			case SerialPortEvent.DATA_AVAILABLE:
				synchronized (object) {
					//					logger.info(">>Has msg to XMS！");
					SwingUtils.log_debug(getClass(), "有数据！！");
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

}*/