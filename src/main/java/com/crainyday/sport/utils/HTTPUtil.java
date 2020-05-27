package com.crainyday.sport.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 模拟浏览器发送请求的工具类 发送GET和POST请求
 * 
 * @author crainyday
 *
 */
public class HTTPUtil {
	/**
	 * 发送GET请求
	 * 
	 * @param url: 要请求的URL.
	 * @param map: 请求参数.
	 * @return
	 * @throws Exception
	 */
	public static String GET(String url, Map<String, String> map) throws Exception {
		// 创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		URI uri = null;
		// 定义请求的参数
		StringBuffer sb = new StringBuffer("?");
		for (Entry<String, String> data : map.entrySet()) {
			sb.append(data.getKey()).append("=");
			sb.append(data.getValue()).append("&");
		}
		url = url + sb.substring(0, sb.length() - 1);
		uri = new URIBuilder(url).build();
		// 创建HTTP GET请求
		HttpGet httpGet = new HttpGet(uri);
		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = httpClient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				return content;
			}
		} finally {
			if (response != null) {
				response.close();
			}
			httpClient.close();
		}
		return null;
	}

	/**
	 * 发送POST请求
	 * 
	 * @param url: 请求的URL.
	 * @param map: 请求参数.
	 * @return
	 * @throws Exception
	 */
	public static String POST(String url, Map<String, String> map) throws Exception {
		// 创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建HTTP POST请求
		HttpPost httpPost = new HttpPost(url);
		// 设置POST请求的参数
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		for (Entry<String, String> data : map.entrySet()) {
			parameters.add(new BasicNameValuePair(data.getKey(), data.getValue()));
		}
		// 构造一个FORM表单式的实体
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, Consts.UTF_8);
		// 将请求实体设置到HttpPost对象中
		httpPost.setEntity(formEntity);
		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = httpClient.execute(httpPost);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				return content;
			}
		} finally {
			if (response != null) {
				response.close();
			}
			httpClient.close();
		}
		return null;
	}

	/**
	 * 发送 POST 请求, 携带 JSON 参数.
	 * 
	 * @param url:  请求的URL
	 * @param json: 携带的JSON数据
	 * @return
	 * @throws Exception
	 */
	public static String PostWithJson(String url, String json) throws Exception {
		// 创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建HTTP POST请求
		HttpPost httpPost = new HttpPost(url);
		// 构造一个 字符串 表单式的实体
		StringEntity requestEntity = new StringEntity(json, "utf-8");
		// 设置POST请求的参数
		requestEntity.setContentEncoding("UTF-8");
		httpPost.setHeader("Content-type", "application/json");
		// 将请求实体设置到HttpPost对象中
		httpPost.setEntity(requestEntity);
		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = httpClient.execute(httpPost);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				return content;
			}
		} finally {
			if (response != null) {
				response.close();
			}
			httpClient.close();
		}
		return null;
	}

	/**
	 * 发送 POST 请求一个 Buffer 流, 携带 JSON 参数.
	 * 
	 * @param url
	 * @param json
	 * @return
	 */
	public static void PostBufferAsFile(String url, String json, String filePath) throws Exception {
		// 创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建HTTP POST请求
		HttpPost httpPost = new HttpPost(url);
		// 构造一个 字符串 表单式的实体
		StringEntity requestEntity = new StringEntity(json, "utf-8");
		// 设置POST请求的参数
		requestEntity.setContentEncoding("UTF-8");
		httpPost.setHeader("Content-type", "application/json");
		// 将请求实体设置到HttpPost对象中
		httpPost.setEntity(requestEntity);
		CloseableHttpResponse response = null;
		OutputStream out = new FileOutputStream(filePath);
		try {
			// 执行请求
			response = httpClient.execute(httpPost);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				byte[] buffer = EntityUtils.toByteArray(response.getEntity());
				out.write(buffer, 0, buffer.length);
			}
		} finally {
			if(out!=null) {
				out.close();
			}
			if (response != null) {
				response.close();
			}
			httpClient.close();
		}
	}
}
