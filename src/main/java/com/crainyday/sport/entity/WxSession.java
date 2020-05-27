package com.crainyday.sport.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 小程序wx.login时, 根据code获得的wxSession
 * @author crainyday
 *
 */
public class WxSession {
	// 小程序openid
	private String openid;
	// 小程序session_key
	@JsonProperty("session_key")
	private String sessionKey;
	// 小程序unionid
	private String unionid;
	// API返回的errcode
	private Integer errcode;
	// API返回的errmsg
	private String errmsg;
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
