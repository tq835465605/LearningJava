package com.foxhis.trustface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.ole.win32.IDispatch;
import org.eclipse.swt.internal.ole.win32.IUnknown;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.ole.win32.OleControlSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.ole.win32.OleListener;
import org.eclipse.swt.ole.win32.Variant;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TrustFaceCtrl implements ITrustFace{


	/**当前Ole窗体*/
	private OleFrame _frame;
	/**当前Ole控制体*/
	private OleControlSite _site;
	/**当前Ole文档加载器*/
	private OleAutomation _auto;
	
	private Shell shell;

    public OleAutomation get_auto() {
		return _auto;
	}

	public void set_auto(OleAutomation _auto) {
		this._auto = _auto;
	}

	public TrustFaceCtrl(String grogID)
    {
    	Display display = Display.getDefault();
        shell = new Shell(display, SWT.CLOSE | SWT.MIN);
         _frame = new OleFrame(shell, SWT.NONE);
         _site = new OleControlSite(_frame, SWT.NONE, grogID);
         _auto = new OleAutomation(_site);
         _site.doVerb(OLE.OLEIVERB_SHOW);
    }
    
	/**
	 * 
	 * @param eventID
	 * @param listener
	 */
    public void addEventListener(int eventID, OleListener listener){
    	_site.addEventListener(_auto,eventID, listener);
    	
    }
	
    public int getID(String name){
        try {
            int[] ids = _auto.getIDsOfNames(new String[]{name});
            if(ids.length>=0)
                return ids[0];
        } catch (RuntimeException e) {               
            e.printStackTrace();           
        }
        return -1;
    }
    
	/**
     * 反射调用Ole对象的方法
     * @param oleAutomation
     * @param method
     * @param values
     * @return
     */
    public Variant invokeMethod(OleAutomation oleAutomation,String method,Object... values)throws Exception{
        int[] ids = oleAutomation.getIDsOfNames(new String[]{method});
        if (ids != null && ids.length > 0){
            int iMethod = oleAutomation.getIDsOfNames(new String[]{method})[0];
            Variant[] variants = new Variant[values.length];
            for (int i = 0; i < values.length; i++) {
                Variant variant = createVariant(values[i]);
                variants[i] = variant;
            }
            Variant pVarResult = oleAutomation.invoke(iMethod, variants);
            //release object
            for (Variant variant : variants) {
                variant.dispose();
            }
            return pVarResult;
        }else{
            return null;
        }
    }
    /**
     * Convert Java Primary type or String to Variant
     *
     * @param value java type data
     * @return Variant
     */
    private static Variant createVariant(Object value) {
        Variant variant = null;

        if (value == null) {
            variant = new Variant();
        } else if (value instanceof Boolean) {
            variant = new Variant((Boolean) value);
        } else if (value instanceof Short) {
            variant = new Variant((Short) value);
        } else if (value instanceof Integer) {
            variant = new Variant((Integer) value);
        } else if (value instanceof Float) {
            variant = new Variant((Float) value);
        } else if (value instanceof String) {
            variant = new Variant(value.toString());
        } else if (value instanceof IDispatch) {
            variant = new Variant((IDispatch) value);
        } else if (value instanceof IUnknown) {
            variant = new Variant((IUnknown) value);
        } else if (value instanceof OleAutomation) {
            variant = new Variant((OleAutomation) value);
        }
        return variant;
    }

	@Override
	public void aboutBox() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public short initControl() throws Exception{
		// TODO Auto-generated method stub
		Variant v=invokeMethod(_auto,ITrustFace.initControl,new Object[] {});
		return v.getShort();
	}

	@Override
	public short setFaceTemplatePhoto(String strPhotoInfo) throws Exception {
		// TODO Auto-generated method stub
		Variant v=invokeMethod(_auto,ITrustFace.setFaceTemplatePhoto,new Object[] {strPhotoInfo});
		return v.getShort();
	}

	@Override
	public short showFaceWindow() throws Exception {
		// TODO Auto-generated method stub
		Variant v=invokeMethod(_auto,ITrustFace.showFaceWindow,new Object[] {});
		return v.getShort();
	}

	@Override
	public String getFaceVerifyPackage()throws Exception {
		// TODO Auto-generated method stub
		Variant v=invokeMethod(_auto,ITrustFace.getFaceVerifyPackage,new Object[] {});
		return v.getString();
	}

	@Override
	public String getFaceImageData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public short setResultMessage(String strMessage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public short recaptureFace() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public short closeFaceWindow() throws Exception {
		// TODO Auto-generated method stub
		Variant v=invokeMethod(_auto,ITrustFace.closeFaceWindow,new Object[] {});
		return v.getShort();
	}

	@Override
	public short setServerSetting(String strSettingInfo) throws Exception {
		// TODO Auto-generated method stub
		Variant v=invokeMethod(_auto,ITrustFace.setServerSetting,new Object[] {strSettingInfo});
		return v.getShort();
	}

	@Override
	public String getMaunualAuditInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCtrlVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public short getFaceVerifyMode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFaceVerifyMode(short newValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getFaceVerifyThreshold() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFaceVerifyThreshold(double newValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public short getFaceCtrlMode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFaceCtrlMode(short newValue) {
		// TODO Auto-generated method stub
		
	}
    
    
}
