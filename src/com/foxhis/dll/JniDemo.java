package com.foxhis.dll;

public class JniDemo {
	
	static {
		System.loadLibrary("MAINDLL.DLL");
	}
	public native int NewKey(String room, String gate, String stime, String guestname,String guestid, 
			String lift, int overflag, int Breakfast, long[] cardno, String track1, String track2);
	public native int ReadCard(byte[] room, byte[] gate, byte[] stime, byte[] guestname,byte[] guestid, 
			String lift, byte[] track1, byte[] track2,long[] cardno, int[] st, int[] Breakfast);
	
	public static void main(String[] args) {
		JniDemo jnid = new JniDemo();
		int i = jnid.NewKey("1001", "99", "201905061538201905091200", null, null, "99", 1, 1, null, null, null);
		System.out.println(i);
		byte[] room = new byte[20];
		byte[] stime = new byte[20];
		byte[] gate = new byte[20];
		String lift = "00";
		byte[] Guestname = new byte[20];
		byte[] Guestid = new byte[20];
		byte[] track1 = new byte[20];
		byte[] track2 = new byte[20];
		long[] cardno = new long[8];
		int[] st = new int[4];
		int[] bkf = new int[4];
		i = jnid.ReadCard(room, gate, stime, Guestname, Guestid, lift, track1, track2, cardno, st, bkf);
		System.out.println(i);
	}

}
