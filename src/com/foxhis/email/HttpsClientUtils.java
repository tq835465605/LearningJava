package com.foxhis.email;

import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

public class HttpsClientUtils {

	private  static CloseableHttpClient createSSLClient() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException
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
			HttpClient httpClient = createSSLClient();
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
}
