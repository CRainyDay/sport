package com.crainyday.sport.entity;

/**
 * 对应数据库的referee数据表
 * 运动会的裁判信息表
 * @author crainyday
 *
 */
public class Referee {
	// 裁判ID
	private Integer refereeId;
	// 小程序用户唯一ID
	private Integer userId;
	// 身份标识
	private String identity;
	// 裁判姓名
	private String refereeName;
	// 裁判手机号
	private String refereePhone;
	// 运动会ID
	private Integer gamesId;
	public Integer getRefereeId() {
		return refereeId;
	}
	public void setRefereeId(Integer refereeId) {
		this.refereeId = refereeId;
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
	public Integer getGamesId() {
		return gamesId;
	}
	public void setGamesId(Integer gamesId) {
		this.gamesId = gamesId;
	}
	public String getRefereeName() {
		return refereeName;
	}
	public void setRefereeName(String refereeName) {
		this.refereeName = refereeName;
	}
	public String getRefereePhone() {
		return refereePhone;
	}
	public void setRefereePhone(String refereePhone) {
		this.refereePhone = refereePhone;
	}
}
