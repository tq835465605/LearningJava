package com.foxhis.proxy;


//申明一个代理类
public class BuyCarProxy implements IBuyCar {

	private Customer buyCar;
	
	public BuyCarProxy(Customer buyCar) {
		// TODO Auto-generated constructor stub
		this.buyCar = buyCar;
	}
	
	
	@Override
	public void buyCar() {
		// TODO Auto-generated method stub
        int cash = buyCar.getCash();
        if(cash<20000)
        {
        	System.out.println("你的钱不够买一辆车");
        }
        else {
			buyCar.buyCar();
		}
	}

}
