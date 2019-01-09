package com.foxhis.email;

import java.util.List;

public class EmailManager {

	private static EmailManager emailManager = null;

	private EmailManager() {
	}

	public static EmailManager getInstence() {
		if (emailManager == null) {
			emailManager = new EmailManager();
		}
		return emailManager;
	}

	/**
	 * * 获取邮箱通讯录 * * @param type 邮箱类型 *
	 * 1-gmail,2-sina,3-163,4-yahoo,5-hotmail,6-139,7-sohu,8-yeah, *
	 * 9-126,10-189,11-tom,12-msn * @param account 帐号名 * * @param password 密码 *
	 * * @return 邮箱通讯录字符串 * * 如果成功 返回格式如下: *
	 * 通讯录有好友:<c><o><n>张三</n><e>aliyunzixun@xxx.com</e></o><o><n>李四</n><e>431
	 * * @qq.com</e></o></c> 其中以C为大标签,o为一个通讯录好友,n为昵称,e为邮箱 * 通讯录无好友:0 * 如果失败 则返回 "-1"
	 */
	public String getEmailContacts(int type, String account, String password) {
		StringBuilder sb = new StringBuilder();
		ContactsImporter importer = null;
		try {
			importer = getContactsImporter(type, account, password);
			if (importer == null) {
				sb.append("-1");
				return sb.toString();
			}
			List<Contact> contacts = importer.getContacts();
			int size = contacts.size();
			if (size > 0) {
				sb.append("<c>");
				for (int i = 0; i < size; i++) {
					sb.append("<o><n>");
					sb.append(contacts.get(i).getUsername());
					sb.append("</n>");
					sb.append("<e>");
					sb.append(contacts.get(i).getEmail());
					sb.append("</e></o>");
				}
				sb.append("</c>");
			} else {
				sb.append("0");
			}
		} catch (ContactsException e) {
			sb.append("-1");
			e.printStackTrace();
		}
		return sb.toString();
	}

	/** * 邀请邮箱好友 * @return 成功则 返回成功个数 <count>6<count> * 失败则 返回-1 */
	public String inviteEmailContacts() {
		String result = "";
		return result;
	}

	/**
	 * * 获取对应邮箱类型的Importer * @param type * @param account * @param password
	 * * @return
	 */
	private ContactsImporter getContactsImporter(int type, String account, String password) {
		ContactsImporter importer = null;
		switch (type) {
		case EmailType.email_163:
			importer = ContactsImporterFactory.getOneSixThreeContacts(account, password);
			break;
		case EmailType.email_hotmail:
			importer = ContactsImporterFactory.getHotmailContacts(account, password);
			break;
		case EmailType.email_139:
			importer = ContactsImporterFactory.getOneThreeNineContacts(account, password);
			break;
		
		case EmailType.email_126:
			importer = ContactsImporterFactory.getOneTwoSixContacts(account, password);
			break;
		case EmailType.email_189:
			importer = ContactsImporterFactory.getOneEightNineContacts(account, password);
			break;
		case EmailType.email_263:
			importer = ContactsImporterFactory.get263Contacts(account, password);
			break;
		case EmailType.email_msn:
			importer = ContactsImporterFactory.getMSNContacts(account, password);
			break;
		default:
			return null;
		}
		return importer;
	}

}
