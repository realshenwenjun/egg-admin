package com.dskj.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtil {
	public static String post(String url, String json, Map<String, String> headers) {
		String result = null;
		try {
			CloseableHttpClient client = createSSLClientDefault();
			HttpPost post = new HttpPost(url);
			StringEntity s = new StringEntity(json.toString());
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			Iterator<Entry<String, String>> iter = headers.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, String> entry = (Entry<String, String>) iter.next();
				post.setHeader(entry.getKey().toString(), entry.getValue().toString());
			}
			post.setEntity(s);
			CloseableHttpResponse response = client.execute(post);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	public static CloseableHttpClient createSSLClientDefault() throws Exception {
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			// 信任所有
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		}).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}

	public static String post(String url, String json) {
		String result = null;
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost post = new HttpPost(url);
			StringEntity s = new StringEntity(json,"UTF-8");
			s.setContentType("application/x-www-form-urlencoded;charset=UTF-8");
			post.setEntity(s);
			post.setHeader("Authorization", "ZjM1ZTZhMzctYTA1Ny00NjJiLWI0ODctMTc4YTI2NGUxYzFk");
			CloseableHttpResponse response = client.execute(post);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String get(String url, Map<String, String> headers) {
		String result = null;
		try {
			CloseableHttpClient client = createSSLClientDefault();
			HttpGet get = new HttpGet(url);
			Iterator<Entry<String, String>> iter = headers.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, String> entry = (Entry<String, String>) iter.next();
				get.setHeader(entry.getKey().toString(), entry.getValue().toString());
			}
			CloseableHttpResponse response = client.execute(get);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
