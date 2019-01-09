package com.foxhis.email;

public class EmailManagerTest {
	public static void main(String[] args) {	
		String emailAccount = "tq8@foxhis.com";	
		//your email account	
		String password = "";	
		// you know	
		int type = EmailType.email_263; 
		// your email type , such : EmailType.email_msn;	
		String str = EmailManager.getInstence().getEmailContacts(type, emailAccount, password); 
		System.out.println("我的邮箱好友:"+str);	
	}

}
