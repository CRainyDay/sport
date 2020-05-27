package com.crainyday.sport.wechat;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessToken {
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("expires_in")
	private Integer expiresIn;
	private Date expires;
	private Integer errcode;
	private String errmsg;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
		expires = new Date(System.currentTimeMillis() + (expiresIn-10)*1000);
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
	/**
	 * access_token 是否过期
	 */
	public boolean isExpires() {
		return new Date().after(expires);
	}
}
