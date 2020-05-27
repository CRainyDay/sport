package com.crainyday.sport.entity;

/**
 * 对应数据库的general数据表
 * 普通用户信息表
 * @author crainyday
 *
 */
public class General {
	// 普通用户ID
	private Integer generalId;
	// 小程序用户唯一ID
	private Integer userId;
	// 用户身份标识
	private String identity;
	// 管理员ID
	private Integer adminId;
	// 普通用户班级
	private String generalClass;
	// 普通用户姓名
	private String generalName;
	// 普通用户性别
	private Integer generalGender;
	// 普通用户单位/学院
	private String generalCollege;
	// 普通用户手机
	private String generalPhone;
	public Integer getGeneralId() {
		return generalId;
	}
	public void setGeneralId(Integer generalId) {
		this.generalId = generalId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getGeneralClass() {
		return generalClass;
	}
	public void setGeneralClass(String generalClass) {
		this.generalClass = generalClass;
	}
	public String getGeneralName() {
		return generalName;
	}
	public void setGeneralName(String generalName) {
		this.generalName = generalName;
	}
	public boolean equals(General general) {
		if(generalClass != null && !generalClass.equals(general.generalClass)) {
			return false;
		}
		if(generalName != null && !generalName.equals(general.generalName)) {
			return false;
		}
		return true;
	}
	public Integer getGeneralGender() {
		return generalGender;
	}
	public void setGeneralGender(Integer generalGender) {
		this.generalGender = generalGender;
	}
	public String getGeneralCollege() {
		return generalCollege;
	}
	public void setGeneralCollege(String generalCollege) {
		this.generalCollege = generalCollege;
	}
	public String getGeneralPhone() {
		return generalPhone;
	}
	public void setGeneralPhone(String generalPhone) {
		this.generalPhone = generalPhone;
	}
}
