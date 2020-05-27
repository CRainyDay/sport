package com.crainyday.sport.entity;

/**
 * 对应数据库的admin数据表
 * 管理员表
 * @author crainyday
 *
 */
public class Admin {
	// 管理员ID
	private Integer adminId;
	// 管理员学校
	private String adminSchool;
	// 学校英文缩写: 默认ujn
	private String prefix;
	// 学校邮箱
	private String eduEmail;
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getAdminSchool() {
		return adminSchool;
	}
	public void setAdminSchool(String adminSchool) {
		this.adminSchool = adminSchool;
	}
	
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix.toLowerCase();
	}
	public String getEduEmail() {
		return eduEmail;
	}
	public void setEduEmail(String eduEmail) {
		this.eduEmail = eduEmail;
	}
}
