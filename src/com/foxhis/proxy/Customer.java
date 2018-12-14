package com.foxhis.proxy;


/**
 * 申明一个买车的用户
 * @author Administrator
 *
 */
public class Customer implements IBuyCar {

	public int cash;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	public Customer(int cash) {
		// TODO Auto-generated constructor stub
		this.cash = cash;
	}
	
	public int getCash() {
		return cash;
	}



	public void setCash(int cash) {
		this.cash = cash;
	}

	public void customSay(AppMain app)
	{
		app.say();
	}


	@Override
	public void buyCar() {
		// TODO Auto-generated method stub
        System.out.println(this.getClass() +"买一辆车花费了"+cash+"元");
	}

}
