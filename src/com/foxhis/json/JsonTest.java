package com.foxhis.json;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class JsonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String  teString = "  \r\n" + 
				"";
		JSONObject sJsonObject =(JSONObject) JSONObject.parse(teString);
		System.out.println(sJsonObject);
		if(sJsonObject.containsKey("name"))
		{
			JSONObject copy = (JSONObject) sJsonObject.clone();
			Map<String, Object> pMap = new HashMap<String, Object>();
			copy.remove("name");
			System.out.println(copy);
			System.out.println(sJsonObject.size());
			System.out.println(copy.size());
		}

	}

}
