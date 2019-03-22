package com.foxhis.email;

import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 采用公司标准的HttpClient-4.3.1
 * Https请求=http请求+SSL/TLS（安全套接层/传输层安全协议）组合
 * 
 * ------------------------------------------------
 *    应用层            Application
 *                SSL/TLS
 *    传输层             Transport
 *                TCP/UDP   
 *    网络层               Internet
 *    
 *    数据链路层         NewWork
 *    
 *  SSL主要有：  认证，加密，维护数据完整
 *  TLS主要有：  再度加密，目标是使SSL更安全
 *    
 * @author Administrator
 *
 */
public class HttpsClientUtils {

	private static CloseableHttpClient createSSLClient() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException
	{
		// TODO Auto-generated constructor stub
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			@Override
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// TODO Auto-generated method stub 信任所有
				return true;
			}
		}).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,new String[] { "TLSv1" },null,
				SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}
	
	/*
	 * 此方法得采用httpclient4.4.以上版本
	 * private  static CloseableHttpClient createSSLClient1() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException
	{
		// TODO Auto-generated constructor stub
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			@Override
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// TODO Auto-generated method stub 信任所有
				return true;
			}
		}).build();
		HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}*/
	
	public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException
	{
		SSLContext sc = SSLContext.getInstance("TLSv1");
		X509TrustManager trustManager = new X509TrustManager() {
			
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
					throws CertificateException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
					throws CertificateException {
				// TODO Auto-generated method stub
				
			}
		};
		sc.init(null, new TrustManager[] {trustManager}, null);
		return sc;
	}
	
	/**
	 * 带参数的https的POST请求
	 * @param url
	 * @param params
	 * @param charset
	 * @return
	 */
	public static String doPost(String url,Map<String, Object> params,String charset) 
	{
		try {
			
			HttpPost httppost = new HttpPost(url);
			List<NameValuePair> listNVP = new ArrayList<NameValuePair>();
			if (params != null) {
				for (String key : params.keySet()) {
					listNVP.add(new BasicNameValuePair(key,(String)params.get(key)));
				}
			}
			UrlEncodedFormEntity  uEntity = new UrlEncodedFormEntity(listNVP, charset);
			httppost.setEntity(uEntity);
			httppost.setHeader("Content-Type", "application/json");
			
			HttpClient httpClient = createSSLClient();
			
			HttpResponse response = httpClient.execute(httppost);
			HttpEntity hEntity = response.getEntity();
			if(hEntity!=null)
			{
				String reString =EntityUtils.toString(hEntity,charset);
				return reString;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		finally {
			
		}
	}
	
	
	/**
	 * 带参数的https的POST请求
	 * @param url
	 * @param params
	 * @param charset
	 * @return
	 */
	public static String doPost(String url,String json,String charset) 
	{
		try {

			HttpPost httppost = new HttpPost(url);
	        StringEntity uEntity = new StringEntity(json,charset);
			httppost.setEntity(uEntity);
			httppost.setHeader("Content-Type", "application/json");
			HttpClient httpClient = createSSLClient();
			HttpResponse response = httpClient.execute(httppost);
			HttpEntity hEntity = response.getEntity();
			if(hEntity!=null)
			{
				String reString =EntityUtils.toString(hEntity,charset);
				return reString;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		finally {
			
		}
	}
	/**
	 * 带参数的https的GET请求
	 * @param url
	 * @param params
	 * @param charset
	 * @return
	 */
	public static String doGet(String url,Map<String, Object> params,String charset) 
	{
		try {
			URIBuilder uriBuilder = new URIBuilder(url);
		
			List<NameValuePair> listNVP = new ArrayList<NameValuePair>();
			if (params != null) {
				for (String key : params.keySet()) {
					listNVP.add(new BasicNameValuePair(key,(String)params.get(key)));
				}
			}
			URI uri = uriBuilder.setParameters(listNVP).build();		
			HttpGet httpget = new HttpGet(uri);
			
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 请求超时时间
                    .setSocketTimeout(60000)// 数据读取超时时间
                    .build();
            // 为httpGet实例设置配置
			httpget.setConfig(requestConfig);
			httpget.setHeader("Content-Type", "application/json");
			HttpClient httpClient = createSSLClient();
			//HttpClient httpClient = HttpClients.createDefault();
			HttpResponse response = httpClient.execute(httpget);
			HttpEntity hEntity = response.getEntity();
			if(hEntity!=null)
			{
				String reString =EntityUtils.toString(hEntity,charset);
				return reString;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		finally {
			
		}
	}
	public static void main(String[] args) {
		
		System.out.println("-------------------------");
		Map<String, Object> params = new HashMap<String, Object>();
		JSONObject jsonObject = new JSONObject();
		params.put("tel", "18806534760");
		params.put("CompanyDB", "SBO_MOP");
		params.put("Password", "9999");
		params.put("UserName", "manager");
		params.put("Language", "15");
		jsonObject.putAll(params);
		System.out.println(HttpsClientUtils.doPost("https://114.116.75.139:50000/b1s/v1/Login", jsonObject.toJSONString(), "utf-8"));
	}
}
