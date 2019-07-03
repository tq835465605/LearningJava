package com.foxhis.h2;


@H2Table(name="Pack_Accnt")
public class Pack_Accnt {

	@H2Column(name="accnt",tp="varchar(32)")
	private String accnt;	
	@H2Column(name="requestid",tp="varchar(32)")
	private String requestid;
	
	
	public String getAccnt() {
		return accnt;
	}
	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}
	public String getRequestid() {
		return requestid;
	}
	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}
	
}
