package com.foxhis.structs;

/**
*/
public class SPMSifHdr  {
	public SPMSifHdr() {
	}
	
	public SPMSifHdr(SPMSifHdr source) {
		if(source == null) {
			throw new NullPointerException("source must be non null");
		}
		this.ui32Synch1 = source.ui32Synch1;
		this.ui32Synch2 = source.ui32Synch2;
		this.ui16Version = source.ui16Version;
		this.ui32Cmd = source.ui32Cmd;
		this.ui32BodySize = source.ui32BodySize;
	}
	public SPMSifHdr clone() {
		return new SPMSifHdr(this);
	}
	
	public static SPMSifHdr read(java.nio.ByteBuffer buf) throws java.io.IOException {
		return read(buf, false);
	}
	
	public static SPMSifHdr read(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		if(buf.remaining() == 0) {
			// avoid empty object construction for partial reads
			throw new java.nio.BufferUnderflowException();
		}
		
		SPMSifHdr obj = new SPMSifHdr();
		
		try {
		
		obj.setUi32Synch1(readUi32Synch1(buf, partialRead));
		
		obj.setUi32Synch2(readUi32Synch2(buf, partialRead));
		
		obj.setUi16Version(readUi16Version(buf, partialRead));
		
		obj.setUi32Cmd(readUi32Cmd(buf, partialRead));
		
		obj.setUi32BodySize(readUi32BodySize(buf, partialRead));
		} catch(java.nio.BufferUnderflowException e) {
			if(!partialRead) {
				throw e;
			}
		}
		
		return obj;
	}
	public void write(java.nio.ByteBuffer buf) throws java.io.IOException {
		writeUi32Synch1(buf);
		writeUi32Synch2(buf);
		writeUi16Version(buf);
		writeUi32Cmd(buf);
		writeUi32BodySize(buf);
		
	}
	
	/**
	*/
	public long getUi32Synch1() {
		return this.ui32Synch1;
	}
	/**
	* Message synch1 = 0x55555555.
	*/
	public long getUi32Synch2() {
		return this.ui32Synch2;
	}
	/**
	* Message synch1 = 0xaaaaaaaa.
	*/
	public long getUi16Version() {
		return this.ui16Version;
	}
	/**
	* Header format version = 1.
	*/
	public long getUi32Cmd() {
		return this.ui32Cmd;
	}
	/**
	* field Command
	*/
	public long getUi32BodySize() {
		return this.ui32BodySize;
	}
	/**
	* Message synch1 = 0x55555555.
	*/
	public void setUi32Synch1(long ui32Synch1) {
		this.ui32Synch1 = ui32Synch1;
	}
	/**
	* Message synch1 = 0xaaaaaaaa.
	*/
	public void setUi32Synch2(long ui32Synch2) {
		this.ui32Synch2 = ui32Synch2;
	}
	/**
	* Header format version = 1.
	*/
	public void setUi16Version(long ui16Version) {
		this.ui16Version = ui16Version;
	}
	/**
	* field Command
	*/
	public void setUi32Cmd(long ui32Cmd) {
		this.ui32Cmd = ui32Cmd;
	}
	
	public void setUi32BodySize(long ui32BodySize) {
		this.ui32BodySize = ui32BodySize;
	}
	
	public static long getSizeOf() {
		return 18;
	}
	
	public String toString() {
		StringBuilder buf = new StringBuilder("com.foxhis.structs.SPMSifHdr[");
		buf.append("ui32Synch1=" + getUi32Synch1());buf.append(", ");
		buf.append("ui32Synch2=" + getUi32Synch2());buf.append(", ");
		buf.append("ui16Version=" + getUi16Version());buf.append(", ");
		buf.append("ui32Cmd=" + getUi32Cmd());buf.append(", ");
		buf.append("ui32BodySize=" + getUi32BodySize());
		buf.append("]");
		return buf.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int)this.ui32Synch1;
		result = prime * result + (int)this.ui32Synch2;
		result = prime * result + (int)this.ui16Version;
		result = prime * result + (int)this.ui32Cmd;
		result = prime * result + (int)this.ui32BodySize;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SPMSifHdr other = (SPMSifHdr) obj;
		
		if (this.ui32Synch1 != other.ui32Synch1)
			return false;
		if (this.ui32Synch2 != other.ui32Synch2)
			return false;
		if (this.ui16Version != other.ui16Version)
			return false;
		if (this.ui32Cmd != other.ui32Cmd)
			return false;
		if (this.ui32BodySize != other.ui32BodySize)
			return false;
		return true;
	}
	
	private static long readUi32Synch1(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		long value = buf.getInt() & 0xFFFFFFFFL;
		return value;
	}
	private static long readUi32Synch2(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		long value = buf.getInt() & 0xFFFFFFFFL;
		return value;
	}
	private static long readUi16Version(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		long value = buf.get() & 0xFFL;
		return value;
	}
	private static long readUi32Cmd(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		long value = buf.getShort() & 0xFFFFL;
		return value;
	}
	private static long readUi32BodySize(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		long value = buf.getInt() & 0xFFFFFFFFL;
		return value;
	}
	private void writeUi32Synch1(java.nio.ByteBuffer buf) throws java.io.IOException {
		buf.putInt((int)getUi32Synch1());
	}
	private void writeUi32Synch2(java.nio.ByteBuffer buf) throws java.io.IOException {
		
		buf.putInt((int)getUi32Synch2());
	}
	private void writeUi16Version(java.nio.ByteBuffer buf) throws java.io.IOException {
		buf.put((byte)getUi16Version());
	}
	private void writeUi32Cmd(java.nio.ByteBuffer buf) throws java.io.IOException {
		buf.putShort((short)getUi32Cmd());
	}
	private void writeUi32BodySize(java.nio.ByteBuffer buf) throws java.io.IOException {
		buf.putInt((int)getUi32BodySize());
		long pad = getSizeOf()-buf.position();
		if(pad > 0) {
			for(int i = 0; i < pad; ++i) {
				buf.put((byte)0);	
			}
		}
	}
	
	/**
	* Message synch1 = 0x55555555.
	*/
	private long ui32Synch1;
	/**
	* Message synch1 = 0xaaaaaaaa.
	*/
	private long ui32Synch2;

	private long ui16Version;
	/**
	* Header format version = 1.
	*/
	private long ui32Cmd;
	/**
	* field Command
	*/
	private long ui32BodySize;
}
