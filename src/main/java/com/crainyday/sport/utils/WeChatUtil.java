package com.crainyday.sport.utils;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.crainyday.sport.wechat.AccessToken;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WeChatUtil {
	private AccessToken accessToken;
	@Value("${wx.appid}")
	private String appId;
	@Value("${wx.secret}")
	private String secret;
	@PostConstruct
	public void init() {
		getAccessToken("client_credential");
	}
	/**
	 * 获取小程序全局唯一后台接口调用凭据（access_token）
	 */
	private void getAccessToken(String grantType) {
		Map<String, String> data = new HashMap<String, String>();
		String url = "https://api.weixin.qq.com/cgi-bin/token";
		data.put("appid", appId);
		data.put("secret", secret);
		data.put("grant_type", grantType);
		try {
			String json = HTTPUtil.GET(url, data);
//			System.err.println(json);
			ObjectMapper mapper = new ObjectMapper();
			accessToken = mapper.readValue(json, AccessToken.class);
		}catch (Exception e) {
			// 网络错误
		}
	}
	public String getAccessToken(){
		if(accessToken.isExpires()) {
			getAccessToken("client_credential");
		}
		return accessToken.getAccessToken();
	}
	/**
	 * 发送订阅消息
	 * @param param:	请求参数
	 * @param msg:		要发送的消息
	 * @return
	 * @throws Exception
	 */
	public String sendSubscribeMessage(Map<String, Object> param, Object msg) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+getAccessToken();
		param.put("access_token", getAccessToken());
		// 小程序类型: 开发版developer 体验版trial 正式版formal(默认)
		param.put("miniprogram_state", "formal");
		param.put("data", msg);
		String json = mapper.writeValueAsString(param);
		System.err.println(json);
		return HTTPUtil.PostWithJson(url, json);
	}
	/**
	 * 获取小程序码
	 * @param filePath:	获取的二维码的存放位置
	 * @param param:	请求参数
	 * @return
	 * @throws Exception
	 */
	public void getWxacodeUnlimited(String filePath, Map<String, Object> param) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+getAccessToken();
//		param.put("access_token", getAccessToken());
		param.put("page", "person/check/check");
		param.put("auto_color", true);
		String json = mapper.writeValueAsString(param);
		System.err.println(json);
		HTTPUtil.PostBufferAsFile(url, json, filePath);
	}
}
