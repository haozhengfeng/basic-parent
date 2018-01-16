package org.haozf.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RedisClient {

	private String url;
	private KeyPathGenerator keyGenerator;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public KeyPathGenerator getKeyGenerator() {
		return keyGenerator;
	}

	public void setKeyGenerator(KeyPathGenerator keyGenerator) {
		this.keyGenerator = keyGenerator;
	}

	private final String exists = "/exists";
	private final String get = "/get";
	private final String set = "/set";
	private final String remove = "/remove";
	private final String type = "/type";

	/**
	 * 连接缓存程序
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public String post(String url, List<NameValuePair> params) {
		HttpClient hc = new DefaultHttpClient();
		String body = null;
		try {
			// Post请求
			HttpPost httppost = new HttpPost(url);
			// 设置参数
			httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 发送请求
			HttpResponse httpresponse = hc.execute(httppost);
			// 获取返回数据
			HttpEntity entity = httpresponse.getEntity();
			body = EntityUtils.toString(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;
	}

	/**
	 * 判断key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public String exists(String key) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", key));
		String rt = post(this.url + this.exists, params);
		return rt;
	}

	/**
	 * 通过key获取值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", key));
		String rt = post(this.url + this.get, params);
		return rt;
	}

	/**
	 * 设置key值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(String key, String value) {

		// 按生成策略生成key
		key = keyGenerator.generate() + "." + key;

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", key));
		params.add(new BasicNameValuePair("value", value));
		String rt = post(this.url + this.set, params);
		return rt;
	}

	/**
	 * 设置key值
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public String set(String key, String value, String expire) {

		// 按生成策略生成key
		key = keyGenerator.generate() + "." + key;

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", key));
		params.add(new BasicNameValuePair("value", value));
		params.add(new BasicNameValuePair("expire", expire));
		String rt = post(this.url + this.set, params);
		return rt;
	}

	/**
	 * 删除key
	 * 
	 * @param key
	 * @return
	 */
	public String remove(String key) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", key));
		String rt = post(this.url + this.remove, params);
		return rt;
	}

	/**
	 * 判断key的类型
	 * 
	 * @param key
	 * @return
	 */
	public String type(String key) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", key));
		String rt = post(this.url + this.type, params);
		return rt;
	}
	
	public static void main(String[] args) {
		RedisClient redisClient = new RedisClient();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("auth", "0"));
		params.add(new BasicNameValuePair("info", "{'newsid':'95103'}"));
		String rt = redisClient.post("http://open.d1cm.com/jxindex/newsdetail.action", params);
		System.out.println(JSONObject.fromObject(rt).get("news"));
		//System.out.println(rt);
	}

}
