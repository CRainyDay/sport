package com.crainyday.sport.data;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 裁判查看的比赛信息
 * @author crainyday
 *
 */
public class MatchReferee {
	private Integer matchId;
	private String eventName;
	private Integer matchGroup;
	private String matchType;
	private Integer matchNum;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date matchTime;
	public Integer getMatchId() {
		return matchId;
	}
	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
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
}
