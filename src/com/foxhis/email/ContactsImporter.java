package com.foxhis.email;

import java.util.List;

import com.foxhis.email.ContactsException;



/**
 * 导入联系人的接口
 * 
 * @author flyerhzm
 * 
 */
public interface ContactsImporter {
	public List<Contact> getContacts() throws ContactsException;
}
