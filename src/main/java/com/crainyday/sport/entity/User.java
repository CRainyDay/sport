package com.crainyday.sport.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 对应数据库的user数据表
 * 微信小程序用户信息表
 * @author crainyday
 *
 */
public class User {
	// 小程序用户唯一ID
	private Integer userId;
	// 小程序openid
	private String openid;
	// 用户类型
	private Integer userType;
	// 用户的管理员ID
	private Integer adminId;
	// 用户昵称
	private String nickName;
	// 用户头像
	private String avatarUrl;
	// 用户性别
	private Integer gender;
	// 用户省份
	private String province;
	// 用户城市
	private String city;
	// 指定Jackson解析的时间格式
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	// 创建时间
	private Date createTime;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", openid=" + openid + ", createTime=" + createTime + "]";
	}
}
