package com.foxhis.wps;

public enum MonthType {
	
	kaifa("开发",1),
	tiaoshi("调试",2),
	weihu("维护",3),
	xiezhu("协助",4);
	
	String type;
	int num;
	
	private MonthType(String type,int num)
	{
		this.type = type;
		this.num = num;
	}

	public String getType()
	{
		return type;
	}
	

	public int getNum()
	{
		return num;
	}

}
