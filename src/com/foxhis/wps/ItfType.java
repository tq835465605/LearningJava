package com.foxhis.wps;

public enum ItfType {
	//第二个为别名
	doorcard("门锁","第吉尔","西容"),
	police("公安","旅业","数据同步"),
	sms("短信","sms",""),
	pbx("电话","华为","国威"),
	idcard("身份","身份证",""),
	ctrlroom("房控","客控",""),
	vod("VOD","电视",""),
	vip("会员","贵宾",""),
	fin("财务","fin",""),
	cti("呼叫中心","cti",""),
	internet("宽带","网关",""),
	passprot("护照","文通","");
	
	String typename;
	String secondname;
	String thirdname;
	
	private ItfType(String typename,String secondname,String thridname)
	{
		this.typename = typename;
		this.secondname = secondname;
		this.thirdname = thridname;
	}

	public String getTypeName()
	{
		return typename;
	}
	public String getSecName()
	{
		return secondname;
	}
	public String getThridName()
	{
		return thirdname;
	}
}
