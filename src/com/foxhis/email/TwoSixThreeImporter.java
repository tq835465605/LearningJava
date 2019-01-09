package com.foxhis.email;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.httpclient.NameValuePair;
import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.foxhis.wps.WeakdayJobs;

/**
 *  导出263联系人列表
 * 
 * @author tq
 * 
 */

public class TwoSixThreeImporter extends EmailImporter{

	    // 请求 URL: https://mail.263.net/wm2e/mail/login/show/loginShowAction_loginShow.do?usr=tq8@foxhis.com&sid=OHQ1cTc4M0A0ZjZvMXgwaDBp&isp_domain=&useCDN=0&ssl=true
	    // 登录url
		private String loginUrl = "https://mail.263.net/wm2e/mail/login/show/loginShowAction_loginShow.do?";
        //usr=tq8@foxhis.com&sid=MHQ5cTE4M0AwZjdvNngzaDlp&isp_domain=&useCDN=0&ssl=true
		// 联系人列表url
		private String contactsUrl = "http://mail.foxhis.com/wm2e/mail/enterpriseAddress/enterprirseAddressAction_getEnterpriseDepartmentPeople.do?";
		// 邮箱首页的body内容
		private String indexPage = null;
		
		private Map<String, String> portment= new HashMap<String, String>();
		/**
		 * 构造函数
		 * 
		 * @param email
		 * @param password
		 */
		public TwoSixThreeImporter(String email, String password) {
			super(email, password);
			// TODO Auto-generated constructor stub
			InitialPartment();
		}

		private void InitialPartment()
		{
			portment.put("财务部", "10");
			portment.put("总经理办公室", "16");
			portment.put("行政人事部", "11");
			portment.put("市场营销部", "17");
			portment.put("实施事业部", "5");
			portment.put("售后事业部", "1");
			portment.put("研发中心", "4");
			portment.put("公用邮箱", "24");
			portment.put("集团事业部", "36");
			portment.put("技术支持事业部", "45");
			portment.put("未分配部门", "-1");
		}
		/**
		 * 登录126邮箱
		 * 
		 * @throws ContactsException
		 */
		public void doLogin() throws ContactsException {
			try {
				NameValuePair params[] = { 
						new NameValuePair("usr", email),
						new NameValuePair("sid", password),
						new NameValuePair("isp_domain", ""),
						new NameValuePair("useCDN", "0"),
						new NameValuePair("ssl", "false")};

				doPost(loginUrl, params, "http://mail.foxhis.com/");
				
				
			} catch (Exception e) {
				throw new ContactsException("263 protocol has changed", e);
			}
		}

		public String doGetContacts(String deptId,String deptName) throws Exception
		{
			
			NameValuePair params1[] = { 
					new NameValuePair("usr", email),
					new NameValuePair("sid", password),
					new NameValuePair("deptId", deptId),
					new NameValuePair("enter", ""),
					new NameValuePair("start", "1"),
					new NameValuePair("sort", "defName"),
					new NameValuePair("deptName", deptName)
					};

			return indexPage = doPost(contactsUrl, params1, "http://mail.foxhis.com/wm2e/mail/login/show/loginShowAction_loginShow.do?usr=tq8@foxhis.com&sid=MnQ0cTc4MEA4ZjNvMngwaDBp&isp_domain=&useCDN=0&ssl=false");
			
		}
		
		/**
		 * 批量获取邮箱
		 * @return
		 * @throws Exception
		 */
		public List<Map<String, String>> doWhile() throws Exception
		{
			List<Map<String, String>> contacts = new ArrayList<Map<String, String>>();
			if(portment.isEmpty()) InitialPartment();
			Set<String> keys = portment.keySet();
			for (String deptName : keys) {
				String htmlcontent = doGetContacts(portment.get(deptName),deptName);
				contacts.addAll(parseContactsMap(htmlcontent));
			}
			return contacts;
		}
		public List<Map<String, String>> parseContactsMap(String htmlcontent) throws ContactsException  {
			try {
				String content =  getSid(htmlcontent, "table");
				System.out.println(content);
				List<Map<String, String>> contacts = new ArrayList<Map<String, String>>();
				DOMParser parser = new DOMParser();
				InputSource is = new InputSource(new ByteArrayInputStream(content
						.getBytes("GBK")));
				is.setEncoding("GBK");
				parser.parse(is);
				NodeList nodes = parser.getDocument().getElementsByTagName("ul");			
				for (int i = 0; i < nodes.getLength(); i++) {
					Node node = nodes.item(i);
					String text = node.getTextContent();
					StringTokenizer stringTokenizer = new StringTokenizer(text, "\t\n");
					int counet = stringTokenizer.countTokens();
					if(counet>3) continue;
					String[] names = new String[3];
					int m=0;
					while (stringTokenizer.hasMoreElements()) {
						String object = (String) stringTokenizer.nextToken();
						names[m++] = object;
						System.out.println(object);
						
					}
					
					Map<String, String> map = new HashMap<String, String>();
					map.put("name", names[0]);
					if(m<=2)
					{
						map.put("mail", names[1]);
						map.put("phone", "");
					}
					else {
						map.put("mail", names[2]);
						map.put("phone", names[1]);
					}
					contacts.add(map);
				}
					
				return contacts;
			} catch (Exception e) {
				throw new ContactsException("263 protocol has changed", e);
			}
		}
		/**
		 * 获取html通讯录table
		 * 
		 * @param content
		 *            首页的页面body
		 * @param frameName
		 *            包含sid的frame的name
		 * @return sid
		 */
		private String getSid(String content, String frameName) {
			int indexMid = content.indexOf("<table ");
			return content.substring(indexMid);
			
		}
		
		public static void main(String[] args) throws Exception {
			TwoSixThreeImporter threeImporter = new TwoSixThreeImporter("tq8@foxhis.com", "MnQ0cTc4MEA4ZjNvMngwaDBp");
			threeImporter.doLogin();
			List<Map<String, String>> datalist = threeImporter.doWhile();
			WeakdayJobs.writeWeakMsg(new File("F:\\wps\\mywps.xls"), datalist);
		}

		@Override
		protected List<Contact> parseContacts() throws ContactsException {
			// TODO Auto-generated method stub
			return null;
		}
		
		
}
