package com.foxhis.trustface  ;

import com4j.*;

@IID("{00020400-0000-0000-C000-000000000046}")
public interface _DTrustFaceCtrl extends Com4jObject {
  // Methods:
  /**
   */

  @DISPID(-552)
  void aboutBox();


  /**
   */

  @DISPID(1)
  short initControl();


  /**
   * @param strPhotoInfo Mandatory java.lang.String parameter.
   */

  @DISPID(2)
  short setFaceTemplatePhoto(
    java.lang.String strPhotoInfo);


  /**
   */

  @DISPID(3)
  short showFaceWindow();


  /**
   */

  @DISPID(4)
  java.lang.String getFaceVerifyPackage();


  /**
   */

  @DISPID(5)
  java.lang.String getFaceImageData();


  /**
   * @param strMessage Mandatory java.lang.String parameter.
   */

  @DISPID(7)
  short setResultMessage(
    java.lang.String strMessage);


  /**
   */

  @DISPID(8)
  short recaptureFace();


  /**
   */

  @DISPID(9)
  short closeFaceWindow();


  /**
   * @param strSettingInfo Mandatory java.lang.String parameter.
   */

  @DISPID(14)
  short setServerSetting(
    java.lang.String strSettingInfo);


  /**
   */

  @DISPID(15)
  java.lang.String getMaunualAuditInfo();


  /**
   */

  @DISPID(16)
  java.lang.String getCtrlVersion();


  // Properties:
  /**
   * <p>
   * Getter method for the COM property "FaceVerifyMode"
   * </p>
   * @return The COM property FaceVerifyMode as a short
   */
  @DISPID(11)
  @PropGet
  short getFaceVerifyMode();

  /**
   * <p>
   * Setter method for the COM property "FaceVerifyMode"
   * </p>
   * @param newValue The new value for the COM property FaceVerifyMode as a short
   */
  @DISPID(11)
  @PropPut
  void setFaceVerifyMode(short newValue);

  /**
   * <p>
   * Getter method for the COM property "FaceVerifyThreshold"
   * </p>
   * @return The COM property FaceVerifyThreshold as a double
   */
  @DISPID(12)
  @PropGet
  double getFaceVerifyThreshold();

  /**
   * <p>
   * Setter method for the COM property "FaceVerifyThreshold"
   * </p>
   * @param newValue The new value for the COM property FaceVerifyThreshold as a double
   */
  @DISPID(12)
  @PropPut
  void setFaceVerifyThreshold(double newValue);

  /**
   * <p>
   * Getter method for the COM property "FaceCtrlMode"
   * </p>
   * @return The COM property FaceCtrlMode as a short
   */
  @DISPID(17)
  @PropGet
  short getFaceCtrlMode();

  /**
   * <p>
   * Setter method for the COM property "FaceCtrlMode"
   * </p>
   * @param newValue The new value for the COM property FaceCtrlMode as a short
   */
  @DISPID(17)
  @PropPut
  void setFaceCtrlMode(short newValue);

}
