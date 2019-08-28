package com.foxhis.databasepool;

import java.util.Date;

/**
 * 停车场的中间库
 * @author Administrator
 *
 */
public class BWCarCOM {
	
	private int id;
	private String c_accno;
	private String c_roomno;
	private String c_cardno;
	private Date dt_checkout;
	private String c_status;
	private Date dt_dt;
	private String c_sync;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getC_accno() {
		return c_accno;
	}
	public void setC_accno(String c_accno) {
		this.c_accno = c_accno;
	}
	public String getC_roomno() {
		return c_roomno;
	}
	public void setC_roomno(String c_roomno) {
		this.c_roomno = c_roomno;
	}
	public String getC_cardno() {
		return c_cardno;
	}
	public void setC_cardno(String c_cardno) {
		this.c_cardno = c_cardno;
	}
	public Date getDt_checkout() {
		return dt_checkout;
	}
	public void setDt_checkout(Date dt_checkout) {
		this.dt_checkout = dt_checkout;
	}
	public String getC_status() {
		return c_status;
	}
	public void setC_status(String c_status) {
		this.c_status = c_status;
	}
	public Date getDt_dt() {
		return dt_dt;
	}
	public void setDt_dt(Date dt_dt) {
		this.dt_dt = dt_dt;
	}
	public String getC_sync() {
		return c_sync;
	}
	public void setC_sync(String c_sync) {
		this.c_sync = c_sync;
	}
	@Override
	public String toString() {
		return "BWCarCOM [id=" + id + ", c_accno=" + c_accno + ", c_roomno=" + c_roomno + ", c_cardno=" + c_cardno
				+ ", dt_checkout=" + dt_checkout + ", c_status=" + c_status + ", dt_dt=" + dt_dt + ", c_sync=" + c_sync
				+ "]";
	}
	
	

}
