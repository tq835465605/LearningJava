package com.foxhis.trustface  ;

import com4j.*;

/**
 * Defines methods to create COM objects
 */
public abstract class ClassFactory {
  private ClassFactory() {} // instanciation is not allowed

//{2434023D-2325-4BBB-840E-86124EA4B27F}
  public static com.foxhis.trustface._DTrustFaceCtrl createTrustFaceCtrl() {//{C8D0CB29-42BE-4237-9040-A89F0809525
    return COM4J.createInstance( com.foxhis.trustface._DTrustFaceCtrl.class, "{2434023D-2325-4BBB-840E-86124EA4B27F}" );
  }
}
