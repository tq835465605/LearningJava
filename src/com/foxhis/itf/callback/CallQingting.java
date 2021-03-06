package com.foxhis.itf.callback;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
/**
 * JNA Wrapper for library <b>Qingting</b><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public interface CallQingting extends Library {
	public static final String JNA_LIBRARY_NAME = "DragonflyComm.dll";
	public static final NativeLibrary JNA_NATIVE_LIB = NativeLibrary.getInstance(CallQingting.JNA_LIBRARY_NAME);
	public static final CallQingting INSTANCE = (CallQingting)Native.loadLibrary(CallQingting.JNA_LIBRARY_NAME, CallQingting.class);
	/**
	 * <i>native declaration : line 2</i><br>
	 * enum values
	 */
	public static interface ETinyCmdType {
		/** <i>native declaration : line 3</i> */
		public static final int HANDSHAKE = 0;
		/** <i>native declaration : line 4</i> */
		public static final int BARCODE = 1;
		/** <i>native declaration : line 5</i> */
		public static final int PAY = 2;
		/** <i>native declaration : line 6</i> */
		public static final int FACE = 3;
		/** <i>native declaration : line 7</i> */
		public static final int SETTINGS = 4;
		/** <i>native declaration : line 8</i> */
		public static final int CANCEL = 5;
		/** <i>native declaration : line 9</i> */
		public static final int TINY_APP = 100;
		/**
		 * \u5f02\u6b65\u4e32\u53e3\u64cd\u4f5c\u65f6\u7684\u9519\u8bef\u6d88\u606f\u63d0\u793a<br>
		 * <i>native declaration : line 10</i>
		 */
		public static final int COMERR_MSG = 2019;
	};
	public interface PDragonflyCallBack extends Callback {
		void apply(int iCmdType, Pointer pResult);
	};
	/**
	 * < \u8fd4\u56de\u503c\uff1a\u8fd4\u56de0\u6210\u529f\uff0c\u8d1f\u6570\u8868\u793a\u5931\u8d25<br>
	 * Original signature : <code>int Send2com(int, const char*)</code><br>
	 * <i>native declaration : line 17</i><br>
	 * @deprecated use the safer methods {@link #Send2com(int, java.lang.String)} and {@link #Send2com(int, com.sun.jna.Pointer)} instead
	 */
	@Deprecated 
	int Send2com(int tinyCmdType, Pointer pData);
	/**
	 * < \u8fd4\u56de\u503c\uff1a\u8fd4\u56de0\u6210\u529f\uff0c\u8d1f\u6570\u8868\u793a\u5931\u8d25<br>
	 * Original signature : <code>int Send2com(int, const char*)</code><br>
	 * <i>native declaration : line 17</i>
	 */
	int Send2com(int tinyCmdType, String pData);
	/**
	 * < \u5f02\u6b65\u63a5\u53d7\u6570\u636e\u56de\u8c03<br>
	 * Original signature : <code>void SetDragonflyCallback(PDragonflyCallBack)</code><br>
	 * <i>native declaration : line 20</i>
	 */
	void SetDragonflyCallback(CallQingting.PDragonflyCallBack pCbDragonfly);
	/**
	 * Original signature : <code>int AsyncSend2Com(int, const char*)</code><br>
	 * <i>native declaration : line 22</i><br>
	 * @deprecated use the safer methods {@link #AsyncSend2Com(int, java.lang.String)} and {@link #AsyncSend2Com(int, com.sun.jna.Pointer)} instead
	 */
	@Deprecated 
	int AsyncSend2Com(int tinyCmdType, Pointer pData);
	/**
	 * Original signature : <code>int AsyncSend2Com(int, const char*)</code><br>
	 * <i>native declaration : line 22</i>
	 */
	int AsyncSend2Com(int tinyCmdType, String pData);
}
