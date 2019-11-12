package com.foxhis.wps;

public enum ItfType {
	//第二个为别名
	doorcard("门锁","第吉尔","西容","必达","Y68","爱迪尔"),
	police("公安","旅业","数据同步","人证比","",""),
	sms("短信","sms","模版","","",""),
	pbx("电话","华为","国威","JDS","JP10",""),
	idcard("身份","身份证","","","",""),
	ctrlroom("房控","客控","尊宝","","",""),
	vod("VOD","电视","","","",""),
	vip("会员","贵宾","明华35","","",""),
	fin("财务","fin","","","",""),
	cti("呼叫中心","cti","","","",""),
	internet("宽带","网关","","","",""),
	passprot("护照","文通","","","","");
	
	String typename;
	String secondname;
	String thirdname;
	String fourname;
	String fivename;
	String sixname;
	
	private ItfType(String typename,String secondname,String thridname,String fourname,String fivename,String sixname)
	{
		this.typename = typename;
		this.secondname = secondname;
		this.thirdname = thridname;
		this.fourname = fourname;
		this.fivename = fivename;
		this.sixname = sixname;
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
	public String getFourName()
	{
		return fourname;
	}
	public String getFiveName()
	{
		return fivename;
	}
	public String getSixName()
	{
		return sixname;
	}
}
