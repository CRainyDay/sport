package com.crainyday.sport.entity;

/**
 * 对应数据库的apply_team数据表
 * 运动会的团队项目报名表
 * @author crainyday
 *
 */
public class Team {
	// 报名ID
	private Integer applyId;
	// 报名用户ID
	private Integer userId;
	// 报名的项目ID
	private Integer eventId;
	// 跑道号
	private String runway;
	// 参赛用户IDs
	private String userIds;
	// 生成的比赛IDs
	private String matchIds;
	// 团队得分
	private Double teamScore;
	public Integer getApplyId() {
		return applyId;
	}
	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRunway() {
		return runway;
	}
	public void setRunway(String runway) {
		this.runway = runway;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public String getMatchIds() {
		return matchIds;
	}
	public void setMatchIds(String matchIds) {
		this.matchIds = matchIds;
	}
	public Double getTeamScore() {
		return teamScore;
	}
	public void setTeamScore(Double teamScore) {
		this.teamScore = teamScore;
	}
}
