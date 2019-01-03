package com.foxhis.structs;

/**
*/
public class SPMSifUnregisterMsg  {
	public SPMSifUnregisterMsg() {
	}
	
	public SPMSifUnregisterMsg(SPMSifUnregisterMsg source) {
		if(source == null) {
			throw new NullPointerException("source must be non null");
		}
		if(source.hdr1 != null) {
			this.hdr1 = new com.foxhis.structs.SPMSifHdr(source.hdr1);
		}
		this.nRet = source.nRet;
	}
	public SPMSifUnregisterMsg clone() {
		return new SPMSifUnregisterMsg(this);
	}
	
	public static SPMSifUnregisterMsg read(java.nio.ByteBuffer buf) throws java.io.IOException {
		return read(buf, false);
	}
	
	public static SPMSifUnregisterMsg read(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		if(buf.remaining() == 0) {
			// avoid empty object construction for partial reads
			throw new java.nio.BufferUnderflowException();
		}
		
		SPMSifUnregisterMsg obj = new SPMSifUnregisterMsg();
		
		try {
		
		obj.setHdr1(readHdr1(buf, partialRead));
		
		obj.setNRet(readNRet(buf, partialRead));
		} catch(java.nio.BufferUnderflowException e) {
			if(!partialRead) {
				throw e;
			}
		}
		
		return obj;
	}
	public void write(java.nio.ByteBuffer buf) throws java.io.IOException {
		writeHdr1(buf);
		writeNRet(buf);
		
	}
	
	/**
	*/
	public com.foxhis.structs.SPMSifHdr getHdr1() {
		return this.hdr1;
	}
	/**
	*/
	public long getNRet() {
		return this.nRet;
	}
	/**
	*/
	public void setHdr1(com.foxhis.structs.SPMSifHdr hdr1) {
		this.hdr1 = hdr1;
	}
	/**
	*/
	public void setNRet(long nRet) {
		this.nRet = nRet;
	}
	
	public static long getSizeOf() {
		return 22;
	}
	
	public String toString() {
		StringBuilder buf = new StringBuilder("com.foxhis.structs.SPMSifUnregisterMsg[");
		buf.append("hdr1=" + getHdr1());buf.append(", ");
		buf.append("nRet=" + getNRet());
		buf.append("]");
		return buf.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.hdr1 == null) ? 0 : this.hdr1.hashCode());
		result = prime * result + (int)this.nRet;
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
		SPMSifUnregisterMsg other = (SPMSifUnregisterMsg) obj;
		
		if (this.hdr1 == null) {
			if (other.hdr1 != null)
				return false;
		} else if (!this.hdr1.equals(other.hdr1))
			return false;
		if (this.nRet != other.nRet)
			return false;
		return true;
	}
	
	private static com.foxhis.structs.SPMSifHdr readHdr1(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		com.foxhis.structs.SPMSifHdr value = com.foxhis.structs.SPMSifHdr.read(buf, partialRead);
		return value;
	}
	private static long readNRet(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		long value = buf.getInt();
		return value;
	}
	private void writeHdr1(java.nio.ByteBuffer buf) throws java.io.IOException {
		if(getHdr1() != null) {
			
			getHdr1().write(buf);
			
		}
	}
	private void writeNRet(java.nio.ByteBuffer buf) throws java.io.IOException {
		buf.putInt((int)getNRet());
	}
	
	/**
	*/
	private com.foxhis.structs.SPMSifHdr hdr1;
	/**
	*/
	private long nRet;
}
