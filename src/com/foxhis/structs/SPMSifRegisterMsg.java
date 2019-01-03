package com.foxhis.structs;

/**
*/
public class SPMSifRegisterMsg  {
	public SPMSifRegisterMsg() {
	}
	
	public SPMSifRegisterMsg(SPMSifRegisterMsg source) {
		if(source == null) {
			throw new NullPointerException("source must be non null");
		}
		if(source.hdr1 != null) {
			this.hdr1 = new com.foxhis.structs.SPMSifHdr(source.hdr1);
		}
		this.szLicense = source.szLicense;
		this.szApplName = source.szApplName;
		this.nRet = source.nRet;
	}
	public SPMSifRegisterMsg clone() {
		return new SPMSifRegisterMsg(this);
	}
	
	public static SPMSifRegisterMsg read(java.nio.ByteBuffer buf) throws java.io.IOException {
		return read(buf, false);
	}
	
	public static SPMSifRegisterMsg read(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		if(buf.remaining() == 0) {
			// avoid empty object construction for partial reads
			throw new java.nio.BufferUnderflowException();
		}
		
		SPMSifRegisterMsg obj = new SPMSifRegisterMsg();
		
		try {
		
		obj.setHdr1(readHdr1(buf, partialRead));
		
		obj.setSzLicense(readSzLicense(buf, partialRead));
		
		obj.setSzApplName(readSzApplName(buf, partialRead));
		
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
		writeSzLicense(buf);
		writeSzApplName(buf);
		writeNRet(buf);
		
	}
	
	/**
	*/
	public com.foxhis.structs.SPMSifHdr getHdr1() {
		return this.hdr1;
	}
	/**
	* 18
	*/
	public String getSzLicense() {
		return this.szLicense;
	}
	/**
	* 20
	*/
	public String getSzApplName() {
		return this.szApplName;
	}
	/**
	* 20
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
	* 18
	*/
	public void setSzLicense(String szLicense) {
		this.szLicense = szLicense;
	}
	/**
	* 20
	*/
	public void setSzApplName(String szApplName) {
		this.szApplName = szApplName;
	}
	/**
	* 20
	*/
	public void setNRet(long nRet) {
		this.nRet = nRet;
	}
	
	public static long getSizeOf() {
		return 62;
	}
	
	public String toString() {
		StringBuilder buf = new StringBuilder("com.foxhis.structs.SPMSifRegisterMsg[");
		buf.append("hdr1=" + getHdr1());buf.append(", ");
		buf.append("szLicense=" + getSzLicense());buf.append(", ");
		buf.append("szApplName=" + getSzApplName());buf.append(", ");
		buf.append("nRet=" + getNRet());
		buf.append("]");
		return buf.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.hdr1 == null) ? 0 : this.hdr1.hashCode());
		result = prime * result + ((this.szLicense == null) ? 0 : this.szLicense.hashCode());
		result = prime * result + ((this.szApplName == null) ? 0 : this.szApplName.hashCode());
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
		SPMSifRegisterMsg other = (SPMSifRegisterMsg) obj;
		
		if (this.hdr1 == null) {
			if (other.hdr1 != null)
				return false;
		} else if (!this.hdr1.equals(other.hdr1))
			return false;
		if (this.szLicense == null) {
			if (other.szLicense != null)
				return false;
		} else if (!this.szLicense.equals(other.szLicense))
			return false;
		if (this.szApplName == null) {
			if (other.szApplName != null)
				return false;
		} else if (!this.szApplName.equals(other.szApplName))
			return false;
		if (this.nRet != other.nRet)
			return false;
		return true;
	}
	
	private static com.foxhis.structs.SPMSifHdr readHdr1(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		com.foxhis.structs.SPMSifHdr value = com.foxhis.structs.SPMSifHdr.read(buf, partialRead);
		return value;
	}
	private static String readSzLicense(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		try {
		byte[] tmp = new byte[20];
		buf.get(tmp);
		int terminatingZeros = "\0".getBytes("UTF-8").length;
		int zerosRead = 0;
		int i = 0;
		int len = 0;
		while(zerosRead < terminatingZeros) {
			if(i >= 20) {
				len = i;
				break;
			}
			if(tmp[i++] == 0) {
				zerosRead++;
			} else {
				zerosRead = 0;
				len = i;
			}
		}
		return new String(tmp, 0, len, "UTF-8");
		} catch(java.io.UnsupportedEncodingException e) {
			throw new java.io.IOException(e);
		}
	}
	private static String readSzApplName(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		try {
		byte[] tmp = new byte[20];
		buf.get(tmp);
		int terminatingZeros = "\0".getBytes("UTF-8").length;
		int zerosRead = 0;
		int i = 0;
		int len = 0;
		while(zerosRead < terminatingZeros) {
			if(i >= 20) {
				len = i;
				break;
			}
			if(tmp[i++] == 0) {
				zerosRead++;
			} else {
				zerosRead = 0;
				len = i;
			}
		}
		return new String(tmp, 0, len, "UTF-8");
		} catch(java.io.UnsupportedEncodingException e) {
			throw new java.io.IOException(e);
		}
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
	private void writeSzLicense(java.nio.ByteBuffer buf) throws java.io.IOException {
		try {
			byte[] encoded = getSzLicense().getBytes("UTF-8");
			int len = Math.min(encoded.length, 20);
			int pad = 20 - len;
			buf.put(encoded, 0, len);
			if(pad > 0) {
				for(int i = 0; i < pad; ++i) {
					buf.put((byte)0);	
				}
			}
		} catch(java.io.UnsupportedEncodingException e) {
			throw new java.io.IOException(e);
		}
	}
	private void writeSzApplName(java.nio.ByteBuffer buf) throws java.io.IOException {
		try {
			byte[] encoded = getSzApplName().getBytes("UTF-8");
			int len = Math.min(encoded.length, 20);
			int pad = 20 - len;
			buf.put(encoded, 0, len);
			if(pad > 0) {
				for(int i = 0; i < pad; ++i) {
					buf.put((byte)0);	
				}
			}
		} catch(java.io.UnsupportedEncodingException e) {
			throw new java.io.IOException(e);
		}
	}
	private void writeNRet(java.nio.ByteBuffer buf) throws java.io.IOException {
		buf.putInt((int)getNRet());
	}
	
	/**
	*/
	private com.foxhis.structs.SPMSifHdr hdr1;
	/**
	* 18
	*/
	private String szLicense;
	/**
	* 20
	*/
	private String szApplName;
	/**
	* 20
	*/
	private long nRet;
}
