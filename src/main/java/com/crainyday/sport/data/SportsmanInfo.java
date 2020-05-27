package com.crainyday.sport.data;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 检录运动员的信息(包含项目的比赛信息)
 * @author crainyday
 *
 */
public class SportsmanInfo {
	private Integer userId;
	private String identity;
	private String generalClass;
	private String generalName;
	private String generalCollege;
	private Integer gradeId;
	private String gradeRemark;
	private String runway;
	private String eventName;
	private Integer matchGroup;
	private String matchType;
	private Integer matchNum;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date matchTime;
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
		Matcher matcher = Pattern.compile("[^0-9]").matcher(identity);
		this.identity = matcher.replaceAll("").trim();
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
	public String getGradeRemark() {
		return gradeRemark;
	}
	public void setGradeRemark(String gradeRemark) {
		this.gradeRemark = gradeRemark;
	}
	public String getGeneralCollege() {
		return generalCollege;
	}
	public void setGeneralCollege(String generalCollege) {
		this.generalCollege = generalCollege;
	}
	public String getRunway() {
		return runway;
	}
	public void setRunway(String runway) {
		this.runway = runway;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Integer getMatchGroup() {
		return matchGroup;
	}
	public void setMatchGroup(Integer matchGroup) {
		this.matchGroup = matchGroup;
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
}
