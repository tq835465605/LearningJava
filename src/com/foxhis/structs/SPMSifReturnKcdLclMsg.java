package com.foxhis.structs;

/**
*/
public class SPMSifReturnKcdLclMsg  {
	public SPMSifReturnKcdLclMsg() {
	}
	
	public SPMSifReturnKcdLclMsg(SPMSifReturnKcdLclMsg source) {
		if(source == null) {
			throw new NullPointerException("source must be non null");
		}
		if(source.hdr1 != null) {
			this.hdr1 = new com.foxhis.structs.SPMSifHdr(source.hdr1);
		}
		this.ff = source.ff;
		this.Dta = source.Dta;
		this.Debug = source.Debug;
		this.szOpId = source.szOpId;
		this.szOpFirst = source.szOpFirst;
		this.szOpLast = source.szOpLast;
	}
	public SPMSifReturnKcdLclMsg clone() {
		return new SPMSifReturnKcdLclMsg(this);
	}
	
	public static SPMSifReturnKcdLclMsg read(java.nio.ByteBuffer buf) throws java.io.IOException {
		return read(buf, false);
	}
	
	public static SPMSifReturnKcdLclMsg read(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		if(buf.remaining() == 0) {
			// avoid empty object construction for partial reads
			throw new java.nio.BufferUnderflowException();
		}
		
		SPMSifReturnKcdLclMsg obj = new SPMSifReturnKcdLclMsg();
		
		try {
		
		obj.setHdr1(readHdr1(buf, partialRead));
		
		obj.setFf(readFf(buf, partialRead));
		
		obj.setDta(readDta(buf, partialRead));
		
		obj.setDebug(readDebug(buf, partialRead));
		
		obj.setSzOpId(readSzOpId(buf, partialRead));
		
		obj.setSzOpFirst(readSzOpFirst(buf, partialRead));
		
		obj.setSzOpLast(readSzOpLast(buf, partialRead));
		} catch(java.nio.BufferUnderflowException e) {
			if(!partialRead) {
				throw e;
			}
		}
		
		return obj;
	}
	public void write(java.nio.ByteBuffer buf) throws java.io.IOException {
		writeHdr1(buf);
		writeFf(buf);
		writeDta(buf);
		writeDebug(buf);
		writeSzOpId(buf);
		writeSzOpFirst(buf);
		writeSzOpLast(buf);
		
	}
	
	/**
	*/
	public com.foxhis.structs.SPMSifHdr getHdr1() {
		return this.hdr1;
	}
	/**
	*/
	public String getFf() {
		return this.ff;
	}
	/**
	*/
	public String getDta() {
		return this.Dta;
	}
	/**
	*/
	public long getDebug() {
		return this.Debug;
	}
	/**
	*/
	public String getSzOpId() {
		return this.szOpId;
	}
	/**
	*/
	public String getSzOpFirst() {
		return this.szOpFirst;
	}
	/**
	*/
	public String getSzOpLast() {
		return this.szOpLast;
	}
	/**
	*/
	public void setHdr1(com.foxhis.structs.SPMSifHdr hdr1) {
		this.hdr1 = hdr1;
	}
	/**
	*/
	public void setFf(String ff) {
		this.ff = ff;
	}
	/**
	*/
	public void setDta(String Dta) {
		this.Dta = Dta;
	}
	/**
	*/
	public void setDebug(long Debug) {
		this.Debug = Debug;
	}
	/**
	*/
	public void setSzOpId(String szOpId) {
		this.szOpId = szOpId;
	}
	/**
	*/
	public void setSzOpFirst(String szOpFirst) {
		this.szOpFirst = szOpFirst;
	}
	/**
	*/
	public void setSzOpLast(String szOpLast) {
		this.szOpLast = szOpLast;
	}
	
	public static long getSizeOf() {
		return 577;
	}
	
	public String toString() {
		StringBuilder buf = new StringBuilder("com.foxhis.structs.SPMSifReturnKcdLclMsg[");
		buf.append("hdr1=" + getHdr1());buf.append(", ");
		buf.append("ff=" + getFf());buf.append(", ");
		buf.append("Dta=" + getDta());buf.append(", ");
		buf.append("Debug=" + getDebug());buf.append(", ");
		buf.append("szOpId=" + getSzOpId());buf.append(", ");
		buf.append("szOpFirst=" + getSzOpFirst());buf.append(", ");
		buf.append("szOpLast=" + getSzOpLast());
		buf.append("]");
		return buf.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.hdr1 == null) ? 0 : this.hdr1.hashCode());
		result = prime * result + ((this.ff == null) ? 0 : this.ff.hashCode());
		result = prime * result + ((this.Dta == null) ? 0 : this.Dta.hashCode());
		result = prime * result + (int)this.Debug;
		result = prime * result + ((this.szOpId == null) ? 0 : this.szOpId.hashCode());
		result = prime * result + ((this.szOpFirst == null) ? 0 : this.szOpFirst.hashCode());
		result = prime * result + ((this.szOpLast == null) ? 0 : this.szOpLast.hashCode());
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
		SPMSifReturnKcdLclMsg other = (SPMSifReturnKcdLclMsg) obj;
		
		if (this.hdr1 == null) {
			if (other.hdr1 != null)
				return false;
		} else if (!this.hdr1.equals(other.hdr1))
			return false;
		if (this.ff == null) {
			if (other.ff != null)
				return false;
		} else if (!this.ff.equals(other.ff))
			return false;
		if (this.Dta == null) {
			if (other.Dta != null)
				return false;
		} else if (!this.Dta.equals(other.Dta))
			return false;
		if (this.Debug != other.Debug)
			return false;
		if (this.szOpId == null) {
			if (other.szOpId != null)
				return false;
		} else if (!this.szOpId.equals(other.szOpId))
			return false;
		if (this.szOpFirst == null) {
			if (other.szOpFirst != null)
				return false;
		} else if (!this.szOpFirst.equals(other.szOpFirst))
			return false;
		if (this.szOpLast == null) {
			if (other.szOpLast != null)
				return false;
		} else if (!this.szOpLast.equals(other.szOpLast))
			return false;
		return true;
	}
	
	private static com.foxhis.structs.SPMSifHdr readHdr1(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		com.foxhis.structs.SPMSifHdr value = com.foxhis.structs.SPMSifHdr.read(buf, partialRead);
		return value;
	}
	private static String readFf(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		try {
		java.io.ByteArrayOutputStream tmp = new java.io.ByteArrayOutputStream();
		int terminatingZeros = "\0".getBytes("UTF-8").length;
		int zerosRead = 0;
		while(zerosRead < terminatingZeros) {
			int b = buf.get();
			tmp.write(b);
			if(b == 0) {
				zerosRead++;
			} else {
				zerosRead = 0;
			}
		}
		return new String(tmp.toByteArray(), 0, tmp.size() - zerosRead, "UTF-8");
		} catch(java.io.UnsupportedEncodingException e) {
			throw new java.io.IOException(e);
		}
	}
	private static String readDta(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		try {
		byte[] tmp = new byte[512];
		buf.get(tmp);
		int terminatingZeros = "\0".getBytes("UTF-8").length;
		int zerosRead = 0;
		int i = 0;
		int len = 0;
		while(zerosRead < terminatingZeros) {
			if(i >= 512) {
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
	private static long readDebug(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		long value = buf.getInt();
		return value;
	}
	private static String readSzOpId(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		try {
		byte[] tmp = new byte[10];
		buf.get(tmp);
		int terminatingZeros = "\0".getBytes("UTF-8").length;
		int zerosRead = 0;
		int i = 0;
		int len = 0;
		while(zerosRead < terminatingZeros) {
			if(i >= 10) {
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
	private static String readSzOpFirst(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		try {
		byte[] tmp = new byte[16];
		buf.get(tmp);
		int terminatingZeros = "\0".getBytes("UTF-8").length;
		int zerosRead = 0;
		int i = 0;
		int len = 0;
		while(zerosRead < terminatingZeros) {
			if(i >= 16) {
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
	private static String readSzOpLast(java.nio.ByteBuffer buf, boolean partialRead) throws java.io.IOException {
		try {
		byte[] tmp = new byte[16];
		buf.get(tmp);
		int terminatingZeros = "\0".getBytes("UTF-8").length;
		int zerosRead = 0;
		int i = 0;
		int len = 0;
		while(zerosRead < terminatingZeros) {
			if(i >= 16) {
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
	private void writeHdr1(java.nio.ByteBuffer buf) throws java.io.IOException {
		if(getHdr1() != null) {
			
			getHdr1().write(buf);
			
		}
	}
	private void writeFf(java.nio.ByteBuffer buf) throws java.io.IOException {
		try {
			byte[] encoded = getFf().getBytes("UTF-8");
			buf.put(encoded);
			buf.put("\0".getBytes("UTF-8"));
		} catch(java.io.UnsupportedEncodingException e) {
			throw new java.io.IOException(e);
		}
	}
	private void writeDta(java.nio.ByteBuffer buf) throws java.io.IOException {
		try {
			byte[] encoded = getDta().getBytes("UTF-8");
			int len = Math.min(encoded.length, 512);
			int pad = 512 - len;
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
	private void writeDebug(java.nio.ByteBuffer buf) throws java.io.IOException {
		buf.putInt((int)getDebug());
	}
	private void writeSzOpId(java.nio.ByteBuffer buf) throws java.io.IOException {
		try {
			byte[] encoded = getSzOpId().getBytes("UTF-8");
			int len = Math.min(encoded.length, 10);
			int pad = 10 - len;
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
	private void writeSzOpFirst(java.nio.ByteBuffer buf) throws java.io.IOException {
		try {
			byte[] encoded = getSzOpFirst().getBytes("UTF-8");
			int len = Math.min(encoded.length, 16);
			int pad = 16 - len;
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
	private void writeSzOpLast(java.nio.ByteBuffer buf) throws java.io.IOException {
		try {
			byte[] encoded = getSzOpLast().getBytes("UTF-8");
			int len = Math.min(encoded.length, 16);
			int pad = 16 - len;
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
	
	/**
	*/
	private com.foxhis.structs.SPMSifHdr hdr1;
	/**
	*/
	private String ff;
	/**
	*/
	private String Dta;
	/**
	*/
	private long Debug;
	/**
	*/
	private String szOpId;
	/**
	*/
	private String szOpFirst;
	/**
	*/
	private String szOpLast;
}
