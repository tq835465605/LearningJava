package com.foxhis.trustface;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class TrustFaceDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_DTrustFaceCtrl dTrustFaceCtrl = ClassFactory.createTrustFaceCtrl();
		//初始化控件
		int i = dTrustFaceCtrl.initControl();
		if(i!=0) return;
		Map<String, Map<String,Object>> remap = new HashMap<String, Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imageWidth", 123);
		map.put("imageHeight", 123);
		map.put("imageType", 123);
		map.put("imageData", 123);
		remap.put("photoInfo", map);

		String jsonObject = JSONObject.toJSONString(remap);
		System.out.println(jsonObject);
		String strPhotoInfo = jsonObject;
		i = dTrustFaceCtrl.setFaceTemplatePhoto(strPhotoInfo);
		if(i!=0) return;
	}

}
