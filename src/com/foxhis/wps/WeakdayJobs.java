package com.foxhis.wps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
  * 将周报文件放入指定的目录然后获取里面的内容，做解析获取周报的格式内容
 * @author Administrator
 *
 */
public class WeakdayJobs {

	private static final String dir = "F:\\wps";
	
	private static List<Map<String,Map<String,Integer>>> mapLists = new ArrayList<Map<String,Map<String,Integer>>>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File weakdir = new File(dir);
		if(!weakdir.exists()) {
			System.err.println(dir+"路径不存在,自动新建，请将周报文件放入该文件夹");
			weakdir.mkdir();
			return;
		}
		if(!weakdir.isDirectory())
		{
			System.err.println(dir+"不是路径,请选择正确的路径");
			return;
		}	
	    File[] wpsfiles = weakdir.listFiles();
		for(File wpsfile : wpsfiles)
		{
			//筛选.xls的文件
			if(!wpsfile.getAbsolutePath().contains(".xls"))
			{
				continue ;
			}
			System.out.println("-------------------------------");
			System.out.println("【文件名称】"+wpsfile.getName());		
			//通过poi获取周报内容信息
			String weakmsg = getWeakMsg(wpsfile);
			//分解周报内容
			String[] mStrings =weakmsg.split("\\n");
			Map<String,Map<String,Integer>> typeMap = new HashMap<String, Map<String,Integer>>();
			for(String string:mStrings) {
				//遇到空或者小于长度5的退出
				if(string==null || string.length()<=0 || string.length()<=5)
				{
					break;
				}
				System.out.println(string);
				String itftype = getMatches(string);
				//System.out.println("itftype:"+itftype);
				if(itftype==null)
				{
					System.out.println("***********匹配接口类型失败，请检查**********************************");
					itftype = "其他";
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
				if(string.contains(WorkType.xiezhuorweihu.getType()))//协助
				{
					getItfType(typeMap, WorkType.xiezhu.getType(), itftype);
				}

			}
			mapLists.add(typeMap);
			System.out.println(typeMap);
		}	
	    if(mapLists.isEmpty())
	    {
	    	System.err.println(dir+"里没有周报文件，请将周报文件放入该文件夹");
	    	return;
	    }
		doWriteOutPut();
	}

	//读
	public static String getWeakMsg(File wpsfile) throws FileNotFoundException, IOException
	{
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(wpsfile));
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFRow  row = sheet.getRow(2);
		HSSFCell cell = row.getCell(4);
		String string = cell.getStringCellValue();
		//System.out.println(string);
		workbook.close();
		return string;
	}
	//写
	public static void writeWeakMsg(File wpsfile,List<Map<String,String>> dataList) throws FileNotFoundException, IOException
	{
		if(!wpsfile.exists()) wpsfile.createNewFile();
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(wpsfile));
		HSSFSheet sheet = workbook.getSheetAt(0);	
		
		/**
               * 删除原有数据，除了属性列
         *//*
        int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
        System.out.println("原始数据总行数，除属性列：" + rowNumber);
        for (int i = 1; i <= rowNumber; i++) {
            Row row = sheet.getRow(i);
            sheet.removeRow(row);
        }
        */
		int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
		System.out.println("getLastRowNum"+rowNumber);
		System.out.println("getPhysicalNumberOfRows"+sheet.getPhysicalNumberOfRows());
		if(sheet.getPhysicalNumberOfRows()<=0)
		{
			Row row = sheet.createRow(0);
			int i=0;
			// 在一行内循环
			row.createCell(i++).setCellValue("姓名");
			row.createCell(i++).setCellValue("邮件地址");
			row.createCell(i++).setCellValue("手机");
		}
		/**
                   * 往Excel中写新数据
         */
        for (int j = 0; j < dataList.size(); j++) {
            // 创建一行：从第二行开始，跳过属性列
        	
            Row row = sheet.createRow(rowNumber+j+1);
            
            // 得到要插入的每一条记录
            Map<String,String> dataMap = dataList.get(j);
            String name = dataMap.get("name").toString();
            String address = dataMap.get("mail").toString();
            String phone = dataMap.get("phone").toString();

            int k=0;
            Cell first = row.createCell(k++);
            first.setCellValue(name);
            Cell second = row.createCell(k++);
            second.setCellValue(address);
            Cell third = row.createCell(k++);
            third.setCellValue(phone);
           
        }
        workbook.write(new FileOutputStream(wpsfile));
		//System.out.println(string);
		workbook.close();
		
	}
    /**
     * 匹配每行的接口类型关键字
     * @param source 每行数据
     * @return
     */
	public static String getMatches(String source)
	{
		/*if(source.indexOf(ItfType.doorcard.getTypeName())>=0 || source.indexOf(ItfType.doorcard.getSecName())>=0 )
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
			return ItfType.cti.getTypeName();*/
		
        if(isContains(source, ItfType.passprot))
        	return ItfType.passprot.getTypeName();
        if(isContains(source, ItfType.doorcard))
        	return ItfType.doorcard.getTypeName();
        if(isContains(source, ItfType.police))
        	return ItfType.police.getTypeName();
        if(isContains(source, ItfType.sms))
        	return ItfType.sms.getTypeName();
        if(isContains(source, ItfType.pbx))
        	return ItfType.pbx.getTypeName();
        if(isContains(source, ItfType.idcard))
        	return ItfType.idcard.getTypeName();
        if(isContains(source, ItfType.ctrlroom))
        	return ItfType.ctrlroom.getTypeName();
        if(isContains(source, ItfType.vod))
        	return ItfType.vod.getTypeName();
        if(isContains(source, ItfType.vip))
        	return ItfType.vip.getTypeName();
        if(isContains(source, ItfType.fin))
        	return ItfType.fin.getTypeName();
        if(isContains(source, ItfType.internet))
        	return ItfType.internet.getTypeName();
        if(isContains(source, ItfType.cti))
        	return ItfType.cti.getTypeName();
		return null;
	}
	
	public static boolean isContains(String source ,ItfType itfType)
	{
		return source.indexOf(itfType.getTypeName())>0
				||source.indexOf(itfType.getSecName())>0 
				||source.indexOf(itfType.getThridName())>0
				||source.indexOf(itfType.getFourName())>0
				||source.indexOf(itfType.getFiveName())>0
				||source.indexOf(itfType.getSixName())>0;
	}

	public static void getItfType(Map<String,Map<String,Integer>> typeMap,String worktype,String itftype)
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
	
	/**
	 * 周报输出
	 * @throws IOException
	 */
	public static void doWriteOutPut() throws IOException {
		//建立输出文件
		File output = new File(dir+File.separator+"outwps.txt");
		if(output.exists())
		{
			output.delete();
			output.createNewFile();
		}
		if(!output.exists())
		{
			output.createNewFile();
		}
		FileWriter writer  = new FileWriter(output,true);
		int kaifaNum = 0;
		int weihuNum = 0;
		int tiaoshiNum = 0;
		int xiezhuNum = 0;
		StringBuilder kaifaStr = new StringBuilder();
		StringBuilder weihuStr = new StringBuilder();
		StringBuilder tiaoshiStr = new StringBuilder();
		StringBuilder xiezhuStr = new StringBuilder();
		
		for(Map<String,Map<String,Integer>> mapType:mapLists)
		{
			Set<String> keyset = mapType.keySet();//开发，调试，维护，协助
			for(String key:keyset)
			{
				Map<String,Integer> type = mapType.get(key);
				Set<String> typeset = type.keySet();
				if(key.equals(WorkType.kaifa.getType()))
				{
							
					for(String typestr:typeset)
					{
						int num = type.get(typestr);
						kaifaNum += num;
						kaifaStr.append(num+"个");
						kaifaStr.append(typestr);
						kaifaStr.append("，");
					}
				}
				if(key.equals(WorkType.tiaoshi.getType()))
				{
					for(String typestr:typeset)
					{
						int num = type.get(typestr);
						tiaoshiNum += num;
						tiaoshiStr.append(num+"个");
						tiaoshiStr.append(typestr);
						tiaoshiStr.append("，");
					}
				}
				if(key.equals(WorkType.weihu.getType()))
				{
					
					for(String typestr:typeset)
					{
						int num = type.get(typestr);
						weihuNum +=num;
						weihuStr.append(num+"个");
						weihuStr.append(typestr);
						weihuStr.append("，");
					}
				}
				if(key.equals(WorkType.xiezhu.getType()))
				{
					
					for(String typestr:typeset)
					{
						int num = type.get(typestr);
						xiezhuNum += num;
						xiezhuStr.append(num+"个");
						xiezhuStr.append(typestr);
						xiezhuStr.append("，");
					}
				}
	
			}
		}
		writer.write(WorkType.kaifa.getNum()+"、接口开发"+kaifaNum+"个："+hbMsg(kaifaStr.toString()));
		writer.write("\r\n");
		writer.write(WorkType.tiaoshi.getNum()+"、接口调试"+tiaoshiNum+"个："+hbMsg(tiaoshiStr.toString()));
		writer.write("\r\n");
		writer.write(WorkType.weihu.getNum()+"、接口维护"+weihuNum+"个："+hbMsg(weihuStr.toString()));
		writer.write("\r\n");
		writer.write(WorkType.xiezhu.getNum()+"、接口协助"+xiezhuNum+"个："+hbMsg(xiezhuStr.toString()));
		writer.write("\r\n");
		writer.flush();
		writer.close();
	}
	
	/**
	 * 分解匹配相同的类型，并合计个数
	 * @param type
	 * @return
	 */
	public static String hbMsg(String type)
	{
		if(type==null||type.length()<=0)
			return null;
		StringTokenizer sTokenizer =new StringTokenizer(type, "，");
		Map<String,Integer> numMap = new HashMap<String, Integer>();
		Pattern pattern = Pattern.compile("[^0-9]");		
		while (sTokenizer.hasMoreElements()) {
			String st = sTokenizer.nextToken();
			if(st==null || st.length()<=0) continue;
			Matcher matcher = pattern.matcher(st);
			String num = matcher.replaceAll("").trim();
			//System.out.println(num);
			String itftype= st.substring(st.indexOf(num)+num.length());
			//System.out.println(itftype);
			if(numMap.containsKey(itftype))
			{
				int nums = numMap.get(itftype).intValue();
				numMap.put(itftype, nums+Integer.valueOf(num));
			}
			else {
				numMap.put(itftype, Integer.valueOf(num));
			}	
		}
		Set<String> typeset = numMap.keySet();
		StringBuilder sBuilder = new StringBuilder();
		for(String key :typeset)
		{
			sBuilder.append(numMap.get(key));
			sBuilder.append(key);
			sBuilder.append("，");
		}
		return sBuilder.toString();
	}

}
