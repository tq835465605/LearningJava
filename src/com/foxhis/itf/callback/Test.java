package com.foxhis.itf.callback;

import com.foxhis.itf.callback.CallQingting.PDragonflyCallBack;
import com.sun.jna.Pointer;

public class Test {
	
	public static void main(String[] args) {
		
		String pData = "{ \"target \": \"QT \",\"content \": { \"bizNo \": \"123423 \", \"totalAmount \": \"0.01 \", \"from \": \"com.alipay.iot.iohub.demo \", \"method \": \"start \"}}";
		int iRet1 = CallQingting.INSTANCE.Send2com(CallQingting.ETinyCmdType.TINY_APP, pData);
		CallQingting.INSTANCE.SetDragonflyCallback(new PDragonflyCallBack() {

			@Override
			public void apply(int iCmdType, Pointer pResult) {
				// TODO Auto-generated method stub
				System.out.println(pResult.getString(0L));
			}
			
		});
		int iRet3 = CallQingting.INSTANCE.AsyncSend2Com(CallQingting.ETinyCmdType.TINY_APP, pData);
		
		
	}

}
