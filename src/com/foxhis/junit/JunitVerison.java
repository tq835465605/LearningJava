package com.foxhis.junit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
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
	
	
	@Test
	public void tset()
	{
		            //T0000220150525100517 000001 8003 8003 8002 09 0.00 70
		String bill ="T20190416162643 000024 8115            8115    13423878679             3  0.28";
		System.out.println(splitPhoneBillToMap(bill));
	}
	
	public Map<String, String> splitPhoneBillToMap(String msg)
	  {
	    Map mapPhoneBill = new HashMap();
	    try {
	      String[] values = msg.split(" +");
	      System.out.println(values.length);
	      if (values.length != 8) {
	        System.out.println(">>> phoneBill format error");
	        return null;
	      }

	      String responsePhfolio = "";
	      String item0 = StringUtils.isNotBlank(values[0]) ? values[0].trim().substring(1) : "";

	      String checkStart = item0.substring(0, 5);

	      String phoneStartTime = item0.substring(5);

	      String call = StringUtils.isNotBlank(values[3]) ? values[3].trim() : "";

	      String called = StringUtils.isNotBlank(values[4]) ? values[4].trim() : "";

	      responsePhfolio = "Q01" + checkStart + phoneStartTime + "1" + "#" + '\r' + '\n';
	     // sendMsgToComport(responsePhfolio, 1);

	      String item3 = StringUtils.isNotBlank(values[1]) ? values[1].trim() : "";
	      int interval = 0;
	      if (StringUtils.isNotBlank(item3)) {
	        int hour = Integer.parseInt(item3.substring(0, 2));
	        int minute = Integer.parseInt(item3.substring(2, 4));
	        int second = Integer.parseInt(item3.substring(4, 6));
	        interval = hour * 3600 + minute * 60 + second;
	      }

	      String item1 = StringUtils.isNotBlank(values[1]) ? values[1].trim() : "";
	      String item2 = StringUtils.isNotBlank(values[2]) ? values[2].trim() : "";

	      if ((StringUtils.isBlank(call)) || (interval == 0) || (StringUtils.isBlank(item1)) || (StringUtils.isBlank(item2)))
	      {
	       // logger.info(">>> 话单格式不合法，过滤掉改掉数据");
	        return null;
	      }
	      //Date endDate = SwingUtils.parseDateTime(phoneStartTime, "yyyyMMddHHmmss");
	     // endDate.setTime(endDate.getTime() + interval * 1000);

	      //mapPhoneBill.put("hotelid", this.hotelId);
	      mapPhoneBill.put("call", call.trim());
	      mapPhoneBill.put("called", called.trim());
	      mapPhoneBill.put("interval", "" + interval);
	     // mapPhoneBill.put("empno", this.locOperate.getProperty("foxitf.empno"));
	     // mapPhoneBill.put("end_time", SwingUtils.formatDateTime(endDate, "yyyy-MM-dd HH:mm:ss"));
	      return mapPhoneBill;
	    } catch (Exception e) {
	      String message = "接口分割话单为Map形式失败";
	     
	    }
	    return null;
	  }

}
