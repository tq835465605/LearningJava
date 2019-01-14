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

public class MonthJobs {

private static final String dir = "F:\\wps";
	
	private static List<Map<String,String>> mapLists = new ArrayList<Map<String,String>>();

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
	    Map<String,String> kaifaMap = new HashMap<String, String>();
	    Map<String,String> tiaoshiMap = new HashMap<String, String>();
	    Map<String,String> weihuMap = new HashMap<String, String>();
	    Map<String,String> xiezhuMap = new HashMap<String, String>();
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
			
			for(String string:mStrings) {
				//遇到空或者小于长度5的退出
				if(string==null || string.length()<=0)
				{
					break;
				}
				System.out.println(string);
				String[] itftype = getMatches(string);
				//System.out.println("itftype:"+itftype);
				if(itftype==null || itftype.length<=2)
				{
					System.out.println("匹配接口类型失败，请检查!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					break;
				}

				if(!itftype[2].endsWith(G_CN))
					itftype[2]=itftype[2]+G_CN;

				if(string.contains(MonthType.kaifa.getType()))//开发
				{	
					getItfType(kaifaMap, itftype);
				}
				if(string.contains(MonthType.tiaoshi.getType()))//调试
				{
					getItfType(tiaoshiMap,  itftype);
				}
				if(string.contains(MonthType.weihu.getType()))//维护
				{
					getItfType(weihuMap,  itftype);
				}
				if(string.contains(MonthType.xiezhu.getType()))//协助
				{
					getItfType(xiezhuMap,  itftype);
				}

			}
			
		}	
		mapLists.add(kaifaMap);
		mapLists.add(tiaoshiMap);
		mapLists.add(weihuMap);
		mapLists.add(xiezhuMap);
		System.out.println(mapLists);
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
		//从0开始数
		HSSFRow  row = sheet.getRow(12);
		//从0开始数
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
	
	private final static String F_EN = ":";
	private final static String F_CN = "：";
	private final static String G_CN = "，";
    /**
     * 匹配每行的接口类型关键字
     * @param source 每行数据
     * @return
     */
	public static String[] getMatches(String source)
	{
		/*if(source.indexOf(":")>=0 )
			return source.substring(source.indexOf(":")+1).endsWith("，")?source.substring(source.indexOf(":")+1):source.substring(source.indexOf(":")+1)+"，";
		if(source.indexOf("：")>=0)
			return source.substring(source.indexOf("：")+1).endsWith("，")? source.substring(source.indexOf(":")+1):source.substring(source.indexOf(":")+1)+"，";
		return null;*/
		/*if(source.indexOf(F_EN)>=0 )
		{
			String[] dod = source.split(F_EN);
			if(!dod[1].endsWith(G_CN))
				dod[1]=dod[1]+G_CN;
			return dod;
		}
		if(source.indexOf(F_CN)>=0 )
		{
			String[] dod = source.split(F_CN);
			if(!dod[1].endsWith(G_CN))
				dod[1]=dod[1]+G_CN;
			return dod;
		}*/
		StringTokenizer stringTokenizer = new StringTokenizer(source, "：:、");
		int count = stringTokenizer.countTokens();
		//System.out.println(count);
		String[] strings = new String[count];
		int i=0;
		while (stringTokenizer.hasMoreElements()) {
			String next = stringTokenizer.nextToken();
			strings[i++] = next;
		}
		
		return strings;
	}

	public static void getItfType(Map<String,String> typeMap,String[] itftype)
	{
		if(typeMap.containsKey(itftype[1]))
		{
			String typestr =typeMap.get(itftype[1]);
			typeMap.remove(itftype[1]);
			typeMap.put(getHebingNum(itftype[1], itftype[1]), typestr+itftype[2]);
		}
		else {	
			typeMap.put(itftype[1], itftype[2]);
		}

	}
	
	/**
	 * 周报输出
	 * @throws IOException
	 */
	public static void doWriteOutPut() throws IOException {
		//建立输出文件
		File output = new File(dir+File.separator+"outwps_month.txt");
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
		
		
		for(Map<String,String> mapType:mapLists)
		{
			StringBuilder kaifaStr = new StringBuilder();
			Set<String> keyset = mapType.keySet();//开发，调试，维护，协助
			Object [] strings =  keyset.toArray();
			int k  = strings.length;
			String start=(String)strings[0];
			kaifaStr.append(mapType.get(start));
			for(int i=0;i<k-1;i++)
			{
				int j=i+1;
				start = getHebingNum(start, (String)strings[j]);
				kaifaStr.append(mapType.get(strings[j]));
			}
			writer.write(start+F_CN+hbMsg(kaifaStr.toString()));
			writer.write("\r\n");
			
		}
		writer.flush();
		writer.close();
	}
	
	/**
	 * 将两个字符串中的数字部分相加，然后重新输出
	 * @param source1
	 * @param source2
	 * @return
	 */
	public static String getHebingNum(String source1,String source2)
	{
		Pattern pattern = Pattern.compile("[^0-9]+");		
		Matcher matcher1 = pattern.matcher(source1);
		Matcher matcher2 = pattern.matcher(source2);
		String num1 = matcher1.replaceAll("").trim();
		String num2 = matcher2.replaceAll("").trim();
		int total = Integer.valueOf(num1)+Integer.valueOf(num2);
		Pattern patternNum = Pattern.compile("[0-9]+");	//匹配多个数字
		Matcher matcherNum = patternNum.matcher(source1);
		String result = matcherNum.replaceAll(String.valueOf(total));
		//System.out.println(result);
		return result;
		
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
