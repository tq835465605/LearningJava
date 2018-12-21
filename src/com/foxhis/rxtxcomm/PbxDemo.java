package com.foxhis.rxtxcomm;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.TooManyListenersException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

public class PbxDemo extends JFrame {

	private static final Log LOGGER = LogFactory.getLog(PbxDemo.class);
	private static final long serialVersionUID = 1L;
	private JTextField textip;
	private JTextField textport;
	private JLabel lblIp;
	private JLabel lblPort;
	private JLabel lblCom;
	private JLabel labelnumber;
	private JLabel lblBaud;
	private JLabel lbljy;
	private JLabel lblstop;
	private JComboBox comboBoxCom;
	private String com;
	private int baud;
	private int jy;
	private int stop;
	private int numb;
	private JTextField textbaud;
	private JComboBox comboBoxNum;
	private JComboBox comboBoxjy;
	private JComboBox comboBoxstop;
	private TextArea textArea;
	private JButton btnCloseCom;
	private JButton btnOpenCom;
	private JButton btnDisconnect;
	private JButton btnConnect;
	private TextArea textAreaSendMsg;
	private CommPort commPort = null;
	private InputStream ins;
	private OutputStream outs;
	private SerialPort serialPort;
	private int availableBytes;
	private Socket socket;
	private String serverip;
	private int port;
	public connectactivemoitor moitor = null;

	public PbxDemo() {
        this.commPort = null;
        this.moitor = null;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                if (PbxDemo.this.moitor != null) {
                    PbxDemo.this.moitor.set_stop();
                }
                PbxDemo.closePort(PbxDemo.this.serialPort);
            }
        });
        this.initComponents();
    }
    
    public void initComponents() {
        this.setBounds(200, 200, 807, 620);
        this.setTitle("电话接口测试");
        final JPanel Contentpanel = new JPanel();
        Contentpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.getContentPane().add(Contentpanel, "Center");
        Contentpanel.setLayout(null);
        (this.lblIp = new JLabel("IP：")).setBounds(10, 10, 31, 15);
        Contentpanel.add(this.lblIp);
        (this.textip = new JTextField()).setText("127.0.0.1");
        this.textip.setBounds(45, 7, 84, 21);
        Contentpanel.add(this.textip);
        this.textip.setColumns(10);
        (this.lblPort = new JLabel("port：")).setHorizontalAlignment(4);
        this.lblPort.setBounds(146, 10, 54, 15);
        Contentpanel.add(this.lblPort);
        (this.textport = new JTextField("5000")).setBounds(216, 9, 74, 21);
        Contentpanel.add(this.textport);
        this.textport.setColumns(10);
        (this.btnConnect = new JButton("connect")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
            	PbxDemo.this.serverip = PbxDemo.this.textip.getText().trim();
                System.out.println(PbxDemo.this.serverip);
                if (PbxDemo.this.serverip == null || PbxDemo.this.serverip.equals("")) {
                    JOptionPane.showMessageDialog(null, "IP不能为空！", "错误", 1);
                }
                else {
                    final String portstr = PbxDemo.this.textport.getText();
                    if (portstr == null || portstr.equals("")) {
                        JOptionPane.showMessageDialog(null, "端口port不能为空！", "错误", 1);
                    }
                    else {
                    	 PbxDemo.this.port = Integer.parseInt(portstr);
                         PbxDemo.this.socket = PbxDemo.this.openSocket(PbxDemo.this.serverip, PbxDemo.this.port);
                        if (PbxDemo.this.socket != null) {
                            PbxDemo.this.btnOpenCom.setEnabled(false);
                            PbxDemo.this.btnCloseCom.setEnabled(false);
                            PbxDemo.this.btnConnect.setEnabled(false);
                            PbxDemo.this.writelog("IP=" + PbxDemo.this.serverip + " port=" + PbxDemo.this.port);
                            (PbxDemo.this.moitor = new connectactivemoitor()).set_start();
                            PbxDemo.this.moitor.start();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "打开TCP/IP客户端失败,请确认服务端是否打开，IP与port正确及IP能ping通");
                        }
                    }
                }
            }
        });
        this.btnConnect.setBounds(347, 7, 93, 23);
        Contentpanel.add(this.btnConnect);
        (this.btnDisconnect = new JButton("disconnect")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                PbxDemo.this.btnOpenCom.setEnabled(true);
                PbxDemo.this.btnCloseCom.setEnabled(true);
                PbxDemo.this.btnConnect.setEnabled(true);
                PbxDemo.this.closeSocket();
            }
        });
        this.btnDisconnect.setBounds(463, 7, 100, 23);
        Contentpanel.add(this.btnDisconnect);
        (this.lblCom = new JLabel("串口号：")).setBounds(10, 50, 54, 15);
        Contentpanel.add(this.lblCom);
        (this.lblBaud = new JLabel("波特率：")).setBounds(139, 50, 54, 15);
        Contentpanel.add(this.lblBaud);
        (this.labelnumber = new JLabel("数据位：")).setBounds(268, 50, 54, 15);
        Contentpanel.add(this.labelnumber);
        (this.comboBoxCom = new JComboBox()).addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent e) {
                if (e.getStateChange() == 1) {
                	  PbxDemo.this.com = e.getItem().toString();
                }
            }
        });
        this.comboBoxCom.setBounds(55, 47, 74, 21);
        this.comboBoxCom.addItem("COM1");
        this.comboBoxCom.addItem("COM2");
        this.comboBoxCom.addItem("COM3");
        this.comboBoxCom.addItem("COM4");
        this.comboBoxCom.addItem("COM5");
        this.comboBoxCom.addItem("COM6");
        this.comboBoxCom.addItem("COM7");
        this.comboBoxCom.addItem("COM8");
        this.comboBoxCom.addItem("COM9");
        Contentpanel.add(this.comboBoxCom);
        (this.textbaud = new JTextField("9600")).setBounds(192, 47, 66, 21);
        Contentpanel.add(this.textbaud);
        this.textbaud.setColumns(10);
        (this.comboBoxNum = new JComboBox()).addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent e) {
                if (e.getStateChange() == 1) {
                	 PbxDemo.this.numb = Integer.parseInt(e.getItem().toString());
                }
            }
        });
        this.comboBoxNum.addItem("8");
        this.comboBoxNum.addItem("7");
        this.comboBoxNum.addItem("6");
        this.comboBoxNum.addItem("5");
     
        this.comboBoxNum.setBounds(313, 47, 47, 21);
        Contentpanel.add(this.comboBoxNum);
        (this.lbljy = new JLabel("校验位：")).setBounds(370, 50, 54, 15);
        Contentpanel.add(this.lbljy);
        (this.comboBoxjy = new JComboBox()).addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent e) {
                if (e.getStateChange() == 1) {
                    final String name = e.getItem().toString();

                    if (name.equals("无校验"))
                    {
                      PbxDemo.this.jy = 0;
                    }
                    else if (name.equals("奇校验")) {
                      PbxDemo.this.jy = 1;
                    }
                    else if (name.equals("偶校验"))
                      PbxDemo.this.jy = 2;
                }
            }
        });
        this.comboBoxjy.addItem("无校验");
        this.comboBoxjy.addItem("奇校验");
        this.comboBoxjy.addItem("偶校验");
        this.comboBoxjy.setBounds(422, 47, 66, 21);
        Contentpanel.add(this.comboBoxjy);
        (this.lblstop = new JLabel("停止位：")).setBounds(498, 50, 54, 15);
        Contentpanel.add(this.lblstop);
        (this.comboBoxstop = new JComboBox()).addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent e) {
                if (e.getStateChange() == 1) {
                	PbxDemo.this.stop = Integer.parseInt(e.getItem().toString());
                }
            }
        });
        this.comboBoxstop.addItem("1");
        this.comboBoxstop.addItem("2");
        this.comboBoxstop.addItem("3");
        this.comboBoxstop.setBounds(547, 47, 47, 21);
        Contentpanel.add(this.comboBoxstop);
        (this.btnOpenCom = new JButton("打开串口")).setBounds(45, 75, 91, 23);
        Contentpanel.add(this.btnOpenCom);
        this.btnOpenCom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                PbxDemo.this.btnConnect.setEnabled(false);
                PbxDemo.this.btnDisconnect.setEnabled(false);
                if (com == null || "".equals(com)) {
                    JOptionPane.showMessageDialog(null, "没有搜索到有效串口！", "错误", 1);
                    return;
                }
                final String bpsStr = PbxDemo.this.textbaud.getText().trim();
                if (bpsStr == null || bpsStr.equals("")) {
                    JOptionPane.showMessageDialog(null, "波特率获取错误！", "错误", 1);
                    return;
                }
                PbxDemo.this.baud = Integer.parseInt(bpsStr);
                try {
                	PbxDemo.this.serialPort = PbxDemo.this.openPort(PbxDemo.this.com, PbxDemo.this.baud, PbxDemo.this.jy, PbxDemo.this.stop, PbxDemo.this.numb);
                    PbxDemo.this.writelog("串口号=" + PbxDemo.this.com + " 波特率=" + PbxDemo.this.baud + " 数据位=" + PbxDemo.this.numb + " 校验位=" + PbxDemo.this.jy + " 停止位=" + PbxDemo.this.stop);
                    PbxDemo.addListener(PbxDemo.this.serialPort, (SerialPortEventListener)new SerialListener());
                    PbxDemo.this.btnConnect.setEnabled(false);
                    PbxDemo.this.btnDisconnect.setEnabled(false);
                }
                catch (NoSuchPortException e2) {
                    JOptionPane.showMessageDialog(null, "没有该端口对应的串口设备");
                }
                catch (PortInUseException e3) {
                    JOptionPane.showMessageDialog(null, "端口已被占用，请先关闭现有该串口，再打开");
                }
                catch (UnsupportedCommOperationException e4) {
                    JOptionPane.showMessageDialog(null, "不支持的串口参数");
                }
            }
        });
        (this.textArea = new TextArea()).setBounds(13, 120, 752, 276);
        Contentpanel.add(this.textArea);
        (this.btnCloseCom = new JButton("关闭串口")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                PbxDemo.this.btnConnect.setEnabled(true);
                PbxDemo.this.btnDisconnect.setEnabled(true);
                PbxDemo.closePort(PbxDemo.this.serialPort);
                PbxDemo.this.writelog("串口已关闭 !");
            }
        });
        this.btnCloseCom.setBounds(146, 75, 100, 23);
        Contentpanel.add(this.btnCloseCom);
        (this.textAreaSendMsg = new TextArea()).setBounds(17, 414, 738, 112);
        Contentpanel.add(this.textAreaSendMsg);
        final JButton btnSendMsg = new JButton("发送");
        btnSendMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String msg = PbxDemo.this.textAreaSendMsg.getText().trim();
                PbxDemo.this.writelog("----->" + msg);
                if (PbxDemo.this.serialPort != null) {
                    PbxDemo.this.sendToPort(PbxDemo.this.serialPort, msg.getBytes());
                }
                if (PbxDemo.this.socket != null) {
                    PbxDemo.this.sendToSocket(msg.getBytes());
                }
                PbxDemo.this.textAreaSendMsg.setText("");
                LOGGER.info("----->" + msg);
            }
        });
        btnSendMsg.setBounds(656, 532, 93, 23);
        Contentpanel.add(btnSendMsg);
    }
    
    private void writelog(final String msg) {
        this.textArea.append(String.valueOf(msg) + "\r\n");
    }
    
    public Socket openSocket(final String severip, final int port) {
        Socket sock = null;
        try {
            sock = new Socket(severip, port);
            this.ins = sock.getInputStream();
            this.outs = sock.getOutputStream();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "服务端没有打开");
        }
        return sock;
    }
    
    public SerialPort openPort(final String portName, final int baudrate, final int jy, final int stop, final int numb) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException {
        SerialPort serialPort = null;
        final CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(this.com);
        final CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);
        System.out.println(commPort instanceof SerialPort);
        if (commPort instanceof SerialPort) {
            serialPort = (SerialPort)commPort;
            serialPort.setSerialPortParams(baudrate, numb, stop, jy);
            this.writelog("串口连接成功！");
        }
        return serialPort;
    }
    
    public static void closePort(SerialPort serialPort) {
        if (serialPort != null) {
            serialPort.close();
            serialPort = null;
        }
    }
    
    public void closeSocket() {
        try {
            if (this.ins != null) {
                this.ins.close();
            }
            if (this.outs != null) {
                this.outs.close();
            }
            if (this.socket != null) {
                this.socket.close();
            }
            this.moitor.set_stop();
            this.writelog("关闭TCP/IP成功");
        }
        catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "关闭TCP/IP失败");
        }
    }
    
    public void sendToPort(final SerialPort serialPort, final byte[] order) {
        OutputStream out = null;
        try {
            out = serialPort.getOutputStream();
            out.write(order);
            out.flush();
        }
        catch (IOException ex) {}
        finally {
            try {
                if (out != null) {
                    out.close();
                    out = null;
                }
            }
            catch (IOException ex2) {}
        }
        try {
            if (out != null) {
                out.close();
                out = null;
            }
        }
        catch (IOException ex3) {}
    }
    
    public byte[] readFromPort(final SerialPort serialPort) {
        InputStream in = null;
        byte[] bytes = null;
        final String string = "";
        try {
            in = serialPort.getInputStream();
            for (int bufflenth = in.available(); bufflenth != 0; bufflenth = in.available()) {
                bytes = new byte[bufflenth];
                in.read(bytes);
                
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "接收数据失败" + e);
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            }
            catch (IOException e2) {
                JOptionPane.showMessageDialog(null, "关闭流失败" + e2);
            }
            return bytes;
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            }
            catch (IOException e2) {
                JOptionPane.showMessageDialog(null, "关闭流失败" + e2);
            }
        }
        try {
            if (in != null) {
                in.close();
                in = null;
            }
        }
        catch (IOException e2) {
            JOptionPane.showMessageDialog(null, "关闭流失败" + e2);
        }
        return bytes;
    }
    
    public static void addListener(final SerialPort port, final SerialPortEventListener listener) {
        try {
            port.addEventListener(listener);
            port.notifyOnDataAvailable(true);
            port.notifyOnBreakInterrupt(true);
        }
        catch (TooManyListenersException e) {
            JOptionPane.showMessageDialog(null, "监听失败");
        }
    }
    
    public static void main(final String[] args) {
        final PbxDemo pbxDemo = new PbxDemo();
        pbxDemo.setDefaultCloseOperation(2);
        pbxDemo.setVisible(true);
    }
    
    public String readinfo() {
        String string = "";
        try {
        Block_2:
            while (true) {
                this.availableBytes = this.ins.available();
                while (this.availableBytes > 0) {
                    final int i = this.ins.read();
                    System.out.print(String.valueOf((char)i));
                    string = String.valueOf(string) + String.valueOf((char)i);
                    this.availableBytes = this.ins.available();
                    if (this.availableBytes == 0) {
                        break Block_2;
                    }
                }
            }
            final String newstr = string;
            string = "";
            this.writelog("<-----" + newstr);
            LOGGER.info("<-----" + newstr);
            return newstr;
        }
        catch (IOException e) {
        	LOGGER.error(e);
            return null;
        }
    }
    
    public void sendToSocket(final byte[] buff) {
        try {
            if (this.outs != null) {
                this.outs.write(buff);
                this.outs.flush();
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "TCP发送失败");
        }
    }
    
    
    
    class connectactivemoitor extends Thread
    {
        volatile boolean strflg;
        
        connectactivemoitor() {
            this.strflg = true;
        }
        
        @Override
        public void run() {
            while (this.strflg) {
                PbxDemo.this.readinfo();
            }
        }
        
        public void set_stop() {
            this.strflg = false;
        }
        
        public void set_start() {
            this.strflg = true;
        }
    }
    
    class SerialListener implements SerialPortEventListener
    {
        public void serialEvent(final SerialPortEvent serialPortEvent) {
            switch (serialPortEvent.getEventType()) {
                case 10: {
                    JOptionPane.showMessageDialog(null, "与串口设备通讯中断", "错误", 1);
                }
                case SerialPortEvent.DATA_AVAILABLE: {
                    byte[] data = null;
                    try {
                        if (PbxDemo.this.serialPort == null) {
                            JOptionPane.showMessageDialog(null, "串口对象为空！监听失败！", "错误", 1);
                        }
                        else {
                            data = PbxDemo.this.readFromPort(PbxDemo.this.serialPort);
                            if (data == null || data.length < 1) {
                                JOptionPane.showMessageDialog(null, "读取数据过程中未获取到有效数据！请检查设备或程序！", "错误", 1);
                                System.exit(0);
                            }
                            else {
                                final String dataOriginal = new String(data);
                                PbxDemo.this.writelog("<-----" + dataOriginal);
                                LOGGER.info("<-----" + dataOriginal);
                            }
                        }
                    }
                    catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e, "错误", 1);
                        System.exit(0);
                    }
                    break;
                }
            }
        }
    }
	
}
