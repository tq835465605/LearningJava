package com.foxhis.wps;

public enum ItfType {
	//第二个为别名
	doorcard("门锁","早餐"),
	police("公安","police"),
	sms("短信","sms"),
	pbx("电话","华为"),
	idcard("身份","身份证"),
	ctrlroom("房控","客控"),
	vod("VOD","电视"),
	vip("会员","贵宾"),
	fin("财务","fin"),
	cti("呼叫中心","cti"),
	internet("宽带","网关");
	
	String typename;
	String secondname;
	
	private ItfType(String typename,String secondname)
	{
		this.typename = typename;
		this.secondname = secondname;
	}

	public String getTypeName()
	{
		return typename;
	}
	public String getSecName()
	{
		return secondname;
	}
}
