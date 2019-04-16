package com.foxhis.junit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import com.foxhis.trustface.ClassFactory;
import com.foxhis.trustface._DTrustFaceCtrl;
import com.foxhis.wps.ItfType;
import com.foxhis.wps.MonthJobs;
import com.foxhis.wps.WeakdayJobs;
import com.foxhis.wps.WorkType;

public class JunitVerison {
	
	
	
	
	@Test
	public void getWpsFile() throws FileNotFoundException, IOException {
		File file = new File("F:\\wps\\定文景_技术支持中心XMS组工作周报表_1月2号-1月6号.xls");
		String msg = WeakdayJobs.getWeakMsg(file);
		System.out.println(msg);
		String[] mStrings =msg.split("\\n");
		Map<String,Map<String,Integer>> typeMap = new HashMap<String, Map<String,Integer>>();
		for(String string:mStrings) {
			
			if(string==null || string.length()<=0)
			{
				break;
			}
			System.out.println(string);
			String itftype = getMatches(string);
			System.out.println("itftype:"+itftype);
			if(itftype==null)
			{
				System.err.println(string+"匹配接口类型失败，请检查");
			}
			if(string.contains(WorkType.kaifa.getType()))//开发
			{	
				getItfType(typeMap, WorkType.kaifa.getType(), itftype);
				
			}
			if(string.contains(WorkType.tiaoshi.getType()))//调试
			{
				getItfType(typeMap, WorkType.tiaoshi.getType(), itftype);
			}
			if(string.contains(WorkType.weihu.getType()))//维护
			{
				getItfType(typeMap, WorkType.weihu.getType(), itftype);
			}
			if(string.contains(WorkType.xiezhu.getType()))//协助
			{
				getItfType(typeMap, WorkType.xiezhu.getType(), itftype);
			}
			
		}
		System.out.println(typeMap);
		
	}
	
	@Test
	public void getType()
	{
		System.out.println(WorkType.kaifa.getType());
	}
	
	public void getItfType(Map<String,Map<String,Integer>> typeMap,String worktype,String itftype)
	{
		if(typeMap.containsKey(worktype))
		{
			Map<String,Integer> reMap =typeMap.get(worktype);
			if(reMap.containsKey(itftype))
			{
				int n =reMap.get(itftype).intValue();
				reMap.put(itftype, ++n);
			}
		    else {
				reMap.put(itftype, 1);
			}
		}
		else {
			Map<String, Integer> reMap = new HashMap<String, Integer>();
			reMap.put(itftype, 1);
			typeMap.put(worktype, reMap);
		}
		
	}
	
	public String getMatches(String source)
	{
		if(source.indexOf(ItfType.doorcard.getTypeName())>=0 || source.indexOf(ItfType.doorcard.getSecName())>=0)
			return ItfType.doorcard.getTypeName();
		if(source.indexOf(ItfType.police.getTypeName())>=0 || source.indexOf(ItfType.police.getSecName())>=0)
			return ItfType.police.getTypeName();
		if(source.indexOf(ItfType.sms.getTypeName())>=0 || source.indexOf(ItfType.sms.getSecName())>=0)
			return ItfType.sms.getTypeName();
		if(source.indexOf(ItfType.pbx.getTypeName())>=0 || source.indexOf(ItfType.pbx.getSecName())>=0)
			return ItfType.pbx.getTypeName();
		if(source.indexOf(ItfType.idcard.getTypeName())>=0 || source.indexOf(ItfType.idcard.getSecName())>=0)
			return ItfType.idcard.getTypeName();
		if(source.indexOf(ItfType.ctrlroom.getTypeName())>=0 || source.indexOf(ItfType.ctrlroom.getSecName())>=0)
			return ItfType.ctrlroom.getTypeName();
		if(source.indexOf(ItfType.vod.getTypeName())>=0 || source.indexOf(ItfType.vod.getSecName())>=0)
			return ItfType.vod.getTypeName();
		if(source.indexOf(ItfType.vip.getTypeName())>=0 || source.indexOf(ItfType.vip.getSecName())>=0)
			return ItfType.vip.getTypeName();
		if(source.indexOf(ItfType.fin.getTypeName())>=0 || source.indexOf(ItfType.fin.getSecName())>=0)
			return ItfType.fin.getTypeName();
		if(source.indexOf(ItfType.internet.getTypeName())>=0 || source.indexOf(ItfType.internet.getSecName())>=0)
			return ItfType.internet.getTypeName();
		if(source.indexOf(ItfType.cti.getTypeName())>=0 || source.indexOf(ItfType.cti.getSecName())>=0)
			return ItfType.cti.getTypeName();
		
		return null;
	}
	
	@Test
	public void getMSg()
	{
		System.out.println(WeakdayJobs.hbMsg("1个电话，2个财务，2个电话，1个门锁"));
		
	}
	@Test
	public void textocx()
	{
		_DTrustFaceCtrl dTrustFaceCtrl = ClassFactory.createTrustFaceCtrl();
		short resultt=dTrustFaceCtrl.initControl();
		System.out.println(resultt);
	}
	
	@Test
	public void textPatten()
	{
		MonthJobs.getHebingNum("接口开发3个", "接口开发2个");
	}
	
	@Test
	public void finalLists() throws InterruptedException
	{
		final Map<String,Object> lists =getLists();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				lists.remove("5");
			}
		}).start();
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				lists.remove("3");
			}
		}).start();
		Thread.currentThread().join(2000);
		System.out.println(lists.toString());
	}
	
	public Map<String,Object> getLists()
	{
		Map<String,Object> liStrings = new ConcurrentHashMap<String, Object>();
		liStrings.put("1",123);
		liStrings.put("2",434);
		liStrings.put("3",942);
		liStrings.put("4",323);
		liStrings.put("5",345);
		return liStrings;
	}

}
