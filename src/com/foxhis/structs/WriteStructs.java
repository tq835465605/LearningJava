package com.foxhis.structs;

import java.io.IOException;
import java.util.Arrays;

public class WriteStructs {

	
	public static String addZeroForNum(String str, int strLength) {

		int strLen = str.length();
		StringBuffer sb = null;
		while (strLen < strLength) {
			sb = new StringBuffer();
			sb.append("0").append(str);// 左补0
			str = sb.toString();
			strLen = str.length();
		}
		return str;
	}
	
	public static void main(String[] args) throws IOException {
		
//		ByteBuffer buffer = ByteBuffer.allocate(20);
//	    buffer.putInt(2);
//	    buffer.putInt(4);
//	    buffer.putInt(6);
//		System.out.println("写remain="+buffer.remaining());//写的时候remaining为剩下的
//		System.out.println("写position="+buffer.position());//此时位置
//		System.out.println("写limit="+buffer.limit());//此时位置
//        System.out.println(buffer.toString());
//		buffer.flip();
//		System.out.println("读remain="+buffer.remaining());//读则是，总共有多少可读
//		System.out.println("读position="+buffer.position());//此时位置
//		System.out.println("读limit="+buffer.limit());//此时位置
//		byte[] dst = new byte[buffer.limit()];
//		System.out.println(buffer.get(dst));
//		System.out.println("position="+buffer.position());//此时位置
//		System.out.println(Arrays.toString(dst));
//		buffer.clear();
//	   	//buffer.reset();
//	   	System.out.println("重置remain="+buffer.remaining());//读则是，总共有多少可读
//		System.out.println("重置position="+buffer.position());//此时位置
//		System.out.println("重置limit="+buffer.limit());//此时位置
//		byte[] dst1 = new byte[buffer.limit()];
//		System.out.println(buffer.get(dst1));
//		System.out.println(Arrays.toString(dst1));
		
		byte[] buf=new GenStructsMsg.Builder().szApplName("Test_Program").szLicense("42886781").build().getWriteCardMsgToSend();
		System.out.println(Arrays.toString(buf));
		for(int i=0;i<buf.length;i++)
		{
			System.out.print(addZeroForNum(Long.toString(buf[i]&0xFF, 16),2));
			System.out.print(" ");
		}
		System.out.println();
//		System.out.println(new String(buf,"UTF-8"));
//		System.out.println(buf.length);
//		System.out.println(0x1E);
		
//		
//		String string = "0xaaaaaaaa";
//		byte[] cs = string.getBytes();
//		long long1  =Long.decode(string);
//		System.out.println(long1);
//		ByteBuffer buffer = ByteBuffer.allocate(8);
//		buffer.putLong(long1);
//		buffer.flip();
//		long long2= buffer.getLong();
//		System.out.print(addZeroForNum(Long.toString(long2, 16),2));
		
//		System.out.println(cs.length);
//		System.out.println(Arrays.toString(cs));
//		ByteBuffer buffer = ByteBuffer.allocate(cs.length);
//		for(byte c:cs)
//		{
//			buffer.put((byte)c);
//			System.out.println(buffer.position());
//		}
//		
//		buffer.flip();
//		byte[] dst = new byte[buffer.limit()];
//		buffer.get(dst);
//		for(int i=0;i<dst.length;i++)
//		{
//			System.out.print(addZeroForNum(Long.toString(dst[i], 16),2));
//			System.out.print(" ");
//		}
	}
}
		
