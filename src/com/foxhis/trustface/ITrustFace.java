package com.foxhis.trustface;


/**
 * 人脸识别的方法类库
 * @author Administrator
 *
 */
public interface ITrustFace {

	final String initControl="InitControl";
	final String setFaceTemplatePhoto="SetFaceTemplatePhoto";
	final String showFaceWindow="ShowFaceWindow";
	final String getFaceVerifyPackage="GetFaceVerifyPackage";
	final String getFaceImageData="GetFaceImageData";
	final String setResultMessage="GetResultMessage";
	final String recaptureFace="RecaptureFace";
	final String closeFaceWindow="CloseFaceWindow";
	final String setServerSetting="SetServerSetting";
	final String getMaunualAuditInfo="GetMaunualAuditInfo";
	final String getCtrlVersion="GetCtrlVersion";
	final String getFaceVerifyMode="GetFaceVerifyMode";

	final int onCaptureFaceImage = 0x6;
	final int onCaptureFaceState =0xA;
	final int onGetFaceResult =0xD;
	
	final String grogID = "TRUSTFACECTRL.TrustFaceCtrlCtrl.1";
	// Methods:
	void aboutBox();
	/**初始化控件 */
	short initControl() throws Exception;
	/**设置人像模板照片  */
	short setFaceTemplatePhoto(String strPhotoInfo) throws Exception;//设置人像模板照片 
	/**显示人像采集窗口   */
	short showFaceWindow() throws Exception;//显示人像采集窗口 
	/**获取人像比对数据包   */
	String getFaceVerifyPackage() throws Exception;//获取人像比对数据包
	/**获取采集人脸图像    */
	String getFaceImageData() throws Exception;//获取采集人脸图像 
	/**设置控件显示结果提示信息 */
	short setResultMessage(String strMessage) throws Exception;
	/**重新采集人像 */
	short recaptureFace() throws Exception;
	/**关闭人像采集窗口  */
	short closeFaceWindow() throws Exception;//关闭人像采集窗口 
	/** */
	short setServerSetting(String strSettingInfo) throws Exception;
	/** */
	String getMaunualAuditInfo();
	/**获取控件版本 */
	String getCtrlVersion();
	/** */
	short getFaceVerifyMode();
	/** */
	void setFaceVerifyMode(short newValue);
	/** */
	double getFaceVerifyThreshold();
	/** */
	void setFaceVerifyThreshold(double newValue);
	/** */
	short getFaceCtrlMode();
	/** */
	void setFaceCtrlMode(short newValue);

}
