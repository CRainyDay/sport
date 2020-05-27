package com.crainyday.sport.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 对应数据库的match数据表
 * 比赛信息表
 * @author crainyday
 *
 */
public class Match {
	// 比赛ID
	private Integer matchId;
	// 项目ID
	private Integer eventId;
	// 裁判ID
	private Integer refereeId;
	// 比赛组别
	private Integer matchGroup;
	// 比赛类型
	private String matchType;
	// 比赛人数
	private Integer matchNum;
	// 比赛用户IDs
	private String userIds;
	// 比赛时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date matchTime;
	public Integer getMatchId() {
		return matchId;
	}
	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public Integer getRefereeId() {
		return refereeId;
	}
	public void setRefereeId(Integer refereeId) {
		this.refereeId = refereeId;
	}
	public String getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	public Integer getMatchNum() {
		return matchNum;
	}
	public void setMatchNum(Integer matchNum) {
		this.matchNum = matchNum;
	}
	public Date getMatchTime() {
		return matchTime;
	}
	public void setMatchTime(Date matchTime) {
		this.matchTime = matchTime;
	}
	public Integer getMatchGroup() {
		return matchGroup;
	}
	public void setMatchGroup(Integer matchGroup) {
		this.matchGroup = matchGroup;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
}
