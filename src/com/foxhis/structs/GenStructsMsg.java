package com.foxhis.structs;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * SPMSifRegisterMsg *m_RegMsg; BYTE *pbytesSend; BYTE *pbytesReceive;
 * 
 * m_RegMsg = new SPMSifRegisterMsg;
 * 
 * strcpy(m_RegMsg->szLicense, m_strLic); //Build the data
 * strcpy(m_RegMsg->szApplName, m_strAppl); m_RegMsg->nRet=0;
 * SetHeader(&m_RegMsg->hdr1, CMD_REGISTER);
 * 
 * pbytesSend = new BYTE[sizeof(SPMSifRegisterMsg)]; memcpy(pbytesSend,
 * m_RegMsg, sizeof(SPMSifRegisterMsg)); //copy m_RegMsg structure to
 * pbytesStore
 * 
 * pbytesReceive=TCPSend(pbytesSend, sizeof(SPMSifRegisterMsg)); //sends
 * pbytesStore and receives the response
 * 
 * m_RegMsg=(SPMSifRegisterMsg*)pbytesReceive; //copy response back to structure
 * m_iRegRes=m_RegMsg->nRet;
 * 
 * @author Administrator
 *
 */
public class GenStructsMsg {

	private String szLicense ;
	private String szApplName;

	private static final int  CMD_REGISTER	=	1;
	private static final int  CMD_UNREGISTER	=2;
	private static final int  CMD_ENCODERMT	   = 4;
	private static final int  CMD_RETURNKCD		=5;
	private static final String LE=String.valueOf((char)30);


	public GenStructsMsg(Builder builder) {
		// TODO Auto-generated constructor stub
		this.szLicense = builder.szLicense;
		this.szApplName = builder.szApplName;
	}

	public static class Builder
	{
		private String szLicense;
		private String szApplName;

		public Builder szLicense(String szlicense)
		{
			szLicense = szlicense;
			return this;
		}
		public Builder szApplName(String szApplName)
		{
			this.szApplName = szApplName;
			return this;
		}
		public GenStructsMsg build()
		{
			return new GenStructsMsg(this);
		}
	}

	private SPMSifRegisterMsg getSPMSifRegisterMsg() {

		SPMSifRegisterMsg sifregmsg = new SPMSifRegisterMsg();
		sifregmsg.setHdr1(getSPMSifHdr(CMD_REGISTER));
		sifregmsg.setSzLicense(szLicense);
		sifregmsg.setSzApplName(szApplName);
		sifregmsg.setNRet(0);
		return sifregmsg;
	}
	
	byte[] getRegMsgToSend() throws IOException
	{
		int capacity = (int)SPMSifRegisterMsg.getSizeOf();
		ByteBuffer buffer = ByteBuffer.allocate(capacity);
		SPMSifRegisterMsg sifRegisterMsg = getSPMSifRegisterMsg();
		sifRegisterMsg.write(buffer);
		buffer.flip();
		byte[] pbytesSend =new byte[buffer.limit()];
		buffer.get(pbytesSend);
		return pbytesSend;
	}
	
	
	private SPMSifEncodeKcdRmtMsg getSPMSifEncodeKcdRmtMsg()
	{
		SPMSifEncodeKcdRmtMsg sptekr = new SPMSifEncodeKcdRmtMsg();
		sptekr.setHdr1(getSPMSifHdr(CMD_ENCODERMT));
		sptekr.setFf("I");
		sptekr.setDta(getDta());
		sptekr.setDd("02");
		sptekr.setSs("99");
		sptekr.setDebug(0);
		sptekr.setSzOpId("7289");
		sptekr.setSzOpFirst("Jason");
		sptekr.setSzOpLast("Phillips");
		return sptekr;
	}
	
   byte[] getWriteCardMsgToSend() throws IOException
   {
	    int capacity = (int)SPMSifEncodeKcdRmtMsg.getSizeOf();
		ByteBuffer buffer = ByteBuffer.allocate(capacity);
		SPMSifEncodeKcdRmtMsg sptekr = getSPMSifEncodeKcdRmtMsg();
		sptekr.write(buffer);
		buffer.flip();
		byte[] pbytesSend =new byte[buffer.limit()];
		buffer.get(pbytesSend);
		return pbytesSend;
   }
   
   String getDta()
   {
	   StringBuilder sBuilder = new StringBuilder();
	   sBuilder.append(LE);
	   sBuilder.append("R");
	   sBuilder.append("9228");
	   sBuilder.append(LE);
	   sBuilder.append("T");
	   sBuilder.append("GUEST");
	   sBuilder.append(LE);
	   sBuilder.append("F");
	   sBuilder.append("Ted");
	   sBuilder.append(LE);
	   sBuilder.append("N");
	   sBuilder.append("Jones");
	   sBuilder.append(LE);
	   sBuilder.append("U");
	   sBuilder.append("GUEST");
	   sBuilder.append(LE);
	   sBuilder.append("D");
	   sBuilder.append("201901021527");
	   sBuilder.append(LE);
	   sBuilder.append("O");
	   sBuilder.append("201901041210");
	   System.out.println(sBuilder.toString());
	   return sBuilder.toString();
   }
	

	/**
	 * hdr1->ui32Synch1=0x55555555; 
	 * hdr1->ui32Synch2=0xaaaaaaaa;
	 * hdr1->ui16Version=1; 
	 * hdr1->ui32Cmd=Cmd;
	 * hdr1->ui32BodySize=GetSize(Cmd)-sizeof(SPMSifHdr);
	 */
	private SPMSifHdr getSPMSifHdr(int cmd) {
		SPMSifHdr sifHdr = new SPMSifHdr();
		sifHdr.setUi32Synch1(0x55555555);
		sifHdr.setUi32Synch2(0xaaaaaaaa);
		sifHdr.setUi16Version((byte)1);
		sifHdr.setUi32Cmd((short)cmd);
		sifHdr.setUi32BodySize((getSize(cmd)-SPMSifHdr.getSizeOf()));
		return sifHdr;
	}
	
	private long getSize(int cmd)
	{
		switch(cmd)
		{
		case CMD_REGISTER:
			return SPMSifRegisterMsg.getSizeOf();
		case CMD_UNREGISTER:
			return SPMSifUnregisterMsg.getSizeOf();
		case CMD_ENCODERMT:
			return SPMSifEncodeKcdRmtMsg.getSizeOf();
		case CMD_RETURNKCD:
			return SPMSifReturnKcdLclMsg.getSizeOf();
		default:
			return SPMSifHdr.getSizeOf();
		}
	}


}
